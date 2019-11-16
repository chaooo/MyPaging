package top.chaooo.hellonetty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NettyController {
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("name", "Dear");
        return "index";
    }
}
