import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Dude {

    private final int actionPointsCONST;
    private int accuracy;
    private int armor;
    private int health;
    private int actionPoints;
    private final String shortName;
    private final String fullName;
    private final String intro;
    protected final static int D4 = 4, D6=6, D8=8, D10 = 10, D12 = 12, D20 = 20;

    List<Effect> debuffs = new ArrayList<>();

    public Dude(int accuracy, int armor, int health, int actionPoints, String shortName, String fullname, String intro) {
        this.accuracy = accuracy;
        this.armor = armor;
        this.health = health;
        this.actionPoints = actionPoints;
        this.actionPointsCONST = actionPoints;
        this.shortName = shortName;
        this.fullName = fullname;
        this.intro = intro;
    }

    void takeTurn(Dude attackTarget){

        debuffs.forEach(e -> e.onTurnStart(this));

        if (!this.isAlive()) return;

        castSpell(attackTarget);

        for (Effect effect : debuffs){
            effect.onTurnEnd(this);
        }

        debuffs.removeIf(Effect::isExpired);

        this.actionPoints = this.actionPointsCONST; // restore action points
    }

    void castSpell(Dude attackTarget){
        Effect spell = this.chooseSpell(attackTarget);
        if (spell != null){
            applySpell(attackTarget, spell);
        }

    }

    void applySpell(Dude attackTarget, Effect spell) {
        if (spell.requiredActionPoints() <= this.getActionPoints()) {

            boolean hit = (dieRoll(D20) + this.getAccuracy()) >= attackTarget.getArmor();

            if (hit) {
                spell.castingMessage(this);

                if (spell.isInstant()) {
                    spell.onHit(attackTarget);
                } else {
                    attackTarget.addEffect(spell);
                }
            } else {
                System.out.printf("%s missed %s%n",
                        this.getFullName(),
                        spell.spellName());
            }

            this.actionPointsReduction(spell.requiredActionPoints());
        }
    }

    abstract Effect chooseSpell(Dude attackTarget);

    public int getAccuracy(){
        return accuracy;
    }

    public int getArmor(){
        return armor;
    }

    public int getHealth(){
        return health;
    }

    public int getActionPoints(){
        return actionPoints;
    }

    public double getActionPointsCONST() {
        return actionPointsCONST;
    }

    public String getShortName(){
        return shortName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getIntro(){
        return intro;
    }

    void addEffect(Effect effect){
        debuffs.add(effect);
    }

    boolean isAlive(){
        return this.health > 0;
    }

    void healthReduction(int damage){
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    void actionPointsReduction(int decrease){
        this.actionPoints -= decrease;
        if (this.actionPoints < 0) this.actionPoints = 0;
    }

    public int dieRoll(int roll){
        return ThreadLocalRandom.current().nextInt(roll) + 1;
    }

    abstract void displayVictory();
}
