package webapp.app.crudsringbootmain.DAO;

import webapp.app.crudsringbootmain.user.Person;

import java.util.List;

public interface BatchUpdateInterface {
    void testMultiplyUpdate();

    void testBatchUpdate();

    List<Person> create1000Users();
}
