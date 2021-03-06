package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vets")
public class Vet extends Person{
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialities",joinColumns = @JoinColumn(name = "vet_id"),
										 inverseJoinColumns = @JoinColumn(name="speciality_id"))
	private Set<Specialty> specialty = new HashSet<>();

	public Set<Specialty> getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Set<Specialty> specialty) {
		this.specialty = specialty;
	}
	
	
}
 