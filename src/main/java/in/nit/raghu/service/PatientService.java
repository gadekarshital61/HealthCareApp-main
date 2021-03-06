package in.nit.raghu.service;

import java.util.List;

import in.nit.raghu.entity.Patient;

public interface PatientService {
	
	public Long savePatient(Patient patient);
	public List<Patient> getAllPatients();
	public void deletePatient(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient patient);

}
