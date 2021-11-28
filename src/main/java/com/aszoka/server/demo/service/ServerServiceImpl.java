package com.aszoka.server.demo.service;

import com.aszoka.server.demo.model.Server;
import com.aszoka.server.demo.model.Status;
import com.aszoka.server.demo.repository.ServerRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j // for logging
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server {} " , server.getName());
        server.setImageUrl(setServerImage());
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {} " , ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        server.setStatus(inetAddress.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    // selects a random picture from the images folder
    // using Random and than converting to Uri
    private String setServerImage() {
        String[] imageNames = {"server1.png", "server2.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(2)]).toUriString();
    }
}
