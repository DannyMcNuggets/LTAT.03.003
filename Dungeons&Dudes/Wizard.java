
public class Wizard extends Dude{
    Effect outOfActionsWizard = new OutOfActionsWizard();

    public Wizard(int accuracy, int armour, int health, int actionPoints, String shortName, String fullName, String intro) {
        super(accuracy, armour, health, actionPoints, shortName, fullName, intro);
    }

    @Override
    Effect chooseSpell(Dude attackTarget) {
        int roll = this.dieRoll(D6);
        boolean prefersFirebolt = roll > 2;

        Effect primary = prefersFirebolt ? new Firebolt() : new Spiderweb();
        Effect secondary = prefersFirebolt ? new Spiderweb() : new Firebolt();

        if (this.getActionPoints() >= primary.requiredActionPoints()) {
            return primary;
        } else if (this.getActionPoints() >= secondary.requiredActionPoints()) {
            return secondary;
        }

        outOfActionsWizard.castingMessage(this);
        return null;
    }


    public void displayVictory(){
        // https://www.asciiart.eu/people/occupations/wizards
        String [] art = new String[]{
                "         /^\\  ",
                "    /\\   \"V\"",
                "   /__\\   I ",
                "  //..\\\\  I ",
                "  \\].`[/  I",
                "  /l\\/j\\  (]",
                " /. ~~ ,\\/I ",
                " \\\\L__j^\\/I",
                "  \\/--v}  I",
                "  |    |  I ",
                "  |    |  I ",
                "  |    l  I ",
                "_/j  L l\\_!"
        };

        for (String line : art) {
            System.out.println(line);
        }
    }
}
