package com.amaderu.SpringArtifact.data.repository;

import com.amaderu.SpringArtifact.data.local.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Iterable<Artifact> findByCategory(String name);

    public Iterable<Artifact> findByCreated(Date date);

}
