package com.adp.assignment.change.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.adp.assignment.change.dao.ChangeDaoService;
import com.adp.assignment.change.exception.ChangeServiceExceptionHandler;
import com.adp.assignment.change.model.ChangeRequest;
import com.adp.assignment.change.service.impl.ChangeServiceImpl;
import com.adp.assignment.change.validation.ChangeValidationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ChangeController.class, ChangeValidationServiceImpl.class,
    ChangeServiceImpl.class, ChangeDaoService.class, ChangeServiceExceptionHandler.class})
@WebMvcTest
class ChangeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetChange_Success_WhenOneDollorBill() throws Exception {
    int bill = 1;

    mockMvc
        .perform(put("/change").contentType(MediaType.APPLICATION_JSON)
            .content(getRequestJsonString(bill)))
        .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.bill").value("1"))
        .andExpect(jsonPath("$.changeCoins[0].coin").value("Penny"))
        .andExpect(jsonPath("$.changeCoins[0].count").value("100"))
        .andExpect(jsonPath("$.changeCoins[0].totalCoinAmount").value("1.0"));
  }

  @Test
  void testGetChange_Error_Throws400_WhenInvalidDollorBill() throws Exception {
    int bill = 25;
    // Exception invalidBillException = assertThrows(InvalidBillException.class, () -> {
    // mockMvc.perform(put("/change").contentType(MediaType.APPLICATION_JSON)
    // .content(getRequestJsonString(bill))).andDo(print()).andReturn();
    // });
    //
    // Assertions.assertEquals("some message", invalidBillException.getMessage());

    mockMvc
        .perform(put("/change").contentType(MediaType.APPLICATION_JSON)
            .content(getRequestJsonString(bill)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.statusCode").value("400"))
        .andExpect(jsonPath("$.statusError").value("Bad Request"))
        .andExpect(jsonPath("$.errorMessage")
            .value("Invalid Bill. Accepts only 1, 2, 5, 10, 20, 50, 100."));
  }

  @Test
  void testGetChange_Error_Throws404_When_InSufficientCoins_forTheBill() throws Exception {
    int bill = 100;
    mockMvc
        .perform(put("/change").contentType(MediaType.APPLICATION_JSON)
            .content(getRequestJsonString(bill)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.statusCode").value("404"))
        .andExpect(jsonPath("$.statusError").value("Not Found"))
        .andExpect(jsonPath("$.errorMessage").value(
            "Insufficient coins. Please try with lesser bills or contact the administrator."));
  }

  private String getRequestJsonString(int bill) throws Exception {
    ChangeRequest request = new ChangeRequest(bill);

    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println("Request" + objectMapper.writeValueAsString(request));
    return objectMapper.writeValueAsString(request);
  }

}
