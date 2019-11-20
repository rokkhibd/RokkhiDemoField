package com.rokkhi.demofieldwork.Model;

public class FBPeople {

    private String b_code;
    private String designation;
    private String doc_id;
    private String name;
    private String number;


    public FBPeople() {
    }

    public FBPeople(String b_code, String designation, String doc_id, String name, String number) {
        this.b_code = b_code;
        this.designation = designation;
        this.doc_id = doc_id;
        this.name = name;
        this.number = number;
    }

    public String getB_code() {
        return b_code;
    }

    public void setB_code(String b_code) {
        this.b_code = b_code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
