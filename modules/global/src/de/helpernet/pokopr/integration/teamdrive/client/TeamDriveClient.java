package de.helpernet.pokopr.integration.teamdrive.client;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.helpernet.pokopr.exception.TeamDriveClientException;
import de.helpernet.pokopr.integration.teamdrive.client.dto.SpaceDto;
import de.helpernet.pokopr.integration.teamdrive.client.response.GetSpacesResponse;
import de.helpernet.pokopr.integration.teamdrive.client.response.JoinSpaceResponse;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TeamDriveClient {
    private final Logger log = LoggerFactory.getLogger(TeamDriveClient.class);

    private final ObjectMapper objectMapper;
    private final OkHttpClient httpClient;
    private final TeamDriveClientProperties properties;

    public TeamDriveClient(ObjectMapper objectMapper, TeamDriveClientProperties properties) {

        this.objectMapper = objectMapper;

        ConnectionPool pool = new ConnectionPool(5, 5000, TimeUnit.MILLISECONDS);

        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .build();

        this.properties = properties;
    }

    public boolean sessionExists() {

        Request request = new Request.Builder()
                .url(properties.getUrl() + "/api/getLoginInformation")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String string = response.body().string();
            log.info("TeamDrive client response: {}", string);
            return string.contains("username");
        } catch (IOException | NullPointerException e) {
            throw new TeamDriveClientException(e);
        }
    }

    public void joinSpace(String spaceId) {

        JoinSpaceRequest joinSpaceRequest = new JoinSpaceRequest().setId(spaceId);
        RequestBody body;
        try {
            body = RequestBody.create(
                    objectMapper.writeValueAsString(joinSpaceRequest),
                    MediaType.get("application/json")
            );
        } catch (JsonProcessingException e) {
            throw new TeamDriveClientException(e);
        }
        Request request = new Request.Builder()
                .url(properties.getUrl() + "/api/joinSpace")
                .post(body)
                .addHeader("Cookie", "Cookie_1=value; SessionID=MRal2eTG7yI")
                .build();

        try (Response r = httpClient.newCall(request).execute()) {
            JoinSpaceResponse response = objectMapper.readValue(r.body().string(), JoinSpaceResponse.class);
            if (!Boolean.TRUE.equals(response.getResult())) {
                throw new TeamDriveClientException("Unexpected result from TeamDrive agent");
            }
        } catch (IOException | NullPointerException e) {
            throw new TeamDriveClientException(e);
        }
    }

    public List<SpaceDto> getSpaces() {

        Request request = new Request.Builder()
                .url(properties.getUrl() + "/api/getSpaces")
                .addHeader("Cookie", "Cookie_1=value; SessionID=MRal2eTG7yI")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            List<GetSpacesResponse> spaceModel = objectMapper.readValue(
                    response.body().string(),
                    new TypeReference<List<GetSpacesResponse>>() {
                    }
            );
            return spaceModel.stream()
                    .map(SpaceDto::from)
                    .collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new TeamDriveClientException(e);
        }
    }
}
