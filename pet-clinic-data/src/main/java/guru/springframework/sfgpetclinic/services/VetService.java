package guru.springframework.sfgpetclinic.services;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Vet;

public interface VetService {

	Vet findByLastName (String lastName);
	Vet findById(Long Id);
	Vet save (Vet vet);
	Set<Vet> findAll();
}
