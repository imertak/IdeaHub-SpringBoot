package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Comments;

import java.util.List;
import java.util.Map;

@Repository
public class CommentsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllComments() {
        String sql = "SELECT * FROM Comments";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAllCommentsByCategoryID(int categoryID) {
        String sql = "SELECT * FROM Comments WHERE commentID = ?";
        return jdbcTemplate.queryForList(sql, categoryID);
    }

    public Map<String, Object> getCommentById(int commentId) {
        String sql = "SELECT * FROM Comments WHERE commentID = ?";
        return jdbcTemplate.queryForMap(sql, commentId);
    }



    public void addComment(Comments comment) {
        String sql = "INSERT INTO Comments (text, userID, topicID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, comment.getText(), comment.getUserID(), comment.getTopicID());
    }

    public void updateComment(int commentId, Comments comment) {
        String sql = "UPDATE Comments SET text = ?, userID = ?, topicID = ? WHERE commentID = ?";
        jdbcTemplate.update(sql, comment.getText(), comment.getUserID(), comment.getTopicID(), commentId);
    }

    public void deleteComment(int commentId) {
        String sql = "DELETE FROM Comments WHERE commentID = ?";
        jdbcTemplate.update(sql, commentId);
    }
}
