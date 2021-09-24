package in.nit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
	

}
