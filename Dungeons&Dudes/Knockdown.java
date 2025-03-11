import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Knockdown implements Effect {

    private static final int COST = 2;

    private static final List<String> castingDescriptions = List.of(
            " delivers a Knockdown, swinging a mighty blow with a thunderous roar!",
            " lands a Knockdown, delivering a crushing strike with a grunt of effort!",
            " slams a Knockdown, bringing his weapon down with a force that shakes the ground!",
            " charges in for a Knockdown, delivering a powerful shoulder tackle!",
            " unleashes a Knockdown, a brutal haymaker with a snarl!",
            " stomps a Knockdown, bringing his foot down with a decisive stomp!",
            " executes a Knockdown, putting all his strength into a sweeping blow!",
            " shoves a Knockdown, sending the target reeling with a powerful push.",
            " performs a Knockdown, putting his entire body weight behind the attack, aiming to topple his foe."
    );

    @Override
    public void onHit(Dude effectTarget) {
        int roll = effectTarget.dieRoll(D6);
        effectTarget.actionPointsReduction(roll);
        System.out.println(effectTarget.getFullName() + " knocked down! He lost " + roll + " action points.");
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
        return true;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public String spellName() {
        return "Knockdown";
    }

}
