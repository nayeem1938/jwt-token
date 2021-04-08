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
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.model.entity.Generics;
import net.ati.product.repository.GenericsRepository;
import net.ati.product.service.GenericsService;

/**
 * @author Nayeemul
 *
 */

@Service
public class GenericsServiceImpl implements GenericsService {

	@Autowired
	private GenericsRepository genericsRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateGenerics(GenericsDto genericsDto) {
		Generics generics = copyProperties.copyGenericsDtoToGenericsEntity(genericsDto);
		generics.setGenericsNo(generics.getGenericsId().getRowLanguageCode(), generics.getGenericsId().getId());

//		String sequence = genericsRepository.sequenceIdValue().toString();
//		String idMiddle = "0";
//
//		for (int i = 0; i <9- sequence.length(); i++) {
//
//			idMiddle = idMiddle + "0";
//		}
//
//		String orderMstId = new SimpleDateFormat("YYMM").format(new Date()) + idMiddle
//				+ sequence;

		if (genericsDto.getGenericsId().getRowLanguageCode().equals("EN")
				&& genericsDto.getGenericsId().getId() == null) {


			generics.getGenericsId().setId(genericsRepository.lastIdValue());

//			generics.getGenericsId().setId(Long.parseLong(orderMstId));
			generics.setGenericsNo(generics.getGenericsId().getRowLanguageCode(), generics.getGenericsId().getId());

			try {
				genericsRepository.save(generics);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (genericsDto.getGenericsId().getRowLanguageCode() != "EN"
				&& genericsRepository.existsByGenericsIdId(genericsDto.getGenericsId().getId()) == true
				&& genericsRepository.existsByGenericsNo(generics.getGenericsNo()) == false) {

			try {
				genericsRepository.save(generics);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (genericsRepository.existsByGenericsNo(genericsDto.getGenericsNo()) == true) {
			try {
				genericsRepository.save(generics);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (genericsDto.getGenericsId().getRowLanguageCode() != "EN"
				&& genericsRepository.existsByGenericsIdId(genericsDto.getGenericsId().getId()) == true
				&& genericsRepository.existsByGenericsNo(generics.getGenericsNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		}

		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}

	}

	@Override
	public GenericsDto findGenericsByGenericsNo(String genericNo) {
		Generics generics = genericsRepository.findByGenericsNo(genericNo);
		GenericsDto genericsDto = copyProperties.copyGenericsEntityToGenericsDto(generics);

		return genericsDto;
	}

	@Override
	public List<GenericsDto> findAllGenerics() {

		List<Generics> allGenerics = genericsRepository.findAll();

		List<Generics> generics = recordShow(allGenerics);

		List<GenericsDto> genericsDtos = new ArrayList<>();

		List<Long> idStore = new ArrayList<>();

		for (Generics generic : generics) {

			if (idStore.contains(generic.getGenericsId().getId())) {

			} else {
				List<Generics> anotherInfo = generics.stream()
						.filter(generic2 -> generic2.getGenericsId().getId().equals(generic.getGenericsId().getId()))
						.collect(Collectors.toList());

				idStore.add(generic.getGenericsId().getId());
				genericsDtos.add(setInFormat(anotherInfo));
			}

		}

		return genericsDtos;
	}

	@Override
	public List<GenericsDto> findGenericsByLanguage(String language) {
		List<Generics> generics = genericsRepository.findByGenericsIdRowLanguageCode(language);

		return recordShow(generics).stream().map(generic -> copyProperties.copyGenericsEntityToGenericsDto(generic))
				.collect(Collectors.toList());
	}

	@Override
	public GenericsDto findGenericsById(Long id) {
		List<Generics> generics = genericsRepository.findByGenericsIdId(id);

//		List<Generics> showGenerics = generics
//				  .stream()
//				  .filter(showGeneric -> (showGeneric.getRecordShowFlag() == 1))
//				  .collect(Collectors.toList());
//		
//		GenericsDto genericsDto = new GenericsDto();
//		
//		List<AnotherLanguageInfoDto> infos = new ArrayList<>();
//		
//		for (Generics generic : showGenerics) {
//			if(generic.getGenericsId().getRowLanguageCode().equals("EN"))
//			{
//				genericsDto = copyProperties.copyGenericsEntityToGenericsDto(generic);
//			}
//			else if(generic.getGenericsId().getRowLanguageCode() != "EN"){
//				
//				AnotherDescriptionDto anotherDescription = new AnotherDescriptionDto(generic.getGenericsName(), generic.getGenericNameByIngredientName());
//				AnotherLanguageInfoDto info = new AnotherLanguageInfoDto(generic.getGenericsId().getRowLanguageCode(), anotherDescription);
//				
//				infos.add(info);	
//			}
//		}
//		
//		genericsDto.setAnotherLanguageInfos(infos);

		return setInFormat(recordShow(generics));
	}

	public List<Generics> recordShow(List<Generics> generics) {
//		List<Generics> showGenerics = generics
//		  .stream()
//		  .filter(showGeneric -> showGeneric.getRecordShowFlag() == 1)
//		  .collect(Collectors.toList());

		return generics.stream().filter(showGeneric -> showGeneric.getRecordShowFlag() == 1)
				.collect(Collectors.toList());

	}

	public GenericsDto setInFormat(List<Generics> showGenerics) {

		GenericsDto genericsDto = new GenericsDto();

		List<AnotherLanguageInfoDto> infos = new ArrayList<>();

		for (Generics generic : showGenerics) {
			if (generic.getGenericsId().getRowLanguageCode().equals("EN")) {
				genericsDto = copyProperties.copyGenericsEntityToGenericsDto(generic);
			} else if (generic.getGenericsId().getRowLanguageCode() != "EN") {

				AnotherDescriptionDto anotherDescriptionDto = new AnotherDescriptionDto(generic.getGenericsName(),
						generic.getGenericNameByIngredientName());
				AnotherLanguageInfoDto info = new AnotherLanguageInfoDto(generic.getGenericsId().getRowLanguageCode(),
						anotherDescriptionDto);

				infos.add(info);
			}
		}

		genericsDto.setAnotherLanguageInfos(infos);

		return genericsDto;
	}

}
