package com.samatkinson.jist.storage;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InMemStorage implements Storage {
    private int id = 0;
    Map<Integer, String> jists = new HashMap<>();

    @Override
    public void connect() throws SQLException {

    }

    @Override
    public void clean() throws SQLException {

    }

    public int addJist(String content) {
        int jistId = id++;
        jists.put(jistId, content);

        System.out.println("jistId = " + jistId + " content = " + content);

        return jistId;
    }

    public String getJist(int jistId) {
        return jists.get(jistId);
    }
}
