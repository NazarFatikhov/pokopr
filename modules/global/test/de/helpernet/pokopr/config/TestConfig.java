package de.helpernet.pokopr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClient;
import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClientProperties;

@Configuration
public class TestConfig {

    @Bean
    public TeamDriveClientProperties teamDriveClientProperties() {

        TeamDriveClientProperties properties = new TeamDriveClientProperties();
        properties.setUrl("http://localhost:4040");
        properties.setUsername("test");
        properties.setPassword("test");
        return properties;
    }

    @Bean
    public TeamDriveClient teamDriveClient(TeamDriveClientProperties teamDriveClientProperties) {

        return new TeamDriveClient(teamDriveClientProperties);
    }
}
