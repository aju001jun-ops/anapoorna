package com.anapoornabackend.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
	

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    @GetMapping("/add-location")
    public String addLocationPage() {
        return "add-location";
    }

    @GetMapping("/register-orphanage")
    public String registerOrphanagePage() {
        return "orphanage-register";
    }

}
