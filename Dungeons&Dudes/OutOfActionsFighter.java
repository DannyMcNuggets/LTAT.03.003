import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OutOfActionsFighter implements Effect {

    private static final int COST = 0;

    private static final List<String> castingDescriptions = List.of(
            "\"My muscles... they refuse!\" Grog strains.",
            "\"This... this is unacceptable!\" Grog roars.",
            "\"My blood boils, but my legs won't budge!\" Grog growls.",
            "\"Can't... reach... my sword...\" Grog mutters.",
            "\"I'm bound by unseen chains!\" Grog exclaims.",
            "\"I need a moment...\" Grog breathes heavily."
    );

    @Override
    public void onHit(Dude effectTarget) {
    }

    @Override
    public void onTurnStart(Dude effectTarget) {
    }

    @Override
    public void onTurnEnd(Dude effectTarget) {
    }

    @Override
    public void castingMessage(Dude caster) {
        System.out.println(caster.getShortName() + castingDescriptions.get(ThreadLocalRandom.current().nextInt(castingDescriptions.size())));
    }

    @Override
    public int requiredActionPoints() {
        return COST;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public String spellName() {
        return "Out of Actions";
    }
}
