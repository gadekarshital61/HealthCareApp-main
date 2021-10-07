package in.nit.raghu.exception;

public class AppointmentNotFoundException extends RuntimeException {
	
	private static final Long serialVersionUID =1L;
	
	public AppointmentNotFoundException() {
		super();
	}
	
	public AppointmentNotFoundException(String message) {
		super(message);
	}

}
