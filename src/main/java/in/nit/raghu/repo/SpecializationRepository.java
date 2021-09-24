package in.nit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.raghu.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>  {
	//for excel export data 
   @Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
   public Integer getSpecCodeCount(String specCode);
   
   @Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")
   public Integer getSpecNameCount(String specName);
   
   ///solution for AJAX bug fix at edit time
   @Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
   public Integer getSpecCodeCountForEdit(String specCode,Long id);
   
   @Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName AND id!=:id")
   public Integer getSpecNameCountForEdit(String specName,Long id);
   
   //for module integration
   @Query("SELECT id,specName FROM Specialization ")
	List<Object[]> getSpecIdAndName();
   
}
