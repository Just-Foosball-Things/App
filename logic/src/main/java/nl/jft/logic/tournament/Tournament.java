package nl.jft.logic.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.ParticipantType;
import nl.jft.logic.util.ParticipantUtil;

/**
 * A {@code Tournament} schedules {@link nl.jft.logic.match.Match matches} for the joined {@link Participant participants}.
 * At the end of a {@code Tournament}, a winner is determined (depending on the {@link TournamentType}).
 *
 * @author Lesley
 */
public class Tournament {

    private final List<Participant> participants = new ArrayList<>();

    private final TournamentType tournamentType;
    private final ParticipantType participantType;

    private TournamentStatus status;
    private Optional<Participant> winner;

    /**
     * Initializes a new {@code Tournament} using the given arguments.
     *
     * @param tournamentType  The {@code TournamentType} determines how {@link nl.jft.logic.match.Match matches}
     *                        should be scheduled, and how a winner should be determined. Should not be {@code null}.
     * @param participantType The {@code ParticipantType} determines what kind of {@link Participant participants}
     *                        can join, should not be {@code null}.
     * @throws NullPointerException If the given {@code TournamentType} is {@code null}.
     * @throws NullPointerException If the given {@code ParticipantType} is {@code null}.
     */
    public Tournament(TournamentType tournamentType, ParticipantType participantType) {
        this.tournamentType = Objects.requireNonNull(tournamentType);
        this.participantType = Objects.requireNonNull(participantType);

        status = TournamentStatus.SETUP;
        winner = Optional.empty();
    }

    /**
     * Starts this {@code Tournament}, given that it is still in the {@link TournamentStatus#SETUP} state.
     *
     * @throws IllegalStateException If this {@code Tournament} is in the {@link TournamentStatus#IN_PROGRESS} state.
     * @throws IllegalStateException If this {@code Tournament} is in the {@link TournamentStatus#FINISHED} state.
     */
    public void start() {
        if (status == TournamentStatus.IN_PROGRESS) {
            throw new IllegalStateException("This tournament has already been started.");
        }

        if (status == TournamentStatus.FINISHED) {
            throw new IllegalStateException("This tournament has already finished.");
        }

        status = TournamentStatus.IN_PROGRESS;
    }

    /**
     * Stops this {@code Tournament}, given that it is in the {@link TournamentStatus#IN_PROGRESS} state.
     *
     * @throws IllegalStateException If this {@code Tournament} is in the {@link TournamentStatus#SETUP} state.
     * @throws IllegalStateException If this {@code Tournament} is in the {@link TournamentStatus#FINISHED} state.
     */
    public void stop() {
        if (status == TournamentStatus.SETUP) {
            throw new IllegalStateException("This tournament has not yet been started.");
        }

        if (status == TournamentStatus.FINISHED) {
            throw new IllegalStateException("This tournament has already finished.");
        }

        status = TournamentStatus.FINISHED;
    }

    /**
     * Adds a {@link Participant} to this {@code Tournament}, given that this {@code Tournament} is in the
     * {@link TournamentStatus#SETUP} state.
     *
     * @param participant The {@code Participant} to add, should not be {@code null}.
     * @throws NullPointerException     If the given {@code Participant} is {@code null}.
     * @throws IllegalArgumentException If the given {@code Participant}'s {@link ParticipantType} is not the same type
     *                                  as this {@code Tournament}'s {@code ParticipantType}.
     * @throws IllegalStateException    If this {@code Tournament} is in the {@link TournamentStatus#IN_PROGRESS} state.
     * @throws IllegalStateException    If this {@code Tournament} is in the {@link TournamentStatus#FINISHED} state.
     */
    public void addParticipant(Participant participant) {
        requireLegalEntry(Objects.requireNonNull(participant));

        synchronized (participants) {
            participants.add(participant);
        }
    }

    /**
     * Removes a {@link Participant} from this {@code Tournament}, given that this {@code Tournament} is in the
     * {@link TournamentStatus#IN_PROGRESS} state.
     *
     * @param participant The {@code Participant} to remove, should not be {@code null}.
     * @throws NullPointerException     If the given {@code Participant} is {@code null}.
     * @throws IllegalArgumentException If the given {@code Participant}'s {@link ParticipantType} is not the same type
     *                                  as this {@code Tournament}'s {@code ParticipantType}.
     * @throws IllegalStateException    If this {@code Tournament} is in the {@link TournamentStatus#IN_PROGRESS} state.
     * @throws IllegalStateException    If this {@code Tournament} is in the {@link TournamentStatus#FINISHED} state.
     */
    public void removeParticipant(Participant participant) {
        requireLegalEntry(Objects.requireNonNull(participant));

        synchronized (participant) {
            participants.remove(participant);
        }
    }

    private void requireLegalEntry(Participant participant) {
        ParticipantType type = ParticipantUtil.getParticipantType(participant);
        if (participantType != type) {
            throw new IllegalArgumentException(String.format("The given participant's type (%s) is not of this tournament's participant type (%s).", type, participantType));
        }

        if (status == TournamentStatus.IN_PROGRESS) {
            throw new IllegalStateException("This tournament has already been started.");
        }

        if (status == TournamentStatus.FINISHED) {
            throw new IllegalStateException("This tournament has already finished.");
        }
    }

    /**
     * Gets the current {@code TournamentStatus} of this {@code Tournament}.
     *
     * @return The {@code TournamentStatus} of this {@code Tournament}.
     */
    public TournamentStatus getStatus() {
        return status;
    }

    /**
     * Gets the winner of this {@code Tournament}.
     *
     * @return An {@link Optional} containing the winner of this {@code Tournament}. May be empty if no winner has
     * been determined yet.
     */
    public Optional<Participant> getWinner() {
        return winner;
    }

    /**
     * Gets all {@link Participant participants} competing in this {@code Tournament}.
     *
     * @return A {@link List} containg all {@code Participants} competing in this {@code Tournament}.
     */
    public List<Participant> getParticipants() {
        List<Participant> list = new ArrayList<>();

        synchronized (participants) {
            list.addAll(participants);
        }

        return list;
    }

    /**
     * Gets the {@link TournamentType} of this {@code Tournament}.
     *
     * @return The {@code TournamentType} of this {@code Tournament}.
     */
    public TournamentType getTournamentType() {
        return tournamentType;
    }

    /**
     * Gets the {@link ParticipantType} of this {@code Tournament}.
     *
     * @return The {@code ParticipantType} of this {@code Tournament}.
     */
    public ParticipantType getParticipantType() {
        return participantType;
    }

}
