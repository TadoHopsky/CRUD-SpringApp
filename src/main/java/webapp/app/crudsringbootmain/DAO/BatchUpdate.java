package webapp.app.crudsringbootmain.DAO;

import webapp.app.crudsringbootmain.user.Person;

import java.util.List;

public interface BatchUpdate {
    void testMultiplyUpdate();

    void testBatchUpdate();

    List<Person> create1000Users();
}
