package de.helpernet.pokopr.web.screen.files;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import de.helpernet.pokopr.integration.teamdrive.client.TeamDriveClient;
import de.helpernet.pokopr.web.model.files.Space;

@UiController("files")
@UiDescriptor("files.xml")
@LookupComponent("spacesTable")
public class FilesScreen extends Screen {

    @Inject
    private CollectionContainer<Space> spacesDc;

    @Inject
    private TeamDriveClient teamDriveClient;

    @Subscribe
    public void onInit(InitEvent event) {

        List<Space> spaces = teamDriveClient.getSpaces()
                .stream()
                .map(Space::from)
                .collect(Collectors.toList());
        spacesDc.setItems(spaces);
    }
}
