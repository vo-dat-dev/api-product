package service.market.product.aop;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class ValidationAspect {
    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Before("execution(* service.market.product.controller.*.*(..)) && args(.., @org.springframework.web.bind.annotation.RequestBody *)")
    public void validateInput(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                Set<ConstraintViolation<Object>> violations = validator.validate(arg);
                if (!violations.isEmpty()) {
                    String errorMessage = violations.stream()
                        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                        .collect(Collectors.joining(", "));
                    logger.error("Validation failed for {}: {}", arg.getClass().getSimpleName(), errorMessage);
                    throw new IllegalArgumentException("Validation failed: " + errorMessage);
                }
            }
        }
    }
} 