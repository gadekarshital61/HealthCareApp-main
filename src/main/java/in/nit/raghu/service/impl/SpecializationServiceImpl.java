package in.nit.raghu.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.exception.SpecializationNotFoundException;
import in.nit.raghu.entity.Specialization;
import in.nit.raghu.repo.SpecializationRepository;
import in.nit.raghu.service.SpecializationService;

@Service
public class SpecializationServiceImpl implements SpecializationService {
   @Autowired
   private SpecializationRepository repo;

@Override
public Long saveSpecialization(Specialization spec) {
	// TODO Auto-generated method stub
	return repo.save(spec).getId();
}

@Override
public List<Specialization> getAllSpecializations() {
	// TODO Auto-generated method stub
	return repo.findAll();
}

@Override
public void removeSpecialization(Long id) {
	//repo.deleteById(id);
	repo.delete(getOneSpecialization(id));
}

@Override public Specialization getOneSpecialization(Long id) {
 
Optional<Specialization>  optional = repo.findById(id);
if(optional.isPresent()) {
	return optional.get();
} else {
	throw new SpecializationNotFoundException(id+ " Not Found");
}
/*return repo.findById(id).orElseThrow(
		()-> new SpecializationNotFoundException(id+ " Not Found")
		);*/
}
@Override
public void updateSpecialization(Specialization spec) {
	repo.save(spec);
}

public boolean isSpecCodeExist(String specCode) {
	/*Integer count = repo.getSpecCodeCount(specCode);
	boolean exist = count>0 ? true : false;
	return exist;*/
	return repo.getSpecCodeCount(specCode)>0;
}

@Override
public boolean isSpecNameExist(String specName) {
	// TODO Auto-generated method stub
	Integer count=repo.getSpecNameCount(specName);
	boolean exist=count>0? true:false;
	return exist;
}

////for ajax bug fix during edit
@Override
public boolean isSpecCodeExistForEdit(String specCode, Long id) {
	// TODO Auto-generated method stub
	return repo.getSpecCodeCountForEdit(specCode,id)>0;
}

@Override
public boolean isSpecNameExistForEdit(String specName, Long id) {
	// TODO Auto-generated method stub
	Integer count=repo.getSpecNameCountForEdit(specName,id);
	boolean exist=count>0? true:false;
	return exist;
}


}
