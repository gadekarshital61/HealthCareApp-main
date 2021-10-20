package in.nit.raghu.service;

import java.util.List;

import in.nit.raghu.entity.Appointment;

public interface AppointmentService {
	
	public Long saveAppointment(Appointment appointment);
	public List<Appointment> getAllAppointments();
	public Appointment getOneAppointment(Long id);
	public void deleteAppointment(Long id);
	public void updateAppointment(Appointment appointment);
	
    // book appointment slot for doctor
	List<Object[]> getAppoinmentsByDoctor(Long docId);
}
