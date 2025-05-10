import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Arvutiparandus {
    public static void main(String[] args) throws IOException, URISyntaxException {

        if (args.length != 1) return;

        Deque<Arvuti> arvutid = readFile(args[0]);
        Map<String, Double> tunnitasud = readTunnitasud("tunnitasud.dat");
        List<TehtudArvuti> tehtud = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)){
            while (true) {
                System.out.println("(P)arandada, uut tööd (R)egistreerida või (L)õpetada");
                if (sc.hasNextLine()) {
                    String line = sc.nextLine().trim().toUpperCase();
                    switch (line) {
                        case "P" -> {
                            tehtud.add(parandamine(arvutid.getFirst(), sc, tunnitasud));
                            arvutid.removeFirst();
                        }
                        case "R" -> {
                            Arvuti arvuti = addNew(sc);
                            if (arvuti.isOnKiirtöö()) {
                                arvutid.addFirst(arvuti);
                            } else {
                                arvutid.addLast(arvuti);
                            }
                        }
                    }
                    if (line.equals("L")) break;
                }
            }
        }
        writeTehtud(tehtud);
        writeTegemata(arvutid);
        outputSummary(tehtud, arvutid.size());

    }


    private static void outputSummary(List<TehtudArvuti> tehtud, int ootel){
        HashMap<String, Integer> results = new HashMap<>();
        double total = 0;

        for (TehtudArvuti arvuti : tehtud){
            total += arvuti.getHind();
            results.put(arvuti.getTootja(), results.getOrDefault(arvuti.getTootja(), 0) + 1);
        }

        System.out.println("Sessiooni kokkuvõte: \n" +
                "Teenitud raha: "+ Math.round(total*100.0)/100.0 +"€ \n" +
                "Parandatud arvuteid: ");

        results.forEach((tootja, kogus) ->
                System.out.println(" " + tootja + ": " + kogus + "tk")
        );

        System.out.println("Ootel jäi: " + ootel);

    }


    private static void writeTegemata(Deque<Arvuti> arvutid) throws IOException {
        try (DataOutputStream ds = new DataOutputStream(new FileOutputStream("ootel.txt"))){
            for (Arvuti arvuti : arvutid) {
                String line = arvuti.getTootja() + ";" +
                        (arvuti.isOnKiirtöö() ? "kiirtöö" : "tavatöö") + ";" +
                        (arvuti instanceof VäliseMonitorigaArvuti ? "monitoriga" : "") + "@" +
                        arvuti.getRegistreerimisAeg().toString();
                ds.write(line.getBytes("UTF-8"));
                ds.writeBytes(System.lineSeparator());
            }
        }
    }


    private static void writeTehtud(List<TehtudArvuti> tehtud) throws IOException {
        try (DataOutputStream ds = new DataOutputStream(new FileOutputStream("tehtud.dat"))){
            ds.writeInt(tehtud.size());
            for (TehtudArvuti arvuti : tehtud) {
                ds.writeUTF(arvuti.getTootja());
                ds.writeUTF(arvuti.getRegistreerimisAeg().toString());
                ds.writeDouble(arvuti.getHind());
            }
        }
    }


    private static Map<String, Double> readTunnitasud(String file) throws IOException{
        Map<String, Double> tunnitasud = new LinkedHashMap<>();
        try (DataInputStream ds = new DataInputStream(new FileInputStream(file))) {
            int n = ds.readInt();
            for (int i = 0; i < n; i++) {
                String nimi = ds.readUTF();
                double tunnitasu = ds.readDouble();
                tunnitasud.put(nimi, tunnitasu);
            }
        }
        return tunnitasud;
    }


    private static TehtudArvuti parandamine(Arvuti arvuti, Scanner sc, Map<String, Double> tunnitasud){
        // Arvuti info: ML;kiirtöö;monitoriga@2025-04-21T14:08:09
        System.out.println(
                arvuti.getTootja() + ";" +
                (arvuti.isOnKiirtöö() ? "kiirtöö" : "tavatöö") + ";" +
                (arvuti instanceof VäliseMonitorigaArvuti ? "monitoriga" : "") + "@" +
                arvuti.getRegistreerimisAeg()
        );

        int aeg;
        while (true){
            System.out.println("Sisesta parandamiseks kulunud aeg (täisminutites): ");
            try{
                aeg = sc.nextInt();
                sc.nextLine();
                if (aeg > 0) break;
                System.out.println("Aeg peab olema positivne");
            } catch (InputMismatchException e){
                sc.next();
            }
        }

        String name;
        while (true) {
            System.out.println("Sisesta enda nimi: ");
            name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Nimi ei või olla tühi.");
            } else if (!tunnitasud.containsKey(name)) {
               System.out.println("Nimi ei leitud, proovi uuesti.");
            } else break;
        }

        double lõpphind = arvuti.arvutaArveSumma(aeg * tunnitasud.get(name));
        System.out.println("Töö tehtud, arve summa on " + lõpphind + "€!");

        return new TehtudArvuti(arvuti.getTootja(), arvuti.isOnKiirtöö(), arvuti.getRegistreerimisAeg(), lõpphind);
    }


    private static Arvuti loeArvuti(String line){
        LocalDateTime date = LocalDateTime.now();
        String [] data;
        String tootja;


        if (line.contains("@")){
            String [] dataAndDate = line.split("@");
            data = dataAndDate[0].split(";");
            try {
                date = LocalDateTime.parse(dataAndDate[1]); // on '@' sees ja aeg on õige
            } catch (DateTimeParseException e){
            }
        } else {
            data = line.split(";");
        }

        // semikoolonitega eraldatud väljade arv on väiksem, kui kaks, või suurem, kui kolm
        if (data.length > 3 || data.length<2){
            throw new FormaadiErind("Elemente arv on vale. Peab olema 2 või 3, saadud: " + data.length);
        }

        tootja = data[0];

        // töö tüübi väljas olev väärtus ei ole "tavatöö" ega "kiirtöö
        if (!data[1].equals("kiirtöö") && !data[1].equals("tavatöö")) throw new FormaadiErind("Teine element ei ole kiir- või tavatöö");

        //väljade arv on kolm, aga kolmanda välja väärtus ei ole "monitoriga"
        if (data.length == 3){
            if (data[2].equals("monitoriga")) return new VäliseMonitorigaArvuti(tootja, data[1].equals("kiirtöö"), date);
            throw new FormaadiErind("Kolmas element ei vasta nõutele. Oodatud 'monitoriga'. Saadud: " + data[2]);
        }

        return new Arvuti(tootja, data[1].equals("kiirtöö"), date);
    }


    private static  Deque<Arvuti> readFile(String file) throws IOException, URISyntaxException {
        Deque<Arvuti> arvutid = new LinkedList<>();
        InputStream s = file.startsWith("http")
                ? new URI(file).toURL().openStream()
                : new FileInputStream(file);

        try(BufferedReader bf = new BufferedReader(new InputStreamReader(s, "UTF-8"))){
            String line;
            while ((line = bf.readLine()) != null) {
                Arvuti arvuti = loeArvuti(line);
                arvutid.add(arvuti);
            }
        }
        return arvutid;
    }


    private static Arvuti addNew(Scanner sc){

        String name;
        boolean kiirtöö;
        boolean monitoriga;

        do {
            System.out.println("Sisesta tootja: ");
            name = sc.nextLine();
        } while(name.isEmpty());

        while (true){
            System.out.println("(K)iirtöö või (T)avaline");
            String choice = sc.nextLine().trim().toUpperCase();
            if (choice.equals("K") || choice.equals("T")){
                kiirtöö = choice.equals("K");
                break;
            }
        }

        while (true){
            System.out.println("(M)onitoriga või (A)rvuti");
            String choice = sc.nextLine().trim().toUpperCase();
            if (choice.equals("M") || choice.equals("A")){
                monitoriga = choice.equals("M");
                break;
            }
        }

        LocalDateTime date = LocalDateTime.now();
        return monitoriga
                ? new VäliseMonitorigaArvuti(name, kiirtöö, date)
                : new Arvuti(name, kiirtöö, date);
    }
}
