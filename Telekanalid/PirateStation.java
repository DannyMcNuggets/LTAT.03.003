public class PirateStation extends Broadcaster implements BroadcastListener {

    @Override
    public void listen(String news) {
        broadcast(news);
    }
}
