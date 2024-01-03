package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.dto.entity_Dto.CommentsResponse;
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

    public List<Comments> getAllComments() {
        return commentsRepository.getAllComments();
    }

    public Comments getCommentById(int commentId) {
        return commentsRepository.getCommentById(commentId);
    }

    public void addComment(Comments comment, String username) {
        commentsRepository.addComment(comment,username);
    }

    public void updateComment(int commentId, Comments comment) {
        commentsRepository.updateComment(commentId, comment);
    }

    public void deleteComment(int commentId) {
        commentsRepository.deleteCommentById(commentId);
    }

    public List<CommentsResponse> getCommentByTopicID(int topicID){
        return commentsRepository.getCommentByTopicID(topicID);
    }
}
