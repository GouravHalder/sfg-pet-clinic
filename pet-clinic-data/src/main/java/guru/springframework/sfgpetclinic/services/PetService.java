package guru.springframework.sfgpetclinic.services;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;

@Service
public interface PetService extends CrudService<Pet,Long>{
}
