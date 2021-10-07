package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.entity.Doctor;
import in.nit.raghu.exception.DoctorNotFoundException;
import in.nit.raghu.repo.DoctorRepository;
import in.nit.raghu.service.DoctorService;
import in.nit.raghu.util.MyCollectionsUtil;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor doc) {
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
		
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		Optional<Doctor>  optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new DoctorNotFoundException(id+ " Not Found");
		}
		/*
		 * return repo.findById(id).orElseThrow( ()->new
		 * DoctorNotFoundException(id+", not exist") );
		 */
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else 
			throw new DoctorNotFoundException(doc.getId()+", not exist"); 
	}

	// module integration
	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list=repo.getDoctorIdAndNames();
		return MyCollectionsUtil.convertToMapIndex(list);
	}


}
