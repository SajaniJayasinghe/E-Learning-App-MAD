package com.example.mynew;

public class I_MainModal {

    String aTitle, aName, aNumber, aEmail, aDes, aurl;

    //default constructor
    I_MainModal() {

    }

    //Overloaded constructor
    public I_MainModal(String aTitle, String aName, String aNumber, String aEmail, String aDes, String aurl) {
        this.aTitle = aTitle;
        this.aName = aName;
        this.aNumber = aNumber;
        this.aEmail = aEmail;
        this.aDes = aDes;
        this.aurl = aurl;
    }
    
    //getters and setters
    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getaDes() {
        return aDes;
    }

    public void setaDes(String aDes) {
        this.aDes = aDes;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }
}

