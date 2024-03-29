package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
//import javax.validation.Valid;



@RequestMapping("/owners")
@Controller
public class OwnerController {
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	@RequestMapping({ "", "/", "/index", "index.html" })
	public String listOwners(Model model) {
		
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}

	@RequestMapping({ "/find" })
	public String findOwners(Model model) {
		 model.addAttribute("owner", Owner.builder().build());
	     return "owners/findOwners";
	}
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	@GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        java.util.List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }
	 @GetMapping("/new")
	    public String initCreationForm(Model model) {
	        model.addAttribute("owner", Owner.builder().build());
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }
	 @GetMapping("/{ownerId}/edit")
	    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
	        model.addAttribute(ownerService.findById(ownerId));
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }
	 @PostMapping("/new")
	    public String processCreationForm(Owner owner, BindingResult result) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            Owner savedOwner =  ownerService.save(owner);
	            return "redirect:/owners/" + savedOwner.getId();
	        }
	    }
	 @PostMapping("/{ownerId}/edit")
	    public String processUpdateOwnerForm(Owner owner, BindingResult result, @PathVariable Long ownerId) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            owner.setId(ownerId);
	            Owner savedOwner = ownerService.save(owner);
	            return "redirect:/owners/" + savedOwner.getId();
	        }
	    }
}
