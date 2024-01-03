package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.dto.entity_Dto.CommentsResponse;
import vtys_project.forum.entity.Comments;
import vtys_project.forum.service.CommentsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentsService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comments> getCommentById(@PathVariable int commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Void> addComment(@RequestBody Comments comment,@PathVariable String username) {
        commentsService.addComment(comment,username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable int commentId, @RequestBody Comments comment) {
        commentsService.updateComment(commentId, comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        commentsService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCommentByTopicID/{topicID}")
    public ResponseEntity<List<CommentsResponse>> getCommentByTopicID(@PathVariable int topicID){
        return new ResponseEntity<>(commentsService.getCommentByTopicID(topicID), HttpStatus.OK);
    }
}
