import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Peaklass {

    public static void main(String[] args) throws Exception {

        String fileName = "laenutus.txt";

        File file = new File(fileName);

        List<Teos> libraryList = readFile(file);

        Collections.sort(libraryList);

        for(Teos i : libraryList){
            System.out.println(i);
        }

        ViiviseHoiataja viiviseHoiataja = new ViiviseHoiataja(0.2);
        SuurimaViiviseLeidja suurimaViiviseLeidja = new SuurimaViiviseLeidja();

        for(Teos i : libraryList){
            viiviseHoiataja.salvestaViivis(i.laenutaja , i.kirjeldus, i.päevadeArv);
        }

        for (String i : viiviseHoiataja.getHoiatatavadLaenutajad()){
            System.out.println(i);
        }

        for(Teos i : libraryList){
            i.arvutaViivis(suurimaViiviseLeidja);
        }

        suurimaViiviseLeidja.saadaHoiatus();

    }

    public static List<Teos> readFile(File file) throws FileNotFoundException { // could be try-with resources but not this time
        List<Teos> libraryList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] parts = line.split("; ");

            String description = parts[0];
            String nameOfBook = parts[1];
            String borrower = parts[2];
            int borrowedFor = Integer.parseInt(parts[3]);

            if (description.contains("/")){
                String [] journalParts = description.split("/");
                description = journalParts[0];
                int year = Integer.parseInt(journalParts[1].substring(0,4)); // clunky and unreliable
                libraryList.add(new Ajakiri(description, nameOfBook, borrower, borrowedFor, year));
            } else {
                libraryList.add(new Raamat(description, nameOfBook, borrower, borrowedFor));
            }
        }
        return libraryList;
    }
}
