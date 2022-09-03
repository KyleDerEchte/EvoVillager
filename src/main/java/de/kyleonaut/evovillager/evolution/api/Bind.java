package de.kyleonaut.evovillager.evolution.api;

import de.kyleonaut.evovillager.event.EvolutionEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bind {
    Class<? extends EvolutionEvent> value();
}
