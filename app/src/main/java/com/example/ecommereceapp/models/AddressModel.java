package com.example.ecommereceapp.models;

public class AddressModel {

    String userAdress;
    boolean isSelected;

    public AddressModel() {
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
