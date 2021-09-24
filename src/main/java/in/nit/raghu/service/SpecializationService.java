package in.nit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nit.raghu.entity.Specialization;

public interface SpecializationService {
     // method for crud app
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	// method for ajax validation
	public boolean isSpecCodeExist(String specCode);
	public boolean isSpecNameExist(String specName);
	
	//method for ajax bug fix during edit time
	public boolean isSpecCodeExistForEdit(String specCode,Long id);
	public boolean isSpecNameExistForEdit(String specName, Long id);
	
	//for module integration
	Map<Long,String> getSpecIdAndName();
	
}
