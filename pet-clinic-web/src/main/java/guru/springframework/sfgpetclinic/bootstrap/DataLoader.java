package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		dog.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		
		Owner owner1 =new Owner();
		owner1.setFirstName("Bombie");
		owner1.setLastName("Halder");
		ownerService.save(owner1);
		
		Owner owner2 =new Owner();
		owner2.setFirstName("Rajat");
		owner2.setLastName("Roy");
		ownerService.save(owner2);
		
		System.out.println("Loading Owners......");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Anupam");
		vet1.setLastName("Hazra");
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("P.C");
		vet2.setLastName("Jha");
		vetService.save(vet2);
		
		System.out.println("Loading Vets......");
		
	}
	

	

	
}
