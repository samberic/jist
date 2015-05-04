package com.samatkinson.jist.storage;


import java.sql.SQLException;

public interface Storage {
    void connect() throws SQLException;

    void clean() throws SQLException;

    int addJist(String content);

    String getJist(int jistId);
}
