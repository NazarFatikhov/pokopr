package de.helpernet.pokopr.web.screen.files;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import de.helpernet.pokopr.exception.TeamDriveClientException;
import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClient;
import de.helpernet.pokopr.web.model.files.Space;

@UiController("files")
@UiDescriptor("files.xml")
@LookupComponent("spacesTable")
public class FilesScreen extends Screen {

    private static Logger log = LoggerFactory.getLogger(FilesScreen.class);

    @Inject
    private CollectionContainer<Space> spacesDc;

    @Inject
    private TeamDriveClient teamDriveClient;

    @Inject
    private Notifications notifications;

    @Inject
    private Table<Space> spacesTable;

    @Inject
    private Events events;

    @Subscribe
    public void onInit(InitEvent event) {

        Action openSpaceAction = Objects.requireNonNull(spacesTable.getAction("openSpace"));
        spacesTable.setItemClickAction(openSpaceAction);

        try {
            List<Space> spaces = teamDriveClient.getSpaces()
                    .stream()
                    .map(Space::from)
                    .collect(Collectors.toList());
            spacesDc.setItems(spaces);
        } catch (TeamDriveClientException e) {
            log.error("Something wrong with TeamDrive", e);
            notifications.create()
                    .withCaption("TeamDrive error")
                    .withType(Notifications.NotificationType.ERROR)
                    .show();
            throw e;
        }
    }

    @Subscribe("spacesTable.openSpace")
    public void onSpaceOpen(Action.ActionPerformedEvent event) {

        Space selectedSpace = spacesTable.getSingleSelected();
        notifications.create().withCaption("Space with id = " + selectedSpace.getId()).show();
    }
}
