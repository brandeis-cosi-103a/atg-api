/**
 * This package contains a single class,
 * {@link edu.brandeis.cosi.atg.cards.Card}, which contains the definition
 * of card types that exist in the game. See the
 * {@link edu.brandeis.cosi.atg.cards.Card} class for more details.
 * <br/>
 * <br/>
 * <strong>Version 2 Additions:</strong>
 * <br/>
 * Version 2 added 7 new action cards with diverse mechanics:
 * <ul>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#BACKLOG Backlog} - discard
 * and draw</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#DAILY_SCRUM Daily Scrum} -
 * card advantage</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#IPO IPO} - multi-benefit
 * card</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#HACK Hack} - Attack
 * card</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#MONITORING Monitoring} -
 * Reaction card</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#TECH_DEBT Tech Debt} -
 * conditional discard</li>
 * <li>{@link edu.brandeis.cosi.atg.cards.Card.Type#PARALLELIZATION
 * Parallelization} - double action play</li>
 * </ul>
 * <br/>
 * These cards introduce new informal card categories: Attack cards
 * ({@link edu.brandeis.cosi.atg.cards.Card.Type#HACK Hack},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#EVERGREEN_TEST Evergreen Test})
 * and Reaction cards ({@link edu.brandeis.cosi.atg.cards.Card.Type#MONITORING
 * Monitoring}).
 */
package edu.brandeis.cosi.atg.cards;