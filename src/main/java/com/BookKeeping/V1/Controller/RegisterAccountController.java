package com.BookKeeping.V1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterAccountController {

	@RequestMapping("/login_page")
	public String login_page() {
		return "loginPage";
	}
}
