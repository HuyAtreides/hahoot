package com.huyphan.hahoot.gameplay.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonConstraint implements ModelConstraint {
    NOT_NULL("NotNull"),
    NOT_EMPTY("NotEmpty");

    private final String annotationName;

    @Override
    public String getAnnotationName() {
        return annotationName;
    }
}