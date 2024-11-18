package webapp.app.crudsringbootmain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;

    @NotEmpty(message = "Not be empty.")
    @Size(min = 2, max = 30, message = "Should be 2-30 chars.")
    private String name;

    @NotEmpty(message = "Not be empty.")
    @Email(message = "invalid formal email.")
    private String email;

    @NotEmpty(message = "Not be empty.")
    @Size(min = 2, max = 20, message = "Should be 2-20 chars.")
    private String link;
}
