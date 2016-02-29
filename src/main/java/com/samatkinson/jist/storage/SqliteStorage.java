package com.samatkinson.jist.storage;


import com.samatkinson.jist.exception.JistException;

import java.io.File;
import java.sql.*;

public class SqliteStorage implements Storage {

    private final File db;
    private Connection c = null;

    public SqliteStorage(String dataDir) {
        if (dataDir == null) dataDir = ".";
        this.db = new File(dataDir, "jist.db");
    }

    public static void main(String[] args) throws SQLException {
        SqliteStorage sqliteStorage = new SqliteStorage(".");
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
            c = DriverManager.getConnection("jdbc:sqlite:" + db.getCanonicalPath());
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
        String insert = "insert into jist (jist) values (?);";
        try {
            PreparedStatement statement = c.prepareStatement(insert);
            statement.setString(1, content);
            statement.execute();
            Statement statement1 = c.createStatement();
            statement1.execute("select last_insert_rowid();");
            int anInt = statement1.getResultSet().getInt(1);
            statement.close();
            return anInt;

        } catch (SQLException e) {
            throw new JistException(e);
        }

    }

    @Override
    public String getJist(int jistId) {
        String select = "select * from jist where id = ?";
        String jist = null;
        try {
            PreparedStatement statement = c.prepareStatement(select);
            statement.setInt(1, jistId);
            statement.execute();
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
