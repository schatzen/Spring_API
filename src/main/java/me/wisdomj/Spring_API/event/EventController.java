package me.wisdomj.Spring_API.event;



import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    //ResponseEntity를 사용하는 이유 : 응답코드, 헤더, 본문 다 다루기 편한 API
    // created로 보낼때는 URI가 필요

    // Location URL 만들기 : linkTo 와 methodOn은 hATEOS가 제공
    // url이 EventController에 있지 않으므로 methodOn 추가
    //URI createUri = linkTo(methodOn(EventController.class).createEvent(null)).slash("{id}").toUri();

    //@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
    // 추가 해줬기 때문에 더이상 methodOn 필요 x

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        URI createUri = linkTo(EventController.class).slash("{id}").toUri();
        event.setId(10);
        return ResponseEntity.created(createUri).body(event);
    }
}
