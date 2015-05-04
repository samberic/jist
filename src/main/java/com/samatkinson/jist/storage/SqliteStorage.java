package com.samatkinson.jist.storage;


import com.samatkinson.jist.exception.JistException;

import java.sql.*;

public class SqliteStorage implements Storage {

    private Connection c = null;

    public static void main(String[] args) throws SQLException {
        SqliteStorage sqliteStorage = new SqliteStorage();
        sqliteStorage.connect();

        sqliteStorage.addJist("Some random text");
        String jist = sqliteStorage.getJist(1);

        System.out.println(jist);

        sqliteStorage.clean();
    }

    @Override
    public void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:jist.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        String doesDbExist = "SELECT name FROM sqlite_master WHERE type='table' AND name='jist';";
        Statement statement = c.createStatement();
        statement.execute(doesDbExist);
        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            System.out.println("making db");
            initDB();
        }
        statement.close();
    }

    @Override
    public void clean() throws SQLException {
        c.close();
    }

    private void initDB() throws SQLException {
        String createTable = "create table jist " +
                "(ID INTEGER primary key autoincrement not null," +
                "jist text not null)";
        Statement statement = c.createStatement();
        statement.execute(createTable);
        statement.close();
    }

    @Override
    public int addJist(String content) {
        String insert = "insert into jist (jist) values ('" + content + "');";
        Statement statement;
        try {

            statement = c.createStatement();
            statement.execute(insert);
            statement.execute("select last_insert_rowid();");
            int anInt = statement.getResultSet().getInt(1);
            statement.close();
            return anInt;

        } catch (SQLException e) {
            throw new JistException(e);
        }

    }

    @Override
    public String getJist(int jistId) {
        String select = "select * from jist where id = " + jistId;
        Statement statement;
        String jist = null;

        try {
            statement = c.createStatement();
            statement.execute(select);
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                jist = resultSet.getString("jist");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jist;
    }
}
