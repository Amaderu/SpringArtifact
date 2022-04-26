package dao;

import entity.Artifact;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArtifactDAO extends CrudRepository<Artifact, Long> {

    public List<Artifact> findByFullNameLike(String name);

    public List<Artifact> findByDateOfBirthGreaterThan(Date date);

    public List<Artifact> findAllR();

}
