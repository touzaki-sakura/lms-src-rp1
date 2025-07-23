package jp.co.sss.lms.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Constraint(validatedBy = { TimeValidator.class })
public @interface Time {
    String message() default "勤怠情報の入力に不正があります。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // チェック対象となるフィールド名を定義
    String fieldTrainingStartTimeHour() default "trainingStartTimeHour";
    String fieldTrainingStartTimeMinute() default "trainingStartTimeMinute";
    String fieldTrainingEndTimeHour() default "trainingEndTimeHour";
    String fieldTrainingEndTimeMinute() default "trainingEndTimeMinute";
}
