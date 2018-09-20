package pl.sporttown.controller.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sporttown.domain.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
    private String text;
    private int votes;
    private LocalDateTime data;
    private byte[] image;
    private User user;

}
