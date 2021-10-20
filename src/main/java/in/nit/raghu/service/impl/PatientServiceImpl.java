package in.nit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.raghu.constants.UserRoles;
import in.nit.raghu.entity.User;
import in.nit.raghu.entity.Patient;
import in.nit.raghu.repo.PatientRepository;
import in.nit.raghu.service.PatientService;
import in.nit.raghu.service.UserService;
import in.nit.raghu.util.MyMailUtil;
import in.nit.raghu.util.UserUtil;

@Service
public class PatientServiceImpl implements PatientService {
    
	@Autowired
	private PatientRepository repo;
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtil util;
	
	@Autowired
	private MyMailUtil mailUtil;
	
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id= repo.save(patient).getId();
		if(id!=null) {
			String pwd=util.genPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			//user.setPassword(util.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			//userService.saveUser(user);
			// TODO : Email part is pending
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your name is " + patient.getEmail() +", password is "+ pwd;
						mailUtil.send(patient.getEmail(), "PATIENT ADDED", text);
					}
				}).start();
		}
		return id;
	
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


