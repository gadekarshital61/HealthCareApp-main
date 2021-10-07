package in.nit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nit.raghu.entity.Doctor;

public interface DoctorService {

	public Long saveDoctor(Doctor doc);
	public List<Doctor> getAllDoctors();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doc);
	
	//for module integration
	public Map<Long,String> getDoctorIdAndNames();
}
