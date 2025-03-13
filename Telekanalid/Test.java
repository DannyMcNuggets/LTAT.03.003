import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) { // it feels like something is missing but seems fine too...

        List<String> pkruNews = new ArrayList<>(List.of("남조선 해방 임박!", "배고픔 없다!", "핵무기 만세!", "주체는 영원하다!"));
        TVStation PKRU = new TVStation(pkruNews);

        List<String> foxNews = new ArrayList<>(List.of("Guns = Freedom", "Fox = Truth", "God bless Trump", "China did it!"));
        TVStation FOX = new TVStation(foxNews);

        PirateStation PIRATE = new PirateStation();
        PKRU.subscribe(PIRATE);
        FOX.subscribe(PIRATE);

        TV KimJongUn = new TV("Kim Jong Un");
        PKRU.subscribe(KimJongUn);
        FOX.subscribe(KimJongUn);

        TV KimYongNam = new TV("Kim Yong Nam");
        PKRU.subscribe(KimYongNam);

        TV PakPongJu = new TV("Pak Pong Ju");
        PIRATE.subscribe(PakPongJu);

        while (!PKRU.newsEmpty() || !FOX.newsEmpty()) {
            if (!PKRU.newsEmpty()) {
                System.out.println("\nPKRU broadcasting");
                PKRU.sendNews();
            }

            if (!FOX.newsEmpty()) {
                System.out.println("\nFOX broadcasting");
                FOX.sendNews();
            }
        }
    }
}
