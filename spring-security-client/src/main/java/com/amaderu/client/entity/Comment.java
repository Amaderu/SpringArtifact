package com.amaderu.client.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
/*@Table(name = "Comment")*/
public class Comment {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Getter
    @Setter
    private UUID id;*/
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Getter
    @Setter
    UUID id;
    @ManyToOne()
    @JoinColumn(name = "artifactid", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ARTIFACT_COMMENT")
    )
    Artifact artifact;
    String userId;
    String content;

}
