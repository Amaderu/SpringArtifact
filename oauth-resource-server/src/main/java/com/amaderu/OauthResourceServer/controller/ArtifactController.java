package com.amaderu.OauthResourceServer.controller;


import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.service.ArtifactService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
public class ArtifactController {

    private ArtifactService service;

    public ArtifactController(ArtifactService service) {
        this.service = service;
    }

    @GetMapping(path = "/api/artifacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Artifact> getAll() {
        return this.service.loadArtifacts();
    }

    @GetMapping(path = "/api/artifacts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Artifact getById(@PathVariable("id") UUID id) {
        return this.service.loadArtifact(id);
    }

}