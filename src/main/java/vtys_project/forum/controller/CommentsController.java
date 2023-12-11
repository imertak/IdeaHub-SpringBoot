package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Map<String, Object>>> getAllComments() {
        List<Map<String, Object>> comments = commentsService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Map<String, Object>> getCommentById(@PathVariable int commentId) {
        Map<String, Object> comment = commentsService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Void> addComment(@RequestBody Comments comment) {
        commentsService.addComment(comment);
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
}
