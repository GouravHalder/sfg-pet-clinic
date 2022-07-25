package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialtyService specialtyService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		int count = petTypeService.findAll().size();
		if (count ==0 )
		{
			loadData();
		}
	}


	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		dog.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology =  specialtyService.save(radiology);
		
		
		Specialty dentist = new Specialty();
		dentist.setDescription("Dentist");
		Specialty savedDentist =  specialtyService.save(dentist);
		
		
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		specialtyService.save(surgery);
		Specialty savedSurgery =  specialtyService.save(dentist);
		
		Owner owner1 =new Owner();
		owner1.setFirstName("Bombie");
		owner1.setLastName("Halder");
		owner1.setAddress("Purbalok");
		owner1.setCity("Kolkata");
		owner1.setTelephone("9836532615");
		ownerService.save(owner1);
		
		Pet bombiePet = new Pet();
		bombiePet.setName("Googly");
		bombiePet.setOwner(owner1);
		bombiePet.setPetType(savedDogPetType);
		bombiePet.setBirthDate(LocalDate.now());
		owner1.getPets().add(bombiePet);
		
		Owner owner2 =new Owner();
		owner2.setFirstName("Rajat");
		owner2.setLastName("Roy");
		owner2.setAddress("CollinsPath");
		owner2.setCity("Durgapur");
		owner2.setTelephone("7047010303");
		ownerService.save(owner2);
		
		Pet pappuPet = new Pet();
		pappuPet.setName("Suzi");
		pappuPet.setOwner(owner2);
		pappuPet.setPetType(savedCatPetType);
		pappuPet.setBirthDate(LocalDate.now());
		owner2.getPets().add(pappuPet);
		
		System.out.println("Loading Owners......");
		
		
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Anupam");
		vet1.setLastName("Hazra");
		vet1.getSpecialty().add(savedSurgery);
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("P.C");
		vet2.setLastName("Jha");
		vet1.getSpecialty().add(savedRadiology);
		vetService.save(vet2);
		
		System.out.println("Loading Vets......");
	}
	

	

	
}
