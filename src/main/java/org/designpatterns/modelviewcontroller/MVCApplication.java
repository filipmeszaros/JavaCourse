package org.designpatterns.modelviewcontroller;

import org.designpatterns.modelviewcontroller.controllerpackages.Controller;
import org.designpatterns.modelviewcontroller.modelpackages.Model;
import org.designpatterns.modelviewcontroller.viewpackages.View;

/**
 * MVC - Model/View/Controller application.
 * MVC application divides all classes to 3 different parts:
 *  - Model      - represents data.
 *  - View       - represents UI.
 *  - Controller - represents business logic. It retrieves data from Model, and sends request to View.
 */
public class MVCApplication {

    public static void main(String[] args) {
        runApp();
    }

    public static void runApp() {
        Model model = new Model();

        //View requests data from model
        View view = new View(model);

        //Controller works with model and view. It gets data from model, and sends instruction to view.
        Controller controller = new Controller(model, view);
    }
}
