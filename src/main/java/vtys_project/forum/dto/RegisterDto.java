package vtys_project.forum.dto;

import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    @Lob
    private byte[] profileImage;
    private Date creationDate;

}
