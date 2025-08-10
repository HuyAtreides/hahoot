package com.huyphan.hahoot.quiz.present.model;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HahootPresentation {
    private final List<Screen> screens;
    private final Theme theme;
    private final Status status;
    private final Creator creator;

}
