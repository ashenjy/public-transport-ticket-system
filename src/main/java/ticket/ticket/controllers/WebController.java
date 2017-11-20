package ticket.ticket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping("/")
    ModelAndView login(ModelAndView modelAndView) {

		modelAndView.setViewName("login");

		return modelAndView;
	}
}