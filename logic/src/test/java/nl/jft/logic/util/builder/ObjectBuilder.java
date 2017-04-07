package nl.jft.logic.util.builder;

/**
 * @author Lesley
 */
public final class ObjectBuilder {

    private ObjectBuilder() {
        throw new UnsupportedOperationException("Should not be called.");
    }

    public static TeamBuilder team() {
        return new TeamBuilder();
    }

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public static GoalBuilder goal() {
        return new GoalBuilder();
    }

    public static TournamentBuilder tournament() {
        return new TournamentBuilder();
    }

    public static MatchResultBuilder matchResult() {
        return new MatchResultBuilder();
    }

    public static MatchBuilder match() {
        return new MatchBuilder();
    }

    public static RuleBuilder rule() {
        return new RuleBuilder();
    }

    public static TitleBuilder title() {
        return new TitleBuilder();
    }

    public static class Statistic {

        public static StatisticCollectionBuilder collection() {
            return new StatisticCollectionBuilder();
        }

        public static TitleStatisticBuilder title() {
            return new TitleStatisticBuilder();
        }

        public static MatchStatisticBuilder match() {
            return new MatchStatisticBuilder();
        }

        public static RatingStatisticBuilder rating() {
            return new RatingStatisticBuilder();
        }

    }

    public static class Rating {

        public static GlickoRatingBuilder glickoRating() {
            return new GlickoRatingBuilder();
        }

    }

}
