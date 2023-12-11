package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Topics;
import vtys_project.forum.repository.TopicsRepository;

import java.util.List;
import java.util.Map;

@Service
public class TopicsService {

    private final TopicsRepository topicsRepository;

    @Autowired
    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public List<Map<String, Object>> getAllTopics() {
        return topicsRepository.getAllTopics();
    }

    public Map<String, Object> getTopicById(int topicId) {
        return topicsRepository.getTopicById(topicId);
    }

    public void addTopic(Topics topic) {
        topicsRepository.addTopic(topic);
    }

    public void updateTopic(int topicId, Topics topic) {
        topicsRepository.updateTopic(topicId, topic);
    }

    public void deleteTopic(int topicId) {
        topicsRepository.deleteTopic(topicId);
    }
}
