package webapp.app.crudsringbootmain.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchUpdateTest implements BatchUpdateInterface {

    private final JdbcTemplate jdbcTemplate;
    private final GenerateUserService generateUserService;

    @Autowired
    public BatchUpdateTest(JdbcTemplate jdbcTemplate, GenerateUserService generateUserService) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateUserService = generateUserService;
    }

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
    public List<Person> create1000Users() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String fullName = generateUserService.getOneRandomUsername();
            people.add(new Person(i, fullName, fullName.replace(" ", "") + "@mail.ru",
                    "@" + fullName.replace(" ", "")));
        }
        return people;
    }
}
