package de.helpernet.pokopr.web.screen.files;

import java.util.Set;

import javax.inject.Inject;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import de.helpernet.pokopr.web.model.files.Space;

@UiController("files")
@UiDescriptor("files.xml")
@LookupComponent("spacesTable")
public class FilesScreen extends Screen {

    @Inject
    private CollectionContainer<Space> spacesDc;

    @Subscribe
    public void onInit(InitEvent event) {

        Set<Space> spaces = Set.of(
                new Space("Active space", false),
                new Space("Archived space", true)
        );
        spacesDc.setItems(spaces);
    }
}
