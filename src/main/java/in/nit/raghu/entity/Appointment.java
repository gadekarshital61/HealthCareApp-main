package in.nit.raghu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="appointmenttab")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      @Column(name="id")
      private Long id;
      
      @DateTimeFormat(pattern = "MM/dd/yyyy")
      @Temporal(TemporalType.DATE)
      @Column(name="date")
      private Date date;
      
      @Column(name="noofaptslots")
      private Integer noOfSlots;
      
      @Column(name="aptdetails")
      private String details;
      
      
      @Column (name="aptfee")
      private Double fee;
      
      //----------Association Mapping------------------
  	  @ManyToOne
      @JoinColumn(name="doctor_id_fk")
  	  private Doctor doctor; //HAS-A
}
