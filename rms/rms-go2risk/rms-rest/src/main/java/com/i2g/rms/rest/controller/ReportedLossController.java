package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.wrapper.ReportedLossWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.ReportedLossRestService;

/**
 * Rest controller for suspect read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class ReportedLossController extends AbstractRestController {
	
	@Autowired
	private ReportedLossRestService _reportedLossRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ALL_REPORTED_LOSSES, method = RequestMethod.GET)
	@Searchable(sourceType = ReportedLossRO.class, value = ReportedLoss.class)
	public List<ReportedLossRO> get() {
		return _reportedLossRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_REPORTED_LOSS_BY_REPORTED_LOSS_ID, method = RequestMethod.GET)
	public ReportedLossRO get(@PathVariable final Long reportedLossId) {
		return _reportedLossRestService.get(reportedLossId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_REPORTED_LOSS, method = RequestMethod.POST)
	public ReportedLossRO createReportedLoss(@Valid @RequestBody final ReportedLossRO reportedLossRO) {
		return _reportedLossRestService.createReportedLoss(reportedLossRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_REPORTED_LOSSES, method = RequestMethod.POST)
	public List<ReportedLossRO> createReportedLosses(@Valid @RequestBody final ReportedLossWrapper reportedLossWrapper) {
		return _reportedLossRestService.createReportedLosses(reportedLossWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_REPORTED_LOSS, method = RequestMethod.PUT)
	public ReportedLossRO updateReportedLoss(@Valid @RequestBody final ReportedLossRO reportedLossRO) {
		return _reportedLossRestService.updateReportedLoss(reportedLossRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_REPORTED_LOSSES, method = RequestMethod.PUT)
	public List<ReportedLossRO> updateReportedLosses(@Valid @RequestBody final ReportedLossWrapper reportedLossWrapper) {
		return _reportedLossRestService.updateReportedLosses(reportedLossWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_REPORTED_LOSS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReportedLoss(final @PathVariable Long reportedLossId) {
		_reportedLossRestService.deleteReportedLoss(reportedLossId);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_REPORTED_LOSSES, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReportedLosses(final @Valid @RequestBody DeleteRO deleteRO) {
		_reportedLossRestService.deleteReportedLosses(deleteRO);
	}
}
