/**
 * @Since Feb 17, 2021
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
import net.ati.product.model.dto.GenericsProdByBrandDto;
import net.ati.product.model.entity.GenericsProdByBrand;
import net.ati.product.repository.GenericsProdByBrandRepository;
import net.ati.product.service.GenericsProdByBrandService;

/**
 * @author Nayeem
 *
 */

@Service
public class GenericsProdByBrandServiceImpl implements GenericsProdByBrandService {
	
	@Autowired
	private GenericsProdByBrandRepository genericsProdByBrandRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateGenericsProdByBrand(GenericsProdByBrandDto genericsProdByBrandDto) {
		GenericsProdByBrand genericsProdByBrand = copyProperties.copyGenericsProdByBrandDtoToGenericsProdByBrandEntity(genericsProdByBrandDto);

		if (genericsProdByBrand.getGenericsProdByBrandId() == null) {
			genericsProdByBrand.setGenericsProdByBrandId(genericsProdByBrandRepository.lastIdValue());
			try {
				genericsProdByBrandRepository.save(genericsProdByBrand);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}
		}

		else {
			GenericsProdByBrand genericsProdByBrandCheck = genericsProdByBrandRepository
					.findById(genericsProdByBrandDto.getGenericsProdByBrandId())
					.orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));

			if (genericsProdByBrand.getGeneric().getGenericsNo() == genericsProdByBrandCheck.getGeneric().getGenericsNo()
					&& genericsProdByBrand.getProductByBrand().getProductByBrandNo() == genericsProdByBrandCheck.getProductByBrand().getProductByBrandNo()) {
				try {
					genericsProdByBrandRepository.save(genericsProdByBrand);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
			else {
				genericsProdByBrandRepository.deleteById(genericsProdByBrand.getGenericsProdByBrandId());
				try {
					genericsProdByBrandRepository.save(genericsProdByBrand);
					return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
				}
			}
		}

	}

	@Override
	public List<GenericsProdByBrandDto> findAllGenericsProdByBrand() {
		List<GenericsProdByBrand> genericsProdByBrand = genericsProdByBrandRepository.findByRecordShowFlag(1);

		return genericsProdByBrand.stream()
				.map(genThearpy -> copyProperties.copyGenericsProdByBrandEntityToGenericsProdByBrandDto(genThearpy))
				.collect(Collectors.toList());
	}

	@Override
	public GenericsProdByBrandDto findGenericsProdByBrandById(Long id) {
		
		GenericsProdByBrand genericsProdByBrand = genericsProdByBrandRepository
				.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
		if(genericsProdByBrand.getRecordShowFlag() == 1)
		{
			return copyProperties.copyGenericsProdByBrandEntityToGenericsProdByBrandDto(genericsProdByBrand);
		}
		else {
			throw new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND);
		}
		

		
	}

}
