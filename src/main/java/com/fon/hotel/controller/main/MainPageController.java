package com.fon.hotel.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {


    @RequestMapping("/main")
    public String mainPage(Model model){
        return "mainPage";
    }

    @RequestMapping("/aboutUsPage")
    public String aboutUsPage(Model model) {
        return "aboutUsPage";
    }
}
