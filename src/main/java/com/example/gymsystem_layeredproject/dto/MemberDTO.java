package com.example.gymsystem_layeredproject.dto;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private String id;
    private String name;
    private String gender;
    private String contact;

    public MemberDTO(String id, String name, String gender, String contact) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getContact(){ return contact;}

}