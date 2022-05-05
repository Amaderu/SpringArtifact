package com.amaderu.OauthResourceServer.repository;

import com.amaderu.OauthResourceServer.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Iterable<Artifact> findByCategory(String name);

    public Iterable<Artifact> findByCreated(Date date);

}
