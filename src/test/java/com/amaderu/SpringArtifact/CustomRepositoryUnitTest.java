package com.amaderu.SpringArtifact;

import dao.ArtifactDAO;
import entity.Artifact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@SpringBootTest(classes = CustomRepositoryApplication.class)
class CustomRepositoryUnitTest {

    /*@Autowired
    private ArtifactDAO userRepository;

    @Test
    public void givenCustomRepository_whenInvokeCustomFindMethod_thenEntityIsFound() {
        Artifact user = new Artifact();

        Artifact persistedUser = userRepository.save(user);

        assertEquals(persistedUser, userRepository.findAllR(user.getId()));
    }*/
}
