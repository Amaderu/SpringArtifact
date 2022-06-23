package com.amaderu.client.controller;

import com.amaderu.client.entity.Artifact;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@Slf4j
public class ArtifactController {
    @Autowired
    private WebClient webClient;
    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Artifact> artifacts(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client){
        Mono<List<Artifact>> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts")
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Artifact>>() {});
        //List<Artifact> artifacts = response.block();
        log.info(client.getPrincipalName());
        log.info(client.getAccessToken().toString());
        return response.block();
    }*/

    @GetMapping(path = "/api/artifacts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Artifact artifact(@PathVariable("id") UUID id, @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) {
        Mono<Artifact> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts/" + id)
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Artifact.class);

        return response.block();
    }

    @GetMapping("/api/artifacts")
    public String getArtifacts(Model model, @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) {
        Mono<List<Artifact>> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts")
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Artifact>>() {
                });
        List<Artifact> artifacts = response.block();
        //log.info(client.getPrincipalName());
        //log.info(client.getAccessToken().toString());
        model.addAttribute("artifacts", artifacts);
        return "artifacts-list";
    }

    @GetMapping(path = "/api/artifact-create")
    public String createArtifactForm(Artifact artifact) {
        return "artifact-create";
    }
    private ClientHttpConnector connector() {
        return new
                ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection()));
    }
    @PostMapping(path = "/api/artifact-create")
    public String createArtifact(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client, Artifact artifact) {
        log.info("at sending " + artifact.toString());
        Mono<Artifact> response = webClient.post()
                .uri("http://127.0.0.1:8090/api/artifacts")
                //.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(artifact), Artifact.class)
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Artifact.class);
        //TODO create SAVE method
        //userService.saveModel();
        log.info("at saved " + response.block().toString());
        return "redirect:/api/artifacts";
    }

    @GetMapping(path = "api/artifact-delete/{id}")
    public String deleteArtifact(@PathVariable("id") UUID id,
                                 @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) {
        Mono<Boolean> response = this.webClient
                .delete()
                .uri("http://127.0.0.1:8090/api/artifacts/" + id)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(Boolean.class);

        Boolean result = response.block();
        //TODO create DELETE method
        log.info("DELETE " + String.valueOf(result));
        return "redirect:/api/artifacts";
    }


    @GetMapping(path = "api/artifact-update/{id}")
    public String updateArtifactForm(@PathVariable("id") UUID id, Model model,
                                     @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) {
        Mono<Artifact> response = this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/artifacts/" + id)
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Artifact.class);
        Artifact artifact = response.block();
        log.info("GET UPDATE PAGE " + artifact.toString());
        model.addAttribute("artifact",  artifact);
        return "artifact-update";
    }
    //FIXME post method is available
    @PostMapping(path = "api/artifact-update")
    public String updateArtifact(Artifact artifact) {
        //TODO create UPDATE method
        log.info("UPDATE " + artifact.toString());
        return "redirect:/api/artifacts";
    }

    /*@PutMapping("api/artifact-update/{id}")
    public String updateArtifact(
            @PathVariable(value = "id") UUID id,
            @RequestBody Artifact artifact,
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) //throws ResourceNotFoundException
    {
        Mono<Artifact> response = this.webClient
                .put()
                .uri("http://127.0.0.1:8090/api/artifacts/" + id)
                .attributes(oauth2AuthorizedClient(client))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Artifact.class);
        artifact = response.block();
        //TODO create UPDATE method
        log.info("UPDATE " + artifact.toString());
        return "redirect:/api/artifacts";
    }*/
}
