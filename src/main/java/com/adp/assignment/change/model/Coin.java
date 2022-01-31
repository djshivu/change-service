package com.adp.assignment.change.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Change coins")
public class Coin {

  @ApiModelProperty(notes = "Coins types. Cents/Nickel/Dime/Quarter")
  private String coin;

  @ApiModelProperty(notes = "Coin Count")
  private int count;

  @ApiModelProperty(notes = "Total Coin Amount.")
  private double totalCoinAmount;

}
