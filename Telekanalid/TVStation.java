import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TVStation extends Broadcaster {

    private final List<String> news;

    public TVStation(List<String> news) {
        this.news = news;
    }

    public boolean newsEmpty(){
        return news.isEmpty();
    }

    public void sendNews() {
        if (news.isEmpty()) return;
        String randomNews = news.get(ThreadLocalRandom.current().nextInt(news.size()));
        broadcast(randomNews);
        news.remove(randomNews);
    }
}
