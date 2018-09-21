package pl.sporttown.controller.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pl.sporttown.domain.model.Category;
import pl.sporttown.domain.model.User;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
    private String text;
    private int votes;
    private LocalDateTime data;
    private MultipartFile image;
    private String imageBase64Encoded;
    private UserDTO userDTO;
    private Category category;
}
