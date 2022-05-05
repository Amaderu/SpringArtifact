package com.amaderu.OauthResourceServer.service;

import com.amaderu.OauthResourceServer.entity.Artifact;
import com.amaderu.OauthResourceServer.entity.Comment;
import com.amaderu.OauthResourceServer.repository.ArtifactRepository;
import com.amaderu.OauthResourceServer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> loadArtifacts(){
        List<Comment> comment = (List<Comment>) commentRepository.findAll();
        return comment;
    }
}
