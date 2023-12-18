package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vtys_project.forum.dto.Statics;

import java.sql.*;

@RestController
public class StaticsController {

    Statics statics;
    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    @GetMapping("/statics")
    public ResponseEntity<Statics> getStatics() {
        Statics statics = new Statics(); // Statics nesnesini başlat

        String sql1 = "SELECT COUNT(*) as countTopics FROM topics";
        int topics = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    topics = resultSet.getInt("countTopics");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "SELECT COUNT(*) as countComments FROM comments";
        int comments = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    comments = resultSet.getInt("countComments");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql3 = "SELECT COUNT(*) as countUsers FROM users";
        int users = 0;
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql3)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    users = resultSet.getInt("countUsers");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql4 = "SELECT username FROM users ORDER BY userID DESC LIMIT 1";
        String lastUser = "";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql4)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lastUser = resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        statics.setCountComment(comments);
        statics.setCountUsers(users);
        statics.setCountTopics(topics);
        statics.setLastUser(lastUser);

        return new ResponseEntity<>(statics, HttpStatus.OK);
    }

}
