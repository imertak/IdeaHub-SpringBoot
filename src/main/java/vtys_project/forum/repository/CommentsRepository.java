package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Comments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentsRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    public Comments addComment(Comments comment) {
        String sql = "INSERT INTO comments (text, creationDate, userID, topicID) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, comment.getText());
                java.sql.Date sqlCreationDate = new java.sql.Date(comment.getCreationDate().getTime()); //ÖNEMLİ
                preparedStatement.setDate(2, sqlCreationDate);
                preparedStatement.setInt(3, comment.getUserID());
                preparedStatement.setInt(4, comment.getTopicID());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comment.setCommentID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public Comments getCommentById(int id) {
        Comments comment = new Comments();
        String sql = "SELECT * FROM comments WHERE commentID=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    comment.setCommentID(resultSet.getInt("commentID"));
                    comment.setText(resultSet.getString("text"));
                    comment.setCreationDate(resultSet.getDate("creationDate"));
                    comment.setUserID(resultSet.getInt("userID"));
                    comment.setTopicID(resultSet.getInt("topicID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public List<Comments> getAllComments() {
        List<Comments> commentsList = new ArrayList<>();
        String sql = "SELECT * FROM comments";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Comments comment = new Comments();
                comment.setCommentID(resultSet.getInt("commentID"));
                comment.setText(resultSet.getString("text"));
                comment.setCreationDate(resultSet.getDate("creationDate"));
                comment.setUserID(resultSet.getInt("userID"));
                comment.setTopicID(resultSet.getInt("topicID"));
                commentsList.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentsList;
    }

    public void deleteCommentById(int commentId) {
        String sql = "DELETE FROM comments WHERE commentID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, commentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateComment(int id, Comments updatedComment) {
        String sql = "UPDATE comments SET text = ?, creationDate = ?, userID = ?, topicID = ? WHERE commentID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updatedComment.getText());
            preparedStatement.setInt(3, updatedComment.getUserID());
            preparedStatement.setInt(4, updatedComment.getTopicID());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
