package pl.sporttown.controller.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDTO {

    private long id;
    private String name;
    private String lastName;
    private String password;
    private String nick;
    private String email;

}