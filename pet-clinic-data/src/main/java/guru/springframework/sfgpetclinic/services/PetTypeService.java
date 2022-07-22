package guru.springframework.sfgpetclinic.services;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;

@Service
public interface PetTypeService extends CrudService<PetType,Long>{
}
