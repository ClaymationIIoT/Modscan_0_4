package com.clay.modscan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Model {
    // Fields
   private String length;
    private String startingAddress;
    private int portNumber;
    private String ipAddress;
    private ModTcpClient modTcpClient;

    // Constructor
    public Model(){
        length = "25";
        ipAddress = "192.168.1.19";
        startingAddress = "100";
        portNumber = 502;
    }

    // Populate fake data
    public int[] populateFakeData(){
        int modbusRegisters[] = new int[Integer.parseInt(length)];
        System.out.println(length);
        Integer startingAddressInt = Integer.parseInt(startingAddress.trim());
        Integer lengthInt = Integer.parseInt(length);
        modbusRegisters = modTcpClient.ReadHoldingRegisters(startingAddressInt - 1, lengthInt);
        return modbusRegisters;
    }

    public void connectModTcpClient(){
        modTcpClient = new ModTcpClient(ipAddress, portNumber);
        modTcpClient.Connect();
        System.out.println("Connecting...");

    }
    public void disconnectModTcpClient(){
        modTcpClient.Disconnect();

    }
    public boolean isConnected(){
        if(modTcpClient == null){
            return false;
        }else
            return modTcpClient.isConnected();
    }

    // Getters and setters
    public String getLength(){
        return this.length;
    }
    public void setLength(String length){
        this.length = length;
    }

    public String getStartingAddress(){
        return this.startingAddress;
    }
    public void setStartingAddress(String startingAddress){
        this.startingAddress = startingAddress;
    }
    public String getIpAddress(){
        return this.ipAddress;
    }
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
    public int getPortNumber(){
        return this.portNumber;
    }
    public void setPortNumber(int portNumber){
        this.portNumber = portNumber;
    }

}
