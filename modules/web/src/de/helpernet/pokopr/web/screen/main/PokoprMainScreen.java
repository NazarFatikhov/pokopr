package de.helpernet.pokopr.web.screen.main;

import javax.inject.Inject;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.screen.MessageBundle;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.app.main.MainScreen;

import de.helpernet.pokopr.web.screen.files.FilesScreen;

@UiController("pokoprMainScreen")
@UiDescriptor("pokopr-main-screen.xml")
public class PokoprMainScreen extends MainScreen {

    @Inject
    protected SideMenu sideMenu;

    @Inject
    protected MessageBundle messageBundle;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe
    protected void initMainMenu(Screen.AfterShowEvent event) {

        createFilesMenuItem();
    }

    private void createFilesMenuItem() {

        SideMenu.MenuItem files = sideMenu.createMenuItem("files");
        files.setCaption(messageBundle.getMessage("filesMenuItem"));
        sideMenu.addMenuItem(files, 0);
        files.setCommand(menuItem ->
                screenBuilders.screen(this)
                        .withScreenClass(FilesScreen.class)
                        .withOpenMode(OpenMode.NEW_TAB)
                        .show()
        );
    }
}
