package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.core.data.Client;
import com.systems.expert.core.data.Employee;
import com.systems.expert.custom.Navigator.Controller;
import com.systems.expert.custom.validators.FormValidator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Kage on 2/19/14.
 */
public class FinancialDetailsController extends Controller implements FormValidator {

    @FXML
    private TextField organisation;
    @FXML
    private TextField job;
    @FXML
    private DatePicker hireDate;
    @FXML
    private TextField salary;

    @FXML
    private TextField addIncome;
    @FXML
    private TextField maintainedMatures;
    @FXML
    private TextField maintainedUnderage;
    @FXML
    private CheckBox house;
    @FXML
    private CheckBox auto;
    @FXML
    private CheckBox debts;
    @FXML
    private CheckBox workCardCopy;
    @FXML
    private CheckBox salaryInvoice;

    private Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 10, 0.0, 0, 0);

    private ObservableList<String> genders;

    @Override
    public void onCreate(ResourceBundle resourceBundle) {


    }


    @FXML
    private void handleSubmitFormAction(ActionEvent event) {

        /**
         * Capitalisation
         */
        if (validate()) {
            organisation.setText(organisation.getText().substring(0, 1).toUpperCase() + organisation.getText().substring(1));
            job.setText(job.getText().substring(0, 1).toUpperCase() + job.getText().substring(1));

            Client client = Application.getContext().getClient();

            client.setOrganisation(organisation.getText());
            client.setJob(job.getText());
            client.setHireDate(hireDate.getValue());
            client.setSalary(Double.valueOf(salary.getText()));
            client.setAddIncome(Double.valueOf(addIncome.getText()));
            client.setMaintainMatures(Integer.valueOf(maintainedMatures.getText()));
            client.setMaintainUnderage(Integer.valueOf(maintainedUnderage.getText()));
            client.setPossessingHouse(house.isSelected());
            client.setPossessingAuto(auto.isSelected());
            client.setPossessingDebts(debts.isSelected());
            System.out.print(debts.isSelected());
            client.setSalaryInvoicePresent(salaryInvoice.isSelected());
            client.setWorkCardCopyPresent(workCardCopy.isSelected());

            /**
             * Push to next form
             */
            Application.getContext().getNavigator().
                    pushTo("../views/creditRequestLayout.fxml","Cererea");
        }
    }

    @Override
    public boolean validate() {

        int errCount = 0;

        /**
         * Normalization
         */
        if(addIncome.getText().isEmpty())
            addIncome.setText("0");

        if(maintainedMatures.getText().isEmpty())
            maintainedMatures.setText("0");

        if(maintainedUnderage.getText().isEmpty())
            maintainedUnderage.setText("0");

        /**
         * Validate Organisation
         */
        if (!organisation.getText().trim().matches("[a-zA-Z0-9\\s\\.,\\\\/\\-]+")) {

            organisation.setEffect(invalidEffect);
            errCount++;

        } else {

            organisation.setEffect(null);

        }

        /**
         * Validate job
         */
        if (!job.getText().trim().matches("[a-zA-Z\\&\\.\\-]+")) {

            job.setEffect(invalidEffect);
            errCount++;

        } else {

            job.setEffect(null);

        }

        /**
         * Validate hire date
         */
        if (hireDate.getValue() == null) {

            hireDate.setEffect(invalidEffect);
            errCount++;

        } else {

            hireDate.setEffect(null);

        }

        if (!addIncome.getText().trim().matches("[0-9]+")) {

            addIncome.setEffect(invalidEffect);
            errCount++;

        } else {

            addIncome.setEffect(null);

        }

        /**
         * Validate maintained
         */
        if (!maintainedMatures.getText().trim().matches("[0-9]+")) {

            maintainedMatures.setEffect(invalidEffect);
            errCount++;

        } else {

            maintainedMatures.setEffect(null);

        }

        if (!maintainedUnderage.getText().trim().matches("[0-9]+")) {

            maintainedUnderage.setEffect(invalidEffect);
            errCount++;

        } else {

            maintainedUnderage.setEffect(null);

        }


        /**
         * Validate salary
         */
        if (!salary.getText().trim().matches("[0-9]*(\\.){0,1}[0-9]+")) {

            salary.setEffect(invalidEffect);
            errCount++;

        } else {

            salary.setEffect(null);

        }
        /**
         * Validate add income
         */
        if (!addIncome.getText().trim().matches("[0-9]*(\\.){0,1}[0-9]+")) {

            addIncome.setEffect(invalidEffect);
            errCount++;

        } else {

            addIncome.setEffect(null);

        }

        /**
         * Validate salary invoice
         */
        if (!salaryInvoice.isSelected()) {

            salaryInvoice.setEffect(invalidEffect);
            errCount++;

        } else {

            salaryInvoice.setEffect(null);

        }

        /**
         * Validate work card
         */
        if (!workCardCopy.isSelected()) {

            workCardCopy.setEffect(invalidEffect);
            errCount++;

        } else {

            workCardCopy.setEffect(null);

        }



        return (errCount == 0)?true:false;
    }
}
