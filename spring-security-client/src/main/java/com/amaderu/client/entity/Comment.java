package com.amaderu.client.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    UUID id;
    @ManyToOne()
    @JoinColumn(name = "artifactid", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ARTIFACT_COMMENT")
    )
    Artifact artifact;
    String userId;
    String content;

    public Comment() {
    }

    public Comment(UUID id, Artifact artifact, String userId, String content) {
        this.id = id;
        this.artifact = artifact;
        this.userId = userId;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
