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
@Table(name="specializationtab")
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specid")
	private Long id;
	
	@Column(name="speccode",
			length = 10,
			nullable = false,
			unique = true)
	private String specCode;

	@Column(name="specname",
			length = 50,
			nullable = false,
			unique = true)
	private String specName;
	
	@Column(name="specnote",
			length = 250,
			nullable = false)
	private String specNote;
	

}
