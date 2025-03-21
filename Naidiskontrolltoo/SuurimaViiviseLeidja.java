public class SuurimaViiviseLeidja implements Kontrollija{

    private String maxLaenutaja = null;
    private String maxTeos = null;
    private double maxViivis = 0.0;

    @Override
    public void salvestaViivis(String laenutaja, String teoseKirjeldus, double viiviseSuurus) {
        if (viiviseSuurus > maxViivis){
            maxLaenutaja = laenutaja;
            maxTeos = teoseKirjeldus;
            maxViivis = viiviseSuurus;
        }
    }

    public void saadaHoiatus(){
       if (maxLaenutaja != null){
           System.out.println("Largest overdue is: " + maxViivis + " by " + maxLaenutaja);
       } else {
           System.out.println("No largest overdue found");
       }
    }
}
