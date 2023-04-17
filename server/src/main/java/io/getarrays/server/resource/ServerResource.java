package io.getarrays.server.resource;


import io.getarrays.server.Service.ServerService;
import io.getarrays.server.Service.implementation.ServerServiceImpl;
import io.getarrays.server.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
private final ServerServiceImpl serverService;

public ResponseEntity<Response> getService


}
