package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Specialty;

public interface SpecialityRepository extends CrudRepository<Specialty, Long>{

}
