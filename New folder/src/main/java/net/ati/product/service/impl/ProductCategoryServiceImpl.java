/**
 * @Since Jan 21, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service.impl
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
import net.ati.product.model.dto.ProductCategoryDto;
import net.ati.product.model.dto.output.ProductCategoryAnotherLanDto;
import net.ati.product.model.dto.output.ProductCategoryOutputDto;
import net.ati.product.model.entity.ProductCategory;
import net.ati.product.repository.ProductCategoryRepository;
import net.ati.product.service.ProductCategoryService;

/**
 * @author Nayeem
 *
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private CopyProperties copyProperties;
	
	@Autowired
	private ProductCategoryRepository categoryRepository; 

	@Override
	public BaseResponse saveAndUpdateProductCategory(ProductCategoryDto productCategoryDto) {
		ProductCategory category = copyProperties.copyProductCategoryDtoToProductCategoryEntity(productCategoryDto);
		category.setCategoryNo(category.getCategoryId().getRowLanguageCode(), category.getCategoryId().getId());

		if (productCategoryDto.getCategoryId().getRowLanguageCode().equals("EN")
				&& productCategoryDto.getCategoryId().getId() == null) {

			Long id = categoryRepository.idValue();
			
			category.getCategoryId().setId(id);

			category.setCategoryNo(category.getCategoryId().getRowLanguageCode(), category.getCategoryId().getId());

			try {
				categoryRepository.save(category);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (productCategoryDto.getCategoryId().getRowLanguageCode() != "EN"
				&& categoryRepository.existsByCategoryIdId(productCategoryDto.getCategoryId().getId()) == true
				&& categoryRepository.existsByCategoryNo(category.getCategoryNo()) == false) {
			try {
				categoryRepository.save(category);

				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (categoryRepository.existsByCategoryNo(productCategoryDto.getCategoryNo()) == true) {
			try {
				categoryRepository.save(category);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (productCategoryDto.getCategoryId().getRowLanguageCode() != "EN"
				&& categoryRepository.existsByCategoryIdId(productCategoryDto.getCategoryId().getId()) == true
				&& categoryRepository.existsByCategoryNo(category.getCategoryNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		} else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}

	}

	@Override
	public ProductCategoryOutputDto findProductCategoryByProductCategoryNo(String categoryNo                       ) {
		
		return copyProperties.copyProductCategoryEntityToProductCategoryDto(categoryRepository.findByCategoryNo(categoryNo));
	}

	@Override
	public List<ProductCategoryOutputDto> findAllProductCategory() {
		List<ProductCategory> categories= categoryRepository.findByRecordShowFlag(1);

		List<ProductCategoryOutputDto> categoryDtos = new ArrayList<>();

		List<Long> idStore = new ArrayList<>();

		for (ProductCategory category : categories) {

			if (idStore.contains(category.getCategoryId().getId())) {

			} else {
				List<ProductCategory> idBasedCategory = categories.stream()
						.filter(category2 -> category2.getCategoryId().getId().equals(category.getCategoryId().getId()))
						.collect(Collectors.toList());

				idStore.add(category.getCategoryId().getId());
				categoryDtos.add(setInFormat(idBasedCategory));
			}

		}

		return categoryDtos;
	}

	@Override
	public List<ProductCategoryOutputDto> findProductCategoryByLanguage(String language) {
		
		List<ProductCategory> categories = categoryRepository.findByCategoryIdRowLanguageCodeAndRecordShowFlag(language, 1);
		
		return categories.stream().map(category -> copyProperties.copyProductCategoryEntityToProductCategoryDto(category))
				.collect(Collectors.toList());
	}

	@Override
	public ProductCategoryOutputDto findProductCategoryById(Long id) {
		return setInFormat(categoryRepository.findByCategoryIdIdAndRecordShowFlag(id, 1));
	}
	
	
	
	
	public ProductCategoryOutputDto setInFormat(List<ProductCategory> showProductCategory) {

		ProductCategoryOutputDto productCategoryDto = new ProductCategoryOutputDto();

		List<ProductCategoryAnotherLanDto> infos = new ArrayList<>();

		for (ProductCategory category : showProductCategory) {
			if (category.getCategoryId().getRowLanguageCode().equals("EN")) {
				productCategoryDto = copyProperties.copyProductCategoryEntityToProductCategoryDto(category);
			} else if (category.getCategoryId().getRowLanguageCode() != "EN") {

				ProductCategoryAnotherLanDto anotherLanDto = new ProductCategoryAnotherLanDto();
				anotherLanDto.setCategoryName(category.getCategoryName());
				anotherLanDto.setCategoryNo(category.getCategoryNo());
				
				if(category.getParentCategory() != null)
				{
				anotherLanDto.setParentCategoryName(category.getParentCategory().getCategoryName());
				anotherLanDto.setParentCategoryNo(category.getParentCategory().getCategoryNo());
				}
				anotherLanDto.setRowLanguageCode(category.getCategoryId().getRowLanguageCode());

				infos.add(anotherLanDto);
			}
		}

		productCategoryDto.setAnotherLanDtos(infos);

		return productCategoryDto;
	}



}
