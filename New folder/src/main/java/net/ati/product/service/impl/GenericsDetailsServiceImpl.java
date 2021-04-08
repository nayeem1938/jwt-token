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
import net.ati.product.model.dto.GenericsDetailsAnotherLanguageInfoDto;
import net.ati.product.model.dto.GenericsDetailsDto;
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.model.dto.output.GenericsDetailsHeadDto;
import net.ati.product.model.dto.output.GenericsDetailsOutputDto;
import net.ati.product.model.entity.GenericsDetails;
import net.ati.product.repository.GenericsDetailsHeadRepository;
import net.ati.product.repository.GenericsDetailsRepository;
import net.ati.product.service.GenericsDetailsService;

/**
 * @author Nayeemul
 *
 */
@Service
public class GenericsDetailsServiceImpl implements GenericsDetailsService {

	@Autowired
	private GenericsDetailsRepository genericsDetailsRepository;
	
	@Autowired
	private GenericsDetailsHeadRepository headRepo;
	
	

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateGenericsDetails(GenericsDetailsDto dto) {
		GenericsDetails entity = copyProperties.copyGenericsDetailsDtoToGenericsDetailsEntity(dto);
		entity.setGenericsDetailsNo(entity.getGenericsDetailsId().getRowLanguageCode(), entity.getGenericsDetailsId().getId());

		

		if (dto.getGenericsDetailsId().getRowLanguageCode().equals("EN")
				&& dto.getGenericsDetailsId().getId() == null) {
			

//			Long lastId = genericsDetailsRepository.lastIdValue();
//			
//			if( lastId == null)
//			{
//				Identity identity = new Identity();
//				identity.setId((long) 1);
//				identity.setRowLanguageCode(dto.getGenericsDetailsId().getRowLanguageCode());
//				entity.setGenericsDetailsId(identity);				
//
//			}
//			else {
//				entity.getGenericsDetailsId().setId(lastId);
//			}
			
			entity.getGenericsDetailsId().setId(genericsDetailsRepository.lastIdValue());
			
			entity.setGenericsDetailsNo(entity.getGenericsDetailsId().getRowLanguageCode(),
					entity.getGenericsDetailsId().getId());


			try {
				genericsDetailsRepository.save(entity);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (dto.getGenericsDetailsId().getRowLanguageCode() != "EN"
				&& genericsDetailsRepository.existsByGenericsDetailsIdId(dto.getGenericsDetailsId().getId()) == true
				&& genericsDetailsRepository.existsByGenericsDetailsNo(entity.getGenericsDetailsNo()) == false) {

			try {
				genericsDetailsRepository.save(entity);
				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}

		}
		
		else if (genericsDetailsRepository.existsByGenericsDetailsNo(dto.getGenericsDetailsNo())) {

			try {
				genericsDetailsRepository.save(entity);
				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE,
						HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
			
			
		}
		else if (dto.getGenericsDetailsId().getRowLanguageCode() != "EN"
				&& genericsDetailsRepository.existsByGenericsDetailsIdId(dto.getGenericsDetailsId().getId()) == true
				&& genericsDetailsRepository.existsByGenericsDetailsNo(entity.getGenericsDetailsNo()) == true) {
			
			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		}
		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}
	}

	@Override
	public GenericsDetailsOutputDto findGenericsDetailsByGenericsDetailsNo(String genericsDetailsNo) {
		GenericsDetails entity = genericsDetailsRepository.findByGenericsDetailsNo(genericsDetailsNo);
		
		GenericsDetailsOutputDto dto = copyProperties.copyGenericsDetailsEntityToGenericsDetailsDto(entity);
		return dto;
	}

	@Override
	public List<GenericsDetailsOutputDto> findAllGenericsDetails() {
		List<GenericsDetails> genericsDetailsList = genericsDetailsRepository.findByRecordShowFlag(1);
		
		List<GenericsDetailsOutputDto> detailsDtos = new ArrayList<>();
		
		List<Long> idStore = new ArrayList<>();
		
		for (GenericsDetails genericDetails : genericsDetailsList)
		{
			if(idStore.contains(genericDetails.getGenericsDetailsId().getId()))
			{
				
			}
			else {
				List<GenericsDetails> anotherInfo = genericsDetailsList.stream().filter(genericDetails2 ->
				genericDetails2.getGenericsDetailsId().getId().equals(genericDetails.getGenericsDetailsId().getId()))
						.collect(Collectors.toList());
				idStore.add(genericDetails.getGenericsDetailsId().getId());
				detailsDtos.add(setInFormat(anotherInfo));
			}
		}
		
		return detailsDtos;

//		return entityList.stream().map(list ->
//		copyProperties.copyGenericsDetailsEntityToGenericsDetailsDto(list)).collect(Collectors.toList());
	}

	@Override
	public List<GenericsDetailsOutputDto> findGenericsDetailsByLanguage(String language) {
		List<GenericsDetails> genericsDetails = genericsDetailsRepository.findByGenericsDetailsIdRowLanguageCodeAndRecordShowFlag(language, 1);
		
		
		
		return genericsDetails.stream().map(genericsDetail -> 
		copyProperties.copyGenericsDetailsEntityToGenericsDetailsDto(genericsDetail)).collect(Collectors.toList());
	}

	@Override
	public GenericsDetailsOutputDto findGenericsDetailsById(Long id) {
		List<GenericsDetails> genericsDetails = genericsDetailsRepository.findByGenericsDetailsIdIdAndRecordShowFlag(id, 1);
		
		return setInFormat(genericsDetails);
	}
	
	
	public GenericsDetailsOutputDto setInFormat(List<GenericsDetails> showGenericsDetails) {

		GenericsDetailsOutputDto genericsDetailsDto = new GenericsDetailsOutputDto();
		
		List<GenericsDetailsAnotherLanguageInfoDto> infos = new ArrayList<>();
		
		for (GenericsDetails genericDetails : showGenericsDetails) {
			if(genericDetails.getGenericsDetailsId().getRowLanguageCode().equals("EN"))
			{
				genericsDetailsDto = copyProperties.copyGenericsDetailsEntityToGenericsDetailsDto(genericDetails);
			}
			else if(genericDetails.getGenericsDetailsId().getRowLanguageCode() != "EN"){
				
				GenericsDto genericsDto = copyProperties.copyGenericsEntityToGenericsDto(genericDetails.getGeneric());
				
				GenericsDetailsAnotherLanguageInfoDto grnDetAnotherLanDto = new GenericsDetailsAnotherLanguageInfoDto(genericDetails.getGenericsDetailsId().getRowLanguageCode(),
						genericDetails.getGenericAttributeHeadId(), genericDetails.getGenericHeadDetails() , genericsDto);
				
				infos.add(grnDetAnotherLanDto);	
			}
		}
		
		genericsDetailsDto.setAnotherLanguageInfo(infos);
		
		return genericsDetailsDto;
	}

	@Override
	public List<GenericsDetailsHeadDto> findAllGenericsDetailsHead() {
		
		return headRepo.findAll();
	}

}
















