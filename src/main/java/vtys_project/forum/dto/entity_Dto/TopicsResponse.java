package vtys_project.forum.dto.entity_Dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicsResponse {
    private int topicID;
    private String title;
    private String content;
    private Date topicCreationDate;
    private int userID;
    private int categoryID;
    private String categoryName;
    private String categoryDescription;
    private String username;
    @Lob
    private byte[] profileImage;
    private Date userCreationDate;
}
