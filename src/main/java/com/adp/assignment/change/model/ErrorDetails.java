package com.adp.assignment.change.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Error Details")
public class ErrorDetails {

  private int statusCode;
  private String statusError;
  private String errorMessage;
  private LocalDateTime localDateTime;

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusError() {
    return statusError;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
  @JsonProperty("localDateTime")
  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

}
