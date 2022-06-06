package io.HaiderRizviLtd.coronacasestracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.HaiderRizviLtd.coronacasestracker.services.coronaVirusDataService;

@Controller
public class HomeController {

    @Autowired
    coronaVirusDataService coronavirusDataService;
    
        
    // it will return the keyword from templates directory
    @GetMapping("/")
    public String home(Model model) {

        // MODEL attribures will be accessible in HTML template file 
        model.addAttribute("locationStats", coronavirusDataService.getAllStats());
        return "home";
    }

    
}
