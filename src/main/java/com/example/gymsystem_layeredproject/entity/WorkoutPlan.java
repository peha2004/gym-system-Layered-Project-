package com.example.gymsystem_layeredproject.entity;

import java.time.LocalDate;

public class WorkoutPlan {

    private String planName;
    private int duration;
    private String memberId;
    private String trainerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String email;

    public WorkoutPlan(String planName, int duration, String memberId, String trainerId, LocalDate startDate , LocalDate endDate , String email) {
        this.planName = planName;
        this.duration = duration;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.email = email;
    }
    public String getPlanName() {return planName;}
    public int getDuration() {return duration;}
    public String getMemberId() {return memberId;}
    public String getTrainerId() {return trainerId;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getEmail() {return email;}
}
