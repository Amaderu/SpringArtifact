package com.amaderu.OauthResourceServer.service;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ArtifactService {
    @Autowired
    private ArtifactRepository artifactRepository;

    public List<Artifact> loadArtifacts(){
        List<Artifact> artifact = (List<Artifact>) artifactRepository.findAll();
        return artifact;
    }

    public Artifact loadArtifact(UUID id){
        Optional<Artifact> artifact = artifactRepository.findById(id);
        return artifact.orElse(null);
    }
}
