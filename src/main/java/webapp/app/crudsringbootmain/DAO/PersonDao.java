package webapp.app.crudsringbootmain.DAO;

import org.springframework.stereotype.Component;
import webapp.app.crudsringbootmain.user.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Tado", "tado@mail.com"));
        people.add(new Person(++PEOPLE_COUNT,"keke", "keke@mail.com"));
        people.add(new Person(++PEOPLE_COUNT,"egor", "egor@mail.com"));
        people.add(new Person(++PEOPLE_COUNT,"NikitaChamitov", "NChamitov@mail.com"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
}
