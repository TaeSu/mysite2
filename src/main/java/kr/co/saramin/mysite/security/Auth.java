package kr.co.saramin.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	public enum Role {ADMIN, USER}
	
	public String value() default "";
	public Role Role() default Role.USER;
}
