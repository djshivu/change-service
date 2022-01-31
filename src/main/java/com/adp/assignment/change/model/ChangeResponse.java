package com.adp.assignment.change.model;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "Change service response.")
public class ChangeResponse {

  @ApiModelProperty(notes = "bill is an input.")
  private int bill;
  private List<Coin> changeCoins;

}
