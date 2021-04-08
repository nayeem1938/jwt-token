/**
 * 
 */
package net.ati.product.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.ati.product.common.exception.RecordNotFoundException;
import net.ati.product.common.message.CustomMessage;
import net.ati.product.model.dto.GenericTherapyDto;
import net.ati.product.model.dto.GenericsDetailsDto;
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.model.dto.GenericsProdByBrandDto;
import net.ati.product.model.dto.IdentityDto;
import net.ati.product.model.dto.ProductByBrandDto;
import net.ati.product.model.dto.ProductCategoryDto;
import net.ati.product.model.dto.ProductImageDto;
import net.ati.product.model.dto.ProductTypeDto;
import net.ati.product.model.dto.ProductUnitOfMeasurmentInfoDto;
import net.ati.product.model.dto.ProductsDto;
import net.ati.product.model.dto.TherapyTreeDto;
import net.ati.product.model.dto.TherapysDto;
import net.ati.product.model.dto.output.GenericsDetailsHeadDto;
import net.ati.product.model.dto.output.GenericsDetailsOutputDto;
import net.ati.product.model.dto.output.ProductCategoryOutputDto;
import net.ati.product.model.entity.GenericTherapy;
import net.ati.product.model.entity.Generics;
import net.ati.product.model.entity.GenericsDetails;
import net.ati.product.model.entity.GenericsProdByBrand;
import net.ati.product.model.entity.Identity;
import net.ati.product.model.entity.ProductByBrand;
import net.ati.product.model.entity.ProductCategory;
import net.ati.product.model.entity.ProductImage;
import net.ati.product.model.entity.ProductType;
import net.ati.product.model.entity.ProductUnitOfMeasurmentInfo;
import net.ati.product.model.entity.Products;
import net.ati.product.model.entity.TherapyTree;
import net.ati.product.model.entity.Therapys;
import net.ati.product.repository.GenericsDetailsHeadRepository;
import net.ati.product.repository.GenericsRepository;
import net.ati.product.repository.ProductByBrandRepository;
import net.ati.product.repository.ProductCategoryRepository;
import net.ati.product.repository.ProductImageRepository;
import net.ati.product.repository.ProductTypeRepository;
import net.ati.product.repository.ProductUnitOfMeasurmentInfoRepository;
import net.ati.product.repository.ProductsRepository;
import net.ati.product.repository.TherapyTreeRepository;
import net.ati.product.repository.TherapysRepository;

/**
 * @author Nayeemul
 * @since 01-2021
 * @version 001
 *
 */

@Service
public class CopyProperties {
	
	@Autowired
	private GenericsRepository genericsRepository;
	
	@Autowired
	private TherapysRepository therapysRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private ProductUnitOfMeasurmentInfoRepository productUomRepository;
	
	@Autowired 
	private ProductsRepository productsRepository;
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	
	@Autowired
	private ProductByBrandRepository productByBrandRepository;
	
	@Autowired
	private TherapyTreeRepository therapyTreeRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	
	@Autowired
	private GenericsDetailsHeadRepository genericsDetailsHeadRepo;
	
	
	/**
	 * @param generics
	 * @return
	 * 
	 * @category Generics
	 */
	
	public GenericsDto copyGenericsEntityToGenericsDto(Generics generics) {
		GenericsDto genericsDto = new GenericsDto();

		IdentityDto inIdentity = new IdentityDto();
		inIdentity.setId(generics.getGenericsId().getId());
		inIdentity.setRowLanguageCode(generics.getGenericsId().getRowLanguageCode());

		BeanUtils.copyProperties(generics, genericsDto);
		genericsDto.setGenericsId(inIdentity);
		return genericsDto;
	}

	public Generics copyGenericsDtoToGenericsEntity(GenericsDto genericsDto) {
		Generics generics = new Generics();
		
		Identity inIdentity = new Identity();
		inIdentity.setId(genericsDto.getGenericsId().getId());
		inIdentity.setRowLanguageCode(genericsDto.getGenericsId().getRowLanguageCode());
		
		BeanUtils.copyProperties(genericsDto, generics);
		generics.setGenericsId(inIdentity);
		generics.setCreatedAt(new Date());
		generics.setUpdatedAt(new Date());
		return generics;
	}
	
	/**
	 * @param genericsDetails
	 * @return
	 * 
	 * @category GenericsDetails
	 */
	
	public GenericsDetailsOutputDto copyGenericsDetailsEntityToGenericsDetailsDto(GenericsDetails genericsDetails)
	{
		GenericsDetailsOutputDto genericsDetailsDto = new GenericsDetailsOutputDto();
		
		IdentityDto identityDto = new IdentityDto();
		identityDto.setId(genericsDetails.getGenericsDetailsId().getId());
		identityDto.setRowLanguageCode(genericsDetails.getGenericsDetailsId().getRowLanguageCode());

		BeanUtils.copyProperties(genericsDetails, genericsDetailsDto);
		genericsDetailsDto.setGenericsDetailsId(identityDto);
		genericsDetailsDto.setGeneric(copyGenericsEntityToGenericsDto(genericsDetails.getGeneric()));
		
		if(genericsDetailsHeadRepo.existsByHeadId(genericsDetailsDto.getGenericAttributeHeadId()))
		{
		GenericsDetailsHeadDto headInfo = genericsDetailsHeadRepo.findByHeadId(genericsDetailsDto.getGenericAttributeHeadId());
			genericsDetailsDto.setGenericAttributeHeadname(headInfo.getHeadName());
		}
		else {
			genericsDetailsDto.setGenericAttributeHeadname("Head Name Not Found");
		}

		return genericsDetailsDto;
	}
	
	public GenericsDetails copyGenericsDetailsDtoToGenericsDetailsEntity(GenericsDetailsDto genericsDetailsDto)
	{
		GenericsDetails genericsDetails = new  GenericsDetails();
		
		Generics generics = genericsRepository.findByGenericsNo(genericsDetailsDto.getGeneric().getGenericsNo());
		
		Identity identity = new Identity();
		identity.setId(genericsDetailsDto.getGenericsDetailsId().getId());
		identity.setRowLanguageCode(genericsDetailsDto.getGenericsDetailsId().getRowLanguageCode());
		
		BeanUtils.copyProperties(genericsDetailsDto, genericsDetails);
		genericsDetails.setGenericsDetailsId(identity);
		genericsDetails.setGeneric(generics);
		genericsDetails.setCreatedAt(new Date());
		genericsDetails.setUpdatedAt(new Date());
		
		return genericsDetails;
		
	}

	/**
	 * @param therapysDto
	 * @return
	 * 
	 * @category Therapy
	 */
	public Therapys copyTherapysDtoToTherapysEntity(TherapysDto therapysDto) {
		Therapys therapys = new Therapys();
		
		
		Identity inIdentity = new Identity();
		inIdentity.setId(therapysDto.getTherapyId().getId());
		inIdentity.setRowLanguageCode(therapysDto.getTherapyId().getRowLanguageCode());
		
		BeanUtils.copyProperties(therapysDto, therapys);
		
		therapys.setTherapyId(inIdentity);
		
		if(therapysDto.getParentTherapyGroupNo() != null) {
			Therapys parentTherapy = therapysRepository.findByTherapyNo(therapysDto.getParentTherapyGroupNo());
			therapys.setParentTherapyGroupNo(parentTherapy);
		
		}
		
		
		
		therapys.setCreatedAt(new Date());
		therapys.setUpdatedAt(new Date());
		return therapys;
	}
	
	public TherapysDto copyTherapysEntityToTherapysDto(Therapys therapys) {
		TherapysDto therapysDto = new TherapysDto();

		IdentityDto inIdentity = new IdentityDto();
		inIdentity.setId(therapys.getTherapyId().getId());
		inIdentity.setRowLanguageCode(therapys.getTherapyId().getRowLanguageCode());

		BeanUtils.copyProperties(therapys, therapysDto);
		therapysDto.setTherapyId(inIdentity);
		if(therapys.getParentTherapyGroupNo() != null) {
		therapysDto.setParentTherapyGroupName(therapys.getParentTherapyGroupNo().getTherapyName());
		therapysDto.setParentTherapyGroupNo(therapys.getParentTherapyGroupNo().getTherapyNo());
		}
		return therapysDto;
	}
	
	/**
	 * @param genericsProdByBrand
	 * @return
	 * 
	 * @category GenericsProdByBrand
	 */

	public GenericsProdByBrandDto copyGenericsProdByBrandEntityToGenericsProdByBrandDto(GenericsProdByBrand genericsProdByBrand)
	{
		GenericsProdByBrandDto genericsProdByBrandDto = new GenericsProdByBrandDto();
		
		BeanUtils.copyProperties(genericsProdByBrand, genericsProdByBrandDto);
		genericsProdByBrandDto.setGenericNo(genericsProdByBrand.getGeneric().getGenericsNo());
		genericsProdByBrandDto.setGenericName(genericsProdByBrand.getGeneric().getGenericsName());
		genericsProdByBrandDto.setProductByBrandNo(genericsProdByBrand.getProductByBrand().getProductByBrandNo());
		genericsProdByBrandDto.setProductByBrandName(genericsProdByBrand.getProductByBrand().getBrandName());
		return genericsProdByBrandDto;
	}
	
	public GenericsProdByBrand copyGenericsProdByBrandDtoToGenericsProdByBrandEntity(GenericsProdByBrandDto genericsProdByBrandDto)
	{
		GenericsProdByBrand genericsProdByBrand = new  GenericsProdByBrand();
		
		Generics generics = genericsRepository.findByGenericsNo(genericsProdByBrandDto.getGenericNo());
		
		ProductByBrand productByBrand = productByBrandRepository.findByProductByBrandNo(genericsProdByBrandDto.getProductByBrandNo());
		
		BeanUtils.copyProperties(genericsProdByBrandDto, genericsProdByBrand);
		genericsProdByBrand.setGeneric(generics);
		genericsProdByBrand.setProductByBrand(productByBrand);
		genericsProdByBrand.setCreatedAt(new Date());
		genericsProdByBrand.setUpdatedAt(new Date());
		
		return genericsProdByBrand;
		
	}
	
	
	/**
	 * @param therapyTree
	 * @return
	 * 
	 * @category TherapyTree
	 */

	public TherapyTreeDto copyTherapyTreeEntityToTherapyTreeDto(TherapyTree therapyTree)
	{
		TherapyTreeDto therapyTreeDto = new TherapyTreeDto();
		
		BeanUtils.copyProperties(therapyTree, therapyTreeDto);
		therapyTreeDto.setTherapyNo(therapyTree.getTherapys().getTherapyNo());
		therapyTreeDto.setTherapyName(therapyTree.getTherapys().getTherapyName());
		if(therapyTree.getParentTree() != null)
		{
			therapyTreeDto.setParentid(therapyTree.getParentTree().getTherapyTreeId());
			therapyTreeDto.setParentTherapyName(therapyTree.getParentTree().getTherapys().getTherapyName());
		}
		return therapyTreeDto;
	}
	
	public TherapyTree copyTherapyTreeDtoToTherapyTreeEntity(TherapyTreeDto therapyTreeDto)
	{
		TherapyTree therapyTree = new  TherapyTree();
		
		Therapys therapy = therapysRepository.findByTherapyNo(therapyTreeDto.getTherapyNo());
		
		BeanUtils.copyProperties(therapyTreeDto, therapyTree);
		
		if(therapyTreeDto.getParentid() == null)
		{
			
		}else {
			TherapyTree parent = therapyTreeRepository.findById(therapyTreeDto.getParentid()).orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
			therapyTree.setParentTree(parent);
		}
		
		therapyTree.setTherapys(therapy);

		therapyTree.setCreatedAt(new Date());
		therapyTree.setUpdatedAt(new Date());
		
		return therapyTree;
		
	}
	
	
	/**
	 * @param genericTherapy
	 * @return
	 * 
	 * @category GenericTherapy
	 */

	public GenericTherapyDto copyGenericTherapyEntityToGenericTherapyDto(GenericTherapy genericTherapy)
	{
		GenericTherapyDto genericTherapyDto = new GenericTherapyDto();
		
//		IdentityDto identityDto = new IdentityDto();
//		identityDto.setId(genericTherapy.getGenericTherapyId().getId());
//		identityDto.setRowLanguageCode(genericTherapy.getGenericTherapyId().getRowLanguageCode());
		
		BeanUtils.copyProperties(genericTherapy, genericTherapyDto);
//		genericTherapyDto.setGenericTherapyId(identityDto);
		genericTherapyDto.setGenericNo(genericTherapy.getGeneric().getGenericsNo());
		genericTherapyDto.setGenericName(genericTherapy.getGeneric().getGenericsName());
		genericTherapyDto.setTherapyNo(genericTherapy.getTherapy().getTherapyNo());
		genericTherapyDto.setTherapyName(genericTherapy.getTherapy().getTherapyName());
		return genericTherapyDto;
	}
	
	public GenericTherapy copyGenericTherapyDtoToGenericTherapyEntity(GenericTherapyDto genericTherapyDto)
	{
		GenericTherapy genericTherapy = new  GenericTherapy();
		
		Generics generics = genericsRepository.findByGenericsNo(genericTherapyDto.getGenericNo());
		
		Therapys therapys = therapysRepository.findByTherapyNo(genericTherapyDto.getTherapyNo());
		
//		Identity identity = new Identity();
//		identity.setId(genericTherapyDto.getGenericTherapyId().getId());
//		identity.setRowLanguageCode(genericTherapyDto.getGenericTherapyId().getRowLanguageCode());
		
		BeanUtils.copyProperties(genericTherapyDto, genericTherapy);
//		genericTherapy.setGenericTherapyId(identity);
		genericTherapy.setGeneric(generics);
		genericTherapy.setTherapy(therapys);
		genericTherapy.setCreatedAt(new Date());
		genericTherapy.setUpdatedAt(new Date());
		
		return genericTherapy;
		
	}
	
	/**
	 * @param productTypeDto
	 * @return
	 * 
	 * @category ProductType
	 */
	public ProductType copyProductTypesDtoToProductTypeEntity(ProductTypeDto productTypeDto) {
		ProductType productType = new ProductType();
		
		Identity inIdentity = new Identity();
		inIdentity.setId(productTypeDto.getProductTypeId().getId());
		inIdentity.setRowLanguageCode(productTypeDto.getProductTypeId().getRowLanguageCode());
		
		ProductType parentProductType = productTypeRepository.findByProductTypeNo(productTypeDto.getParentProductTypeNo());
		
		BeanUtils.copyProperties(productTypeDto, productType);
		productType.setProductTypeId(inIdentity);
		productType.setCreatedAt(new Date());
		productType.setUpdatedAt(new Date());
		
		productType.setParentProductTypeNo(parentProductType);
		
		return productType;
	}
	
	public ProductTypeDto copyProductTypeEntityToProductTypeDto(ProductType productType) {
		ProductTypeDto productTypeDto = new ProductTypeDto();

		IdentityDto inIdentity = new IdentityDto();
		inIdentity.setId(productType.getProductTypeId().getId());
		inIdentity.setRowLanguageCode(productType.getProductTypeId().getRowLanguageCode());

		BeanUtils.copyProperties(productType, productTypeDto);
		productTypeDto.setProductTypeId(inIdentity);
		
		if(productType.getParentProductTypeNo() != null)
		{
			productTypeDto.setParentProductTypeName(productType.getParentProductTypeNo().getProductTypeName());
			productTypeDto.setParentProductTypeNo(productType.getParentProductTypeNo().getProductTypeNo());
		}
		return productTypeDto;
	}
	
	
	/**
	 * @param products
	 * @return
	 * 
	 * @category Products
	 */

	public ProductsDto copyProductsEntityToProductsDto(Products products)
	{
		ProductsDto productsDto = new ProductsDto();
		
		IdentityDto identityDto = new IdentityDto();
		identityDto.setId(products.getProductId().getId());
		identityDto.setRowLanguageCode(products.getProductId().getRowLanguageCode());
		
		BeanUtils.copyProperties(products, productsDto);
		productsDto.setProductId(identityDto);
		productsDto.setGeneric(copyGenericsEntityToGenericsDto(products.getGeneric()));
		productsDto.setProductType(copyProductTypeEntityToProductTypeDto(products.getProductType()));
		productsDto.setProductUnitOfMeasurment(
				copyProductUomEntityToProductUomDto(products.getProductUnitOfMeasurment()));
		productsDto.setProductByBrand(copyProductByBrandEntityToProductByBrandDto(products.getProductByBrand()));
		productsDto.setProductCatagoryNo(products.getProductCatagory().getCategoryNo());
		productsDto.setProductCatagoryName(products.getProductCatagory().getCategoryName());
		productsDto.setCategoryType(products.getProductCatagory().getCategoryType());
		
		List<ProductImage> images = productImageRepository.findByProductProductNo(products.getProductNo());
		List<String> getLink = new ArrayList<>();
//		List<String> downLink = new ArrayList<>();
		
		for(ProductImage image: images)
		{
			getLink.add(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/getFile/")
					.path(image.getImageLink()).toUriString());
//			downLink.add(ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
//					.path(image.getImageLink()).toUriString());
		}
		
		productsDto.setProductImageGetUrl(getLink);
//		productsDto.setProductImageDownloadUrl(downLink);
		
		
		if(products.getParentProductNo() != null)
		{
			productsDto.setParentProductName(products.getParentProductNo().getProductName());
			productsDto.setParentProductNo(products.getParentProductNo().getProductNo());
		}
		
		
		return productsDto;
	}
	
	public Products copyProductsDtoToProductsEntity(ProductsDto productsDto)
	{
		Products products = new  Products();
		
		Products parentProduct = productsRepository.findByProductNo(productsDto.getParentProductNo());
		
		Generics generics = genericsRepository.findByGenericsNo(productsDto.getGeneric().getGenericsNo());
		
		ProductType productType = productTypeRepository.findByProductTypeNo(productsDto.getProductType().getProductTypeNo());
		
		ProductUnitOfMeasurmentInfo productUom = productUomRepository
				.findByProductUomNo(productsDto.getProductUnitOfMeasurment().getProductUomNo());
		ProductByBrand productByBrand = productByBrandRepository.findByProductByBrandNo(productsDto.getProductByBrand().getProductByBrandNo());
		
		Identity identity = new Identity();
		identity.setId(productsDto.getProductId().getId());
		identity.setRowLanguageCode(productsDto.getProductId().getRowLanguageCode());
		
		BeanUtils.copyProperties(productsDto, products);
		
		products.setProductId(identity);
		products.setGeneric(generics);
		products.setProductType(productType);
		products.setProductUnitOfMeasurment(productUom);
		products.setProductCatagory(categoryRepository.findByCategoryNo(productsDto.getProductCatagoryNo()));
		products.setProductByBrand(productByBrand);
		products.setParentProductNo(parentProduct);

		
		products.setCreatedAt(new Date());
		products.setUpdatedAt(new Date());
		products.setDrugAdminRecordDate(new Date());
		
		return products;
		
	}
	
	
	/**
	 * @param productByBrand
	 * @return
	 * 
	 * @category ProductByBrand
	 */
	
	public ProductByBrandDto copyProductByBrandEntityToProductByBrandDto(ProductByBrand productByBrand)
	{
		ProductByBrandDto productByBrandDto = new ProductByBrandDto();
		
		IdentityDto identityDto = new IdentityDto();
		identityDto.setId(productByBrand.getProductByBrandId().getId());
		identityDto.setRowLanguageCode(productByBrand.getProductByBrandId().getRowLanguageCode());

		BeanUtils.copyProperties(productByBrand, productByBrandDto);
		productByBrandDto.setProductByBrandId(identityDto);
//		productByBrandDto.setGeneric(copyGenericsEntityToGenericsDto(productByBrand.getGeneric()));

		return productByBrandDto;
	}
	
	public ProductByBrand copyProductByBrandDtoToProductByBrandEntity(ProductByBrandDto productByBrandDto)
	{
		ProductByBrand productByBrand = new  ProductByBrand();
		
//		Generics generics = genericsRepository.findByGenericsNo(productByBrandDto.getGeneric().getGenericsNo());
		
		Identity identity = new Identity();
		identity.setId(productByBrandDto.getProductByBrandId().getId());
		identity.setRowLanguageCode(productByBrandDto.getProductByBrandId().getRowLanguageCode());
		
		BeanUtils.copyProperties(productByBrandDto, productByBrand);
		productByBrand.setProductByBrandId(identity);
//		productByBrand.setGeneric(generics);
		productByBrand.setCreatedAt(new Date());
		productByBrand.setUpdatedAt(new Date());
		
		return productByBrand;
		
	}
	
	
	/**
	 * @param productTypeDto
	 * @return
	 * 
	 * @category ProductType
	 */
	public ProductUnitOfMeasurmentInfo copyProductUomDtoToProductUomEntity(ProductUnitOfMeasurmentInfoDto productUomDto) {
		ProductUnitOfMeasurmentInfo productUom = new ProductUnitOfMeasurmentInfo();
		
		Identity inIdentity = new Identity();
		inIdentity.setId(productUomDto.getProductUomId().getId());
		inIdentity.setRowLanguageCode(productUomDto.getProductUomId().getRowLanguageCode());
		
		BeanUtils.copyProperties(productUomDto, productUom);
		productUom.setProductUomId(inIdentity);
		productUom.setCreatedAt(new Date());
		productUom.setUpdatedAt(new Date());
		return productUom;
	}
	
	public ProductUnitOfMeasurmentInfoDto copyProductUomEntityToProductUomDto(ProductUnitOfMeasurmentInfo productUom) {
		ProductUnitOfMeasurmentInfoDto productUomDto = new ProductUnitOfMeasurmentInfoDto();

		IdentityDto inIdentity = new IdentityDto();
		inIdentity.setId(productUom.getProductUomId().getId());
		inIdentity.setRowLanguageCode(productUom.getProductUomId().getRowLanguageCode());

		BeanUtils.copyProperties(productUom, productUomDto);
		productUomDto.setProductUomId(inIdentity);
		return productUomDto;
	}
	
	
	
	/**
	 * @param ProductCategory
	 * @return
	 * 
	 * @category ProductCategory
	 */
	
	public ProductCategoryOutputDto copyProductCategoryEntityToProductCategoryDto(ProductCategory productCategory) {
		ProductCategoryOutputDto productCategoryDto = new ProductCategoryOutputDto();

		productCategoryDto.setCategoryId(productCategory.getCategoryId().getId());
		productCategoryDto.setRowLanguageCode(productCategory.getCategoryId().getRowLanguageCode());

		BeanUtils.copyProperties(productCategory, productCategoryDto);
		if(productCategory.getParentCategory() != null)
		{
		productCategoryDto.setParentCategoryNo(productCategory.getParentCategory().getCategoryNo());
		productCategoryDto.setParentCategoryName(productCategory.getParentCategory().getCategoryName());
		
		}
		return productCategoryDto;
	}

	public ProductCategory copyProductCategoryDtoToProductCategoryEntity(ProductCategoryDto productCategoryDto) {
		ProductCategory productCategory = new ProductCategory();
		
		
		Identity identity = new Identity();
		identity.setId(productCategoryDto.getCategoryId().getId());
		identity.setRowLanguageCode(productCategoryDto.getCategoryId().getRowLanguageCode());
		
		BeanUtils.copyProperties(productCategoryDto, productCategory);
		productCategory.setCategoryId(identity);
		
		if(productCategoryDto.getParentCategoryNo() != null)
		{
			ProductCategory parentCategory = categoryRepository.findByCategoryNo(productCategoryDto.getParentCategoryNo());
			productCategory.setParentCategory(parentCategory);
			}
		
		productCategory.setCreatedAt(new Date());
		productCategory.setUpdatedAt(new Date());
		return productCategory;
	}
	
	
	/**
	 * @param ProductImage
	 * @return
	 * 
	 * @category ProductImage
	 */
	
	public ProductImageDto copyProductImageEntityToProductImageDto(ProductImage productImage) {
		ProductImageDto productImageDto = new ProductImageDto();

		productImageDto.setImageId(productImage.getImageId().getId());
		productImageDto.setRowLanguageCode(productImage.getImageId().getRowLanguageCode());

		BeanUtils.copyProperties(productImage, productImageDto);

		productImageDto.setProductByBrandNo(productImage.getProductByBrand().getProductByBrandNo());
		productImageDto.setProductByBrandName(productImage.getProductByBrand().getBrandName());
		
		productImageDto.setProductNo(productImage.getProduct().getProductNo());
		productImageDto.setProductName(productImage.getProduct().getProductName());

		return productImageDto;
	}

	public ProductImage copyProductImageDtoToProductImageEntity(ProductImageDto productImageDto) {
		ProductImage productImage = new ProductImage();
		
		
		Identity identity = new Identity();
		identity.setId(productImageDto.getImageId());
		identity.setRowLanguageCode(productImageDto.getRowLanguageCode());
		
		BeanUtils.copyProperties(productImageDto, productImage);
		productImage.setImageId(identity);
		
		productImage.setProduct(productsRepository.findByProductNo(productImageDto.getProductNo()));
		
		productImage.setProductByBrand(productByBrandRepository.findByProductByBrandNo(productImageDto.getProductByBrandNo()));
		
		productImage.setCreatedAt(new Date());
		productImage.setUpdatedAt(new Date());
		return productImage;
	}
	
	
}
