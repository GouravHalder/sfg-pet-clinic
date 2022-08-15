package guru.springframework.sfgpetclinic.services;

import java.util.List;

import guru.springframework.sfgpetclinic.model.Owner;

//@Service
public interface OwnerService extends CrudService<Owner,Long>{

	Owner findByLastName (String lastName);
	List<Owner> findAllByLastNameLike(String lastName);
}
