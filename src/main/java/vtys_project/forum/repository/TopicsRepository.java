package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.dto.entity_Dto.TopicsDto;
import vtys_project.forum.dto.entity_Dto.TopicsResponse;
import vtys_project.forum.entity.Topics;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TopicsRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    LocalDateTime currentDateTime = LocalDateTime.now();

    public Topics addTopic(TopicsDto topic) {
        Topics topics = new Topics();

        // categoryId alma
        String sql1 = "SELECT categoryID FROM categories WHERE categoryName=?";
        int categoryID = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            preparedStatement.setString(1, topic.getCategoryName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    categoryID = resultSet.getInt("categoryID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // userId alma
        String sql2 = "SELECT * FROM users WHERE username=?";
        int userID = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.setString(1, topic.getUsername());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userID = resultSet.getInt("userID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO topics (title, content, creationDate, userID, categoryID) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, topic.getTitle());
                preparedStatement.setString(2, topic.getContent());
                // currentDateTime'un doğru bir şekilde alındığından emin olun
                java.sql.Date sqlCreationDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());
                sqlCreationDate = new java.sql.Date(sqlCreationDate.getTime() + TimeZone.getDefault().getOffset(sqlCreationDate.getTime()));
                preparedStatement.setDate(3, sqlCreationDate);
                preparedStatement.setInt(4, userID);
                preparedStatement.setInt(5, categoryID);
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        topics.setTopicID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        topics.setCategoryID(categoryID);
        topics.setTitle(topic.getTitle());
        return topics;
    }


    public Topics getTopicById(int id) {
        Topics topic = new Topics();
        String sql = "SELECT * FROM topics WHERE topicID=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    topic.setTopicID(resultSet.getInt("topicID"));
                    topic.setTitle(resultSet.getString("title"));
                    topic.setContent(resultSet.getString("content"));
                    topic.setCreationDate(resultSet.getDate("creationDate"));
                    topic.setUserID(resultSet.getInt("userID"));
                    topic.setCategoryID(resultSet.getInt("categoryID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }

    public List<Topics> getAllTopics() {
        List<Topics> topicsList = new ArrayList<>();
        String sql = "SELECT * FROM topics";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Topics topic = new Topics();
                topic.setTopicID(resultSet.getInt("topicID"));
                topic.setTitle(resultSet.getString("title"));
                topic.setContent(resultSet.getString("content"));
                topic.setCreationDate(resultSet.getDate("creationDate"));
                topic.setUserID(resultSet.getInt("userID"));
                topic.setCategoryID(resultSet.getInt("categoryID"));
                topicsList.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicsList;
    }

    public void deleteTopicById(int topicId) {
        String sql = "DELETE FROM topics WHERE topicID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, topicId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTopic(int id, Topics updatedTopic) {
        String sql = "UPDATE topics SET title = ?, content = ?, creationDate = ?, userID = ?, categoryID = ? WHERE topicID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updatedTopic.getTitle());
            preparedStatement.setString(2, updatedTopic.getContent());
            preparedStatement.setInt(5, updatedTopic.getCategoryID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TopicsResponse> getNewTopics(){
        List<TopicsResponse> topicsResponseList = new ArrayList<>();
        String sql = "SELECT topicID, title, content, topics.creationDate as topicCreationDate, topics.userID, topics.categoryID,categoryName,categoryDescription, username, profileImage, users.creationDate as userCreationDate  FROM topics JOIN categories ON topics.categoryID=categories.categoryID JOIN users ON topics.userID=users.userID ORDER BY topicID DESC LIMIT 100";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                TopicsResponse topicsResponse = new TopicsResponse();
                topicsResponse.setTopicID(resultSet.getInt("topicID"));
                topicsResponse.setTitle(resultSet.getString("title"));
                topicsResponse.setContent(resultSet.getString("content"));
                topicsResponse.setTopicCreationDate(resultSet.getDate("topicCreationDate"));
                topicsResponse.setUserID(resultSet.getInt("userID"));
                topicsResponse.setCategoryID(resultSet.getInt("categoryID"));
                topicsResponse.setCategoryName(resultSet.getString("categoryName"));
                topicsResponse.setCategoryDescription(resultSet.getString("categoryDescription"));
                topicsResponse.setUsername(resultSet.getString("username"));
                topicsResponse.setProfileImage(resultSet.getBytes("profileImage"));
                topicsResponse.setUserCreationDate(resultSet.getDate("userCreationDate"));
                topicsResponseList.add(topicsResponse);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicsResponseList;
    }





}
