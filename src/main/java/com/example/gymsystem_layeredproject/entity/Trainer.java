package com.example.gymsystem_layeredproject.entity;

public class Trainer {
    private String trainerId;
    private String trainerName;
    private String trainerPhone;
    private String specialization;

    public Trainer(String trainerId, String trainerName, String trainerPhone, String specialization) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.trainerPhone = trainerPhone;
        this.specialization = specialization;
    }
    public String getTrainerId() {return trainerId;}
    public String getTrainerName() {return trainerName;}
    public String getTrainerPhone() {return trainerPhone;}
    public String getSpecialization() {return specialization;}
}
