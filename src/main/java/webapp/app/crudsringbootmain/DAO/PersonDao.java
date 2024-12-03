package webapp.app.crudsringbootmain.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    // Show all users in DB
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    // Show one user from DB found by ID
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    // Add new user from DB
    public void create(Person person) {
        List<Person> people;
        people = jdbcTemplate.query("SELECT id FROM Person", new BeanPropertyRowMapper<>(Person.class));
        int lastID = 0;
        for(Person p : people){
            if(p.getId()>lastID){
                lastID = p.getId();
            }
        }
        jdbcTemplate.update("INSERT INTO Person (id, name, email, link) VALUES(?, ?, ?, ?)",
                lastID + 1, person.getName(), person.getEmail(), person.getLink());
    }

    // Update User information
    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, email=?, link=? WHERE id=?",
                updatePerson.getName(), updatePerson.getEmail(), updatePerson.getLink(), id);
    }

    // Delete user from DB
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person where id=?", id);
    }

    // Delete All users from DB
    public void deleteAllUsersFromDb() {
        List<Person> people = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        for (Person person : people) {
            jdbcTemplate.update("DELETE FROM Person where id=?", person.getId());
        }
    }
}


