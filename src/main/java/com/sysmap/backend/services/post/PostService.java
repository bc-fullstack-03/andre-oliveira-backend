package com.sysmap.backend.services.post;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.dtos.post.PostRequest;
import com.sysmap.backend.dtos.post.PostResponse;
import com.sysmap.backend.exceptions.NotFoundException;
import com.sysmap.backend.model.Comment;
import com.sysmap.backend.model.Like;
import com.sysmap.backend.model.Post;
import com.sysmap.backend.repositories.PostRepository;

@Service
public class PostService implements IPostService {

  private PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  @Override
  public PostResponse createPost(PostRequest post) {
    Post newPost = new Post(post);
    return new PostResponse(repository.save(newPost));
  }

  @Override
  public List<PostResponse> getPosts(String userId) {
    return repository.findAllByUserId(userId).stream().map(PostResponse::new).toList();
  }

  @Override
  public PostResponse createComment(String idPost, CommentRequest comment) {
    Post post = repository.findById(idPost).orElseThrow(() -> new NotFoundException("Post não encontrado"));
    Comment newComment = new Comment(comment);
    post.getComments().add(newComment);
    return new PostResponse(repository.save(post));
  }

  @Override
  public List<LikeDTO> likeComment(LikeDTO like, UUID idComment, String idPost) {
    Post post = repository.findById(idPost).orElseThrow(() -> new NotFoundException("Post não encontrado"));
    Comment c = new Comment();
    for (Comment comment : post.getComments()) {
      if (Objects.equals(comment.getId(), idComment)) {
        comment.getLikes().add(new Like(like));
        c = comment;
      }
    }
    repository.save(post);
    return c.getLikes().stream().map(LikeDTO::new).toList();
  }

  @Override
  public List<LikeDTO> likePost(LikeDTO like, String idPost) {
    Post post = repository.findById(idPost).orElseThrow(() -> new NotFoundException("Post não encontrado"));
    post.getLikes().add(new Like(like));
    post = repository.save(post);
    return post.getLikes().stream().map(LikeDTO::new).toList();
  }

}
