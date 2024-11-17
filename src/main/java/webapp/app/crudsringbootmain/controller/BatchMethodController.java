package webapp.app.crudsringbootmain.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.app.crudsringbootmain.DAO.PersonDao;

@Controller
@RequestMapping("/batch-page")
public class BatchMethodController {

    private PersonDao personDao;

    @Autowired
    public BatchMethodController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(){
        return "/batch/index";
    }

    @GetMapping("/delete-all")
    public String delete(){
        personDao.deleteAllUsersFromDb();
        return "redirect:/people";
    }

    @GetMapping("/without")
    public String updateWithout(){
        personDao.testMultiplyUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String updateWith(){
        personDao.testBatchUpdate();
        return "redirect:/people";
    }
}
