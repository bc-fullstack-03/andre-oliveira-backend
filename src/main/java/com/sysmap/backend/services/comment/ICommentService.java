package com.sysmap.backend.services.comment;

import java.util.List;

import com.sysmap.backend.dtos.comment.CommentRequest;
import com.sysmap.backend.dtos.like.LikeDTO;
import com.sysmap.backend.model.Comment;

public interface ICommentService {
  Comment createComment(CommentRequest comment);

  List<LikeDTO> likeComment(LikeDTO like, String idComment);

}
