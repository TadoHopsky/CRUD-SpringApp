package webapp.app.crudsringbootmain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.app.crudsringbootmain.DAO.PersonDao;
import webapp.app.crudsringbootmain.user.Person;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonDao personDao;

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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute(personDao.show(id));
        return "/first/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/first/edit";
        }
        personDao.update(id, person);
        return "redirect:/people";
    }

    // При переходе на /people/new создаётся Person с пустыми значениями и кладётся в Model
    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") Person person) {
        return "/first/new";
    }

    // При переходе на /people создаётся Person с пустыми значениями
    // Пустые значения заполняются информацией из html формы и помещается в Model
    // Вызов метода create() с передачей аргумента person
    @PostMapping()
    public String createNewUser(@ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/first/new";
        }
        personDao.create(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }
}