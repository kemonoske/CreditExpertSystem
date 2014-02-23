package com.systems.expert.custom.Navigator;

import javafx.fxml.Initializable;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kage on 2/19/14.
 */
public abstract class Controller implements Initializable{

    private String name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        onCreate(resourceBundle);
    }

    public abstract void onCreate(ResourceBundle resourceBundle);


    public String getName(){

        if(name == null){

            String[] className = this.getClass().getName().split("\\.");

            return className[className.length - 1];
        }

        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
