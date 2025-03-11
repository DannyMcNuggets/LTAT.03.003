import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int LONG_SLEEP = 0; // just for fun. decrease if needed
    static int SHORT_SLEEP = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Dude> champions = initiateChampions();

        DrawIntro.drawIntro(LONG_SLEEP, SHORT_SLEEP, champions);

        while (champions.size() > 1) {
            for (int i = 0; i < champions.size(); i++) {
                Dude current = champions.get(i);
                Dude target = champions.get((i + 1) % champions.size()); // pick next one. rework if more than 2 champs

                TurnAnouncer.announceTurn(current.getFullName(), current.getActionPoints());

                current.takeTurn(target);

                //Thread.sleep(LONG_SLEEP);
                CombatLog.displayHealthUpdate(current.getShortName(), current.getHealth(), target.getShortName(), target.getHealth());

                champions.removeIf(champion -> !champion.isAlive());
            }
        }

        if (champions.isEmpty()) {
            declareDraw();
        } else {
            declareWinner(champions.getFirst());
        }
    }

    public static void declareWinner(Dude winner) {
        System.out.println("Hear ye, hear ye! The arena trembles before the mighty " + winner.getFullName() + "! A true champion has emerged!");
        winner.displayVictory();
    }

    public static void declareDraw() {
        System.out.println("Draw! Everyone gets a participation trophy. Or not.");
    }

    private static List<Dude> initiateChampions() {
        List<Dude> champions = new ArrayList<>();
        Fighter fighter = new Fighter(  5, 14,17,6, "Sir Grog",
                "Sir Grog Thunderpants", "Behold, Sir Grog Thunderpants, whose battle cry is only slightly louder than his stomach's rumble!");
        Wizard wizard = new Wizard(7,8,13, 10, "Wizardo",
                "Wizardo the Slightly Confused", "Wizardo the Slightly Confused, whose spells are as unpredictable as his hat, is ready to... maybe do some magic!");
        champions.add(fighter);
        champions.add(wizard);
        Collections.shuffle(champions);
        return champions;
    }
}