package com.huyphan.hahoot.quiz.present.model;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextBasedAnswer implements Answer, AnswerScreen<String> {
    private final String text;
    private final Media media;

    @Override
    public boolean matches(@NotNull Answer obj) {
        if (obj instanceof TextBasedAnswer other) {
            return this.text.equals(other.text);
        }

        throw new IllegalArgumentException("Cannot compare TextBasedAnswer with " + obj.getClass().getSimpleName());
    }

    public String getText() {
        return text;
    }

    @Override
    public void setMedia(Media media) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValue(String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Media getMedia() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }
}