import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Firebolt implements Effect{

    private boolean expired = false;
    private static final int COST = 5;

    private static final List<String> castingDescriptions = List.of(
            " casts Firebolt, hurling a bolt of fire with a flick of his wrist!",
            " casts Firebolt, igniting a fiery projectile with a crackling incantation!",
            " casts Firebolt, launching a searing blast with a wave of his staff!",
            " casts Firebolt, conjuring a ball of flame with a whispered word!",
            " casts Firebolt, unleashing a burst of heat with a snap of his fingers!",
            " casts Firebolt, sending a wave of fire with a flourish of his robe!",
            " casts Firebolt, spitting a fireball with a mischievous grin!",
            " casts Firebolt, with a focused gaze, he sends the flame towards his foe.",
            " casts Firebolt, the air crackles with energy as the firebolt leaves his hands."
    );

    @Override
    public void onHit(Dude effectTarget) {
    }

    @Override
    public void onTurnStart(Dude effectTarget) {
    }

    @Override
    public void onTurnEnd(Dude effectTarget) {
        int roll = effectTarget.dieRoll(D10);
        System.out.println("FIREBOLT! Burn, " + effectTarget.getShortName() + ", burn! " + "Damage: " + roll);
        effectTarget.healthReduction(roll);
        expired = true;
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
        return expired;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public String spellName() {
        return "Firebolt";}


}
