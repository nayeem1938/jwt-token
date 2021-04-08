/**
 * @Since Feb 18, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ati.product.model.entity.TherapyTree;

/**
 * @author Nayeem
 *
 */
public interface TherapyTreeRepository extends JpaRepository<TherapyTree, Long> {

	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_THXTREES_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();

	boolean existsByTherapyTreeId(Long id);
	
	TherapyTree findByTherapyTreeIdAndRecordShowFlag(Long id ,int showFlag);

	List<TherapyTree> findByRecordShowFlag(int showFlag);
}
