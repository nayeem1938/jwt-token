/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.ati.product.common.exception.CustomDataIntegrityViolationException;
import net.ati.product.common.exception.RecordNotFoundException;
import net.ati.product.common.message.BaseResponse;
import net.ati.product.common.message.CustomMessage;
import net.ati.product.common.utils.CopyProperties;
import net.ati.product.model.dto.GenericTherapyDto;
import net.ati.product.model.entity.GenericTherapy;
import net.ati.product.repository.GenericTherapyRepository;
import net.ati.product.service.GenericTherapyService;

/**
 * @author Nayeemul
 *
 */
@Service
public class GenericTherapyServiceImpl implements GenericTherapyService {

	@Autowired
	private GenericTherapyRepository genericTherapyRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateGenericTherapy(GenericTherapyDto genericTherapyDto) {

		GenericTherapy genericTherapy = copyProperties.copyGenericTherapyDtoToGenericTherapyEntity(genericTherapyDto);

		if (genericTherapy.getGenericTherapyId() == null) {
			genericTherapy.setGenericTherapyId(genericTherapyRepository.lastIdValue());
			try {
				genericTherapyRepository.save(genericTherapy);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}
		}

		else {
			GenericTherapy genericTherapyCheck = genericTherapyRepository
					.findById(genericTherapyDto.getGenericTherapyId())
					.orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));

			if (genericTherapy.getGeneric().getGenericsNo() == genericTherapyCheck.getGeneric().getGenericsNo()
					&& genericTherapy.getTherapy().getTherapyNo() == genericTherapyCheck.getTherapy().getTherapyNo()) {
				try {
					genericTherapyRepository.save(genericTherapy);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
			else {
				genericTherapyRepository.deleteById(genericTherapy.getGenericTherapyId());
				try {
					genericTherapyRepository.save(genericTherapy);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
		}

	}

//	@Override
//	public BaseResponse saveAndUpdateGenericTherapy(GenericTherapyDto dto) {
//		GenericTherapy entity = copyProperties.copyGenericTherapyDtoToGenericTherapyEntity(dto);
//		entity.setGenericTherapyNo(entity.getGenericTherapyId().getRowLanguageCode(),
//				entity.getGenericTherapyId().getId());
//
//		if (dto.getGenericTherapyId().getRowLanguageCode().equals("EN") && dto.getGenericTherapyId().getId() == null) {
//
//			entity.getGenericTherapyId().setId(genericTherapyRepository.lastIdValue());
//
//			entity.setGenericTherapyNo(entity.getGenericTherapyId().getRowLanguageCode(),
//					entity.getGenericTherapyId().getId());
//
//			try {
//				genericTherapyRepository.save(entity);
//				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
//			} catch (DataIntegrityViolationException e) {
//				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
//			}
//
//		}
//
//		else if (dto.getGenericTherapyId().getRowLanguageCode() != "EN"
//				&& genericTherapyRepository.existsByGenericTherapyIdId(dto.getGenericTherapyId().getId()) == true
//				&& genericTherapyRepository.existsByGenericTherapyNo(entity.getGenericTherapyNo()) == false) {
//
//			try {
//				genericTherapyRepository.save(entity);
//				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
//						HttpStatus.CREATED.value());
//			} catch (DataIntegrityViolationException e) {
//				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
//			}
//
//		}
//
//		else if (genericTherapyRepository.existsByGenericTherapyNo(entity.getGenericTherapyNo())) {
//
//			try {
//				genericTherapyRepository.save(entity);
//				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
//			} catch (DataIntegrityViolationException e) {
//				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
//			}
//
//		} else if (dto.getGenericTherapyId().getRowLanguageCode() != "EN"
//				&& genericTherapyRepository.existsByGenericTherapyIdId(dto.getGenericTherapyId().getId()) == true
//				&& genericTherapyRepository.existsByGenericTherapyNo(dto.getGenericTherapyNo()) == true) {
//			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());
//
//		}
//
//		else {
//			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());
//
//		}
//	}

//	@Override
//	public GenericTherapyDto findGenericTherapyByGenericTherapyNo(String genericTherapyNo) {
//		GenericTherapy entity = genericTherapyRepository.findByGenericTherapyNo(genericTherapyNo);
//
//		GenericTherapyDto dto = copyProperties.copyGenericTherapyEntityToGenericTherapyDto(entity);
//
//		return dto;
//	}

	@Override
	public List<GenericTherapyDto> findAllGenericTherapy() {
		List<GenericTherapy> genericTherapies = genericTherapyRepository.findByRecordShowFlag(1);
//		List<GenericTherapyDto> genericTherapyDtos = new ArrayList<>();
//		List<Long> idStore = new ArrayList<>();
//
//		for(GenericTherapy genericTherapy : genericTherapies)
//		{
//			if(idStore.contains(genericTherapy.getGenericTherapyId().getId()))
//			{
//				
//			}
//			else {
//				List<GenericTherapy> idBasedList = genericTherapies.stream().filter(genericTherapy2 ->
//					genericTherapy2.getGenericTherapyId().getId().equals(genericTherapy.getGenericTherapyId().getId()))
//						.collect(Collectors.toList());
//				idStore.add(genericTherapy.getGenericTherapyId().getId());
//				
//				genericTherapyDtos.add(setInFormat(idBasedList));
//			}
//		}

		return genericTherapies.stream()
				.map(genThearpy -> copyProperties.copyGenericTherapyEntityToGenericTherapyDto(genThearpy))
				.collect(Collectors.toList());

	}

//	@Override
//	public List<GenericTherapyDto> findGenericTherapyByLanguage(String language) {
//		List<GenericTherapy> genericTherapies = genericTherapyRepository
//				.findByGenericTherapyIdRowLanguageCodeAndRecordShowFlag(language, 1);
//		return recordShow(genericTherapies);
//	}

	@Override
	public GenericTherapyDto findGenericTherapyById(Long id) {
		GenericTherapy genericTherapies = genericTherapyRepository.findByGenericTherapyIdAndRecordShowFlag(id, 1);
		if(genericTherapies.getGeneric() == null)
		{
			throw new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND);
		}
		return copyProperties.copyGenericTherapyEntityToGenericTherapyDto(genericTherapies);
	}

//	public List<GenericTherapyDto> recordShow(List<GenericTherapy> genericTherapies) {
//		List<GenericTherapy> showGenericTheraies = genericTherapies.stream()
//				.filter(genericTherapie -> genericTherapie.getRecordShowFlag() == 1).collect(Collectors.toList());
//
//		return showGenericTheraies.stream()
//				.map(list -> copyProperties.copyGenericTherapyEntityToGenericTherapyDto(list))
//				.collect(Collectors.toList());
//	}

//	public GenericTherapyDto setInFormat(List<GenericTherapy> genericTherapys) 
//	{
//		GenericTherapyDto genericTherapyDto = new GenericTherapyDto();
//		
//		List<GenericTherapyAnotherLanguageDto> anotherLanguageDtos = new ArrayList<>();
//		for (GenericTherapy genericTherapy : genericTherapys) {
//			if(genericTherapy.getGenericTherapyId().getRowLanguageCode().equals("EN"))
//			{
//				genericTherapyDto = copyProperties.copyGenericTherapyEntityToGenericTherapyDto(genericTherapy);
//			}
//			
//			else {
//				GenericsDto genericDto = copyProperties.copyGenericsEntityToGenericsDto(genericTherapy.getGeneric());
//				TherapysDto therapysDto = copyProperties.copyTherapysEntityToTherapysDto(genericTherapy.getTherapy());
//				
////				List<GenericTherapy> anotherLanguageDto = genericTherapys.stream().filter(genericTherapy2 ->
////				genericTherapy2.getGenericTherapyId().getId().equals(genericTherapy.getGenericTherapyId().getId())).collect(Collectors.toList());
//				
//				GenericTherapyAnotherLanguageDto anotherLanguageDto = new GenericTherapyAnotherLanguageDto(genericTherapy.getGenericTherapyId().getRowLanguageCode(),
//						genericDto, therapysDto);
//				
//				anotherLanguageDtos.add(anotherLanguageDto);
//			}
//		}
//		
//		
//		genericTherapyDto.setAnotherLanguageInfo(anotherLanguageDtos);
//		
//		return genericTherapyDto;
//	
//		
//	}

}
