
public interface Effect {

    void onHit(Dude effectTarget);

    void onTurnStart(Dude effectTarget);

    void onTurnEnd(Dude effectTarget);

    void castingMessage(Dude caster);

    int requiredActionPoints();

    boolean isExpired();

    boolean isInstant();

    String spellName();

    final int D4 = 4, D6=6, D8=8, D10 = 10, D12 = 12, D20 = 20; // just in case

}
