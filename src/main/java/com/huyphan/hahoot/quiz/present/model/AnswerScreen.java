package com.huyphan.hahoot.quiz.present.model;

public interface AnswerScreen<Value> extends Screen {
    void setMedia(Media media);

    void setValue(Value value);

}
