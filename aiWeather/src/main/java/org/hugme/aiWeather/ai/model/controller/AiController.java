package org.hugme.aiWeather.ai.model.controller;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.ai.model.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AiController {
	@Autowired
	private AiService aiService;
	
	@RequestMapping(value="search.ai", method=RequestMethod.POST)
	public String search(HttpSession session, String content) {
		try {
			String result = aiService.perplexityService(content);
			session.setAttribute("alertMsg", result);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
    		session.setAttribute("alertMsg", "접근 실패?");
    		return "redirect:/";
		}
	}
}
