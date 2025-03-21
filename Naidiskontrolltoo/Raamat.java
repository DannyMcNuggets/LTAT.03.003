import java.util.List;
import java.util.Set;

public class Raamat extends Teos{

    public Raamat(String teoseKirjeldus, String tähis, String laenutaja, int päevadeArv) {
        super(teoseKirjeldus, tähis, laenutaja, päevadeArv);
    }

    @Override
    public boolean kasHoidlast() {
        Set<String> bookMarkings = Set.of("sinine", "kollane"); // could be switch-case
        return bookMarkings.contains(this.getTahis());
    }

    public String toString(){
        return ("Tegemist on raamatuga. " + super.toString());
    }

    public String getTahis(){
        return this.tähis;
    }
}
