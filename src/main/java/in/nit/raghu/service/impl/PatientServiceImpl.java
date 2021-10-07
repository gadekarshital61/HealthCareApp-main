package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Long savePatient(Patient patient) {
		return repo.save(patient).getId();
	}

	@Override
	@Transactional
	public void updatePatient(Patient patient) {
		repo.save(patient);
	}

	@Override
	@Transactional
	public void deletePatient(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public Patient getOnePatient(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}
	
}


