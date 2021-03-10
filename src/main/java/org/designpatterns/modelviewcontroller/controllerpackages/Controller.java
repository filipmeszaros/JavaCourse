package org.designpatterns.modelviewcontroller.controllerpackages;

import org.designpatterns.modelviewcontroller.modelpackages.Model;
import org.designpatterns.modelviewcontroller.viewpackages.View;

/**
 * Controller contains business logic of your application (brain of your application).
 * If something isn't your data (Model), or if
 * something isn't your GUI (View),
 * it goes to your Controller.
 */
public class Controller {

    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
    }
}
