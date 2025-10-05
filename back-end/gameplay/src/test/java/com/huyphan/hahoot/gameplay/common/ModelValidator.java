package com.huyphan.hahoot.gameplay.common;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class ModelValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void assertModelViolateConstraints(T model, ModelConstraint... constraints) {
        var constraintViolations = validator.validate(model).stream().map(
                violation -> violation
                        .getConstraintDescriptor()
                        .getAnnotation()
                        .annotationType()
                        .getSimpleName()).collect(Collectors.toSet()
        );
        var expectedConstraints = Arrays.stream(constraints).map(ModelConstraint::getAnnotationName).toArray(String[]::new);

        assertThat(constraintViolations, containsInAnyOrder(expectedConstraints));
    }

    public static <T> void assertNotViolate(T model) {
        assertThat(validator.validate(model), is(empty()));
    }
}