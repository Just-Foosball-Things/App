package nl.jft.logic.participant.impl;

import java.util.Objects;

import nl.jft.common.rating.Rating;
import nl.jft.common.util.Arguments;
import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.Title;

/**
 * A {@code User} represents a registered user within this application.
 * Implements the {@link Participant} interface because a {@code User} can partake in {@link nl.jft.logic.match.Match matches} and {@link nl.jft.logic.tournament.Tournament tournaments}.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
public class User implements Participant {

    private final int id;
    private final String username;

    private final Rating rating;
    private final Title title;

    /**
     * Initializes a new {@code User} by invoking an other constructor ({@link #User(int, String, Elo, Title)}.
     *
     * @param username The {@code username} of this {@code User}, should not be {@code null} or empty.
     * @param rating   The {@link Rating} of this {@code User}, should not be {@code null}.
     * @param title    The active {@code Title} of this {@code User}. Should not be {@code null}.
     * @throws NullPointerException     If the given {@code username} was null.
     * @throws IllegalArgumentException IF the given {@code username} was empty.
     */
    public User(String username, Rating rating, Title title) {
        this(LogicConstants.INTERNAL_ID, username, rating, title);
    }

    /**
     * Initializes a new {@code User} using the given {@code username}.
     *
     * @param id       The {@code id} of this {@code User}.
     * @param username The {@code username} of this {@code User}, should not be {@code null} or empty.
     * @param rating   The {@link Rating} of this {@code User}, should not be {@code null}.
     * @param title    The active {@code Title} of this {@code User}. Should not be {@code null}.
     * @throws NullPointerException     If the given {@code username} was null.
     * @throws IllegalArgumentException IF the given {@code username} was empty.
     */
    public User(int id, String username, Rating rating, Title title) {
        this.id = id;
        this.username = Arguments.requireNotEmpty(username);
        this.rating = Objects.requireNonNull(rating);
        this.title = Objects.requireNonNull(title);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }

        User o = (User) other;
        return o.username.equals(username);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets the active {@code Title} of this {@code User}.
     *
     * @return The active {@code Title} of this {@code User}.
     */
    public Title getActiveTitle() {
        return title;
    }

}
