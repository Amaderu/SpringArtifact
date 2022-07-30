package com.amaderu.OauthResourceServer.repository;

import com.amaderu.OauthResourceServer.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {
    public List<Artifact> findByCategory(String category);


    public List<Artifact> findByCreated(LocalDate date);

    //@Query("SELECT a FROM Artifact a WHERE a.category = ?1 AND a.description = ?2")
    List<Artifact> findByCategoryAndDescription(String category, String description);

}
