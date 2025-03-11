import java.util.List;

public class DrawIntro {
    public static void drawIntro(int LONG_SLEEP, int SHORT_SLEEP, List<Dude> champs) throws InterruptedException {

        String[] asciiArt = {
                " ______            _        _______  _______  _______  _        _______      __      ______            ______   _______  _______ ",
                "(  __  \\ |\\     /|( (    /|(  ____ \\(  ____ \\(  ___  )( (    /|(  ____ \\    /__\\    (  __  \\ |\\     /|(  __  \\ (  ____ \\(  ____ ",
                "| (  \\  )| )   ( ||  \\  ( || (    \\/| (    \\/| (   ) ||  \\  ( || (    \\/   ( \\/ )   | (  \\  )| )   ( || (  \\  )| (    \\/| (    \\/",
                "| |   ) || |   | ||   \\ | || |      | (__    | |   | ||   \\ | || (_____     \\  /    | |   ) || |   | || |   ) || (__    | (_____ ",
                "| |   | || |   | || (\\ \\) || | ____ |  __)   | |   | || (\\ \\) |(_____  )    /  \\/\\  | |   | || |   | || |   | ||  __)   (_____  )",
                "| |   ) || |   | || | \\   || | \\_  )| (      | |   | || | \\   |      ) |   / /\\  /  | |   ) || |   | || |   ) || (            ) |",
                "| (__/  )| (___) || )  \\  || (___) || (____/\\| (___) || )  \\  |/\\____) |  (  \\/  \\  | (__/  )| (___) || (__/  )| (____/\\/\\____) |",
                "(______/ (_______)|/    )_)(_______)(_______/(_______)|/    )_)\\_______)   \\___/\\/  (______/ (_______)(______/ (_______/\\_______)",
                "\nEnter, brave souls, where the roar of the crowd and the clang of combat will forge legends in the Dungeons and Dudes arena.\n"

        }; // https://patorjk.com/software/taag/#p=display&f=Epic&t=Dungeons%20%26%20DUDES

        for (String line : asciiArt) {
            System.out.println(line);
            Thread.sleep(SHORT_SLEEP);
        }

        Thread.sleep(LONG_SLEEP);

        // yeah, hardcoding is not cool...
        System.out.println("A thunderous drumroll, a slightly off-key trombone fanfare, and now, the moment you've all been waiting for: our first champion!");
        Thread.sleep(LONG_SLEEP);
        System.out.println(champs.get(0).getIntro());
        Thread.sleep(LONG_SLEEP);
        System.out.println("");
        System.out.println("...The dust settles, the crowd roars for more! But wait, what's this? A shimmering portal opens, and a strange, sweet scent fills the air...");
        Thread.sleep(LONG_SLEEP);
        System.out.println(champs.get(1).getIntro());
        Thread.sleep(LONG_SLEEP);

        System.out.println("\nThe moment of truth has arrived! Let the battle begin!\n");

    }
}
