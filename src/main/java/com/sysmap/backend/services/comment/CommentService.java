package com.sysmap.backend.services.comment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.exceptions.NotFoundException;
import com.sysmap.backend.model.Comment;
import com.sysmap.backend.model.Like;
import com.sysmap.backend.repositories.CommentRepository;

@Service
public class CommentService implements ICommentService {

  private CommentRepository repository;

  public CommentService(CommentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Comment createComment(CommentRequest comment) {
    return repository.save(new Comment(comment));
  }

  @Override
  public List<LikeDTO> likeComment(LikeDTO like, String idComment) {
    Comment comment = repository.findById(idComment)
        .orElseThrow(() -> new NotFoundException("Comentário não encontrado"));
    comment.getLikes().add(new Like(like));
    comment = repository.save(comment);
    return comment.getLikes().stream().map(LikeDTO::new).toList();
  }

}
