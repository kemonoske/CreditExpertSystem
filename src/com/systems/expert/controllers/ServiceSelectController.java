package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.custom.Navigator.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ResourceBundle;

/**
 * Created by Kage on 2/19/14.
 */
public class ServiceSelectController extends Controller {

    @FXML
    private Button creditButton;

    @Override
    public void onCreate(ResourceBundle resourceBundle) {



    }

    @FXML
    private void handleCreditAction(ActionEvent event) {

        Application.getContext().getNavigator().
                pushTo("../views/personalDetailsLayout.fxml", "Detalii Personale");

    }
}
