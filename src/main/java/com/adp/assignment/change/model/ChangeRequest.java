package com.adp.assignment.change.model;

import org.hibernate.validator.constraints.Range;
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
@ApiModel(value = "Change service request")
public class ChangeRequest {

  @Range(min = 1, max = 100, message = "Accepts only 1, 2, 5, 10, 20, 50, 100.")
  @ApiModelProperty(notes = "bill is an input. Acceps only 1,2,5,10,25,100 bills.")
  private int bill;

}
