package com.codingbox.jpaitem.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // 테스트를 위해 파라미터가 있는 생성자 만들기
    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // 기본생성자
    public Period(){
    }
}
