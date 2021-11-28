package com.aszoka.server.demo.service;

import com.aszoka.server.demo.model.Server;

import java.io.IOException;
import java.util.Collection;

// implementing the methods we want our service to have
public interface ServerService {

    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
