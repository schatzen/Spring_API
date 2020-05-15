package me.wisdomj.Spring_API.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest // Web과 관련된 Bean들 등록
public class EventControllerTest {

    //WebMvcTest가 단위테스트로 보기 어려운데데 despatcher가 가진 기능들 조합이 된 상태로 동작하기 때문

    @Autowired
    MockMvc mockMvc;
    //MockMvc는 웹은 띄우지 않기 떄문에 빠르나, 단위 테스트보다는 더 걸린다.

    @Autowired
    ObjectMapper objectMapper;
    // 본문 요청 코드를 Json코드로 변환시키기 위해해

   @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("REST_API Dvelopment with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2020,05,15,11,40))
                .closeEnrollmentDateTime(LocalDateTime.of(2020,05,15,11,40))
                .beginEventDateTime(LocalDateTime.of(2020,05,15,11,40))
                .endEventDateTime(LocalDateTime.of(2020,05,15,11,40))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D@ 스타텁 팩토리")
                .build();

        //http post요청 본냄 해당 주소로
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)   // 요청 본문에 JSON을 담아서 넘기고 있따
                .accept(MediaTypes.HAL_JSON_VALUE)    // 어떠한 응답 타입을 원하는지
                .content(objectMapper.writeValueAsString(event))   //요청 본문 주는 코드 (json으로 변환)
       )
                .andDo(print()) // 콘솔에서 어떤 응답과 용청을 받을 수 있는지 확인
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());

                // java.lang.AssertionError: No value at JSON path "id"

        //응답코드 201이 나오는지 확인할 것
        //java.lang.AssertionError: Status
        //Expected :201
        //Actual   :404
        //<Click to see difference>
        // 와 같은 코드 확인가능능
   }


}

