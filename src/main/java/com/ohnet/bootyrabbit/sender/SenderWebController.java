package com.ohnet.bootyrabbit.sender;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SenderWebController {

	@RequestMapping("/")
	@ResponseBody
	private String home() {
		return "hallo..";
	}
	
}
