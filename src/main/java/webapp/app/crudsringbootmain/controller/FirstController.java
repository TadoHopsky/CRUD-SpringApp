package webapp.app.crudsringbootmain.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
        if (name == null & surname == null){
            return "/first/hello";
        }else {
            model.addAttribute("massage", "Welcome to the site " + name + " " + surname);
            return "/first/hello";
        }
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "/first/goodbye";
    }
}
