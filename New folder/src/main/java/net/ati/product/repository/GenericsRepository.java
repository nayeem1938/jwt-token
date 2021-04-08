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
import net.ati.product.model.entity.Generics;
import net.ati.product.model.entity.Identity;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface GenericsRepository extends JpaRepository<Generics, Identity> {
	
//	@Query(value = "select max(generic_id) from pm_generics", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_GENERICS_SEQ') from dual ", nativeQuery = true)
	Long lastIdValue();
	
//	@Query(value = "SELECT generics_id_seq.nextval from dual", nativeQuery = true)
//	Long sequenceIdValue();
	
	Generics findByGenericsNo(String genericNo);
	
	List<Generics> findByGenericsIdId(Long id);
	
	boolean existsByGenericsIdId(Long id);
	
	boolean existsByGenericsNo(String genericNo);
	
	List<Generics> findByGenericsIdRowLanguageCode(String language);

}
