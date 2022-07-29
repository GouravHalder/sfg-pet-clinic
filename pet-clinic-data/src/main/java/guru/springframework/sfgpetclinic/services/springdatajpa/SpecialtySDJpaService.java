package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService{

	private final SpecialityRepository specialityRepository;
	
	
	public SpecialtySDJpaService(SpecialityRepository specialityRepository) {
		super();
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Set<Specialty> findAll() {
		// TODO Auto-generated method stub
		HashSet<Specialty> specialtys = new HashSet<>();
		specialityRepository.findAll().forEach(specialtys::add);
		return specialtys;
	}

	@Override
	public Specialty findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Specialty> optionalSpecialty = specialityRepository.findById(id);
		return optionalSpecialty.orElse(null);
	}

	@Override
	public Specialty save(Specialty object) {
		// TODO Auto-generated method stub
		return specialityRepository.save(object);
	}

	@Override
	public void delete(Specialty object) {
		// TODO Auto-generated method stub
		specialityRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		specialityRepository.deleteById(id);
	}

}
