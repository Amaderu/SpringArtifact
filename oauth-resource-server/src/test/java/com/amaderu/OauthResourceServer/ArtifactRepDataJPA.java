package com.amaderu.OauthResourceServer;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureDataJpa
@EnableJpaRepositories
@EnableAutoConfiguration
@ActiveProfiles("h2")
//@EntityScan(basePackageClasses = Artifact.class)
@EntityScan("com.amaderu.OauthResourceServer.entity")
@RunWith(SpringRunner.class)

//@SpringBootTest(classes = OauthResourceServerApplication.class)
public class ArtifactRepDataJPA {
    @Autowired
    ArtifactRepository artifactRepository;
    @Autowired
    TestEntityManager testEntityManager;
    @Test
    //@DirtiesContext
//    @Transactional
    //Context проблема
//    @Rollback(value = false)
    public void testSaveNewArtifact() {
        Artifact artifact = new Artifact(UUID.randomUUID(), LocalDate.now(), UUID.randomUUID().toString(), "Test Category", "Description");
//        artifact = artifactRepository.findByCategory("Test Category").get(0);
//        testEntityManager.persist(artifact);
//        artifact = testEntityManager.find(Artifact.class, UUID.fromString("41a763ef-5766-4e1b-96bd-633fcbc59ae2"));
//        artifact = testEntityManager.find(Artifact.class, UUID.randomUUID());
        artifact = new Artifact(UUID.fromString("41a763ef-5766-4e1b-96bd-633fcbc59ae2"),
                LocalDate.parse("2022-07-30"),
                "903f2fe2-4093-4720-b7e5-5dd306423560","Test Category",
                "Description");
        artifact = new Artifact();
        artifact.setCreated(LocalDate.parse("2022-07-30"));
        artifact.setCategory("Test Category");
        artifact.setDescription("Description");
        artifact.setUserId("903f2fe2-4093-4720-b7e5-5dd306423560");
        testEntityManager.persist(artifact);
        assertThat(artifact.getId()).isEqualTo(UUID.fromString("41a763ef-5766-4e1b-96bd-633fcbc59ae2"));
        artifact = artifactRepository.findAll().get(0);
        assertThat(artifactRepository.count()).isGreaterThan(1);
        assertThat(artifact.getCategory()).isEqualTo("Test Category");

    }
}
