import java.util.ArrayList;
import java.util.List;

public class ViiviseHoiataja implements Kontrollija{

    private final double lubatudViivis;
    private List<String> laenutajadÜlePiiri = new ArrayList<>();

    public ViiviseHoiataja(double lubatudViivis){
        this.lubatudViivis = lubatudViivis;
    }

    @Override
    public void salvestaViivis(String laenutaja, String teoseKirjeldus, double viiviseSuurus) {
        if (!laenutajadÜlePiiri.contains(laenutaja) && viiviseSuurus > this.lubatudViivis) {
            laenutajadÜlePiiri.add(laenutaja);
        }
    }

    public List<String> getHoiatatavadLaenutajad(){
        return laenutajadÜlePiiri;
    }

}
