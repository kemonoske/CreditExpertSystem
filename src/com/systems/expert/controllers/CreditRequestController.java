package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.core.data.Debitor;
import com.systems.expert.custom.Navigator.Controller;
import com.systems.expert.custom.validators.FormValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

/**
 * Created by Kage on 2/23/14.
 */
public class CreditRequestController extends Controller implements FormValidator {

    @FXML
    private TextField creditAmount;
    @FXML
    private TextField creditDuration;

    private Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 10, 0.0, 0, 0);

    @Override
    public void onCreate(ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleSubmitFormAction(ActionEvent event) {

        if(validate()){

            Debitor debitor = Application.getContext().getClient();

            debitor.setCreditAmount(Double.valueOf(creditAmount.getText()));
            debitor.setCreditDuration(Integer.valueOf(creditDuration.getText()));

            Application.getContext().getNavigator().pushTo("../views/decisionTakeLayout.fxml","Decizia");

        }

    }

    @Override
    public boolean validate() {

        int errCount = 0;

        /**
         * Validate credit amount
         */
        if (creditAmount.getText().trim().matches("[0-9]*(\\.){0,1}[0-9]+") &&
                Double.valueOf(creditAmount.getText().trim()) >= 3000 &&
                Integer.valueOf(creditAmount.getText().trim()) <= 10000000) {

            creditAmount.setEffect(null);

        } else {

            creditAmount.setEffect(invalidEffect);
            errCount++;

        }

        /**
         * Validate credit duration
         */
        if (creditDuration.getText().trim().matches("[0-9]+") &&
                Integer.valueOf(creditDuration.getText().trim()) > 0 &&
                Integer.valueOf(creditDuration.getText().trim()) <= 60) {

            creditDuration.setEffect(null);

        } else {

            creditDuration.setEffect(invalidEffect);
            errCount++;
        }

        return (errCount == 0) ? true : false;
    }
}
