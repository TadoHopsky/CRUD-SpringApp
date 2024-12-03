package webapp.app.crudsringbootmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.app.crudsringbootmain.util.BatchUpdate;
import webapp.app.crudsringbootmain.DAO.PersonDao;

@Controller
@RequestMapping("/batch-page")
@RequiredArgsConstructor
public class BatchMethodController {

    private final PersonDao personDao;
    private final BatchUpdate batchUpdate;

    @GetMapping()
    public String index() {
        return "/batch/index";
    }

    @GetMapping("/delete-all")
    public String delete() {
        personDao.deleteAllUsersFromDb();
        return "redirect:/people";
    }

    @GetMapping("/without")
    public String updateWithout() {
        batchUpdate.testMultiplyUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String updateWith() {
        batchUpdate.testBatchUpdate();
        return "redirect:/people";
    }
}
