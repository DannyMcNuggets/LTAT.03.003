import java.util.Map;

public abstract class Teos implements Comparable<Teos>{

    final String kirjeldus; // we dont make getters for now
    final String tähis;
    String laenutaja ;
    int päevadeArv;

    public Teos(String kirjeldus, String tähis, String laenutaja , int päevadeArv) {
        this.kirjeldus = kirjeldus;
        this.tähis = tähis;
        this.laenutaja  = laenutaja ;
        this.päevadeArv = päevadeArv;
    }

    public abstract boolean kasHoidlast();

    @Override
    public int compareTo(Teos o) {
        return this.kirjeldus.compareTo(o.kirjeldus);
    }

    public int laenutusaeg(){
        switch (tähis){
            case "roheline":
                return 1;
            case "puudub":
                return 14;
            case "sinine":
                return 60;
            case "kollane":
                return 30;
            default:
                return 0;
        }
    }

    public double päevaneViivis(){
        Map<String, Double> finePerDay = Map.of( // switch case is more reasonable, but just for the sake of practice...
                "roheline", 2.0,
                "puudub", 0.15,
                "kollane", 0.05,
                "sinine", 0.05
        );

        return finePerDay.getOrDefault(tähis, 0.0);
    }

    public void arvutaViivis(Kontrollija kontrollija){
        int allowedDays = laenutusaeg();
        if (päevadeArv > allowedDays){
            int overdueDays = päevadeArv - allowedDays;
            double fine = overdueDays * päevaneViivis();
            kontrollija.salvestaViivis(laenutaja , kirjeldus, fine);
        }
    }

    public String toString(){
        String tellidahoidlast = "Ei ole vaja hoidlast tellida";
        if (kasHoidlast()) tellidahoidlast = "Jah, on vaja hoidlast tellida";
        return ("Kirjeldus: " + kirjeldus + ". Tähis: " + tähis + " Laenutaja: " + laenutaja  + " Päevad: " + päevadeArv
        + " " + tellidahoidlast);
    }

}
