package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.entity.Patient;
import in.nit.raghu.exception.DoctorNotFoundException;
import in.nit.raghu.exception.PatientNotFoundException;
import in.nit.raghu.repo.PatientRepository;
import in.nit.raghu.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    
	@Autowired
	private PatientRepository repo;
	
	@Override
	public Long savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return repo.save(patient).getId();
	}

	@Override
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removePatient(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getOnePatient(id));
	}

	@Override
	public Patient getOnePatient(Long id) {
		Optional<Patient>  optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new PatientNotFoundException(id+ " Not Found");
		}
		/*
		 * return repo.findById(id).orElseThrow( ()->new
		 * DoctorNotFoundException(id+", not exist") );
		 */
	}

	@Override
	public void updatePatient(Patient patient) {
		if(repo.existsById(patient.getId()))
			repo.save(patient);
		else 
			throw new DoctorNotFoundException(patient.getId()+", not exist"); 
	}


}
