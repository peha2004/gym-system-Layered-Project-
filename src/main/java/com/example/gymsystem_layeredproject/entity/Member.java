package com.example.gymsystem_layeredproject.entity;


public class Member  {

    private String id;
    private String name;
    private String gender;
    private String contact;

    public Member() {}

    public Member(String id, String name, String gender, String contact) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

}
