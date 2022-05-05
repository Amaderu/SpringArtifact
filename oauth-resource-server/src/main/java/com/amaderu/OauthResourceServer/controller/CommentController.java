package com.amaderu.OauthResourceServer.controller;


import com.amaderu.OauthResourceServer.entity.Comment;
import com.amaderu.OauthResourceServer.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping(path = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Comment> getAll() {
        return this.service.loadArtifacts();
    }

}