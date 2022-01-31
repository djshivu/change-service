package com.adp.assignment.change.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adp.assignment.change.dao.ChangeDaoService;
import com.adp.assignment.change.entity.CoinEntity;
import com.adp.assignment.change.model.ChangeResponse;
import io.swagger.annotations.ApiOperation;

@RestController
public class CoinStatusController {

  private static final Logger logger = LoggerFactory.getLogger(CoinStatusController.class);

  private ChangeDaoService daoService;

  public CoinStatusController(ChangeDaoService daoService) {
    this.daoService = daoService;
  }

  @ApiOperation(value = "This change API takes the bill as input and provides the changes in coins",
      produces = MediaType.APPLICATION_JSON_VALUE, response = ChangeResponse.class)
  @GetMapping("/coin")
  public ResponseEntity<List<CoinEntity>> getCoinStatus() {

    return new ResponseEntity<>(daoService.getCoins(), HttpStatus.OK);
  }

}
