/**
 * @Since Jan 25, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ati.product.model.dto.ProductMobileDetailsDto;

/**
 * @author Nayeem
 *
 */
public interface ProductMobileDetailsRepository extends JpaRepository<ProductMobileDetailsDto, BigDecimal> {
	
	List<ProductMobileDetailsDto> findByRowLanguageCode(String lan);
	ProductMobileDetailsDto findByProductNo(String No);

}
