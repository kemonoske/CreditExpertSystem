package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.core.data.Nationality;
import com.systems.expert.core.data.Person;
import com.systems.expert.custom.Navigator.Controller;
import com.systems.expert.custom.validators.FormValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

/**
 * Created by Kage on 2/19/14.
 */
public class PersonalDetailsController extends Controller implements FormValidator {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private DatePicker birthDate;
    @FXML
    private CheckBox nationality;
    @FXML
    private ChoiceBox<String> sex;
    @FXML
    private CheckBox identityCardCopy;

    private Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 10, 0.0, 0, 0);

    private ObservableList<String> genders;

    @Override
    public void onCreate(ResourceBundle resourceBundle) {

        genders = FXCollections.observableArrayList();

        genders.add("M");
        genders.add("F");

        sex.setItems(genders);

    }


    @FXML
    private void handleSubmitFormAction(ActionEvent event) {

        /**
         * Capitalisation
         */
        if (validate()) {
            firstName.setText(firstName.getText().substring(0, 1).toUpperCase() + firstName.getText().substring(1));
            lastName.setText(lastName.getText().substring(0, 1).toUpperCase() + lastName.getText().substring(1));
            address.setText(address.getText().substring(0, 1).toUpperCase() + address.getText().substring(1));

            Person person = Application.getContext().getClient();

            person.setName(firstName.getText().trim() + " " + lastName.getText().trim());
            person.setAddress(address.getText().trim());
            person.setBirthDate(birthDate.getValue());
            person.setIdentityCardCopyPresent(identityCardCopy.isSelected());
            person.setNationality(Nationality.MD);

            /**
             * Push to next form
             */
            Application.getContext().getNavigator().
                    pushTo("../views/financialDetailsLayout.fxml", "Detalii Financiare");
        }
    }

    @Override
    public boolean validate() {

        int errCount = 0;

        /**
         * Validate First Name
         */
        if (!firstName.getText().trim().matches("[a-zA-Z]+")) {

            firstName.setEffect(invalidEffect);
            errCount++;

        } else {

            firstName.setEffect(null);

        }

        /**
         * Validate Last Name
         */
        if (!lastName.getText().trim().matches("[a-zA-Z]+")) {

            lastName.setEffect(invalidEffect);
            errCount++;

        } else {

            lastName.setEffect(null);

        }

        /**
         * Validate Address
         */
        if (!address.getText().trim().matches("[a-zA-Z0-9\\s\\.,\\\\/\\-]+")) {

            address.setEffect(invalidEffect);
            errCount++;

        } else {

            address.setEffect(null);

        }

        /**
         * Validate birthDate
         */
        if (birthDate.getValue() == null) {

            birthDate.setEffect(invalidEffect);
            errCount++;

        } else {

            birthDate.setEffect(null);

        }

        /**
         * Validate nationality
         */
        if (!nationality.isSelected()) {

            nationality.setEffect(invalidEffect);
            errCount++;

        } else {

            nationality.setEffect(null);

        }

        /**
         * Validate sex
         */
        if (sex.getValue() == null) {

            sex.setEffect(invalidEffect);
            errCount++;

        } else {

            sex.setEffect(null);

        }

        /**
         * Validate identity card copy
         */
        if (!identityCardCopy.isSelected()) {

            identityCardCopy.setEffect(invalidEffect);
            errCount++;

        } else {

            identityCardCopy.setEffect(null);

        }



        return (errCount == 0)?true:false;
    }
}
