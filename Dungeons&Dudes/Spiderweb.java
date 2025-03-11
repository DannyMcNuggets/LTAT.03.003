import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Spiderweb implements Effect{

    private int duration = 2;
    private static final int COST = 4;
    private boolean expired = false;

    private static final List<String> castingDescriptions = List.of(
        " casts Spiderweb, weaving a sticky web with a flick of his fingers!",
        " casts Spiderweb, conjuring strands of magical silk with a low chant!",
        " casts Spiderweb, spinning a web of arcane energy with a graceful gesture!",
        " casts Spiderweb, summoning a clinging net of shadows with a subtle incantation!",
        " casts Spiderweb, entangling the air with a series of intricate hand movements!",
        " casts Spiderweb, releasing a cloud of magical webs with a puff of arcane smoke!",
        " casts Spiderweb, trapping the target with a sly grin and a flick of his wrist!",
        " casts Spiderweb, the shadows around the target begin to coalesce into a web.",
        " casts Spiderweb, he pulls strands of magic from the air, forming the web."
    );

    @Override
    public void onHit(Dude effectTarget) {
    }

    @Override
    public void onTurnStart(Dude effectTarget) {
        if (duration > 0) {
            int roll = effectTarget.dieRoll(D6);
            effectTarget.actionPointsReduction(roll);
            System.out.println(effectTarget.getFullName() + " is covered in web... " + "He lost " + roll + " action points.");
            duration--;
        } else {
            expired = true;
        }
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
        return expired;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public String spellName() {
        return "Spiderweb";
    }

}
