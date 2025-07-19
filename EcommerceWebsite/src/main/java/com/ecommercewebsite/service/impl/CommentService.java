package com.ecommercewebsite.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICommentDAO;
import com.ecommercewebsite.model.CommentModel;
import com.ecommercewebsite.service.ICommentService;

public class CommentService implements ICommentService {

	@Inject
	private ICommentDAO commentDAO;

	@Override
	public void saveComment(CommentModel comment) {
		commentDAO.saveComment(comment);

	}

	@Override
	public List<CommentModel> getCommentsByPost(Long postId) {
		return commentDAO.getCommentsByPost(postId);
	}

}
