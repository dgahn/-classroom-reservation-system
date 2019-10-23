package me.dgahn.crs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes = CrsApplication.class)
@ExtendWith(SpringExtension.class)
class CrsApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  @DisplayName("테스트")
  void test() {
    Assertions.assertThat(1).isOne();
  }

}
