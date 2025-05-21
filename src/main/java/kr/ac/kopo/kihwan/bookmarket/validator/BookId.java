package kr.ac.kopo.kihwan.bookmarket.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookIdValidator.class) // 유효성 검사에 쓰일 클래스
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookId {
    String message() default "{BookId.book.bookID}";

//    속성명으로 사용될 예정
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
