package in.nit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="doctortab")
public class Doctor {

	@Id
	@Column(name="docid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="docfname")
	private String firstName;
	
	@Column(name="doclname")
	private String lastName;
	
	@Column(name="docemail")
	private String email;
	
	@Column(name="docaddr")
	private String address;
	
	@Column(name="docmobile")
	private String mobile;
	
	@Column(name="docgender")
	private String gender;
	
	@Column(name="docnote")
	private String note;
	
	@Column(name="docimg")
	private String photoLoc;


	//----------Association Mapping------------------
	@ManyToOne
	@JoinColumn(name="spec_id_fk")
	private Specialization specialization; //HAS-A
}
