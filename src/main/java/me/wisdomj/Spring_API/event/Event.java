package me.wisdomj.Spring_API.event;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

// @Builder로 구현한 Events는 기본 생성자가 없어서
// 다른 곳에서 객체를 만들기 애매하다.
// 그래서 다음과 같은 추가 코드를 생성한다.

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter@EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice;// (optional)
    private int maxPrice; //(optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
}
