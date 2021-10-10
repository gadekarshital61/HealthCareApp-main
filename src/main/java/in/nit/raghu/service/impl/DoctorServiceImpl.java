package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.constants.UserRoles;
import in.nit.raghu.entity.User;
import in.nit.raghu.entity.Doctor;
import in.nit.raghu.exception.DoctorNotFoundException;
import in.nit.raghu.repo.DoctorRepository;
import in.nit.raghu.service.DoctorService;
import in.nit.raghu.service.UserService;
import in.nit.raghu.util.MyCollectionsUtil;
import in.nit.raghu.util.UserUtil;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserUtil util;


	@Override
	public Long saveDoctor(Doctor doc) {
		Long id = repo.save(doc).getId();
		if(id!=null) {
			User user = new User();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(util.genPwd());
			user.setRole(UserRoles.DOCTOR.name());
			userService.saveUser(user);
			// TODO : Email part is pending
		}
		return id;
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
