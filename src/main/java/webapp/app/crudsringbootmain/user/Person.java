package webapp.app.crudsringbootmain.user;

import lombok.Data;

/*
    Класс, который содержит информацию о пользователе.
    Не является Bean, не внедряется в качестве Component в Spring Context
 */
@Data
public class Person {
    private int id;
    private String name;
    private String email;
    private String link;

    public Person(){};

    public Person(int id, String name, String email, String link) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.link = link;
    }
}
