package com.systems.expert;

import com.systems.expert.core.data.Client;
import com.systems.expert.core.data.Nationality;
import com.systems.expert.custom.Navigator.Navigable;
import com.systems.expert.custom.UndecoratedStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Application extends javafx.application.Application {

    private static Application applicationContext;
    private Navigable navigator;

    private Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        client = new Client();
//        client.setName("Bostanica Ion");
//        client.setAddress("or. Singera, str. Pacii 2");
//        client.setIdentityCardCopyPresent(true);
//        client.setBirthDate(LocalDate.of(1991,9,20));
//        client.setNationality(Nationality.MD);
//
//        client.setOrganisation("Downfall");
//        client.setJob("Programator");
//        client.setHireDate(LocalDate.of(2009, 9, 20));
//        client.setSalary(32000);
//        client.setAddIncome(2000);
//        client.setMaintainMatures(1);
//        client.setMaintainUnderage(3);
//        client.setPossessingHouse(true);
//        client.setPossessingAuto(true);
//        client.setPossessingDebts(true);
//        client.setSalaryInvoicePresent(true);
//        client.setWorkCardCopyPresent(true);
//
//        client.setCreditAmount(100000);
//        client.setCreditDuration(60);

        applicationContext = this;

        Parent root = FXMLLoader.load(getClass().getResource("views/applicationLayout.fxml"));

        UndecoratedStage mainWindow = new UndecoratedStage(root);
        mainWindow.setWindowTitle("Sistem Expert");
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Application getContext() {

        return applicationContext;

    }

    public Navigable getNavigator() {

        return navigator;

    }

    public void setNavigator(Navigable navigator) {

        this.navigator = navigator;

    }

    public Client getClient() {

        if (client == null)
            client = new Client();

        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
