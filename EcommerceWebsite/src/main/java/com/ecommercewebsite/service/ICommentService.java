package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.CommentModel;

public interface ICommentService {

	public void saveComment(CommentModel comment);

	public List<CommentModel> getCommentsByPost(Long postId);

}
