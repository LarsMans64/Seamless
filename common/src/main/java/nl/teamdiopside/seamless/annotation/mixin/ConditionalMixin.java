package nl.teamdiopside.seamless.annotation.mixin;

import nl.teamdiopside.seamless.compat.Mods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalMixin {
  Mods[] mods();

  boolean applyIfPresent() default true;
}