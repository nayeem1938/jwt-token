/**
 * @Since Jan 24, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service.impl
 */
package net.ati.product.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import net.ati.product.common.exception.CustomDataIntegrityViolationException;
import net.ati.product.common.exception.FileNotFoundException;
import net.ati.product.common.exception.FileStorageException;
import net.ati.product.common.message.BaseResponse;
import net.ati.product.common.message.CustomMessage;
import net.ati.product.common.utils.CopyProperties;
import net.ati.product.common.utils.FileStorageProperties;
import net.ati.product.model.dto.ProductImageDto;
import net.ati.product.model.entity.ProductImage;
import net.ati.product.model.entity.Products;
import net.ati.product.repository.ProductImageRepository;
import net.ati.product.repository.ProductsRepository;
import net.ati.product.service.ProductImageService;

/**
 * @author Nayeem
 *
 */

@Service
public class ProductImageServiceImpl implements ProductImageService {

	private final Path productLoc;
	private final Path thumbLoc;

	/**
	 * @param fileStorageLocation
	 */
	public ProductImageServiceImpl(FileStorageProperties properties) {
		this.productLoc = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();
		this.thumbLoc = Paths.get(properties.getUploadDirThumb()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.productLoc);
			Files.createDirectories(this.thumbLoc);
		} catch (Exception e) {

			throw new FileStorageException("Could not Create the Directory", e);

		}
	}

	@Autowired
	private CopyProperties copyProperties;

	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductsRepository productRepo;

	public BaseResponse storeFile(ProductImageDto productImageDto, Map<String, MultipartFile> fileMap) {
		BaseResponse response = new BaseResponse();

		try {
			
			for(MultipartFile file : fileMap.values()) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				String FileCaption = StringUtils.cleanPath(file.getName());
				
				System.out.println("fileName : " + fileName);
				
				Date date = new Date();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SSSSSS");
				
				
				
				if (fileName.contains("..")) {
					throw new FileStorageException("File Name not in valid format" + fileName);
				}
				
				if(!StringUtils.isEmpty(fileName) && FileCaption.toLowerCase().equals("thumb")) {
					fileName = "Thumb" + productImageDto.getProductNo()+ dateFormat.format(date) + ".png";
					
					System.out.println("new fileName : " + fileName);
					
					Path targetLocation = this.productLoc.resolve(fileName);
					Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
					Products product = productRepo.findByProductNo(productImageDto.getProductNo());
					
					product.setThumbLink(fileName);
					
					try {
						productRepo.save(product);
						response.setCode(HttpStatus.CREATED.value());
						response.setMesssage(CustomMessage.SAVE_SUCCESS_MESSAGE);
					} catch (DataIntegrityViolationException e) {
						throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
					}
				}
				else if(!StringUtils.isEmpty(fileName)){
					fileName = "Product" + productImageDto.getProductNo() + dateFormat.format(date) + ".png";
					System.out.println("new fileName : " + fileName);
					Path targetLocation = this.productLoc.resolve(fileName);
					Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

//					String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/downloadFile/")
//							.path(fileName).toUriString();
//					
//					String getUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/prod-img/getFile/")
//							.path(fileName).toUriString();
					
//					productImageDto.setImagePath(targetLocation.toString());
					
					productImageDto.setImageLink(fileName);
//					productImageDto.setImageDownLink(downloadUri);
					response = saveAndUpdateProductImage(productImageDto);
					
				}


			}
			
			
			

//			FileFormat fileFormat = new FileFormat(fileName, fileDownloadUri, file.getContentType(), file.getSize());
//
//			productImageRepository.save(fileFormat);

		} catch (IOException e) {
			
			//throw new FileStorageException("Could not Stored" + fileName + ". Please try Again!");

		}
		
		return response;
	}
	
	public Resource loadFileAsResource(String fileName) {
		
		try {
			Path filePath = this.productLoc.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists())
			{
				return resource;
			}else {
				throw new FileNotFoundException("File Not Found");
			}
		}catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
	
	}
	
	@Override
	public BaseResponse saveAndUpdateProductImage(ProductImageDto productImageDto) {
		ProductImage productImage = copyProperties.copyProductImageDtoToProductImageEntity(productImageDto);
		productImage.setImageNo(productImage.getImageId().getRowLanguageCode(), productImage.getImageId().getId());

		if (productImageDto.getRowLanguageCode().equals("EN") && productImageDto.getImageId() == null) {

			Long id = productImageRepository.idValue();

			productImage.getImageId().setId(id);

			productImage.setImageNo(productImage.getImageId().getRowLanguageCode(), productImage.getImageId().getId());

			try {
				

				productImageRepository.save(productImage);

				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}

		}

		else if (productImageDto.getRowLanguageCode() != "EN"
				&& productImageRepository.existsByImageIdId(productImageDto.getImageId()) == true
				&& productImageRepository.existsByImageNo(productImage.getImageNo()) == false) {
			try {
				productImageRepository.save(productImage);
				return new BaseResponse(CustomMessage.RECORD_SAVE_SUCCESS_BY_ANOTHER_LANGUAGE,
						HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.RECORD_SAVE_FAILED_BY_ANOTHER_LANGUAGE);
			}
		}

		else if (productImageRepository.existsByImageNo(productImageDto.getImageNo()) == true) {
			try {
				productImageRepository.save(productImage);

				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		} else if (productImageDto.getRowLanguageCode() != "EN"
				&& productImageRepository.existsByImageIdId(productImageDto.getImageId()) == true
				&& productImageRepository.existsByImageNo(productImage.getImageNo()) == true) {

			return new BaseResponse(CustomMessage.DATA_EXIST, HttpStatus.NOT_ACCEPTABLE.value());

		} else {
			return new BaseResponse(CustomMessage.FORMAT_MISMACH, HttpStatus.NOT_ACCEPTABLE.value());

		}

	}

	@Override
	public List<ProductImageDto> findImagebyProductNo(String productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductImageDto findImageByNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
