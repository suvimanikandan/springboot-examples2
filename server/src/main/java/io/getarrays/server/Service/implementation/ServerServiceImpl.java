package io.getarrays.server.Service.implementation;

import io.getarrays.server.Service.ServerService;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.Enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.Enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server: {} ", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server create(Server server, String setServerImageUrl) {
        return null;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging Server IP: {} ", ipAddress);
        Server server=serverRepo.findByIpAddress(ipAddress);
        InetAddress address= InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)?SERVER_UP:SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
       log.info("Updating a server: {} ",server.getName());
       return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}",id);
        serverRepo.deleteById(id);
        return TRUE;
    }
    private String setServerImageUrl() {
        String[] imageNames={ "server1.png","server1.png","server1.png","server1.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" +imageNames[new Random().nextInt(4)]).toUriString();
    }


}