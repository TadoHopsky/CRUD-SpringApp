package webapp.app.crudsringbootmain.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import webapp.app.crudsringbootmain.user.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Person");

        ResultSet resultSet = ps.executeQuery();

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

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        Person person = new Person();

        resultSet.next();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setLink(resultSet.getString("link"));

        return person;
    }

    // Метод, который сохраняет Person с заданными данными в PostgreSQL db
    public void create(Person person) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Person VALUES (1, ?, ?, ?)");

        ps.setString(1, person.getName());
        ps.setString(2, person.getEmail());
        ps.setString(3, person.getLink());

        ps.executeUpdate();
    }

    public void update(int id, Person updatePerson) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(
                "UPDATE Person SET name=?, email=?, link=? WHERE id=?");

        ps.setString(1, updatePerson.getName());
        ps.setString(2, updatePerson.getEmail());
        ps.setString(3, updatePerson.getLink());
        ps.setInt(4, id);

        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("DELETE FROM Person WHERE id=?");
        ps.setInt(1, id);

        ps.executeUpdate();
    }
}
