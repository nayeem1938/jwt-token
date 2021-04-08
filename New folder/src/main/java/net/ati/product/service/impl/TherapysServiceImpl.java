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
import net.ati.product.model.dto.TherapysDto;
import net.ati.product.model.entity.Therapys;
import net.ati.product.repository.TherapysRepository;
import net.ati.product.service.TherapysService;

/**
 * @author Nayeemul
 *
 */
@Service
public class TherapysServiceImpl implements TherapysService {

	@Autowired
	private TherapysRepository therapysRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateTherapys(TherapysDto therapysDto) {
		Therapys therapys = copyProperties.copyTherapysDtoToTherapysEntity(therapysDto);
		therapys.setTherapyNo(therapys.getTherapyId().getRowLanguageCode(), therapys.getTherapyId().getId());

		if (therapysDto.getTherapyId().getRowLanguageCode().equals("EN")
				&& therapysDto.getTherapyId().getId() == null) {

//			Long lastId = therapysRepository.lastIdValue();
//
//			if (lastId == null) {
//				Identity identity = new Identity();
//				identity.setId((long) 1);
//				identity.setRowLanguageCode(therapysDto.getTherapyId().getRowLanguageCode());
//				therapys.setTherapyId(identity);
//			} else {
//				therapys.getTherapyId().setId(therapysRepository.lastIdValue() + 1);
//			}

			therapys.getTherapyId().setId(therapysRepository.lastIdValue());

			therapys.setTherapyNo(therapys.getTherapyId().getRowLanguageCode(), therapys.getTherapyId().getId());

			try {
				therapysRepository.save(therapys);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (therapysDto.getTherapyId().getRowLanguageCode() != "EN"
				&& therapysRepository.existsByTherapyIdId(therapysDto.getTherapyId().getId()) == true
				&& therapysRepository.existsByTherapyNo(therapys.getTherapyNo()) == false) {
			try {
				therapysRepository.save(therapys);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (therapysRepository.existsByTherapyNo(therapysDto.getTherapyNo()) == true) {
			try {
				therapysRepository.save(therapys);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (therapysDto.getTherapyId().getRowLanguageCode() != "EN"
				&& therapysRepository.existsByTherapyIdId(therapysDto.getTherapyId().getId()) == true
				&& therapysRepository.existsByTherapyNo(therapys.getTherapyNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		} else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}
	}

	@Override
	public TherapysDto findTherapysByTherapyNo(String therapyNo) {
		Therapys therapys = therapysRepository.findByTherapyNo(therapyNo);
		return copyProperties.copyTherapysEntityToTherapysDto(therapys);
	}

	@Override
	public List<TherapysDto> findAllTherapys() {

		List<Therapys> therapys = therapysRepository.findByRecordShowFlag(1);

		List<TherapysDto> therapysDtos = new ArrayList<>();

		List<Long> idStore = new ArrayList<>();

		for (Therapys therapy : therapys) {

			if (idStore.contains(therapy.getTherapyId().getId())) {

			} else {
				List<Therapys> types = therapys.stream()
						.filter(therapy2 -> therapy2.getTherapyId().getId().equals(therapy.getTherapyId().getId()))
						.collect(Collectors.toList());

				idStore.add(therapy.getTherapyId().getId());
				therapysDtos.add(setInFormat(types));
			}

		}

		return therapysDtos;

	}

	@Override
	public List<TherapysDto> findByTherapysLanguage(String language) {
		List<Therapys> therapys = therapysRepository.findByTherapyIdRowLanguageCodeAndRecordShowFlag(language, 1);
		return therapys.stream().map(therapy -> copyProperties.copyTherapysEntityToTherapysDto(therapy))
				.collect(Collectors.toList());
	}

	@Override
	public TherapysDto findByTherapysId(Long id) {
		List<Therapys> therapys = therapysRepository.findByTherapyIdIdAndRecordShowFlag(id, 1);
		return setInFormat(therapys);
	}

	public TherapysDto setInFormat(List<Therapys> showTherapys) {

		TherapysDto therapysDto = new TherapysDto();

		List<AnotherLanguageInfoDto> infos = new ArrayList<>();

		for (Therapys therapy : showTherapys) {
			if (therapy.getTherapyId().getRowLanguageCode().equals("EN")) {
				therapysDto = copyProperties.copyTherapysEntityToTherapysDto(therapy);
			} else if (therapy.getTherapyId().getRowLanguageCode() != "EN") {

				AnotherDescriptionDto anotherDescriptionDto = new AnotherDescriptionDto(therapy.getTherapyName(),
						therapy.getTherapyDescription());
				AnotherLanguageInfoDto info = new AnotherLanguageInfoDto(therapy.getTherapyId().getRowLanguageCode(),
						anotherDescriptionDto);

				infos.add(info);
			}
		}

		therapysDto.setAnotherLanguageInfos(infos);

		return therapysDto;
	}

}
