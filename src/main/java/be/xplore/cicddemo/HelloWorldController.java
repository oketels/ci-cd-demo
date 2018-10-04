package be.xplore.cicddemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a mailto="olivier.ketels@xplore.be">Olivier Ketels</a>
 */

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("dayOfWeek", LocalDate.now().getDayOfWeek());
        return "greeting";
    }


    @RequestMapping(value = "/action")
    public Map<String, Object> doSomeThing(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

}