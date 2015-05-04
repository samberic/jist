package com.samatkinson.jist;


import com.samatkinson.jist.storage.InMemStorage;
import com.samatkinson.jist.storage.SqliteStorage;
import com.samatkinson.jist.storage.Storage;
import spark.template.mustache.MustacheTemplateEngine;

import java.sql.SQLException;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

public class Server {

    private static Storage storage = new SqliteStorage();

    public static void main(String[] args) throws SQLException {
        storage.connect();
        staticFileLocation("static");
        post("/add", new AddJist(storage));
        get("/jists/:id", new ServeJist(storage), new MustacheTemplateEngine());
    }
}
