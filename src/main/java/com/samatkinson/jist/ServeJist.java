package com.samatkinson.jist;


import com.samatkinson.jist.storage.InMemStorage;
import com.samatkinson.jist.storage.Storage;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServeJist implements TemplateViewRoute {
    private Storage storage;

    public ServeJist(Storage storage) {
        this.storage = storage;
    }

    public ModelAndView handle(Request request, Response response) {
        Optional<String> jistId = Optional.ofNullable(request.params(":id"));

        final Map map = new HashMap();

        jistId.ifPresent(id -> map.put("jist", storage.getJist(Integer.parseInt(id))));
        map.put("isNewJist", !jistId.isPresent());
        map.put("server", request.host());

        return new ModelAndView(map, "jist.mustache");
    }

}
