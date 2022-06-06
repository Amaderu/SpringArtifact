package com.amaderu.client.controller;

import com.amaderu.client.entity.Artifact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@Slf4j
@RequestMapping("/api/artifacts")
public class ArtifactController {
    @Autowired
    private WebClient webClient;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Artifact> artifacts(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client){
        Mono<List<Artifact>> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts")
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Artifact>>() {});
        List<Artifact> artifacts = response.block();
        log.info(client.getPrincipalName());
        log.info(client.getAccessToken().toString());
        return new ArrayList<>(artifacts);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Artifact artifact(@PathVariable("id") UUID id, @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client){
        Mono<Artifact> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts/"+id)
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Artifact.class);

        return response.block();
    }
}
