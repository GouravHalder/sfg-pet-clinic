package guru.springframework.sfgpetclinic.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateOwnerForm";
	private PetTypeService petTypeService;
	private OwnerService ownerService;
	
	public PetController(PetTypeService petTypeService, OwnerService ownerService) {
		super();
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
	}
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes(){
		return petTypeService.findAll();
	}
	public Owner findOwner(@PathVariable Long ownerId) {
		return ownerService.findById(ownerId);
	}
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		System.out.println("##########In initOwnerBinder############");
		dataBinder.setDisallowedFields("id");
	}
}

	