import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeaponAttack implements Effect {

    private static final int COST = 3;

    private static final List<String> castingDescriptions = List.of(
        " swings a WeaponAttack, a brutal strike with his trusty weapon!",
        " lands a WeaponAttack, a powerful blow with a resounding thud!",
        " delivers a WeaponAttack, a swift and deadly strike!",
        " unleashes a WeaponAttack, a flurry of blows with unmatched ferocity!",
        " connects with a WeaponAttack, a solid hit that sends the opponent reeling!",
        " strikes with a WeaponAttack, a well-aimed blow that finds its mark!",
        " executes a WeaponAttack, a forceful swing that leaves a mark!",
        " smashes a WeaponAttack, his weapon connects with a heavy impact!",
        " brings down a WeaponAttack, a decisive strike that echoes through the arena!"
    );

    @Override
    public void onHit(Dude effectTarget) {
        int roll = effectTarget.dieRoll(D6);
        effectTarget.healthReduction(roll);
        System.out.println(effectTarget.getShortName() + " receives " + roll + " damage!");
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
    public String spellName() {return "Weapon Attack";}

}
