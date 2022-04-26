package init;

import dao.ArtifactDAO;
import entity.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    private ArtifactDAO artifactDAO;

    /*private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");*/

    @Autowired
    public DataInit(ArtifactDAO artifactDAO) {
        this.artifactDAO = artifactDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = artifactDAO.count();

        if (count == 0) {
            Artifact p1 = new Artifact();
            artifactDAO.save(p1);
        }

    }

}
