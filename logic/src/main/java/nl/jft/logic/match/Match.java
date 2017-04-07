package nl.jft.logic.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.jft.logic.match.event.MatchListener;
import nl.jft.logic.match.event.impl.GoalRemovedEvent;
import nl.jft.logic.match.event.impl.GoalScoredEvent;
import nl.jft.logic.match.event.impl.MatchStatusChangedEvent;
import nl.jft.logic.participant.Participant;

/**
 * A {@code Match} is a game between two {@link nl.jft.logic.participant.Participant participants}. {@link Goal goals}
 * can be scored, and added, in a {@code Match} to determine the winning {@code Participant}.
 *
 * @author Lesley
 */
public class Match {

    private final List<MatchListener> listeners = new ArrayList<>();

    private final List<Goal> goals = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    private final Participant firstParticipant;
    private final Participant secondParticipant;

    private MatchStatus status;
    private MatchType type;

    /**
     * Initializes a new {@code Match} with the given {@link Participant participants}.
     *
     * @param firstParticipant  The first {@code Participant} of this {@code Match}, should not be {@code null}.
     * @param secondParticipant The second {@code Participant} of this {@code Match}, should not be {@code null}.
     * @param type              The type of {@code Match} that is being played, should not be {@code null}.
     */
    public Match(Participant firstParticipant, Participant secondParticipant, MatchType type) {
        this.firstParticipant = Objects.requireNonNull(firstParticipant);
        this.secondParticipant = Objects.requireNonNull(secondParticipant);
        this.type = Objects.requireNonNull(type);

        status = MatchStatus.SETUP;
    }

    /**
     * Starts this {@code Match}, given that it is still in the {@link MatchStatus#SETUP} state.
     *
     * @throws IllegalStateException If this {@code Match} is in the {@link MatchStatus#IN_PROGRESS} state.
     * @throws IllegalStateException If this {@code Match} is in the {@link MatchStatus#FINISHED} state.
     */
    public void start() {
        if (status == MatchStatus.IN_PROGRESS) {
            throw new IllegalStateException("This match has already been started.");
        }

        if (status == MatchStatus.FINISHED) {
            throw new IllegalStateException("This match has already finished.");
        }

        MatchStatus oldStatus = status;
        status = MatchStatus.IN_PROGRESS;

        fireMatchStatusChanged(oldStatus, status);
    }

    private void fireMatchStatusChanged(MatchStatus oldStatus, MatchStatus newStatus) {
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(this, oldStatus, newStatus);

        synchronized (listeners) {
            for (MatchListener listener : listeners) {
                listener.onMatchStatusChanged(event);
            }
        }
    }

    /**
     * Stops this {@code Match}, given that it is in the {@link MatchStatus#IN_PROGRESS} state.
     *
     * @throws IllegalStateException If this {@code Match} is in the {@link MatchStatus#SETUP} state.
     * @throws IllegalStateException If this {@code Match} is in the {@link MatchStatus#FINISHED} state.
     */
    public void stop() {
        if (status == MatchStatus.SETUP) {
            throw new IllegalStateException("This match has not yet been started.");
        }

        if (status == MatchStatus.FINISHED) {
            throw new IllegalStateException("This match has already finished.");
        }

        MatchStatus oldStatus = status;
        status = MatchStatus.FINISHED;

        fireMatchStatusChanged(oldStatus, status);
    }

    public void addListener(MatchListener listener) {
        Objects.requireNonNull(listener);

        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void removeListener(MatchListener listener) {
        Objects.requireNonNull(listener);

        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    public List<MatchListener> getListeners() {
        List<MatchListener> list = new ArrayList<>();

        synchronized (listeners) {
            list.addAll(listeners);
        }

        return list;
    }

    /**
     * Adds a {@link Goal} to this {@code Match}.
     *
     * @param goal The {@code Goal} to add to this {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the specified {@code Goal} is {@code null}.
     */
    public void addGoal(Goal goal) {
        Objects.requireNonNull(goal);

        synchronized (goals) {
            goals.add(goal);
        }

        GoalScoredEvent event = new GoalScoredEvent(this, goal);

        synchronized (listeners) {
            for (MatchListener listener : listeners) {
                listener.onGoalScored(event);
            }
        }
    }

    /**
     * Removes a {@link Goal} from this {@code Match}.
     *
     * @param goal The {@code Goal} to remove from this {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the specified {@code Goal} is {@code null}.
     */
    public void removeGoal(Goal goal) {
        Objects.requireNonNull(goal);

        synchronized (goals) {
            goals.remove(goal);
        }

        GoalRemovedEvent event = new GoalRemovedEvent(this, goal);

        synchronized (listeners) {
            for (MatchListener listener : listeners) {
                listener.onGoalRemoved(event);
            }
        }
    }

    /**
     * Adds a {@link Rule} to this {@code Match}.
     *
     * @param rule The {@code Rule} to add to this {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the specified {@code Rule} is {@code null}.
     */
    public void addRule(Rule rule) {
        Objects.requireNonNull(rule);

        synchronized (rules) {
            rules.add(rule);
        }
    }

    /**
     * Removes a {@link Rule} from this {@code Match}.
     *
     * @param rule The {@code Rule} to remove from this {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the specified {@code Rule} is {@code null}.
     */
    public void removeRule(Rule rule) {
        Objects.requireNonNull(rule);

        synchronized (rules) {
            rules.remove(rule);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Match)) {
            return false;
        }

        Match o = (Match) other;
        return o.firstParticipant.equals(firstParticipant) && o.secondParticipant.equals(secondParticipant)
                && o.goals.equals(goals) && o.rules.equals(rules);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + firstParticipant.hashCode();
        result = 31 * result + secondParticipant.hashCode();
        result = 31 * result + goals.hashCode();
        result = 31 * result + rules.hashCode();
        return result;
    }

    /**
     * Gets the current {@link MatchStatus} of this {@code Match}.
     *
     * @return The {@code MatchStatus} of this {@code Match}.
     */
    public MatchStatus getStatus() {
        return status;
    }

    /**
     * Gets the current {@link MatchType} of this {@code Match}.
     *
     * @return The {@code MatchType} of this {@code Match}.
     */
    public MatchType getType() {
        return type;
    }

    /**
     * Gets all {@link Goal Goals} from this {@code Match}.
     *
     * @return A {@link List} containing all {@code Goals} added to this {@code Match}.
     */
    public List<Goal> getGoals() {
        List<Goal> list = new ArrayList<>();

        synchronized (goals) {
            list.addAll(goals);
        }

        return list;
    }

    /**
     * Gets all {@link Rule Rules} from this {@code Match}.
     *
     * @return A {@link List} containing all {@code Rules} added to this {@code Match}.
     */
    public List<Rule> getRules() {
        List<Rule> list = new ArrayList<>();

        synchronized (rules) {
            list.addAll(rules);
        }

        return list;
    }

    /**
     * Gets the first {@link Participant} competing in this {@code Match}.
     *
     * @return The first {@code Participant} competing in this {@code Match}.
     */
    public Participant getFirstParticipant() {
        return firstParticipant;
    }

    /**
     * Gets the second {@link Participant} competing in this {@code Match}.
     *
     * @return The second {@code Participant} competing in this {@code Match}.
     */
    public Participant getSecondParticipant() {
        return secondParticipant;
    }

}
