package com.amaderu.OauthResourceServer.repository;

import com.amaderu.OauthResourceServer.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {
    public Iterable<Artifact> findByCategory(String name);

    public Iterable<Artifact> findByCreated(Date date);

}
