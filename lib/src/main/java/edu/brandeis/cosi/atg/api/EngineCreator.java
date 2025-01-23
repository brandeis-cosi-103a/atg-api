package edu.brandeis.cosi.atg.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a method as an engine creator.
 *
 * An engine creator method must adhere to the following requirements:
 * <ul>
 * <li>It must be public and static</li>
 * <li>It must be annotated with @EngineCreator</li>
 * <li>It must return an instance of {@link Engine}</li>
 * <li>It must accept 3 arguments: Player player1, Player player2, GameObserver
 * observer</li>
 * <ul>
 * <li>The first two arguments are the players that will be playing the
 * game.</li>
 * <li>The third argument is an observer that will be notified of all game
 * events. This argument may be null if no observer is required.</li>
 * </ul>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EngineCreator {
}