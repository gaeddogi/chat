package com.example.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat")
    public ModelAndView chatGET(@RequestParam(value = "name", required = true) String name,
                                ModelAndView mv) {
        log.info("@ChatController, username: " + name);


        mv.addObject("name", name);
        mv.setViewName("chat");

        return mv;
    }
}
