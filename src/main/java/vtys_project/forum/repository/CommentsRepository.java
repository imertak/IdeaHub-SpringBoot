package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.dto.entity_Dto.CommentsResponse;
import vtys_project.forum.entity.Comments;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Repository
public class CommentsRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    LocalDateTime currentDateTime = LocalDateTime.now();

    public Comments addComment(Comments comment, String username) {
        // userID alma
        String sql2 = "SELECT userID FROM users WHERE username=?";
        int userID = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userID = resultSet.getInt("userID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql = "INSERT INTO comments (content, creationDate, userID, topicID) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, comment.getText());
                java.sql.Date sqlCreationDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());
                sqlCreationDate = new java.sql.Date(sqlCreationDate.getTime() + TimeZone.getDefault().getOffset(sqlCreationDate.getTime()));
                preparedStatement.setDate(2, sqlCreationDate);
                preparedStatement.setInt(3, userID);
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

    public List<CommentsResponse> getCommentByTopicID(int topicID){
        List<CommentsResponse> commentsList = new ArrayList<>();
        String sql = "SELECT commentID,content,comments.creationDate as creationDate,comments.userID as userID,topicID,users.username as username  FROM comments JOIN users ON users.userID=comments.userID WHERE topicID=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, topicID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CommentsResponse comment = new CommentsResponse();
                    comment.setCommentID(resultSet.getInt("commentID"));
                    comment.setText(resultSet.getString("content"));
                    comment.setCreationDate(resultSet.getDate("creationDate"));
                    comment.setUserID(resultSet.getInt("userID"));
                    comment.setTopicID(resultSet.getInt("topicID"));
                    comment.setUsername(resultSet.getString("username"));
                    commentsList.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentsList;
    }


}
