package webapp.app.crudsringbootmain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.app.crudsringbootmain.DAO.PersonDao;

/*
    Компонент КОНТРОЛЛЕР в котором внедрена зависимость bean PersonDAO
    Идёт добавление в Model результат методов index | show
 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;                                    // <---- final

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "/first/index";
    }

//    @PathVariable - даёт возможность получить (в данном случае ID) напрямую через URL адрес
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "/first/show";
    }
}