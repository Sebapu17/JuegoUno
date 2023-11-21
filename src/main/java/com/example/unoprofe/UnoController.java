package com.example.unoprofe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnoController {
    
        private Partida gameState;


	@GetMapping("/greeting")
	public String greeting(Model model) {
		gameState = new Partida(new JugadorHumano("seba"));
                model.addAttribute("partida", gameState);
                return "test";
	}
        
        
        @GetMapping("/hola")
	public String hola(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}

}