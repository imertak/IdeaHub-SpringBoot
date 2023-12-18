package vtys_project.forum.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Roles {
    @Id
    private int RoleID;
    private String RoleName;


    //Getter and Setter Methods



}
