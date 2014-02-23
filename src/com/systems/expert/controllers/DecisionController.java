package com.systems.expert.controllers;

import com.systems.expert.Application;
import com.systems.expert.core.Decision;
import com.systems.expert.core.FinancialInvestigator;
import com.systems.expert.core.PersonalInvestigator;
import com.systems.expert.core.data.Client;
import com.systems.expert.custom.CheckListItem.CheckListItem;
import com.systems.expert.custom.Navigator.Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Kage on 2/22/14.
 */
public class DecisionController extends Controller {

    @FXML
    private AnchorPane main;
    @FXML
    private ListView<CheckListItem> personalConditionsList;
    @FXML
    private ListView<CheckListItem> financialConditionsList;
    @FXML
    private ListView<String> financialDetailsList;
    @FXML
    private Region veil;
    @FXML
    private ProgressIndicator progress;

    private ObservableList<CheckListItem> personalConditions;
    private ObservableList<CheckListItem> financialConditions;
    private ObservableList<String> financialDetails;

    @FXML
    private StackPane chartHolder;
    private BarChart chart;

    @Override
    public void onCreate(ResourceBundle resourceBundle) {

        // Use binding to be notified whenever the data source chagnes
        Task<Void> task = new ConsultExpertSystem();
        progress.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        progress.visibleProperty().bind(task.runningProperty());

        new Thread(task).start();

    }

    public class ConsultExpertSystem extends Task<Void> {
        @Override
        protected Void call() throws Exception {

            try {
                personalConditions = FXCollections.observableArrayList();
                PersonalInvestigator pe = new PersonalInvestigator();
                pe.consumeData(Application.getContext().getClient());

                financialConditions = FXCollections.observableArrayList();
                FinancialInvestigator fi = new FinancialInvestigator();
                fi.consumeData(Application.getContext().getClient());

                HashMap<String, Boolean> conditions = pe.getDecisionConditions();

                int toTotalProgress = pe.getDecisionConditions().size() + fi.getDecisionConditions().size();
                int progress = 0;

                for (String key : conditions.keySet()) {
                    CheckListItem item = new CheckListItem();
                    item.setText(key);
                    item.setChecked(conditions.get(key));
                    personalConditions.add(item);

                    updateProgress(++progress, toTotalProgress);

                    Thread.sleep(200);
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        personalConditionsList.setItems(personalConditions);
                    }
                });


                conditions = fi.getDecisionConditions();

                for (String key : conditions.keySet()) {
                    CheckListItem item = new CheckListItem();
                    item.setText(key);
                    item.setChecked(conditions.get(key));
                    financialConditions.add(item);

                    updateProgress(++progress, toTotalProgress);

                    Thread.sleep(200);
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        financialConditionsList.setItems(financialConditions);
                    }
                });

                double MATURE_CONSUME = 3000;
                double UNDERAGE_CONSUME = 2000;
                Client client = Application.getContext().getClient();

                double maintainConsume = MATURE_CONSUME * client.getMaintainMatures() + UNDERAGE_CONSUME * client.getMaintainUnderage();
                double creditRate = client.getCreditAmount() * (0.15 / 12) / (1 - Math.pow(1 + 0.15 / 12, -client.getCreditDuration()));
                double totalConsum = creditRate + MATURE_CONSUME + maintainConsume;
                double totalIncome = client.getAddIncome() + client.getSalary();
                double netIncome = totalIncome - totalConsum;

                financialDetails = FXCollections.observableArrayList();

                financialDetails.add("Persoane mature în întreținere: " + client.getMaintainMatures());
                financialDetails.add("Persoane minore în întreținere: " + client.getMaintainUnderage());
                financialDetails.add("Consum întreținere maturi: " + MATURE_CONSUME * client.getMaintainMatures() + " lei");
                financialDetails.add("Consum întreținere minori: " + UNDERAGE_CONSUME * client.getMaintainUnderage() + " lei");
                financialDetails.add("Consum personal: " + MATURE_CONSUME + " lei");
                financialDetails.add("Rata fixa a creditului: " + String.format("%.2f", creditRate) + " lei");
                financialDetails.add("Consum total: " + String.format("%.2f", totalConsum) + " lei");
                financialDetails.add("Salariu: " + client.getSalary());
                financialDetails.add("Venituri aditionale: " + client.getAddIncome());
                financialDetails.add("Venit total brut: " + totalIncome);
                financialDetails.add("Venit net: " + String.format("%.2f", netIncome));
                financialDetails.add("Durata creditului: " + client.getCreditDuration() + " luni");
                financialDetails.add("Suma pentru lichidarea creditului: " + String.format("%.2f",client.getCreditDuration() * creditRate) + " lei");

                boolean garant = true;

                if(client.getCreditAmount() >= 1000000){
                    garant = client.isPossessingHouse() && client.isPossessingAuto();
                    financialDetails.add("Garant imobiliar: " + (client.isPossessingHouse()?"DA":"NU"));
                    financialDetails.add("Garant auto: " + (client.isPossessingAuto()?"DA":"NU"));
                } else if (client.getCreditAmount() >= 500000){
                    garant = client.isPossessingHouse();
                    financialDetails.add("Garant imobiliar: " + (client.isPossessingHouse()?"DA":"NU"));
                    financialDetails.add("Garant auto: " + (client.isPossessingAuto()?"DA":"NU"));
                } else if (client.getCreditAmount() >= 100000){
                    garant = client.isPossessingHouse() || client.isPossessingAuto();
                    financialDetails.add("Garant imobiliar: " + (client.isPossessingHouse()?"DA":"NU"));
                    financialDetails.add("Garant auto: " + (client.isPossessingAuto()?"DA":"NU"));
                }


                financialDetails.add("Acordarea creditului: " + ((netIncome > 0 &&
                        pe.takeDecision() == Decision.POSITIVE &&
                        fi.takeDecision() == Decision.POSITIVE && garant) ? "DA" : "NU"));

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        financialDetailsList.setItems(financialDetails);
                    }
                });

                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Lei");

                ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                        new BarChart.Series("Salariu", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", client.getSalary())
                        )),
                        new BarChart.Series("Venituri Suplimentare", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", client.getAddIncome())
                        )),
                        new BarChart.Series("Consum Personal", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", MATURE_CONSUME)/*Pe persoana matura*/
                        )),
                        new BarChart.Series("Persoane in Intretinere", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", maintainConsume)
                        )),
                        new BarChart.Series("Rata Credit", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", creditRate)
                        )),
                        new BarChart.Series("Total Consum", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", totalConsum)
                        )),
                        new BarChart.Series("Total Venit", FXCollections.observableArrayList(
                                new BarChart.Data("Venit/Consum/Credit", totalIncome)
                        ))
                );

                chart = new BarChart(xAxis, yAxis, barChartData);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        chartHolder.getChildren().add(chart);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
