package webapp.app.crudsringbootmain.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
        jdbcTemplate.update("INSERT INTO Person (name, email, link) VALUES(?, ?, ?)",
                person.getName(), person.getEmail(), person.getLink());
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
    public void deleteAllUsersFromDb(){
        List<Person> people = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        for(Person person : people){
            jdbcTemplate.update("DELETE FROM Person where id=?", person.getId());
        }
    }

    /**
     * ================================================================================================
     * ==================================== Batch Update Test =========================================
     * ================================================================================================
     **/


    public void testMultiplyUpdate() { // ----------------------------------------------------->  Time : 213
        List<Person> people = create1000Users();

        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)",
                    person.getId(), person.getName(), person.getEmail(), person.getLink());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time : " + (after - before) + "mc");
    }

    public void testBatchUpdate() { // -------------------------------------------------------->  Time : 44
        List<Person> people = create1000Users();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setString(3, people.get(i).getEmail());
                        ps.setString(4, people.get(i).getLink());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });


        long after = System.currentTimeMillis();

        System.out.println("Time : " + (after - before) + "mc");
    }

    // Создание списка из 1000 людей
    private List<Person> create1000Users() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String fullName = getOneRandomUsername();
            people.add(new Person(i, fullName, fullName.replace(" ", "") + "@mail.ru",
                    "@" + fullName.replace(" ", "")));
        }
        return people;
    }

    /**
     * ================================================================================================
     * =============================== Generate Username for users ====================================
     * ================================================================================================
     **/

    // Generate Username for users
    public Map<Integer, String> generateUsername(){
        Map<Integer, String> fullInfoMap = new HashMap<>();

        String name = "Алексей, Мария, Иван, Ольга, Дмитрий, Анна, Сергей, Екатерина, Владимир, Наталья, Артем," +
                " Елена, Михаил, Татьяна, Андрей, Юлия, Роман, Виктория, Павел, Анастасия, Константин, Ирина," +
                " Александр, Валерия, Евгений, София, Василий, Ксения, Николай, Вероника";

        String surname = "Иванов, Петров, Сидоров, Смирнов, Кузнецов, Попов, Васильев, Новиков, Федоров, Морозов," +
                " Волков, Алексеев, Лебедев, Семенов, Егоров, Павлов, Козлов, Степанов, Николаев, Орлов, Макаров," +
                " Захаров, Кравцов, Беляев, Гусев, Андреев, Данилов, Сергиев, Тимофеев, Филиппов";

        String[] names = name.split(", ");
        String[] surnames = surname.split(", ");

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String randomName = names[random.nextInt(names.length)];
            String randomSurname = surnames[random.nextInt(names.length)];

            String fullName = randomName + " " + randomSurname;

            fullInfoMap.put(i, fullName);
        }
        return fullInfoMap;
    }

    public String getOneRandomUsername(){
        Map<Integer, String> map = generateUsername();

        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(map.size());

        for (int i = 0; i < map.size(); i++) {
            if(randomNumber == i){
                return map.get(i);
            }
        }
        return null;
    }
}

