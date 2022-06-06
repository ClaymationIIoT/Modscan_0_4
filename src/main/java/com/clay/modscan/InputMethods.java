package com.clay.modscan;

public class InputMethods {

    public static boolean validateModAddress(String modbusAddress){
        if(Integer.parseInt(modbusAddress) >= 1){
            return true;
        }
        return false;
    }

}
