package com.amaderu.OauthResourceServer.controller;


import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.service.ArtifactService;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
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

    //GET /users?sort=-created_at,username
    //◦ время создания
    //◦ категория
    //◦ пользователь
    // /users?sort=created,category,username
    //@SortDefault(value = "name", direction = Sort.Direction.ASC)
    //Fixme переписать логику формирования запроса к сервису
    @GetMapping(path = "/api/artifacts", params ="sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Artifact> getSortedAll(@RequestParam(required = false, name = "sort") String[] sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sorting[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sorting) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(Sort.Direction.fromString(sorting[1]), sorting[0]));
        }
        return this.service.loadArtifacts(orders);
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