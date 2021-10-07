package in.nit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
	

}
