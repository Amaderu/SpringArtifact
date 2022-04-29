package com.amaderu.SpringArtifact.service;

import com.amaderu.SpringArtifact.data.local.entity.Artifact;
import com.amaderu.SpringArtifact.data.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MainService {
    @Autowired
    private ArtifactRepository artifactRepository;

    public List<Artifact> loadArtifacts(){
        List<Artifact> artifact = (List<Artifact>) artifactRepository.findAll();
        return artifact;
    }
}
