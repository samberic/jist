package com.samatkinson.jist;


import com.samatkinson.jist.storage.SqliteStorage;
import com.samatkinson.jist.storage.Storage;
import spark.template.mustache.MustacheTemplateEngine;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Server {

    private static Storage storage = new SqliteStorage(System.getenv("APP_DATA"));

    public static void main(String[] args) throws SQLException {
        port(parseInt(System.getenv("APP_PORT") != null ? System.getenv("APP_PORT") : "4567"));
        String prefix = "prod".equalsIgnoreCase(System.getenv("APP_ENV")) ? "/jist" : "";
        storage.connect();
        staticFileLocation("static");
        post(prefix + "/add", new AddJist(storage));
        get(prefix + "/jists/:id", new ServeJist(storage, prefix), new MustacheTemplateEngine());
        get(prefix + "/", new ServeJist(storage, prefix), new MustacheTemplateEngine());
    }
}
