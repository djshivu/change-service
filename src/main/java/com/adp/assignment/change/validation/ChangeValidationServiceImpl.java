package com.adp.assignment.change.validation;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.adp.assignment.change.dao.ChangeDaoService;
import com.adp.assignment.change.exception.ChangeException;
import com.adp.assignment.change.exception.InsufficientChangeException;
import com.adp.assignment.change.exception.InvalidBillException;

@Component
public class ChangeValidationServiceImpl implements ChangeValidationService {

  private static final Logger logger = LoggerFactory.getLogger(ChangeValidationServiceImpl.class);

  private ChangeDaoService daoService;

  @Value("#{'${bills.available}'.split(',')}")
  private List<Integer> bills;

  @Value("${invalid.bills.error.message}")
  private String invalidBillsErrorMessage;

  public ChangeValidationServiceImpl(ChangeDaoService daoService) {
    this.daoService = daoService;
  }

  @Override
  public void validateBill(int bill) {

    bills.stream().filter(b -> b == bill).findFirst().ifPresentOrElse(b -> {
    }, () -> {
      logger.error(invalidBillsErrorMessage);
      throw new InvalidBillException(invalidBillsErrorMessage);
    });
  }

  @Override
  public void checkCoinsAvailability(int bill) {

    Optional<Double> totalValueOfAllAvailableCoins = daoService.getCoins().stream()
        .map(c -> c.getCoinValue() * c.getCount()).reduce(Double::sum);

    if (totalValueOfAllAvailableCoins.isPresent()) {
      if (bill > totalValueOfAllAvailableCoins.get()) {
        logger.error(
            "Insufficient coins. Please try with lesser bills or contact the administrator.");
        throw new InsufficientChangeException(
            "Insufficient coins. Please try with lesser bills or contact the administrator.");
      }
    } else {
      logger.error("Insufficient coins. Please contact the administrator.");
      throw new ChangeException("Insufficient coins. Please contact the administrator.");
    }
  }

}
