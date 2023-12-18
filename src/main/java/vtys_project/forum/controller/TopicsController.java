package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.dto.entity_Dto.TopicsDto;
import vtys_project.forum.dto.entity_Dto.TopicsResponse;
import vtys_project.forum.entity.Topics;
import vtys_project.forum.service.TopicsService;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    private final TopicsService topicsService;

    @Autowired
    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Topics> addTopic(@RequestBody TopicsDto topic) {
        return new ResponseEntity<>(topicsService.addTopic(topic), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public Topics getTopicById(@PathVariable int id) {
        return topicsService.getTopicById(id);
    }

    @GetMapping("/getAll")
    public List<Topics> getAllTopics() {
        return topicsService.getAllTopics();
    }

    @DeleteMapping("/{id}")
    public void deleteTopicById(@PathVariable int id) {
        topicsService.deleteTopicById(id);
    }

    @PutMapping("/{id}")
    public void updateTopic(@PathVariable int id, @RequestBody Topics updatedTopic) {
        topicsService.updateTopic(id, updatedTopic);
    }

    @GetMapping("/getNewTopics")
    public ResponseEntity<List<TopicsResponse>> getNewTopics(){
        return new ResponseEntity<>(topicsService.getNewTopics(),HttpStatus.OK);
    }



}
