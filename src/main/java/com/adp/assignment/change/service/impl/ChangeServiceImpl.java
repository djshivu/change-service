package com.adp.assignment.change.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.adp.assignment.change.dao.ChangeDaoService;
import com.adp.assignment.change.entity.CoinEntity;
import com.adp.assignment.change.exception.ChangeException;
import com.adp.assignment.change.exception.InsufficientChangeException;
import com.adp.assignment.change.model.ChangeResponse;
import com.adp.assignment.change.model.Coin;
import com.adp.assignment.change.service.ChangeService;

@Service
public class ChangeServiceImpl implements ChangeService {

  private static final Logger logger = LoggerFactory.getLogger(ChangeServiceImpl.class);

  private ChangeDaoService daoService;

  public ChangeServiceImpl(ChangeDaoService daoService) {
    this.daoService = daoService;
  }

  @Override
  public ChangeResponse getChange(int requestBill) {

    ChangeResponse response = new ChangeResponse();
    double bill = requestBill;

    // get coins for the given bill
    List<Coin> changeCoins = getChangeCoins(bill);

    // Populating the response
    response.setBill(requestBill);
    response.setChangeCoins(changeCoins);

    return response;

  }

  private List<Coin> getChangeCoins(double bill) {
    List<Coin> changeCoins = new ArrayList<>();

    try {

      for (CoinEntity c : daoService.getCoins()) {
        if ("Penny".equalsIgnoreCase(c.getCoinName())) {
          double centsAmount = c.getCoinValue() * c.getCount();

          if (c.getCount() != 0 && centsAmount <= bill) {
            changeCoins.add(new Coin("Penny", c.getCount(), c.getCount() * c.getCoinValue()));
            bill = bill - centsAmount;
            c.setCount(0);
          }
        } else if ("Nickel".equalsIgnoreCase(c.getCoinName())) {
          double nickelAmount = c.getCoinValue() * c.getCount();
          if (bill == 0.0) {
            break;
          }
          if (c.getCount() != 0) {
            if (nickelAmount <= bill) {
              changeCoins.add(new Coin("Nickel", c.getCount(), c.getCount() * c.getCoinValue()));
              bill = bill - nickelAmount;
              c.setCount(0);
            } else {
              int requiredCoinCount = (int) (bill / c.getCoinValue());
              if (requiredCoinCount > c.getCount()) {
                changeCoins.add(new Coin("Nickel", c.getCount(), c.getCount() * c.getCoinValue()));
                bill = bill - (c.getCount() * c.getCoinValue());
                c.setCount(0);
              } else {
                changeCoins.add(
                    new Coin("Nickel", requiredCoinCount, requiredCoinCount * c.getCoinValue()));
                bill = bill - (requiredCoinCount * c.getCoinValue());
                c.setCount(c.getCount() - requiredCoinCount);
              }
            }
          }
        } else if ("Dime".equalsIgnoreCase(c.getCoinName())) {
          double dimeAmount = c.getCoinValue() * c.getCount();
          if (bill == 0.0) {
            break;
          }
          if (c.getCount() != 0) {
            if (dimeAmount <= bill) {
              changeCoins.add(new Coin("Dime", c.getCount(), c.getCount() * c.getCoinValue()));
              bill = bill - dimeAmount;
              c.setCount(0);
            } else {
              int requiredCoinCount = (int) (bill / c.getCoinValue());
              if (requiredCoinCount > c.getCount()) {
                changeCoins.add(new Coin("Dime", c.getCount(), c.getCount() * c.getCoinValue()));
                bill = bill - (c.getCount() * c.getCoinValue());
                c.setCount(0);
              } else {
                changeCoins
                    .add(new Coin("Dime", requiredCoinCount, requiredCoinCount * c.getCoinValue()));
                bill = bill - (requiredCoinCount * c.getCoinValue());
                c.setCount(c.getCount() - requiredCoinCount);
              }
            }
          }
        } else if ("Quarter".equalsIgnoreCase(c.getCoinName())) {
          double quarterAmount = c.getCoinValue() * c.getCount();
          if (bill == 0.0) {
            break;
          }
          if (c.getCount() != 0) {
            if (quarterAmount <= bill) {
              changeCoins.add(new Coin("Quarter", c.getCount(), c.getCount() * c.getCoinValue()));
              bill = bill - quarterAmount;
              c.setCount(0);
            } else {
              int requiredCoinCount = (int) (bill / c.getCoinValue());
              if (requiredCoinCount > c.getCount()) {
                throw new InsufficientChangeException(
                    "Insufficient coins. Please try with lesser bills or contact the administrator.");
              } else {
                changeCoins.add(
                    new Coin("Quarter", requiredCoinCount, requiredCoinCount * c.getCoinValue()));
                bill = bill - (requiredCoinCount * c.getCoinValue());
                c.setCount(c.getCount() - requiredCoinCount);
              }
            }
          }
        }

      }
    } catch (

    Exception ex) {
      logger.error("Exception occured during: ", ex);
      throw new ChangeException(ex.getMessage());
    }

    return changeCoins;
  }

}
