package edu.brandeis.cosi.atg.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a method as a player creator.
 *
 * A player creator method must adhere to the following requirements:
 * <ul>
 * <li>It must be public and static</li>
 * <li>It must be annotated with @PlayerCreator</li>
 * <li>It must return an instance of {@link Player}</li>
 * <li>It must not require any arguments.</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PlayerCreator {
    /**
     * A short and unique identifier for this player.
     *
     * @return the player name
     */
    String playerName();
}