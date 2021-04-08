/**
 * @Since Feb 18, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service.impl
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
import net.ati.product.model.dto.TherapyTreeDto;
import net.ati.product.model.entity.TherapyTree;
import net.ati.product.repository.TherapyTreeRepository;
import net.ati.product.service.TherapyTreeService;

/**
 * @author Nayeem
 *
 */

@Service
public class TherapyTreeServiceImpl implements TherapyTreeService {
	
	@Autowired
	private TherapyTreeRepository therapyTreeRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateTherapyTree(TherapyTreeDto therapyTreeDto) {
		TherapyTree therapyTree = copyProperties.copyTherapyTreeDtoToTherapyTreeEntity(therapyTreeDto);

		if (therapyTree.getTherapyTreeId() == null) {
			therapyTree.setTherapyTreeId(therapyTreeRepository.lastIdValue());
			try {
				therapyTreeRepository.save(therapyTree);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}
		}

		else {
			TherapyTree therapyTreeCheck = therapyTreeRepository
					.findById(therapyTreeDto.getTherapyTreeId())
					.orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));

			if (therapyTree.getTherapys().getTherapyNo() == therapyTreeCheck.getTherapys().getTherapyNo()
					&& therapyTree.getTherapys().getTherapyNo() == therapyTreeCheck.getTherapys().getTherapyNo()) {
				try {
					therapyTreeRepository.save(therapyTree);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
			else {
				therapyTreeRepository.deleteById(therapyTree.getTherapyTreeId());
				therapyTree.setCreatedAt(therapyTreeCheck.getCreatedAt());
				therapyTree.setCreatedBy(therapyTreeCheck.getCreatedBy());
				try {
					therapyTreeRepository.save(therapyTree);
					therapyTreeRepository.save(therapyTree);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
		}

	}

	@Override
	public List<TherapyTreeDto> findAllTherapyTree() {
		List<TherapyTree> therapyTree = therapyTreeRepository.findByRecordShowFlag(1);

		return therapyTree.stream()
				.map(genThearpy -> copyProperties.copyTherapyTreeEntityToTherapyTreeDto(genThearpy))
				.collect(Collectors.toList());
	}

	@Override
	public TherapyTreeDto findTherapyTreeById(Long id) {
		TherapyTree therapyTree = therapyTreeRepository
				.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
		if(therapyTree.getRecordShowFlag() == 1)
		{
			return copyProperties.copyTherapyTreeEntityToTherapyTreeDto(therapyTree);
		}
		else {
			throw new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND);
		}
	}

}
