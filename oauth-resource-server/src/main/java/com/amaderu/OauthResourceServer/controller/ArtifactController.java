package com.amaderu.OauthResourceServer.controller;


import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.service.ArtifactService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/api/artifacts")
    public @ResponseBody Artifact saveOne(@RequestBody Artifact artifact) {
        return this.service.saveArtifact(artifact);
    }

    /*@PutMapping(path = "/api/artifacts/{id}")
    public @ResponseBody Artifact putOne(@PathVariable("id") UUID id, @RequestBody Artifact artifactDetails) {
        Artifact artifact = this.service.loadArtifact(id);
        artifact.setCategory(artifactDetails.getCategory());
        artifact.setCreated(artifactDetails.getCreated());
        artifact.setDescription(artifactDetails.getDescription());
        artifact.setUserId(artifactDetails.getUserId());
        return service.saveArtifact(artifact);
    }*/

    @DeleteMapping(path = "/api/artifacts/{id}")
    public @ResponseBody Boolean deleteById(@PathVariable("id") UUID id) {
        Artifact artifact = service.loadArtifact(id);
        if (artifact == null) return true;
        this.service.deleteArtifact(artifact);
        if (service.loadArtifact(id) == null)
            return true;
        else return false;
    }


}