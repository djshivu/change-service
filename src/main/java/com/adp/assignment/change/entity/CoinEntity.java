package com.adp.assignment.change.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinEntity {

  private Integer id;
  private String coinName;
  private double coinValue;
  private int count;

}
