package com.systems.expert.custom.BreadcrumbBar;

/**
 * Created by Kage on 2/19/14.
 */

import com.systems.expert.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * BreadcrumbBar
 */
public class BreadcrumbBar extends HBox {

    private String path;
    private String deliminator = "/";
    private List<Button> buttons = new ArrayList<Button>();

    public BreadcrumbBar() {
        super(0);
        getStylesheets().addAll(BreadcrumbBar.class.getResource("BreadcrumBar.css").toExternalForm());
        getStyleClass().setAll("breadcrumb-bar");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
//        System.out.println("BreadcrumbBar.setPath("+path+")");
        this.path = path;
        String[] parts = path.split(deliminator);
        String pathSoFar = "";
      
        for (int i = 0; i < Math.max(parts.length, buttons.size()); i++) {
            if (i < parts.length) {
                // we have a part for this index
                pathSoFar += (i == 0) ? parts[i] : deliminator + parts[i];
                final String currentPath = pathSoFar;
                Button button = null;
                if (i < buttons.size()) {
                    // alread have a button
                    button = buttons.get(i);
                } else {
                    button = new Button(parts[i]);
                    buttons.add(button);
                    getChildren().add(button);
                }
                button.setVisible(true);
                button.setText(parts[i]);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        Application.getContext().getNavigator().goToPage(currentPath);
                    }
                });
                if (i == parts.length - 1) {
                    if (i == 0) {
                        button.getStyleClass().setAll("nav-button", "only-nav-button");
                    } else {
                        button.getStyleClass().setAll("nav-button", "last-nav-button");
                    }
                } else if (i == 0) {
                    button.getStyleClass().setAll("nav-button", "first-nav-button");
                } else {
                    button.getStyleClass().setAll("nav-button", "middle-nav-button");
                }
            } else {
                // don't need this button for now
                buttons.get(i).setVisible(false);
            }
        }
    }
}
