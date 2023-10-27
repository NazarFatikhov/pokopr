package de.helpernet.pokopr.integration.teamdrive.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.helpernet.pokopr.config.TestConfig;
import de.helpernet.pokopr.integration.teamdrive.client.dto.SpaceDto;
import de.helpernet.pokopr.integration.teamdrive.client.dto.SpaceStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TeamDriveClientTest {

    @Autowired
    private TeamDriveClient teamDriveClient;

    @Test
    public void sessionExists_Test() {

        assertFalse(teamDriveClient.sessionExists());
    }

    @Test
    public void getSpaces_Test() {

        List<SpaceDto> spaces = teamDriveClient.getSpaces();
        assertEquals(6, spaces.size());
    }

    @Test
    public void getSpaces_Filter_Test() {

        List<SpaceDto> spaces = teamDriveClient.getSpaces();
        assertEquals(1, spaces.stream().filter(spaceDto -> spaceDto.getStatus() == SpaceStatus.ACTIVE).count());
    }

    @Test
    public void joinSpace_Test() {

        assertDoesNotThrow(() -> teamDriveClient.joinSpace("3"));
    }
}
