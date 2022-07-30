package com.amaderu.OauthResourceServer.service;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    public List<Artifact> loadArtifacts(List<Sort.Order> ordersList){
        List<Artifact> artifact = (List<Artifact>) artifactRepository.findAll(Sort.by(ordersList));
        return artifact;
    }

    public Artifact loadArtifact(UUID artifactId){
        Optional<Artifact> artifact = artifactRepository.findById(artifactId);
        return artifact.orElseThrow(() -> new ResourceNotFoundException("Artifact not found for this id :: " + artifactId));
    }
    public Artifact saveArtifact(Artifact artifact){
        return artifactRepository.save(artifact);
    }

    //FIXME fix FK_ (FK_ARTIFACT_COMMENT) to deletet strategy
    //Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint ["FK_ARTIFACT_COMMENT: PUBLIC.COMMENT FOREIGN KEY(ARTIFACTID) REFERENCES PUBLIC.ARTIFACT(ID) ('f71a1319-4850-4686-89ea-341789cafaa6')"; SQL statement:
    //delete from artifact where id=? [23503-212]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement] with root cause
    //
    //org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Нарушение ссылочной целостности: "FK_ARTIFACT_COMMENT: PUBLIC.COMMENT FOREIGN KEY(ARTIFACTID) REFERENCES PUBLIC.ARTIFACT(ID) ('f71a1319-4850-4686-89ea-341789cafaa6')"
    //Referential integrity constraint violation: "FK_ARTIFACT_COMMENT: PUBLIC.COMMENT FOREIGN KEY(ARTIFACTID) REFERENCES PUBLIC.ARTIFACT(ID) ('f71a1319-4850-4686-89ea-341789cafaa6')"; SQL statement:
    //delete from artifact where id=? [23503-212]

    public void deleteArtifact(Artifact artifact){
        artifactRepository.delete(artifact);
    }

    public void deleteArtifactById(UUID artifactId){
        if(!artifactRepository.existsById(artifactId)) throw
                new ResourceNotFoundException("Artifact not found for this id :: " + artifactId);
        Optional<Artifact> artifact = artifactRepository.findById(artifactId);
        artifact.ifPresent(value -> artifactRepository.delete(value));
    }
}
