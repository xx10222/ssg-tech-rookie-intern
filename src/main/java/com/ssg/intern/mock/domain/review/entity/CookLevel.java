package com.ssg.intern.mock.domain.review.entity;

import lombok.Getter;

@Getter
public enum CookLevel {

    EASY("쉬워요"),
    MEDIUM("보통예요"),
    HARD("어려워요");

    private final String level;

    CookLevel(final String level) {
        this.level = level;
    }
}