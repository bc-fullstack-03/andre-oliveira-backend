package com.sysmap.backend.services.post;

import java.util.List;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.dtos.post.PostRequest;
import com.sysmap.backend.dtos.post.PostResponse;

public interface IPostService {
  PostResponse createPost(PostRequest post);

  List<PostResponse> getPosts(String userId);

  PostResponse createComment(String postId, CommentRequest comment);

  List<LikeDTO> likePost(LikeDTO like, String idPost);

  List<LikeDTO> likeComment(LikeDTO like, String idComment, String idPost);
}
