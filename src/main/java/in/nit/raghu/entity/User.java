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
@Table(name="usertab")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Long id;
	
	@Column(name="userdisplay_name")
	private String displayName;
	
	@Column(name="useruname")
	private String username;
	
	@Column(name="userpwd")
	private String password;
	
	@Column(name="userrole")
	private String role;
}
