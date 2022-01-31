package com.adp.assignment.change.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.adp.assignment.change.model.ChangeRequest;
import com.adp.assignment.change.model.ChangeResponse;
import com.adp.assignment.change.service.ChangeService;
import com.adp.assignment.change.validation.ChangeValidationService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ChangeController {

  private static final Logger logger = LoggerFactory.getLogger(ChangeController.class);

  private ChangeValidationService validationService;

  private ChangeService changeService;

  public ChangeController(ChangeValidationService validationService, ChangeService changeService) {
    this.validationService = validationService;
    this.changeService = changeService;
  }

  @ApiOperation(value = "This change API takes the bill as input and provides the changes in coins",
      produces = MediaType.APPLICATION_JSON_VALUE, response = ChangeResponse.class)
  @PutMapping("/change")
  public ChangeResponse getChange(@Valid @RequestBody ChangeRequest request) {

    logger.debug("Request: {}", request);

    // validate for bill values. If not valid bill, throw error.
    validationService.validateBill(request.getBill());

    // check whether sufficient coins available for the given bill.
    // If not throw error.
    validationService.checkCoinsAvailability(request.getBill());

    return changeService.getChange(request.getBill());
  }

}
