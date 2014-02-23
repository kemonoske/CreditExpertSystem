package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.custom.BreadcrumbBar.BreadcrumbBar;
import com.systems.expert.custom.Navigator.Controller;
import com.systems.expert.custom.Navigator.Navigable;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by Kage on 2/19/14.
 */
public class ApplicationController extends Controller implements Navigable {

    @FXML
    private ToolBar navigationBarHolder;
    private BreadcrumbBar navigationBar;

    @FXML
    private StackPane navigationContent;

    /**
     * Animations
     */
    private TranslateTransition slideOutLeft;
    private TranslateTransition slideOutRight;
    private TranslateTransition slideInLeft;
    private TranslateTransition slideInRight;

    /**
     * Controller stack
     */
    private Stack<Controller> controllerStack;

    @Override
    public void onCreate(ResourceBundle resourceBundle) {

        Application.getContext().setNavigator(this);

        navigationBarHolder.setId("nav-toolbar");

        navigationBar = new BreadcrumbBar();
        navigationBarHolder.getItems().add(navigationBar);

        controllerStack = new Stack<Controller>();

        slideOutLeft = new TranslateTransition(Duration.millis(300));
        slideOutLeft.setFromX(0);
        slideOutLeft.setToX(-628);
        slideOutLeft.setCycleCount(1);

        slideOutRight = new TranslateTransition(Duration.millis(300));
        slideOutRight.setFromX(0);
        slideOutRight.setToX(628);
        slideOutRight.setCycleCount(1);

        slideInRight = new TranslateTransition(Duration.millis(300));
        slideInRight.setFromX(628);
        slideInRight.setToX(0);
        slideInRight.setCycleCount(1);
        slideInRight.setAutoReverse(true);

        slideInLeft = new TranslateTransition(Duration.millis(300));
        slideInLeft.setFromX(-628);
        slideInLeft.setToX(0);
        slideInLeft.setCycleCount(1);
        slideInLeft.setAutoReverse(true);

        pushTo("../views/serviceSelectLayout.fxml", "Servicii");
    }

    @Override
    public void goToPage(String path) {

        int page = path.split("/").length;

        while (controllerStack.size() > page) {
            pop();
        }

        navigationBar.setPath(path);
    }

    @Override
    public void pushTo(String viewPath, String name) {

        FXMLLoader loader = new FXMLLoader();
        try {

            Node newNode = (Node) loader.load(getClass().getResource(viewPath).openStream());
            navigationContent.getChildren().add(newNode);

            slideInRight.setNode(newNode);
            slideInRight.play();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Node oldNode = null;

        try {
            oldNode = navigationContent.getChildren().get(navigationContent.getChildren().size() - 2);
        } catch(ArrayIndexOutOfBoundsException e) {
        }

        if (oldNode != null) {

            slideOutLeft.setNode(oldNode);
            slideOutLeft.play();

        }

        Controller controller = loader.<Controller>getController();

        controller.setName(name);
        controllerStack.push(controller);

        if (navigationBar.getPath() != null)
            navigationBar.setPath(navigationBar.getPath() + "/" + controller.getName());
        else
            navigationBar.setPath(controller.getName());

    }

    @Override
    public void pop() {

        String[] path = navigationBar.getPath().split("/");

        if (path.length == 0) {

            navigationBar.setPath("");

        } else {

            String newPath = "";

            for (int i = 0; i < path.length - 1; i++)
                newPath += path[i] + "/";

            navigationBar.setPath(newPath);

        }


        if (controllerStack.size() > 0) {

            final Node oldNode = navigationContent.getChildren().get(navigationContent.getChildren().size() - 1);
            Node newNode = navigationContent.getChildren().get(navigationContent.getChildren().size() - 2);

            if (oldNode != null) {

                slideOutRight.setNode(oldNode);
                slideOutRight.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        navigationContent.getChildren().remove(oldNode);
                    }
                });
                slideOutRight.play();

            }

            if (newNode != null) {

                slideInLeft.setNode(newNode);
                slideInLeft.play();

            }

            controllerStack.pop();

        }

    }
}
