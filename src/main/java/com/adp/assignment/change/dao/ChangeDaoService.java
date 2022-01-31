package com.adp.assignment.change.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.adp.assignment.change.entity.CoinEntity;

@Repository
public class ChangeDaoService {

  private static final Logger logger = LoggerFactory.getLogger(ChangeDaoService.class);

  private static List<CoinEntity> coins = new ArrayList<>();

  static {
    coins.add(new CoinEntity(1, "Penny", 0.01, 100));
    coins.add(new CoinEntity(2, "Nickel", 0.05, 100));
    coins.add(new CoinEntity(3, "Dime", 0.10, 100));
    coins.add(new CoinEntity(4, "Quarter", 0.25, 100));
  }

  public List<CoinEntity> getCoins() {
    coins.stream().forEach(c -> logger.debug(c.toString()));
    return coins;
  }

  public CoinEntity findCoinByCoinName(String coinName) {
    return coins.stream().filter(c -> c.getCoinName().equals(coinName)).findAny().orElse(null);
  }

}
