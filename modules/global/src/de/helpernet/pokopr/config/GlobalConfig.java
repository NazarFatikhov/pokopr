package de.helpernet.pokopr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClient;
import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClientProperties;

@Configuration
public class GlobalConfig {

    @Bean
    public ObjectMapper objectMapper() {

        return new ObjectMapper();
    }

    @Bean
    public TeamDriveClientProperties teamDriveClientProperties() {

        TeamDriveClientProperties properties = new TeamDriveClientProperties();
        properties.setUrl("http://localhost:4040");
        properties.setUsername("test");
        properties.setPassword("test");
        return properties;
    }

    @Bean
    public TeamDriveClient teamDriveClient(ObjectMapper objectMapper, TeamDriveClientProperties teamDriveClientProperties) {

        return new TeamDriveClient(objectMapper, teamDriveClientProperties);
    }
}
