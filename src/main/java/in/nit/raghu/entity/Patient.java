package in.nit.raghu.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

//import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	
	@Column(name="patientmobile")
	private String mobile;
	
	@Column(name="patientDOB")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBith;
	
	@Column(name="marialstatus")
	private String marialStatus;
	
	@Column(name="presentaddr")
	private String presentAddr;
	
	@Column(name="communicationaddr")
	private String commAddrs;
	
	@ElementCollection
	@CollectionTable(
			name="patientmedicalhistory",
			joinColumns = @JoinColumn(name="patientmedicalhistory_idfk")
			)
	@Column(name="patientmedicalhistory")
	private Set<String> mediHistory;

	@Column(name="patientifother")
	private String ifOther;
	
	@Column(name="patientnote")
	private String note;
}
