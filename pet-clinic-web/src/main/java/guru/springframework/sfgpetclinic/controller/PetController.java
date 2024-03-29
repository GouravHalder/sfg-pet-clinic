package guru.springframework.sfgpetclinic.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private PetTypeService petTypeService;
	private OwnerService ownerService;
	private PetService petService;
	
	
	public PetController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
		this.petService = petService;
	}
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes(){
		return petTypeService.findAll();
	}
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable Long ownerId) {
		return ownerService.findById(ownerId);
	}
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		System.out.println("##########In initOwnerBinder############");
		dataBinder.setDisallowedFields("id");
	}
	@GetMapping("/pets/new")
    public String initCreationForm(Owner owner,Model model) {
		System.out.println("In the initCreationForm........");
		Pet p = new Pet();
		owner.getPets().add(p);
		System.out.println(owner.getFirstName());
		System.out.println(owner.getLastName());
		p.setOwner(owner);
        model.addAttribute("pet", p);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }
	 @PostMapping("/pets/new")
	    public String processCreationForm(Owner owner, Pet pet, BindingResult result, ModelMap model) {
	        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
	        	System.out.println("Validation FAILED in Petcontroler");
	            result.rejectValue("name", "duplicate", "already exists");
	        }
	        owner.getPets().add(pet);
	        if (result.hasErrors()) {
	        	System.out.println("Retruning to ten input form og Adding Pet");
	            model.put("pet", pet);
	            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	        } else {
	            petService.save(pet);

	            return "redirect:/owners/" + owner.getId();
	        }
	    }
	 @GetMapping("/pets/{petId}/edit")
	    public String initUpdateForm(@PathVariable Long petId, Model model) {
	        model.addAttribute("pet", petService.findById(petId));
	        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	    }
	  @PostMapping("/pets/{petId}/edit")
	    public String processUpdateForm(Pet pet, BindingResult result, Owner owner, Model model) {
	        if (result.hasErrors()) {
	            pet.setOwner(owner);
	            model.addAttribute("pet", pet);
	            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	        } else {
	            owner.getPets().add(pet);
	            petService.save(pet);
	            return "redirect:/owners/" + owner.getId();
	        }
	    }
}

	