package de.helpernet.pokopr.integration.teamdrive.client;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.helpernet.pokopr.config.TestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TeamDriveClientTest {

    @Autowired
    private TeamDriveClient teamDriveClient;

    @Test
    public void sessionExistsTest() {

        assertFalse(teamDriveClient.sessionExists());
    }
}
