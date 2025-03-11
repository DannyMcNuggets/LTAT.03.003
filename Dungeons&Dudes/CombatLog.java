import java.util.List;
import java.util.Random;

public class CombatLog {

    private static final Random rand = new Random();

    private static final List<String> healthUpdates = List.of(
        "\nMid-round report: %s is down to %d health, while %s is at %d.",
        "\nStatus check: %s has %d HP, and %s has %d.",
        "\nHow are we looking? %s is at %d, and %s is at %d. Someone's getting a beatdown!",
        "\nBattlefield update! %s's health: %d. %s's health: %d.",
        "\nRound summary: %s is struggling with %d health. %s is holding strong at %d.",
        "\nCombat analysis: %s has %d health remaining. %s is currently at %d.",
        "\nQuick health update: %s has %d health. %s is at %d health.",
        "\nIntermediate health check: %s at %d HP, %s at %d HP. It's a wild fight!",
        "\nHealth report: %s is at %d health, %s is at %d health. The arena is buzzing!");

    public static void displayHealthUpdate(String currentName, int currentHealth, String targetName, int targetHealth) {
        String message = healthUpdates.get(rand.nextInt(healthUpdates.size()));
        System.out.printf(message, currentName, currentHealth, targetName, targetHealth);
        System.out.println("\n");
    }

}
