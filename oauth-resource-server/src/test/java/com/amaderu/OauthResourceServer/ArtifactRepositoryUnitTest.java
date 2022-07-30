package com.amaderu.OauthResourceServer;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import com.amaderu.OauthResourceServer.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest(classes = {ResourceServerConfig.class, OauthResourceServerApplication.class, ArtifactRepository.class, CommentRepository.class, Artifact.class})
//@DataJpaTest
//@TestPropertySource(locations="classpath:application.yaml")
/*@EnableJpaRepositories(basePackageClasses = ArtifactRepository.class)
@EntityScan(basePackageClasses = Artifact.class)*/

@ActiveProfiles("h2")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ArtifactRepositoryUnitTest {

    @Autowired
    private ArtifactRepository artifactRepository;
    @Autowired
    private CommentRepository commentRepository;
    List<Artifact> artifacts;
    Artifact testArtifact;

    // data initialization
    @BeforeEach
    public void setUp() throws Exception {
        artifacts = (List<Artifact>) asList(
                new Artifact(UUID.randomUUID(), LocalDate.now(), UUID.randomUUID().toString(), "Category", "Description"),
                new Artifact(UUID.randomUUID(), LocalDate.now(), UUID.randomUUID().toString(), "Test Category", "Test Description"),
                new Artifact()
        );
    }

    @Test
    public void whenCreateOneToRepository_thenRepositoryHaveMoreAtOne() {
        Long old = artifactRepository.count();
        testArtifact = artifactRepository.save(artifacts.get(1));
        assertThat(testArtifact).isNotNull();
        assertThat(artifactRepository.count()).isEqualTo(old+1);
    }

    @Test
    public void whenFindAll_ThenTakeAllExisting() {
        List<Artifact> artifacts = artifactRepository.findAll();
        assertThat(artifacts).isNotNull();
        assertThat(artifacts.size()).isNotEqualTo(0);
    }

    //FIXME testArtifact is null
    @Test
    public void whenFindById_ThenTakeOneExisting() {
        UUID id = UUID.fromString("acf295af-96f3-4d44-9516-3c491c9ef3ea");
        testArtifact =  artifactRepository.findById(id).orElse(null);
        assertThat(testArtifact).isNotNull();
        Optional<Artifact> artifact = artifactRepository.findById(testArtifact.getId());
        assertThat(testArtifact).isNotNull();
    }

    @Test
    public void whenSavedOne_ThenTakeOneWithCategory(){
        testArtifact = artifactRepository.findByCategory("Test Category").get(0);
        assertThat(testArtifact).isNotNull();
        assertThat(testArtifact).isNotEqualTo(0);
        assertThat(testArtifact.getCategory()).isEqualTo("Test Category");
    }

    @Test
    public void whenFindByCategoryAndDescription_ThenTakeOneExisting() {
        artifacts =  artifactRepository.findByCategoryAndDescription("Test Category","Test Description");
        assertThat(artifacts).isNotNull();
        assertThat(artifacts.size()).isGreaterThan(0);
        assertThat(artifacts.get(0).getCategory()).isEqualTo("Test Category");
        assertThat(artifacts.get(0).getDescription()).isEqualTo("Test Description");
    }

    @Test
    public void whenDeleteOneFromRepository_thenRepositoryShouldBeLessAtOne() {
        long old = artifactRepository.count();
        testArtifact = artifactRepository.findByCategory("Test Category").get(0);
        artifactRepository.deleteById(testArtifact.getId());
        assertThat(artifactRepository.count()).isEqualTo(old-1);
    }

    //FIXME DELETE Test is not pass because Delete category stop for artifacts thats has comment
    /*@Test
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
        artifactRepository.deleteAll();
        assertThat(artifactRepository.count()).isEqualTo(0);
        assertThat(commentRepository.count()).isEqualTo(0);
        assertThat(artifactRepository.count()).isLessThan(1);
    }*/


}
