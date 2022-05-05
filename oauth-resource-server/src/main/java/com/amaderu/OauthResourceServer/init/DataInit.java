package com.amaderu.OauthResourceServer.init;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.entity.Comment;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import com.amaderu.OauthResourceServer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Component
public class DataInit implements ApplicationRunner {

    private ArtifactRepository artifactRepository;
    private CommentRepository commentRepository;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final String WEB_RESPONSE_FORMAT="EEE MMM dd HH:mm:ss ZZZZZ yyyy"; // Thu Oct 26 07:31:08 +0000 2017


    @Autowired
    public DataInit(ArtifactRepository artifactRepository,
                    CommentRepository commentRepository) {
        this.artifactRepository = artifactRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = artifactRepository.count();

        if (count == 0) {
            Artifact p1 = new Artifact();

            p1.setUserId(UUID.randomUUID().toString());
            p1.setCategory("Оружие"+count);
            p1.setDescription("Оружие древних времён"+count);
            //Date d1 = df.parse(Calendar.getInstance().getTime().toString());
            /*Date d1 = df.parse("2022-04-25 23:30:44.000000");
            p1.setCreated(d1);*/

            artifactRepository.save(p1);
        }
        long countComments = commentRepository.count();

        if (countComments == 0) {
            for (Artifact artifact:
                 artifactRepository.findAll()) {
                Comment c1 = new Comment();
                c1.setUserId(UUID.randomUUID().toString());
                c1.setArtifact(artifact);
                c1.setContent("Интересный артефакт");
                commentRepository.save(c1);
            }
        }

    }

}
