/**
 * @Since Jan 21, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.ati.product.model.entity.ProductImage;

/**
 * @author Nayeem
 *
 */

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PRODIMGS_SEQ') from dual", nativeQuery = true)
	Long idValue();

	boolean existsByImageNo(String imageNo);
	boolean existsByImageIdId(Long imageId);
	
	List<ProductImage> findByProductProductNo(String productNo);

}
