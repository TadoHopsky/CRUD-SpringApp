package webapp.app.crudsringbootmain.util.batch;

import webapp.app.crudsringbootmain.user.Person;

import java.util.List;

public interface BatchUpdate {
    void testMultiplyUpdate();

    void testBatchUpdate();

    List<Person> create30Users();
}
