package com.amaderu.client.controller;

import com.amaderu.client.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@Slf4j
public class CommentController {
    @Autowired
    private WebClient webClient;
    @GetMapping(path = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Comment> comments(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client){
        Mono<List<Comment>> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/comments")
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {});
        List<Comment> comments = response.block();
        log.info(client.getPrincipalName().toString());
        return new ArrayList<>(comments);
    }
}
