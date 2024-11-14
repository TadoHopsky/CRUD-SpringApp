package webapp.app.crudsringbootmain.DAO;

import org.springframework.stereotype.Component;
import webapp.app.crudsringbootmain.user.Person;

import java.util.ArrayList;
import java.util.List;

/*
    Компонент Spring MVC приложения, который отвечает за подключение к БД
    в данном случае используется ArrayList на замену БД.
    В дальнейшем подключить БД.
    Тут же реализованы методы получения информации из БД метод Index() - возвращает список всех людей
    show(id) - возвращает человека по ID
 */
@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tado", "tado@mail.com", "@Tado12"));
        people.add(new Person(++PEOPLE_COUNT, "keke", "keke@mail.com", "@keke12333"));
        people.add(new Person(++PEOPLE_COUNT, "egor", "egor@mail.com", "@egor1488"));
        people.add(new Person(++PEOPLE_COUNT, "NikitaChamitov", "NChamitov@mail.com", "@nch_gang"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    // Метод, который сохраняет Person с заданными данными в List
    public void create(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setEmail(updatePerson.getEmail());
        personToBeUpdated.setLink(updatePerson.getLink());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
