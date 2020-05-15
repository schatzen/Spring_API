package me.wisdomj.Spring_API.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder() {
    Event event = Event.builder()
            .name("Inflearn Spring Rest API")
            .description("REST API development with Spring")
            .build();

    // not null인지 확인
    assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String name = "Event";
        String description = "Spring";

        //WHen
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);


    }


}