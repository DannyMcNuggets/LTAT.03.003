import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OutOfActionsWizard implements Effect {

    private static final int COST = 0;

    private static final List<String> castingDescriptions = List.of(
        "\"My mana... it's gone!\" Wizardo exclaims, his hands fluttering.",
        "\"Out of juice!\" Wizardo sighs, his shoulders slumping.",
        "\"Where did all my magic go?\" Wizardo wonders, scratching his head.",
        "\"I seem to have misplaced my energy,\" Wizardo mumbles, looking bewildered.",
        "\"Well, this is awkward,\" Wizardo admits, fidgeting with his robes.",
        "\"I'm experiencing a temporary magical outage,\" Wizardo announces, waving his hands helplessly.",
        "\"I'd cast a spell, but... you know,\" Wizardo trails off, shrugging.",
        "\"I need a recharge!\" Wizardo declares, tapping his staff on the ground."
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
    public String spellName() {return "Out of Actions";}

}
