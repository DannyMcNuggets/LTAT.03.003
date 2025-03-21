public class Ajakiri extends Teos{

    private final int aasta;

    public Ajakiri(String kirjeldus, String tähis, String laenutaja, int päevadeArv, int aasta) {
        super(kirjeldus, tähis, laenutaja, päevadeArv);
        this.aasta = aasta;
    }

    @Override
    public boolean kasHoidlast() {
        return aasta <= 2000;
    }

    public String toString(){
        return ("Tegemist on ajakirjaga. " + super.toString());
    }
}
