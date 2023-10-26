package de.helpernet.pokopr.integration.teamdrive.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TeamDriveClient {

    private final Logger log = LoggerFactory.getLogger(TeamDriveClient.class);
    private final OkHttpClient httpClient;
    private final TeamDriveClientProperties properties;

    public TeamDriveClient(TeamDriveClientProperties properties) {

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
            throw new RuntimeException(e);
        }
    }
}
