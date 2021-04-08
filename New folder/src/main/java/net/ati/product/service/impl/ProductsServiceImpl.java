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

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.ati.product.common.exception.CustomDataIntegrityViolationException;
import net.ati.product.common.message.BaseResponse;
import net.ati.product.common.message.CustomMessage;
import net.ati.product.common.utils.CopyProperties;
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.model.dto.ProductMobileDetailsDto;
import net.ati.product.model.dto.ProductTypeDto;
import net.ati.product.model.dto.ProductUnitOfMeasurmentInfoDto;
import net.ati.product.model.dto.ProductsAnotherLanguageDto;
import net.ati.product.model.dto.ProductsDto;
import net.ati.product.model.dto.output.SingleProductMobileDetailsDto;
import net.ati.product.model.entity.ProductImage;
import net.ati.product.model.entity.Products;
import net.ati.product.repository.ProductImageRepository;
import net.ati.product.repository.ProductMobileDetailsRepository;
import net.ati.product.repository.ProductsRepository;
import net.ati.product.service.ProductsService;

/**
 * @author Nayeemul
 *
 */
@Service
public class ProductsServiceImpl implements ProductsService
{

	@Autowired
	private ProductsRepository productsRepository;
	
	@Resource
	private ProductMobileDetailsRepository prodRepo;
	
	@Autowired
	private ProductImageRepository imageRepository;

	@Autowired
	private CopyProperties copyProperties;

	@Override
	public BaseResponse saveAndUpdateProducts(ProductsDto productsDto) {
		Products products = copyProperties.copyProductsDtoToProductsEntity(productsDto);
		products.setProductNo(products.getProductId().getRowLanguageCode(), products.getProductId().getId());

		if (productsDto.getProductId().getId() == null
				&& productsDto.getProductId().getRowLanguageCode().equals("EN")) {

			
			products.getProductId().setId(productsRepository.lastIdValue());

			products.setProductNo(products.getProductId().getRowLanguageCode(), products.getProductId().getId());

			try {
				productsRepository.save(products);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}
		}

		else if (products.getProductId().getId() != null
				&& productsRepository.existsByProductIdId(products.getProductId().getId()) == true
				&& productsRepository.existsByProductNo(products.getProductNo()) == false) {

			try {
				productsRepository.save(products);
				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}

		}

		else if (productsRepository.existsByProductNo(productsDto.getProductNo()) == true) {

			try {
				productsRepository.save(products);
				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} 
		
		else if (products.getProductId().getId() != null
				&& productsRepository.existsByProductIdId(products.getProductId().getId()) == true
				&& productsRepository.existsByProductNo(products.getProductNo()) == true) {
					
					return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());
		}
		else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}

	}

	@Override
	public ProductsDto findProductsByProductNo(String productNo) {
		Products products = productsRepository.findByProductNo(productNo);

		ProductsDto productsDto = copyProperties.copyProductsEntityToProductsDto(products);
		
//		List<String> productImages = new ArrayList<>();
//		
//		productImages.add("https://www.google.com/imgres?imgurl=https%3A%2F%2Fmumbaimirror.indiatimes.com%2Fphoto%2F76949707.cms&imgrefurl=https%3A%2F%2Fmumbaimirror.indiatimes.com%2Fopinion%2Fcolumnists%2Fdr-altaf-patel%2Fsecret-societies-and-symbolism-in-medicine%2Farticleshow%2F76949708.cms&tbnid=A4eZpuhrbLYf_M&vet=12ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygBegUIARDTAQ..i&docid=uJrdrKxD9og-_M&w=1200&h=900&q=medicine%20images&ved=2ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygBegUIARDTAQ");
//		productImages.add("https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.guim.co.uk%2Fimg%2Fmedia%2F20491572b80293361199ca2fc95e49dfd85e1f42%2F0_236_5157_3094%2Fmaster%2F5157.jpg%3Fwidth%3D1200%26height%3D900%26quality%3D85%26auto%3Dformat%26fit%3Dcrop%26s%3D80ea7ebecd3f10fe721bd781e02184c3&imgrefurl=https%3A%2F%2Fwww.theguardian.com%2Fsociety%2F2018%2Faug%2F30%2Fmodern-medicine-major-threat-public-health&tbnid=pLbLUaP7TYSAmM&vet=12ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygCegUIARDVAQ..i&docid=FwatdcmuMtRACM&w=1200&h=900&q=medicine%20images&ved=2ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygCegUIARDVAQ");
//		productImages.add("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.outlookindia.com%2Fpublic%2Fuploads%2Farticles%2F2020%2F4%2F1%2Frajasthans-free-medicine-scheme-secures-top-rank.jpg&imgrefurl=https%3A%2F%2Fwww.outlookindia.com%2Fwebsite%2Fstory%2Findia-news-struggling-to-get-your-medicine-due-to-corona-lockdown-try-these-five-options%2F349826&tbnid=GA26Xxb61DdhnM&vet=12ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygDegUIARDXAQ..i&docid=4U5CWOZbaj8ucM&w=1000&h=525&q=medicine%20images&ved=2ahUKEwjb2LHekaruAhXc_3MBHYldBGEQMygDegUIARDXAQ");
		
		List<ProductImage> images = imageRepository.findByProductProductNo(productNo);
//		List<String> downloadUrl = new ArrayList<>();
		
		List<String> getUrl = new ArrayList<>();

		
		for(ProductImage image: images)
		{
//			downloadUrl.add(image.getImageDownLink());
//			getUrl.add(image.getImageGetLink());
			
			getUrl.add( ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/getFile/")
					.path(image.getImageLink()).toUriString());
//			downloadUrl.add(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
//					.path(image.getImageLink()).toUriString());

		}
		
//		productsDto.setProductImageDownloadUrl(downloadUrl);
		productsDto.setProductImageGetUrl(getUrl);
		
		
		return productsDto;
	}

	@Override
	public List<ProductsDto> findAllProducts() {

		List<Products> products = productsRepository.findByRecordShowFlag(1);

		return setAllInFormat(products);
				
	}

	@Override
	public List<ProductsDto> findProductsByLanguage(String language) {
		List<Products> products = productsRepository.findByProductIdRowLanguageCodeAndRecordShowFlag(language, 1);

		return copyToDto(products);
				
	}

	@Override
	public ProductsDto findProductsById(Long id) {
		List<Products> products = productsRepository.findByProductIdIdAndRecordShowFlag(id, 1);

		return setInFormat(products);
				
	}

	@Override
	public List<ProductsDto> findNewProduct() {
		List<Products> products = productsRepository.findByShowProductNewFlagAndRecordShowFlag(1, 1);

		return setAllInFormat(products);
				
	}
	
	public List<ProductsDto> copyToDto(List<Products> products)
	{	
		return products.stream().map(product -> copyProperties.copyProductsEntityToProductsDto(product))
				.collect(Collectors.toList());
	}
	
	
	public ProductsDto setInFormat(List<Products> products)
	{
		ProductsDto productsDto = new ProductsDto();
		
		List<ProductsAnotherLanguageDto> anotherLanguageInfos = new ArrayList<>();
		
		for(Products product : products)
		{
			if(product.getProductId().getRowLanguageCode().equals("EN"))
			{
				productsDto = copyProperties.copyProductsEntityToProductsDto(product);
			}
			else {
				GenericsDto genericsDto = copyProperties.copyGenericsEntityToGenericsDto(product.getGeneric());
				
				ProductTypeDto productTypeDto = copyProperties.copyProductTypeEntityToProductTypeDto(product.getProductType());
				
				ProductUnitOfMeasurmentInfoDto productUomDto = copyProperties.copyProductUomEntityToProductUomDto(product.getProductUnitOfMeasurment());
				
				ProductsAnotherLanguageDto anotherLanguageInfoDto = new ProductsAnotherLanguageDto();
				
				BeanUtils.copyProperties(product, anotherLanguageInfoDto);
				
				anotherLanguageInfoDto.setRowLanguageCode(product.getProductId().getRowLanguageCode());
				anotherLanguageInfoDto.setGeneric(genericsDto);
				anotherLanguageInfoDto.setProductType(productTypeDto);
				anotherLanguageInfoDto.setProductUnitOfMeasurment(productUomDto);
				
				anotherLanguageInfos.add(anotherLanguageInfoDto);
			}
			
			
		}
		
		productsDto.setAnotherLanguageinfo(anotherLanguageInfos);
		return productsDto;
		
	}
	
	public List<ProductsDto> setAllInFormat(List<Products> products)
	{
		List<ProductsDto> productsDtos = new ArrayList<>();
		
		List<Long> idStore = new ArrayList<>();
		
		for(Products product: products)
		{
			if(idStore.contains(product.getProductId().getId()))
			{
				
			}
			else {
				List<Products> idBasedProduct = products.stream().filter(product2 ->
				product2.getProductId().getId().equals(product.getProductId().getId()))
						.collect(Collectors.toList());
				idStore.add(product.getProductId().getId());
				productsDtos.add(setInFormat(idBasedProduct));
			}
		}
		
		return productsDtos;
	}

	@Override
	public List<ProductMobileDetailsDto> findAllProductForMobile() {
		return setThumbLink(prodRepo.findAll());
	}
	
	@Override
	public List<ProductMobileDetailsDto> findProductForMobileByLanguage(String lan) {
		return setThumbLink(prodRepo.findByRowLanguageCode(lan));
	}

	@Override
	public SingleProductMobileDetailsDto findProductForMobileByNo(String no) {
		
		ProductMobileDetailsDto detailsDto = prodRepo.findByProductNo(no);
		SingleProductMobileDetailsDto detailsDto2 = new SingleProductMobileDetailsDto();
		
		BeanUtils.copyProperties(detailsDto, detailsDto2);
		if(detailsDto.getThumbLink() == null) {
			detailsDto2.setThumbLink(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
					.path("noImage.png").toUriString());
		}
		else {
			detailsDto2.setThumbLink(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
					.path( detailsDto2.getThumbLink()).toUriString());
		}
		List<ProductImage> images = imageRepository.findByProductProductNo(no);
		List<String> downloadUrl = new ArrayList<>();
		for(ProductImage image: images)
		{
			downloadUrl.add(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
					.path(image.getImageLink()).toUriString());

		}
		detailsDto2.setImages(downloadUrl);
		
		return detailsDto2;
	}
	
	public List<ProductMobileDetailsDto> setThumbLink(List<ProductMobileDetailsDto> detailsDto)
	{
		for(ProductMobileDetailsDto detailsDto2: detailsDto)
		{
			
			if(detailsDto2.getThumbLink() == null) {
				detailsDto2.setThumbLink(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
						.path("noImage.png").toUriString());
			}
			else {
				detailsDto2.setThumbLink(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
						.path( detailsDto2.getThumbLink()).toUriString());
			}
		}
		
		
		return detailsDto;
	}

}





















