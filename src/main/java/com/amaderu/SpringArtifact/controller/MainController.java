package com.amaderu.SpringArtifact.controller;

import com.amaderu.SpringArtifact.data.local.entity.Artifact;
import com.amaderu.SpringArtifact.data.repository.ArtifactRepository;
import com.amaderu.SpringArtifact.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ArtifactRepository artifactRepository;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Artifact> all = artifactRepository.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getId() + "<br>"
                +p.getUserId() + "<br>"
                +p.getCreated() + "<br>"
                +p.getCategory() + "<br>"
                +p.getDescription() + "<br>"));

        return sb.toString();
    }

    private MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @GetMapping(path = "/artifacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Artifact> getAll() {
        return this.service.loadArtifacts();
    }

}