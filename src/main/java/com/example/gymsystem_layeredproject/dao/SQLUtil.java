package com.example.gymsystem_layeredproject.dao;

import com.example.gymsystem_layeredproject.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {

    private static Connection transactionalConnection = null;

    public static void beginTransaction() throws SQLException, ClassNotFoundException {
        if (transactionalConnection == null) {
            transactionalConnection = DBConnection.getConnection();
            transactionalConnection.setAutoCommit(false);
        }
    }

    public static void commit() throws SQLException {
        if (transactionalConnection != null) {
            transactionalConnection.commit();
            transactionalConnection.setAutoCommit(true);
            transactionalConnection = null;
        }
    }

    public static void rollback() throws SQLException {
        if (transactionalConnection != null) {
            transactionalConnection.rollback();
            transactionalConnection.setAutoCommit(true);
            transactionalConnection = null;
        }
    }

    private static Connection getCurrentConnection() throws SQLException, ClassNotFoundException {
        if (transactionalConnection != null) {
            return transactionalConnection;
        }
        return DBConnection.getConnection();
    }

    public static ResultSet executeQuery(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = getCurrentConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for (int i = 0; i < ob.length; i++) {
            pstm.setObject(i + 1, ob[i]);
        }
        return pstm.executeQuery();
    }

    public static boolean executeUpdate(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = getCurrentConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for (int i = 0; i < ob.length; i++) {
            pstm.setObject(i + 1, ob[i]);
        }
        return pstm.executeUpdate() > 0;
    }
}