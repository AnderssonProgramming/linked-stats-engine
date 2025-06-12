package edu.eci.arsw.linked_stats_engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling web requests and serving the UI.
 */
@Controller
public class WebController {
    
    /**
     * Serves the main page of the application.
     * 
     * @return the name of the view to render
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
