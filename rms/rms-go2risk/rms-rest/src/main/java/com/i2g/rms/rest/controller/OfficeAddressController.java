package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.OfficeAddress;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.OfficeAddressRO;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.OfficeAddressRestService;

/**
 * Rest controller for Office Address read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class OfficeAddressController extends AbstractRestController {

	@Autowired
	private OfficeAddressRestService _officeAddressRestService;

	@RequestMapping(value = RequestMappingConstants.GET_OFFICE_ADDRESSES, method = RequestMethod.GET)
	@Searchable(sourceType = OfficeAddressRO.class, value = OfficeAddress.class)
	public List<OfficeAddressRO> get() {
		return _officeAddressRestService.get();
	}

	@RequestMapping(value = RequestMappingConstants.GET_OFFICE_ADDRESS, method = RequestMethod.GET)
	public OfficeAddressRO get(final @PathVariable long id) {
		return _officeAddressRestService.get(id);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_OFFICE_ADDRESS, method = RequestMethod.POST)
	public OfficeAddressRO create(final @Valid @RequestBody OfficeAddressRO officeAddressRO) {
		return _officeAddressRestService.create(officeAddressRO);
	}
}
