package webapp.app.crudsringbootmain.DAO;


import org.springframework.jdbc.core.RowMapper;
import webapp.app.crudsringbootmain.user.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person p = new Person();

        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setEmail(rs.getString("email"));
        p.setLink(rs.getString("link"));

        return p;
    }
}

