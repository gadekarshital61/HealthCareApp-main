package in.nit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="patienttab")
public class Patient {

	@Id
	@Column(name="patientid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="patientfname")
	private String firstName;
	
	@Column(name="patientlname")
	private String lastName;
	
	@Column(name="patientgender")
	private String gender;
	
	@Column(name="patientphone")
	private String phone;
	
	@Column(name="marialstatus")
	private String marialStatus;
	
	@Column(name="presentaddr")
	private String presentAddress;
	
	@Column(name="communicationaddr")
	private String communicationAddress;
	
	@Column(name="pastmedicalhistory")
	private String pastMedicalHistory;
	
	@Column(name="otherdetails")
	private String otherDetails;
}
