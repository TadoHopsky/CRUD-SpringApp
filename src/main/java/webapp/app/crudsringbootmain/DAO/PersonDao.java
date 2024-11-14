package webapp.app.crudsringbootmain.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
    Компонент Spring MVC приложения, который отвечает за подключение к БД
    в данном случае используется ArrayList на замену БД.
    В дальнейшем подключить БД.
    Тут же реализованы методы получения информации из БД метод Index() - возвращает список всех людей
    show(id) - возвращает человека по ID
 */
@Service
public class PersonDao {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Fhrfif1488";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();
        String SQL = "SELECT * FROM Person";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            Person p = new Person();

            p.setId(resultSet.getInt("id"));
            p.setName(resultSet.getString("name"));
            p.setEmail(resultSet.getString("email"));
            p.setLink(resultSet.getString("link"));

            people.add(p);
        }
        return people;
    }

    public Person show(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT id, name, email, link FROM Person";

        ResultSet resultSet = statement.executeQuery(sql);

        Person person = new Person();

        while (resultSet.next()) {
            if (resultSet.getInt("id") == id) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setLink(resultSet.getString("link"));
            }
        }
        return person;
    }

    // Метод, который сохраняет Person с заданными данными в PostgreSQL db
    public void create(Person person) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO Person VALUES (" + 1 + ", '" + person.getName() + "', '" +
                person.getEmail() + "', '" + person.getLink() + "')";

        statement.executeUpdate(SQL);
    }

    public void update(int id, Person updatePerson) throws SQLException {
        Person personToBeUpdated = show(id);

        Statement statement = connection.createStatement();
        String sql = "UPDATE Person SET name='" + updatePerson.getName() + "', email= '" +
                updatePerson.getEmail() + "', link='" + updatePerson.getLink() + "' WHERE id=" + id;

        statement.executeUpdate(sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Person WHERE id = " + id;

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
