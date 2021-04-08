/**
 * @Since Mar 29, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ati.product.model.dto.output.GenericsDetailsHeadDto;

/**
 * @author Nayeem
 *
 */

@Repository
public interface GenericsDetailsHeadRepository extends JpaRepository<GenericsDetailsHeadDto, BigDecimal> {
	
	public GenericsDetailsHeadDto findByHeadId(Integer id);
	
	public Boolean existsByHeadId(Integer id);

}
