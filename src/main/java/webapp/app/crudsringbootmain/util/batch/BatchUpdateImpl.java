package webapp.app.crudsringbootmain.util.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchUpdateImpl implements BatchUpdate {

    private final JdbcTemplate jdbcTemplate;
    private final GeneratorUser generateUser;  // <---- Интерфейс

    public void testMultiplyUpdate() { // ----------------------------------------------------->  Time : 213
        List<Person> people = create30Users();

        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?, ?)",
                    person.getId(), person.getName(), person.getEmail(), person.getLink(), person.getAddress());
        }

        long after = System.currentTimeMillis();
        System.out.println("Time : " + (after - before) + "mc");
    }

    public void testBatchUpdate() { // -------------------------------------------------------->  Time : 44
        List<Person> people = create30Users();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setString(3, people.get(i).getEmail());
                        ps.setString(4, people.get(i).getLink());
                        ps.setString(5, people.get(i).getAddress());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long after = System.currentTimeMillis();

        System.out.println("Time : " + (after - before) + "mc");
    }

    // Создание списка из 30 людей
    public List<Person> create30Users() {
        var people = new ArrayList<Person>();
        for (int i = 0; i < 30; i++) {
            String fullName = generateUser.getOneRandomUsername();
            var person = Person.builder()
                    .id(i)
                    .name(fullName)
                    .email(fullName.toLowerCase().replace(" ", "") + "@mail.ru")
                    .link("@" + fullName.toLowerCase().replace(" ", ""))
                    .address("City, Country, 123123")
                    .build();

            people.add(person);
        }
        return people;
    }
}
