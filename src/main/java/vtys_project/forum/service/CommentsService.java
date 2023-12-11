package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Comments;
import vtys_project.forum.repository.CommentsRepository;

import java.util.List;
import java.util.Map;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<Map<String, Object>> getAllComments() {
        return commentsRepository.getAllComments();
    }

    public Map<String, Object> getCommentById(int commentId) {
        return commentsRepository.getCommentById(commentId);
    }

    public void addComment(Comments comment) {
        commentsRepository.addComment(comment);
    }

    public void updateComment(int commentId, Comments comment) {
        commentsRepository.updateComment(commentId, comment);
    }

    public void deleteComment(int commentId) {
        commentsRepository.deleteComment(commentId);
    }
}
