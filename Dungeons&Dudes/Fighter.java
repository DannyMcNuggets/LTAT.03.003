
public class Fighter extends Dude{

    Effect outOfActionsFighter = new OutOfActionsFighter();

    public Fighter(int accuracy, int armour, int health, int actionPoints, String shortName, String fullName, String intro){
        super(accuracy, armour, health, actionPoints, shortName, fullName, intro);
    }

    @Override
    Effect chooseSpell(Dude attackTarget) {

        int roll = this.dieRoll(D4);
        Effect chosenSpell = (roll > 2) ? new WeaponAttack() : new Knockdown();

        if (this.getActionPoints() >= chosenSpell.requiredActionPoints()) {
            return chosenSpell;
        }

        outOfActionsFighter.castingMessage(this);
        return null;
    }

    public void displayVictory() {
        // https://www.asciiart.eu/people/occupations/knights
        String [] art = new String[] {
                " / \\",
                "  | |",
                "  |.|",
                "  |.|",
                "  |:|      __",
                ",_|:|_,   /  )",
                "  (Oo    / _I_",
                "   +\\ \\  || __|",
                "      \\ \\||___|",
                "        \\ /.:.\\-\\",
                "         |.:. /-----",
                "         |___|::oOo::|",
                "         /   |:<_T_>:|",
                "        |_____\\ ::: /",
                "         | |  \\ \\:/",
                "         | |   | |",
                "         \\ /   | \\___",
                "         / |   \\_____\\",
                "         `-' "
        };

        for (String line : art) {
            System.out.println(line);
        }
    }
}
