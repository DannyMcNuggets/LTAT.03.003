import java.time.LocalDateTime;

public class VäliseMonitorigaArvuti extends Arvuti {

    public VäliseMonitorigaArvuti(String tootja, boolean onKiirtöö, LocalDateTime date){
        super(tootja, onKiirtöö, date);
    }

    @Override
    public double arvutaArveSumma(double baashind){
        return baashind + (this.isOnKiirtöö()? 10 : 0) + 1;
    }
}
