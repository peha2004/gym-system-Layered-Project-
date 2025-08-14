package com.example.gymsystem_layeredproject.dao.custom.impl;

import com.example.gymsystem_layeredproject.dao.SQLUtil;
import com.example.gymsystem_layeredproject.dao.custom.TrainerDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.Trainer;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT trainer_id FROM Trainer ORDER BY trainer_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString("trainer_id");
            if (lastId != null && lastId.length() > 1) {
                int num = Integer.parseInt(lastId.substring(1));
                return String.format("T%03d", num + 1);
            }
        }
        return "T001";
    }
    public List<Trainer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Trainer");
        ArrayList<Trainer> trainers = new ArrayList<>();
        while (rst.next()) {
            trainers.add(new Trainer(
                    rst.getString("trainer_id"),
                    rst.getString("name"),
                    rst.getString("contact"),
                    rst.getString("specialization")
            ));
        }
        return trainers;
    }
    public boolean save(Trainer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Trainer (trainer_id, name, contact, specialization) VALUES (?, ?, ?, ?)", entity.getTrainerId(), entity.getTrainerName(), entity.getTrainerPhone(), entity.getSpecialization());
    }
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT trainer_id FROM Trainer");
        ArrayList<String> trainerIds = new ArrayList<>();
        while (rst.next()) {
            trainerIds.add(rst.getString("trainer_id"));
        }
        return trainerIds;
    }
    public boolean update(Trainer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Trainer SET name = ?, contact = ?, specialization = ? WHERE trainer_id = ?", entity.getTrainerName(), entity.getTrainerPhone(), entity.getSpecialization(), entity.getTrainerId());
    }
    public boolean delete(String trainerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Trainer WHERE trainer_id = ?", trainerId);
    }
}
