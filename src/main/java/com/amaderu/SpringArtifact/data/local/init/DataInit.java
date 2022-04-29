package com.amaderu.SpringArtifact.data.local.init;

import com.amaderu.SpringArtifact.data.local.entity.Artifact;
import com.amaderu.SpringArtifact.data.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class DataInit implements ApplicationRunner {

    private ArtifactRepository artifactRepository;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final String TWITTER_RESPONSE_FORMAT="EEE MMM dd HH:mm:ss ZZZZZ yyyy"; // Thu Oct 26 07:31:08 +0000 2017


    @Autowired
    public DataInit(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
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

    }

}
