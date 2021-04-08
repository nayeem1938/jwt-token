/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.ati.product.common.exception.CustomDataIntegrityViolationException;
import net.ati.product.common.message.BaseResponse;
import net.ati.product.common.message.CustomMessage;
import net.ati.product.common.utils.CopyProperties;
import net.ati.product.model.dto.ProductByBrandAnotherLanguageDto;
import net.ati.product.model.dto.ProductByBrandDto;
import net.ati.product.model.entity.ProductByBrand;
import net.ati.product.repository.ProductByBrandRepository;
import net.ati.product.service.ProductByBrandService;

/**
 * @author Nayeemul
 *
 */

@Service
public class ProductByBrandServiceImpl implements ProductByBrandService {
	

	@Autowired
	private CopyProperties copyProperties;
	
	@Autowired
	private ProductByBrandRepository productByBrandRepository; 

	@Override
	public BaseResponse saveAndUpdateProductByBrand(ProductByBrandDto productByBrandDto) {
		ProductByBrand productByBrand = copyProperties.copyProductByBrandDtoToProductByBrandEntity(productByBrandDto);
		productByBrand.setProductByBrandNo(productByBrand.getProductByBrandId().getRowLanguageCode(), productByBrand.getProductByBrandId().getId());

		if (productByBrandDto.getProductByBrandId().getRowLanguageCode().equals("EN")
				&& productByBrandDto.getProductByBrandId().getId() == null) {

//			Long lastId = productByBrandRepository.lastIdValue();
//
//			if (lastId == null) {
//				Identity identity = new Identity();
//				identity.setId((long) 1);
//				identity.setRowLanguageCode(productByBrandDto.getProductByBrandId().getRowLanguageCode());
//				productByBrand.setProductByBrandId(identity);
//			} else {
//				productByBrand.getProductByBrandId().setId(productByBrandRepository.lastIdValue() + 1);
//			}
			
			productByBrand.getProductByBrandId().setId(productByBrandRepository.lastIdValue());

			productByBrand.setProductByBrandNo(productByBrand.getProductByBrandId().getRowLanguageCode(), productByBrand.getProductByBrandId().getId());

			try {
				productByBrandRepository.save(productByBrand);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (productByBrandDto.getProductByBrandId().getRowLanguageCode() != "EN"
				&& productByBrandRepository.existsByProductByBrandIdId(productByBrandDto.getProductByBrandId().getId()) == true
				&& productByBrandRepository.existsByProductByBrandNo(productByBrand.getProductByBrandNo()) == false) {

			try {
				productByBrandRepository.save(productByBrand);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (productByBrandRepository.existsByProductByBrandNo(productByBrandDto.getProductByBrandNo()) == true) {
			try {
				productByBrandRepository.save(productByBrand);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		}
		else if (productByBrandDto.getProductByBrandId().getRowLanguageCode() != "EN"
				&& productByBrandRepository.existsByProductByBrandIdId(productByBrandDto.getProductByBrandId().getId()) == true
				&& productByBrandRepository.existsByProductByBrandNo(productByBrand.getProductByBrandNo()) == true) {
			
			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		}
		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}
	}

	@Override
	public ProductByBrandDto findProductsByProductByBrandNo(String productByBrandNo) {
		ProductByBrand productByBrand = productByBrandRepository.findByProductByBrandNo(productByBrandNo);
		return copyProperties.copyProductByBrandEntityToProductByBrandDto(productByBrand);
	}

	@Override
	public List<ProductByBrandDto> findAllProductByBrand() {
		List<ProductByBrand> productByBrands = productByBrandRepository.findByRecordShowFlag(1);
		
		List<ProductByBrandDto> productByBrandDtos = new ArrayList<>();
		
		List<Long> idStore = new ArrayList<>();
		
		for(ProductByBrand productByBrand : productByBrands)
		{
			if(idStore.contains(productByBrand.getProductByBrandId().getId()))
			{
				
			}
			else {
				List<ProductByBrand> idBasedProductByBrands = productByBrands.stream().filter(productByBrand2 ->
				productByBrand.getProductByBrandId().getId().equals(productByBrand2.getProductByBrandId().getId())).collect(Collectors.toList());
				
				idStore.add(productByBrand.getProductByBrandId().getId());
				productByBrandDtos.add(setInFormat(idBasedProductByBrands));
			}
		}
		
		return productByBrandDtos;
		
	}

	@Override
	public List<ProductByBrandDto> findProductByBrandByLanguage(String language) {
		List<ProductByBrand> productByBrands = productByBrandRepository.findByProductByBrandIdRowLanguageCodeAndRecordShowFlag(language, 1);
		
		return productByBrands.stream().map(productByBrand ->
		copyProperties.copyProductByBrandEntityToProductByBrandDto(productByBrand)).collect(Collectors.toList());
	}

	@Override
	public ProductByBrandDto findProductByBrandById(Long id) {
		List<ProductByBrand> productByBrands = productByBrandRepository.findByProductByBrandIdIdAndRecordShowFlag(id, 1);
		return setInFormat(productByBrands);
	}
	
	
//	public List<ProductByBrandDto> recordShow(List<ProductByBrand> productByBrands)
//	{
//		List<ProductByBrand> showProductByBrands = productByBrands
//				  .stream()
//				  .filter(productByBrand -> productByBrand.getRecordShowFlag() == 1)
//				  .collect(Collectors.toList());
//		
//		return showProductByBrands.stream().map(productByBrand ->
//		copyProperties.copyProductByBrandEntityToProductByBrandDto(productByBrand)).collect(Collectors.toList());
//	}
	
	
	public ProductByBrandDto setInFormat(List<ProductByBrand> productByBrands)
	
	{
		ProductByBrandDto productByBrandDto = new ProductByBrandDto();
		
		List<ProductByBrandAnotherLanguageDto> anotherlanguageInfos = new ArrayList<>();
		
		for(ProductByBrand productbybrand: productByBrands)
		{
			if(productbybrand.getProductByBrandId().getRowLanguageCode().equals("EN"))
			{
				productByBrandDto = copyProperties.copyProductByBrandEntityToProductByBrandDto(productbybrand);
			}
			
			else {
//				GenericsDto genericsDto = copyProperties.copyGenericsEntityToGenericsDto(productbybrand.getGeneric());
				ProductByBrandAnotherLanguageDto anotherLanguageInfo = new 
						ProductByBrandAnotherLanguageDto(productbybrand.getProductByBrandId().getRowLanguageCode(), 
								productbybrand.getBrandName()/*, genericsDto*/);
				
				anotherlanguageInfos.add(anotherLanguageInfo);
			}
		}
		
		productByBrandDto.setAnotherLanguageInfos(anotherlanguageInfos);
		return productByBrandDto;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
