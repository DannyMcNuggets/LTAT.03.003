import java.util.List;
import java.util.Random;

public class TurnAnouncer {

    private static final Random rand = new Random();

    private static final List<String> turnMessage = List.of(
        "Now it's %s's turn! They've got %d action points to burn.",
        "Up next: %s! With %d action points, what mischief will they cause?",
        "It's %s's time to shine! %d action points at the ready.",
        "The stage is set for %s! They have %d action points. Let the chaos begin!",
        "And now, the moment you've been waiting for! %s with %d action points.",
        "Get ready, folks! It's %s's turn with %d action points.",
        "%s steps into the arena with %d action points. What will they do?",
        "It is %s turn. They have %d action points.",
        "%s is up. Action points: %d. Let the battle continue!");

    public static void announceTurn(String name, int actionPoints) {
        String message = turnMessage.get(rand.nextInt(turnMessage.size()));
        System.out.printf(message, name, actionPoints);
        System.out.println();
    }
}