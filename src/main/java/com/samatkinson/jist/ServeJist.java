package com.samatkinson.jist;


import com.samatkinson.jist.storage.Storage;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServeJist implements TemplateViewRoute {
    private Storage storage;
    private String prefix;

    public ServeJist(Storage storage, String prefix) {
        this.storage = storage;
        this.prefix = prefix;
    }

    public ModelAndView handle(Request request, Response response) {
        Optional<String> jistId = Optional.ofNullable(request.params(":id"));

        final Map map = new HashMap();

        jistId.ifPresent(id -> map.put("jist", storage.getJist(Integer.parseInt(id))));
        map.put("isNewJist", !jistId.isPresent());
        map.put("server", request.host());
        map.put("prefix", prefix);

        return new ModelAndView(map, "jist.mustache");
    }

}
