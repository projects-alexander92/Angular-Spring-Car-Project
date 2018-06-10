package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAngular
{
    @GetMapping("/")
    public String returnNg()
    {
        return "index.html";
    }
}
