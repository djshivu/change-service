package com.adp.assignment.change;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.adp.assignment.change.controller.ChangeController;

@SpringBootTest
class ChangeServiceApplicationTests {

  @Autowired
  private ChangeController controller;

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }

}
