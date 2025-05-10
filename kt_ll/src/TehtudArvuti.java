import java.time.LocalDateTime;

public class TehtudArvuti extends Arvuti{

    boolean monitoriga;
    double hind;

    public TehtudArvuti(String tootja, boolean onKiirtöö, LocalDateTime time, double hind) {
        super(tootja, onKiirtöö, time);
        this.hind = hind;
    }


    public double getHind() {
        return hind;
    }
}
