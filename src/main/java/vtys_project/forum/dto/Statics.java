package vtys_project.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statics {
    private int countTopics;
    private int countComment;
    private int countUsers;
    private String lastUser;
}
