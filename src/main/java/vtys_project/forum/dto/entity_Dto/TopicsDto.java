package vtys_project.forum.dto.entity_Dto;

import lombok.Data;

import java.util.Date;

@Data
public class TopicsDto {
    private String title;
    private String content;
    private String username;
    private String categoryName;
}
