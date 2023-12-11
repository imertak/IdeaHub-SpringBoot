package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Topics;

import java.util.List;
import java.util.Map;

@Repository
public class TopicsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TopicsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllTopics() {
        String sql = "SELECT * FROM Topics";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getTopicById(int topicId) {
        String sql = "SELECT * FROM Topics WHERE topicID = ?";
        return jdbcTemplate.queryForMap(sql, topicId);
    }

    public void addTopic(Topics topic) {
        String sql = "INSERT INTO Topics (title, content, userID, categoryID) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, topic.getTitle(), topic.getContent(), topic.getUserID(), topic.getCategoryID());
    }

    public void updateTopic(int topicId, Topics topic) {
        String sql = "UPDATE Topics SET title = ?, content = ?, userID = ?, categoryID = ? WHERE topicID = ?";
        jdbcTemplate.update(sql, topic.getTitle(), topic.getContent(), topic.getUserID(), topic.getCategoryID(), topicId);
    }

    public void deleteTopic(int topicId) {
        String sql = "DELETE FROM Topics WHERE topicID = ?";
        jdbcTemplate.update(sql, topicId);
    }
}
