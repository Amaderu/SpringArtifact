package com.amaderu.OauthResourceServer.repository;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    public Iterable<Comment> findByArtifact(Artifact artifact);

    public Iterable<Comment> findByUserId(String userid);

}
