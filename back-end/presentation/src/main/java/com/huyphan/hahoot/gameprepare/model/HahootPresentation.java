package com.huyphan.hahoot.gameprepare.model;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class HahootPresentation {
    private final UUID id;
    private final List<Screen> screens;
    private final Theme theme;
    private final Status status;
    private final Creator creator;
}
