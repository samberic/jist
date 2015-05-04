package com.samatkinson.jist;


import com.samatkinson.jist.storage.InMemStorage;
import com.samatkinson.jist.storage.Storage;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ServeJist implements TemplateViewRoute {
    private Storage storage;

    public ServeJist(Storage storage) {
        this.storage = storage;
    }

    public ModelAndView handle(Request request, Response response) {
        String jistId = request.params(":id");
        String jist = "";

        boolean isNewJist = jistId.equals("create");
        if(!isNewJist)
            jist  = storage.getJist(Integer.parseInt(jistId));

        Map map = new HashMap();
        map.put("jist", jist);
        map.put("isNewJist", isNewJist);

        return new ModelAndView(map, "jist.mustache");
    }

}
