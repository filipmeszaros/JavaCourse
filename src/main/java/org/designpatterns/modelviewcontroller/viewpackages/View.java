package org.designpatterns.modelviewcontroller.viewpackages;

import org.designpatterns.modelviewcontroller.modelpackages.Model;

/**
 * View represents your GUI (Graphical User Interface) of your application (front-end of your application).
 * It gets data from Model by making requests to Model.
 */
public class View {

    private Model model;

    public View(Model model) {
        this.model = model;
    }

    //GUI implementation goes here
}
