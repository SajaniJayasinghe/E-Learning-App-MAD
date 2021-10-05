package com.example.mynew;

public class R_MainModel {

    String subject_name, subject_title, subject_description,surl;

    R_MainModel()
    {

    }
    public R_MainModel(String subject_name, String subject_title, String subject_description, String surl) {
        this.subject_name = subject_name;
        this.subject_title = subject_title;
        this.subject_description = subject_description;
        this.surl = surl;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getSubject_description() {
        return subject_description;
    }

    public void setSubject_description(String subject_description) {
        this.subject_description = subject_description;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
