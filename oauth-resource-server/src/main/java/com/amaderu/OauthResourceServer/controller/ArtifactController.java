package com.amaderu.OauthResourceServer.controller;


import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.service.ArtifactService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}