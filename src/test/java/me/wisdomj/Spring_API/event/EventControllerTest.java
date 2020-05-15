package me.wisdomj.Spring_API.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest // Web과 관련된 Bean들 등록
public class EventControllerTest {

    //WebMvcTest가 단위테스트로 보기 어려운데데 despatcher가 가진 기능들 조합이 된 상태로 동작하기 때문

    @Autowired
    MockMvc mocMvc;
    //MockMvc는 웹은 띄우지 않기 떄문에 빠르나, 단위 테스트보다는 더 걸린다.

    @Test
    public void createEvent() throws Exception {
        //http post요청 본냄 해당 주소로
        mocMvc.perform(post("/api.events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)   // 요청 본문에 JSON을 담아서 넘기고 있따
                .accept(MediaTypes.HAL_JSON)    // 어떠한 응답 타입을 원하는지
        )
                .andExpect(status().isCreated());
    }


}
