package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"","/","index","index.html"})
	public String index()
	{
		System.out.println("I AM THE SHIVA");
		return "index";
	}
	@RequestMapping({ "/oups" })
	public String oupsHandler() {
		return "notImplemented";
	}
}
