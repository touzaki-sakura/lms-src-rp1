package jp.co.sss.lms.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Constraint(validatedBy = {InputAttendace.class})
public @interface Attendace {

	String message() default "";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    
}
