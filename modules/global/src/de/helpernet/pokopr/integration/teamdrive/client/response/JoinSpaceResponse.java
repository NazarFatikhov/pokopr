package de.helpernet.pokopr.integration.teamdrive.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinSpaceResponse {

    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public JoinSpaceResponse setResult(Boolean result) {
        this.result = result;
        return this;
    }
}
