    package com.example.gymsystem_layeredproject.dao.custom.impl;

    import com.example.gymsystem_layeredproject.dao.SQLUtil;
    import com.example.gymsystem_layeredproject.dao.custom.WorkoutPlanDAO;
    import com.example.gymsystem_layeredproject.db.DBConnection;
    import com.example.gymsystem_layeredproject.entity.WorkoutPlan;

    import java.sql.*;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;

    public class WorkoutPlanDAOImpl implements WorkoutPlanDAO {
        public  boolean save(WorkoutPlan entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("INSERT INTO Workout_Plan (plan_name, duration, member_id, trainer_id, start_date, end_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)", entity.getPlanName(), entity.getDuration(), entity.getMemberId(), entity.getTrainerId(), entity.getStartDate(), entity.getEndDate(), entity.getEmail());
        }
        public  List<WorkoutPlan> getAll() throws SQLException, ClassNotFoundException {
            ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Workout_Plan");
            List<WorkoutPlan> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new WorkoutPlan(
                        rs.getString("plan_name"),
                        rs.getInt("duration"),
                        rs.getString("member_id"),
                        rs.getString("trainer_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("email")
                ));
            }
            return list;
        }
        public  boolean update(WorkoutPlan entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("UPDATE Workout_Plan SET duration = ?, end_date = ?, email = ? WHERE plan_name = ? AND member_id = ? AND trainer_id = ? AND start_date = ?", entity.getDuration(), entity.getEndDate(), entity.getEmail(), entity.getPlanName(), entity.getMemberId(), entity.getTrainerId(), entity.getStartDate());
        }

        @Override
        public boolean delete(String id) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("DELETE FROM Workout_Plan WHERE plan_name = ?", id);
        }

        public  boolean delete(String planName, String memberId, String trainerId, LocalDate startDate , String email) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("DELETE FROM Workout_Plan WHERE plan_name = ? AND member_id = ? AND trainer_id = ? AND start_date = ? AND email = ?", planName, memberId, trainerId, startDate, email);
        }
    }
