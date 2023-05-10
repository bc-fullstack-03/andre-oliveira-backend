package com.sysmap.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.dtos.post.PostRequest;
import com.sysmap.backend.dtos.post.PostResponse;
import com.sysmap.backend.services.post.IPostService;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

  private IPostService service;

  public PostController(IPostService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponse createPost(@RequestBody PostRequest post) {
    return service.createPost(post);
  }

  @PostMapping("{idPost}/comment")
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponse createComment(@PathVariable String idPost, @RequestBody CommentRequest comment) {
    return service.createComment(idPost, comment);
  }

  @PostMapping("{idPost}/like")
  public List<LikeDTO> likePost(@RequestBody LikeDTO like, @PathVariable String idPost) {
    return service.likePost(like, idPost);
  }

  @PostMapping("{idPost}/comment/{idComment}/like")
  public List<LikeDTO> likeComment(@RequestBody LikeDTO like, @PathVariable String idPost,
      @PathVariable UUID idComment) {
    return service.likeComment(like, idComment, idPost);
  }

  @GetMapping("/user/{userId}")
  public List<PostResponse> getPosts(@PathVariable String userId) {
    return service.getPosts(userId);
  }
}
