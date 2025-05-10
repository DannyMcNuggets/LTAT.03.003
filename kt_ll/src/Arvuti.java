import java.time.LocalDateTime;

public class Arvuti {

    public String tootja;
    public boolean onKiirtöö;
    public LocalDateTime registreerimisAeg;

    public Arvuti(String tootja, boolean onKiirtöö, LocalDateTime time){
        this.tootja = tootja;
        this.onKiirtöö = onKiirtöö;
        this.registreerimisAeg = time;
    }

    public String getTootja() {
        return tootja;
    }

    public boolean isOnKiirtöö() {
        return onKiirtöö;
    }

    public LocalDateTime getRegistreerimisAeg() {
        return registreerimisAeg;
    }

    public double arvutaArveSumma(double baashind){
        return baashind + (this.isOnKiirtöö()? 10 : 0);
    }


}
