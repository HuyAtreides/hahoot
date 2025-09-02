package com.huyphan.hahoot.quiz.present.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HahootPresentation {
    private final List<Screen> screens;
    private final Theme theme;
    private final Status status;
    private final Creator creator;
}
