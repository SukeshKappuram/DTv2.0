/**
 * 
 */
package com.devops.ecomerce.service;

import com.devops.ecomerce.models.colabaration.Comment;

/**
 * @author iamsu
 *
 */
public interface ICommentService {
	public void addComment(Comment c);
	public Comment viewComment(int commentId);
	public void deleteComment(int commentId);
}
