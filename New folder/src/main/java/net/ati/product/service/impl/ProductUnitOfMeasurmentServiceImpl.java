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
import net.ati.product.model.dto.ProductUnitOfMeasurmentInfoDto;
import net.ati.product.model.dto.ProductUomAnotherLanguageinfo;
import net.ati.product.model.entity.ProductUnitOfMeasurmentInfo;
import net.ati.product.repository.ProductUnitOfMeasurmentInfoRepository;
import net.ati.product.service.ProductUnitOfMeasurmentService;

/**
 * @author Nayeemul
 *
 */

@Service
public class ProductUnitOfMeasurmentServiceImpl implements ProductUnitOfMeasurmentService {

	@Autowired
	private ProductUnitOfMeasurmentInfoRepository productUomRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateProductUom(ProductUnitOfMeasurmentInfoDto productUomDto) {
		ProductUnitOfMeasurmentInfo productUom = copyProperties.copyProductUomDtoToProductUomEntity(productUomDto);
		productUom.setProductUomNo(productUomDto.getProductUomId().getRowLanguageCode(),
				productUomDto.getProductUomId().getId());

		if (productUomDto.getProductUomId().getRowLanguageCode().equals("EN")
				&& productUomDto.getProductUomId().getId() == null) {

//			Long lastId = productUomRepository.lastIdValue();
//			
//			if( lastId == null)
//			{
//				Identity identity = new Identity();
//				identity.setId((long) 1);
//				identity.setRowLanguageCode(productUomDto.getProductUomId().getRowLanguageCode());
//				productUom.setProductUomId(identity);				
//
//			}
//			else {
//				productUom.getProductUomId().setId(lastId + 1);
//			
//			}

			productUom.getProductUomId().setId(productUomRepository.lastIdValue());
			productUom.setProductUomNo(productUom.getProductUomId().getRowLanguageCode(),
					productUom.getProductUomId().getId());

			try {
				productUomRepository.save(productUom);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (productUomDto.getProductUomId().getRowLanguageCode() != "EN"
				&& productUomRepository.existsByProductUomIdId(productUomDto.getProductUomId().getId()) == true
				&& productUomRepository.existsByProductUomNo(productUom.getProductUomNo()) == false) {

			try {
				productUomRepository.save(productUom);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (productUomRepository.existsByProductUomNo(productUom.getProductUomNo()) == true) {
			try {
				productUomRepository.save(productUom);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (productUomDto.getProductUomId().getRowLanguageCode() != "EN"
				&& productUomRepository.existsByProductUomIdId(productUomDto.getProductUomId().getId()) == true
				&& productUomRepository.existsByProductUomNo(productUom.getProductUomNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		}

		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}

	}

	@Override
	public ProductUnitOfMeasurmentInfoDto findUomByUomNo(String uomNo) {

		ProductUnitOfMeasurmentInfo productUom = productUomRepository.findByProductUomNo(uomNo);

		ProductUnitOfMeasurmentInfoDto productUomDto = copyProperties.copyProductUomEntityToProductUomDto(productUom);

		return productUomDto;
	}

	@Override
	public List<ProductUnitOfMeasurmentInfoDto> findAllUom() {
		List<ProductUnitOfMeasurmentInfo> productUoms = productUomRepository.findByRecordShowFlag(1);
		
		List<ProductUnitOfMeasurmentInfoDto> measurmentInfoDtos = new ArrayList<>();
		
		List<Long> idStore = new ArrayList<>();
		
		for(ProductUnitOfMeasurmentInfo productUom : productUoms)
		{
			if(idStore.contains(productUom.getProductUomId().getId()))
			{
				
			}
			else {
				List<ProductUnitOfMeasurmentInfo> idBasedProductUom = productUoms.stream().filter(productUom2 ->
				productUom2.getProductUomId().getId().equals(productUom.getProductUomId().getId())).collect(Collectors.toList());
					
				idStore.add(productUom.getProductUomId().getId());
				measurmentInfoDtos.add(setInFormat(idBasedProductUom));
				
			}
			
		}
		
		return measurmentInfoDtos;
//		return list.stream().map(productUom -> copyProperties.copyProductUomEntityToProductUomDto(productUom))
//				.collect(Collectors.toList());
	}

	@Override
	public List<ProductUnitOfMeasurmentInfoDto> findUomByLanguage(String language) {
		List<ProductUnitOfMeasurmentInfo> list = productUomRepository
				.findByProductUomIdRowLanguageCodeAndRecordShowFlag(language, 1);

		return list.stream().map(productUom -> copyProperties.copyProductUomEntityToProductUomDto(productUom))
				.collect(Collectors.toList());
	}

	@Override
	public ProductUnitOfMeasurmentInfoDto findUomById(Long id) {
		List<ProductUnitOfMeasurmentInfo> list = productUomRepository.findByProductUomIdIdAndRecordShowFlag(id, 1);

		return setInFormat(list);
	}
	
	
	public ProductUnitOfMeasurmentInfoDto setInFormat(List<ProductUnitOfMeasurmentInfo> productUoms)
	{

		ProductUnitOfMeasurmentInfoDto productUomDto = new ProductUnitOfMeasurmentInfoDto();
		
		List<ProductUomAnotherLanguageinfo> anotherLanguageinfos = new ArrayList<>();
		
		for (ProductUnitOfMeasurmentInfo productUom : productUoms) {
			if(productUom.getProductUomId().getRowLanguageCode().equals("EN"))
			{
				productUomDto = copyProperties.copyProductUomEntityToProductUomDto(productUom);
			}
			else {
				ProductUomAnotherLanguageinfo anotherLanguageinfo = new ProductUomAnotherLanguageinfo(
						productUom.getProductUomId().getRowLanguageCode(), productUom.getUnitOfMeasurmentName(),
						productUom.getUnitOfMeasurmentComment(), productUom.getProductStrengthFlag());
				
				anotherLanguageinfos.add(anotherLanguageinfo);
			}
		}
		
		productUomDto.setAnotherLanguageinfos(anotherLanguageinfos);
		
		return productUomDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
