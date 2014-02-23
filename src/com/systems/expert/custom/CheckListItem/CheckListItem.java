package com.systems.expert.custom.CheckListItem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Kage on 2/22/14.
 */
public class CheckListItem extends BorderPane {

    @FXML
    private Label text;
    @FXML
    private ImageView checked;

    private Image positive;
    private Image negative;

    public CheckListItem() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "CheckListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        try {
            positive = new Image(getClass().getResource(
                    "images/checked.png").openStream());
            negative = new Image(getClass().getResource(
                    "images/unchecked.png").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setText(String text){
        this.text.setText(text);
    }

    public void setChecked(boolean checked){

        if(checked)
            this.checked.setImage(positive);
        else
            this.checked.setImage(negative);

    }
}
