package com.sysmap.backend.services.post;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.dtos.post.PostRequest;
import com.sysmap.backend.dtos.post.PostResponse;
import com.sysmap.backend.model.Comment;
import com.sysmap.backend.model.Like;
import com.sysmap.backend.model.Post;
import com.sysmap.backend.repositories.PostRepository;
import com.sysmap.backend.services.comment.CommentService;

@Service
public class PostService implements IPostService {

  private PostRepository repository;
  private CommentService commentService;

  public PostService(PostRepository repository, CommentService commentService) {
    this.repository = repository;
    this.commentService = commentService;
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
    Post post = repository.findById(idPost).get();
    Comment newComment = commentService.createComment(comment);
    post.getComments().add(newComment);
    return new PostResponse(repository.save(post));
  }

  @Override
  public List<LikeDTO> likeComment(LikeDTO like, String idComment, String idPost) {
    Post post = repository.findById(idPost).get();
    for (Comment comment : post.getComments()) {
      if (Objects.equals(comment.getId(), idComment)) {
        comment.getLikes().add(new Like(like));
        repository.save(post);
      }
    }
    return commentService.likeComment(like, idComment);
  }

  @Override
  public List<LikeDTO> likePost(LikeDTO like, String idPost) {
    Post post = repository.findById(idPost).get();
    post.getLikes().add(new Like(like));
    post = repository.save(post);
    return post.getLikes().stream().map(LikeDTO::new).toList();
  }

}
