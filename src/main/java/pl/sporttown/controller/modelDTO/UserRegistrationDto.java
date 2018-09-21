package pl.sporttown.controller.modelDTO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.sporttown.validation.FieldsValueMatch;
import javax.validation.constraints.*;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmPassword"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail"
        )
})
@EqualsAndHashCode
@Data
public class UserRegistrationDto {
    @NotEmpty
    @Size(min = 3, max = 10)
    private String password;

    @NotEmpty
    @Size(min = 3, max = 10)
    private String confirmPassword;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String nick;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;


}