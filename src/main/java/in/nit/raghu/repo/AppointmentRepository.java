package in.nit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.raghu.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
	
	//view appointment slot for doctor
	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
	public List<Object[]> getAppoinmentsByDoctor(Long docId);

}
