package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;

@Component
public class DataLoader implements CommandLineRunner{

	
	public OwnerService ownerService;
	public VetService vetService;
	public DataLoader() {
		super();
		ownerService = new OwnerMapService();
		vetService = new VetMapService();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Owner owner1 =new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Bombie");
		owner1.setLastName("Halder");
		ownerService.save(owner1);
		
		Owner owner2 =new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Rajat");
		owner2.setLastName("Roy");
		ownerService.save(owner2);
		
		System.out.println("Loading Owners......");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Anupam");
		vet1.setLastName("Hazra");
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(1L);
		vet2.setFirstName("P.C");
		vet2.setLastName("Jha");
		vetService.save(vet2);
		
		System.out.println("Loading Vets......");
		
	}

	
}
