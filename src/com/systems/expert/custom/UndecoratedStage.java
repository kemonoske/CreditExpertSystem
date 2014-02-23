package com.systems.expert.custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Kage on 2/17/14.
 */
public class UndecoratedStage extends Stage{

    private double initX;
    private double initY;

    private BorderPane titleBar;
    protected Label titleLabel;
    private Button closeButton;

    private AnchorPane window;

    private SubScene windowContent;

    public UndecoratedStage(Parent content) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("simpleWindow.fxml"));

        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);

        titleBar = (BorderPane) root.lookup("#titleBar");
        titleLabel = (Label)root.lookup("#titleLabel");
        closeButton = (Button)root.lookup("#closeButton");
        window = (AnchorPane)root.lookup("#windowBody");

        windowContent = (SubScene)root.lookup("#windowContent");

        windowContent.setRoot(content);

        EventHandler mousePressHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                initX = me.getScreenX() - getX();
                initY = me.getScreenY() - getY();
            }
        };

        EventHandler mouseDragHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                setX(me.getScreenX() - initX);
                setY(me.getScreenY() - initY);
            }
        };

        //when mouse button is pressed, save the initial position of screen
        titleBar.setOnMousePressed(mousePressHandler);
        window.setOnMousePressed(mousePressHandler);

        //when screen is dragged, translate it accordingly
        titleBar.setOnMouseDragged(mouseDragHandler);
        window.setOnMouseDragged(mouseDragHandler);

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        setScene(new Scene(window));

        getScene().setFill(Color.TRANSPARENT);

    }

    public void setWindowTitle(String title){

        titleLabel.setText(title);

    }

}
