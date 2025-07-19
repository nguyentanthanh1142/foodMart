package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.CommentModel;

public interface ICommentDAO extends GenericDAO<CommentModel>{
	 public void saveComment(CommentModel comment);
	 public List<CommentModel> getCommentsByPost(Long postId);
}
