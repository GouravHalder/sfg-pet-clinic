package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService <T extends BaseEntity,Id extends Long>{
	
	protected HashMap<Long,T> map =new HashMap<>();
	Set<T> findAll(){
		return new HashSet(map.values());
	}
	T findById(Id id)
	{
		return map.get(id);
	}
	public T save (T object)
	{
		if (null!= object)
		{
			if (null == object.getId())
			{
				object.setId(getNextId());
			}
			map.put(object.getId(), object);
		}
		
		return object;
	}
	void deleteById(Id id)
	{
		map.remove(id);
	}
	void delete (T object)
	{
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	private Long getNextId() {
		Long nextId=null;
		
		try {
			nextId= Collections.max(map.keySet()) +1 ;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			nextId=1L;
		}
		return nextId;
	}
}
