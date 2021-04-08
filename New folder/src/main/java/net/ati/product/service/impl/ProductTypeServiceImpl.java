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
import net.ati.product.model.dto.AnotherDescriptionDto;
import net.ati.product.model.dto.AnotherLanguageInfoDto;
import net.ati.product.model.dto.ProductTypeDto;
import net.ati.product.model.entity.ProductType;
import net.ati.product.repository.ProductTypeRepository;
import net.ati.product.service.ProductTypeService;

/**
 * @author Nayeemul
 *
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateProductType(ProductTypeDto productTypeDto) {

		ProductType productType = copyProperties.copyProductTypesDtoToProductTypeEntity(productTypeDto);
		productType.setProductTypeNo(productType.getProductTypeId().getRowLanguageCode(),
				productType.getProductTypeId().getId());

		if (productTypeDto.getProductTypeId().getRowLanguageCode().equals("EN")
				&& productTypeDto.getProductTypeId().getId() == null) {

//			Long lastId = productTypeRepository.lastIdValue();
//
//			if (lastId == null) {
//				Identity identity = new Identity();
//				identity.setId((long) 1);
//				identity.setRowLanguageCode(productTypeDto.getProductTypeId().getRowLanguageCode());
//				productType.setProductTypeId(identity);
//			} else {
//				productType.getProductTypeId().setId(lastId + 1);
//			}

			productType.getProductTypeId().setId(productTypeRepository.lastIdValue());

			productType.setProductTypeNo(productType.getProductTypeId().getRowLanguageCode(),
					productType.getProductTypeId().getId());

			try {
				productTypeRepository.save(productType);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (productTypeDto.getProductTypeId().getRowLanguageCode() != "EN"
				&& productTypeRepository.existsByProductTypeIdId(productTypeDto.getProductTypeId().getId()) == true
				&& productTypeRepository.existsByProductTypeNo(productType.getProductTypeNo()) == false) {

			try {
				productTypeRepository.save(productType);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (productTypeRepository.existsByProductTypeNo(productTypeDto.getProductTypeNo()) == true) {
			try {
				productTypeRepository.save(productType);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (productTypeDto.getProductTypeId().getRowLanguageCode() != "EN"
				&& productTypeRepository.existsByProductTypeIdId(productTypeDto.getProductTypeId().getId()) == true
				&& productTypeRepository.existsByProductTypeNo(productType.getProductTypeNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		}

		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}
	}

	@Override
	public ProductTypeDto findProductTypeByProductTypeNo(String productTypeNo) {
		ProductType productType = productTypeRepository.findByProductTypeNo(productTypeNo);
		ProductTypeDto productTypeDto = copyProperties.copyProductTypeEntityToProductTypeDto(productType);

		return productTypeDto;
	}

	@Override
	public List<ProductTypeDto> findAllProductType() {
		List<ProductType> allProductTypes = productTypeRepository.findAll();
		
		List<ProductType> productTypes = recordShow(allProductTypes);
		
		List<ProductTypeDto> productTypeDtos = new ArrayList<>();
		
		List<Long> idStore = new ArrayList<>();
		
		for (ProductType productType : productTypes) {

			if(idStore.contains(productType.getProductTypeId().getId()))
			{
				
			}
			else {
				List<ProductType> types =  productTypes.stream().filter(productType2 -> productType2.getProductTypeId().getId()
						.equals(productType.getProductTypeId().getId()))
						.collect(Collectors.toList());

				idStore.add(productType.getProductTypeId().getId());
				productTypeDtos.add(setInFormat(types));
			}	
			
		}

		return productTypeDtos;
	}

	@Override
	public List<ProductTypeDto> findProductTypeByLanguage(String language) {
		List<ProductType> productTypes = productTypeRepository.findByProductTypeIdRowLanguageCode(language);

		return recordShow(productTypes).stream()
				.map(productType -> copyProperties.copyProductTypeEntityToProductTypeDto(productType))
				.collect(Collectors.toList());
	}

	@Override
	public ProductTypeDto findProductTypeById(Long id) {
		List<ProductType> productTypes = productTypeRepository.findByProductTypeIdId(id);
		return setInFormat(recordShow(productTypes));

	}

	public List<ProductType> recordShow(List<ProductType> productTypes) {
		return  productTypes.stream()
				.filter(showProductType -> showProductType.getRecordShowFlag() == 1).collect(Collectors.toList());
	}

	public ProductTypeDto setInFormat(List<ProductType> showProductTypes) {
		ProductTypeDto productTypeDto = new ProductTypeDto();

		List<AnotherLanguageInfoDto> infos = new ArrayList<>();

		for (ProductType productType : showProductTypes) {
			if (productType.getProductTypeId().getRowLanguageCode().equals("EN")) {
				productTypeDto = copyProperties.copyProductTypeEntityToProductTypeDto(productType);
			} else if (productType.getProductTypeId().getRowLanguageCode() != "EN") {

				AnotherDescriptionDto anotherDescriptionDto = new AnotherDescriptionDto(productType.getProductTypeName(),
						productType.getProductTypeDescription());
				AnotherLanguageInfoDto info = new AnotherLanguageInfoDto(productType.getProductTypeId().getRowLanguageCode(),
						anotherDescriptionDto);

				infos.add(info);
			}
		}
		productTypeDto.setAnotherLanguageInfos(infos);
		return productTypeDto;

	}
}
