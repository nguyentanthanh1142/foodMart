package com.ecommercewebsite.dao.impl;

import java.util.List;

import com.ecommercewebsite.dao.ICommentDAO;
import com.ecommercewebsite.mapper.CommentMapper;
import com.ecommercewebsite.model.CommentModel;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO{

	@Override
	public void saveComment(CommentModel comment) {
		String sql = "INSERT INTO comment (userId, postId, content, parentId, createdAt) VALUES (?, ?, ?, ?, NOW())";
		System.out.println(sql);
		Long id = insert(sql, comment.getUserId(), comment.getPostId(), comment.getContent(),comment.getParentId());
	}

	@Override
	public List<CommentModel> getCommentsByPost(Long postId) {
		 String sql = "SELECT c.*, u.fullname FROM comment c JOIN users u ON c.userId = u.id WHERE c.postId = ? ORDER BY c.createdAt desc";
		 System.out.println(sql);
		 return query(sql,new CommentMapper(), postId);
	}

}
