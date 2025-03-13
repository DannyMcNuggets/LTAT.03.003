public class TV implements BroadcastListener {

    private final String name;

    public TV(String name) {
        this.name = name;
    }

    @Override
    public void listen(String news) {
        System.out.println(name + " received: " + news);
    }
}
