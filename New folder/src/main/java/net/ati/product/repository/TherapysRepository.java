/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import net.ati.product.model.entity.Therapys;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface TherapysRepository extends JpaRepository<Therapys, Long> {
	
//	@Query(value = "select max(therapy_id) from pm_therapys", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_THERAPYS_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();
	
	Therapys findByTherapyNo(String therapyNo);
	
	boolean existsByTherapyNo(String therapyNo);
	
	List<Therapys> findByTherapyIdIdAndRecordShowFlag(Long id ,int showFlag);
	
	boolean existsByTherapyIdId(Long id);
	
	List<Therapys> findByTherapyIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	
	List<Therapys> findByRecordShowFlag(int showFlag);


}
