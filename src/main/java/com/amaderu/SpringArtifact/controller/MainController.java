package controller;

import data.local.dao.ArtifactDAO;
import data.local.entity.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private ArtifactDAO personDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Artifact> all = personDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getFullName() + "<br>"));

        return sb.toString();
    }

}