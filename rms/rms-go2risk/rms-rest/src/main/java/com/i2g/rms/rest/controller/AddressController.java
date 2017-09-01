package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.service.AddressRestService;

/**
 * Rest controller for Address read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class AddressController extends AbstractRestController {
	
	@Autowired
	private AddressRestService _addressRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES, method = RequestMethod.GET)
	public List<AddressRO> get() {
		return _addressRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESS, method = RequestMethod.GET)
	public AddressRO get(final @PathVariable long id) {
		return _addressRestService.get(id);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_ADDRESS, method = RequestMethod.POST)
	public AddressRO create(final @Valid @RequestBody AddressRO addressRO) {
		return _addressRestService.create(addressRO);
	}
}
