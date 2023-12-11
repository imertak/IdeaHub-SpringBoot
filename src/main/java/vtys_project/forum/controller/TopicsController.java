package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.Topics;
import vtys_project.forum.service.TopicsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    private final TopicsService topicsService;

    @Autowired
    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllTopics() {
        List<Map<String, Object>> topics = topicsService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<Map<String, Object>> getTopicById(@PathVariable int topicId) {
        Map<String, Object> topic = topicsService.getTopicById(topicId);
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    public ResponseEntity<Void> addTopic(@RequestBody Topics topic) {
        topicsService.addTopic(topic);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<Void> updateTopic(@PathVariable int topicId, @RequestBody Topics topic) {
        topicsService.updateTopic(topicId, topic);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int topicId) {
        topicsService.deleteTopic(topicId);
        return ResponseEntity.ok().build();
    }
}
