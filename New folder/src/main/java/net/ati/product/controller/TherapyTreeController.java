/**
 * @Since Feb 18, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.controller
 */
package net.ati.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.TherapyTreeDto;
import net.ati.product.service.TherapyTreeService;

/**
 * @author Nayeem
 *
 */

@RestController
@RequestMapping("ttree")
@Validated
public class TherapyTreeController {
	
	
	@Autowired
	private TherapyTreeService service;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateTherapyTree(@Valid @RequestBody TherapyTreeDto dto) {
		BaseResponse response = service.saveAndUpdateTherapyTree(dto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<TherapyTreeDto>> findGenericsTherapyList() {
		List<TherapyTreeDto> dtos = service.findAllTherapyTree();
		return new ResponseEntity<List<TherapyTreeDto>>(dtos, HttpStatus.OK);
	}

	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<TherapyTreeDto> findTherapyTreeById(@PathVariable("id") Long id) {
		return new ResponseEntity<TherapyTreeDto>(service.findTherapyTreeById(id), HttpStatus.OK);
	}

}
