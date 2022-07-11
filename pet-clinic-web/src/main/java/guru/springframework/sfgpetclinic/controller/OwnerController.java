package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	@RequestMapping({"","/","/index","index.html"})
	public String listOwners()
	{
		return "owners/index";
	}
	/*
	 * @RequestMapping({"/error"}) public String index1() { return "abc"; }
	 */
}
