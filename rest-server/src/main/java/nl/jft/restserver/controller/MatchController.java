package nl.jft.restserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import nl.jft.common.rating.glicko.GlickoRating;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchType;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.Title;
import nl.jft.logic.participant.impl.User;

@RestController
public final class MatchController {

    private final Random random = new Random();
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<Integer, Match> matches = new HashMap<>();
    private final Map<Integer, Participant> participants = new HashMap<>();

    public MatchController() {
        Participant firstParticipant = new User(count.incrementAndGet(), "Lesley", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));
        participants.put(firstParticipant.getId(), firstParticipant);

        Participant secondParticipant = new User(count.incrementAndGet(), "Oscar", new GlickoRating(1500, 350, 0.06), new Title("Schützelkoning"));
        participants.put(secondParticipant.getId(), secondParticipant);

        for (int i = 0; i < 50; i++) {
            Match match = createRandomMatch(firstParticipant, secondParticipant);
            matches.put(match.getId(), match);
        }
    }

    @RequestMapping("/match")
    public Match match(@RequestParam("match_id") int matchId) {
        Match match = matches.get(matchId);
        return match;
    }

    @RequestMapping("/matches")
    public Collection<Match> matches() {
        return matches.values();
    }

    @RequestMapping("/score")
    public Match score(@RequestParam("match_id") int matchId, @RequestParam("player_id") int playerId) {
        Match match = matches.get(matchId);
        Participant participant = participants.get(playerId);

        Goal goal = new Goal(participant, new Date());
        match.addGoal(goal);

        return match;
    }

    private Match createRandomMatch(Participant firstParticipant, Participant secondParticipant) {
        Match match = new Match(count.incrementAndGet(), firstParticipant, secondParticipant, MatchType.FRIENDLY);

        /*int goalsFirstParticipant = 0;
        int goalsSecondParticipant = 0;

        while (goalsFirstParticipant < 10 && goalsSecondParticipant < 10) {
            boolean first = random.nextBoolean();

            if (first) {
                goalsFirstParticipant += 1;
            } else {
                goalsSecondParticipant += 1;
            }

            Participant participant = first ? firstParticipant : secondParticipant;
            Date date = new Date();

            match.addGoal(new Goal(count.incrementAndGet(), participant, date));
        }*/

        return match;
    }
}
