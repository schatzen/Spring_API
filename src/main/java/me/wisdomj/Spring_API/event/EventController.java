package me.wisdomj.Spring_API.event;


import org.modelmapper.ModelMapper;
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

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;



    public EventController(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    //ResponseEntity를 사용하는 이유 : 응답코드, 헤더, 본문 다 다루기 편한 API
    // created로 보낼때는 URI가 필요

    // Location URL 만들기 : linkTo 와 methodOn은 HATEOS가 제공
    // url이 EventController에 있지 않으므로 methodOn 추가
    //URI createUri = linkTo(methodOn(EventController.class).createEvent(null)).slash("{id}").toUri();

    //@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
    // 추가 해줬기 때문에 더이상 methodOn 필요 x

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
        // 원래대로라면 다음과 같이 코딩해줘야 하는데 ModelMapping 쓰면 됨
        // Event event = Event.builder()
        //        .name(eventDto.getName()) ...
        //       .build();

        //EventDto에 있는 걸 Event class의 타입의 인스턴스로 만들어달라.
        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI createUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();

        return ResponseEntity.created(createUri).body(event);
    }
}
