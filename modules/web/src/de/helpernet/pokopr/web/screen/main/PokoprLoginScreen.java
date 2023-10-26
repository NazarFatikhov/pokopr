package de.helpernet.pokopr.web.screen.main;

import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.app.login.LoginScreen;

//@Route(path = "login", root = true)
//@UiController("login")
//@UiDescriptor("pokopr-login-screen.xml")
public class PokoprLoginScreen extends LoginScreen {

    @Subscribe("submit")
    public void onSubmit(Action.ActionPerformedEvent event) {

        login();
    }
}
