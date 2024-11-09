package webapp.app.crudsringbootmain.user;

import lombok.Data;

@Data
public class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
