package com.example.gymsystem_layeredproject.entity;

public class Equipment {

    private String equipmentId;
    private String name;
    private String status;

    public Equipment(String equipmentId, String name, String status) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.status = status;
    }
    public String getEquipmentId() {return equipmentId;}
    public String getName() {return name;}
    public String getStatus() {return status;}
}
