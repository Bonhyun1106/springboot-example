package org.example.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto helloDto = new HelloResponseDto(name, amount);

        // then
        Assertions.assertThat(helloDto.getName()).isEqualTo(name);
        Assertions.assertThat(helloDto.getAmount()).isEqualTo(amount);
    }
}
