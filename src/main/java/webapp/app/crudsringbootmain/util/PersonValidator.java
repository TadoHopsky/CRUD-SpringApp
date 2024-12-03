package webapp.app.crudsringbootmain.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import webapp.app.crudsringbootmain.DAO.PersonDao;
import webapp.app.crudsringbootmain.user.Person;

import java.awt.*;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PersonValidator  implements Validator {

    private final PersonDao personDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDao.show(person.getEmail()).isPresent()){
            errors.rejectValue("email", "", "This email is already busy :(");
        }


    }
}
