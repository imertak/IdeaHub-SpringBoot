package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.dto.entity_Dto.TopicsDto;
import vtys_project.forum.dto.entity_Dto.TopicsResponse;
import vtys_project.forum.entity.Topics;
import vtys_project.forum.repository.TopicsRepository;

import java.util.List;

@Service
public class TopicsService {

    private final TopicsRepository topicsRepository;

    @Autowired
    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public Topics addTopic(TopicsDto topic) {
        return topicsRepository.addTopic(topic);
    }

    public Topics getTopicById(int id) {
        return topicsRepository.getTopicById(id);
    }

    public List<Topics> getAllTopics() {
        return topicsRepository.getAllTopics();
    }

    public void deleteTopicById(int topicId) {
        topicsRepository.deleteTopicById(topicId);
    }

    public void updateTopic(int id, Topics updatedTopic) {
        topicsRepository.updateTopic(id, updatedTopic);
    }

    public List<TopicsResponse> getNewTopics(){
        return topicsRepository.getNewTopics();
    }

    public TopicsResponse getTopic(int id){
        return topicsRepository.getTopic(id);
    }

    public List<TopicsResponse> getSearchTopics(String searchTopic){return topicsRepository.getSearchTopics(searchTopic);}

    public List<TopicsResponse> getMyTopics(String username){
        return topicsRepository.getMyTopics(username);
    }
}
