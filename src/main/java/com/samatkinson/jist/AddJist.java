package com.samatkinson.jist;


import com.samatkinson.jist.storage.Storage;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddJist implements Route {

    private Storage storage;

    public AddJist(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String content = request.queryParams("jist");
        int jistId = storage.addJist(content);
        return jistId;
    }
}
