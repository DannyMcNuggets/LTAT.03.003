import java.util.ArrayList;
import java.util.List;

public class Broadcaster {

    private final List<BroadcastListener> broadcastListeners = new ArrayList<>();

    public void subscribe(BroadcastListener listener) {
        broadcastListeners.add(listener);
    }

    public void broadcast(String news) {
        for (BroadcastListener listener : broadcastListeners) {
            listener.listen(news);
        }
    }

}
