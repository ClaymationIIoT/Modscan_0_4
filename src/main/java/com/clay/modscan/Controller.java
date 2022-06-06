package com.clay.modscan;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private TextField ipAddressTextField;
    @FXML
    private TextField portNumberTextField;
    @FXML
    private TextField startingAddressTextField;
    @FXML
    private TextField lengthTextField;
    public Model myModel;
    private ObservableList<Label> valueLabels;
    @FXML
    private FlowPane flowpane_body;
    private boolean polling;
    Thread pollingThread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myModel = new Model();
        lengthTextField.setText(myModel.getLength());
        startingAddressTextField.setText(myModel.getStartingAddress());
        ipAddressTextField.setText((myModel.getIpAddress()));
        portNumberTextField.setText(String.valueOf(myModel.getPortNumber()));
        valueLabels = FXCollections.observableArrayList();
        pollingThread = new Thread(this::handleThread);
        polling = true;
        pollingThread.start();
    }
    private void handleThread(){
        while(polling){
            System.out.println("Polling");
            if(myModel.isConnected()) {
                Platform.runLater(() -> {
                    addLabelsToFlowpane();
                });
            }
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addLabelsToFlowpane(){
        System.out.println("Adding Labels to Flowpane");
        if(!flowpane_body.getChildren().isEmpty()){
            flowpane_body.getChildren().clear();
        }
        if(myModel.isConnected()) {
            flowpane_body.getChildren().addAll(registersToLabels(myModel.populateFakeData()));
        }
    }

    public void clickNewDataBtn(ActionEvent actionEvent) {

        registersToLabels(myModel.populateFakeData());
        //makeRegisterLabels();
        addLabelsToFlowpane();
    }
    public void typeLengthTextField(KeyEvent actionEvent) {
        myModel.setLength(lengthTextField.getText());
        if(myModel.isConnected()){
            addLabelsToFlowpane();
        }
    }
    public void typeStartingAddressTextField(KeyEvent keyEvent) {
        myModel.setStartingAddress(startingAddressTextField.getText());
        if(myModel.isConnected() && InputMethods.validateModAddress(myModel.getStartingAddress())){
            addLabelsToFlowpane();
        }
    }
    public ArrayList<Label> registersToLabels(int[] registers){
        ArrayList<Label> labels = new ArrayList<Label>();
        int startingAddress =  Integer.parseInt(myModel.getStartingAddress());
        for(int i = 0; i < registers.length; ++i){
            int addr = i + 40000 + startingAddress;
            int val = registers[i];
            Label label = new Label(addr + ": <" + String.valueOf(val) + ">");
            labels.add(label);
        }
        return labels;
    }
    public void clickConnectButton(ActionEvent actionEvent) {
        myModel.setIpAddress(ipAddressTextField.getText());
        myModel.setPortNumber(Integer.parseInt(portNumberTextField.getText()));
        myModel.connectModTcpClient();
        polling = true;
    }

    public void clickDisconnectButton(ActionEvent actionEvent) {
        myModel.disconnectModTcpClient();
        flowpane_body.getChildren().clear();

    }

    public Model getMyModel(){
        return this.myModel;
    }

    public void dispose(){
        polling = false;
    }
}