package com.example.mynew.Model;

public class User {

    String Name ,PhoneNumber, Email;

    public User() {
    }

    public User(String name, String phoneNumber, String email) {
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
