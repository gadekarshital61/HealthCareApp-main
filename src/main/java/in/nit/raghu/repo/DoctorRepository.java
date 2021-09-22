package in.nit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	

}
