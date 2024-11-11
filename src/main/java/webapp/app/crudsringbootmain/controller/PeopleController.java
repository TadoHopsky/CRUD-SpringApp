package webapp.app.crudsringbootmain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.app.crudsringbootmain.DAO.PersonDao;
import webapp.app.crudsringbootmain.user.Person;

/*
    Компонент КОНТРОЛЛЕР в котором внедрена зависимость bean PersonDAO
    Идёт добавление в Model результат методов index | show
 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;                                    // <---- final

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "/first/index";
    }

    // @PathVariable - даёт возможность получить (в данном случае ID) напрямую через URL адрес
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "/first/show";
    }

    // При переходе на /people/new создаётся Person с пустыми значениями и кладётся в Model
    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") Person person){
        return "/first/new";
    }

    // При переходе на /people создаётся Person с пустыми значения и помещается в Model
    // Вызов метода create() с передачей аргумента person
    @PostMapping()
    public String createNewUser(@ModelAttribute ("person") Person person){
        personDao.create(person);
        return "redirect:/people";
    }
}