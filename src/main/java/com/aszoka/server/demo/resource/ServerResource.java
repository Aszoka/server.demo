package com.aszoka.server.demo.resource;

import com.aszoka.server.demo.model.Response;
import com.aszoka.server.demo.model.Server;
import com.aszoka.server.demo.model.Status;
import com.aszoka.server.demo.service.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl service;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(Map.of("servers", service.list(30)))
                .message("Servers retrieved.")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = service.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", service.ping(ipAddress)))
                        .message(server.getStatus() == Status.SERVER_UP ? "Server pinged." : "Ping failed.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", service.create(server)))
                        .message("Server created.")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", service.get(id)))
                        .message("Server retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", service.delete(id)))
                        .message("Server deleted.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    // by default we return json but defining produces we return a png type image
    @GetMapping(path ="/image/{name}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("name") String name) throws IOException {
        return Files.readAllBytes(Paths.get("D:/programing/fullstack/spring-angular/images" + name));
    }
}
