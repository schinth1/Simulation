import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Simulation {

    private ArrayList<Rocket> rocketU1;
    private ArrayList<Rocket> rocketU2;

    public List<Item> loadItems(String textFile) {
        File file = new File(textFile);
        List<Item> items = new ArrayList<>();
        try{
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split("=");
//                Item item = new Item(data[0], Integer.parseInt(data[1]));
                items.add(new Item(data[0], Integer.parseInt(data[1])));

//                System.out.println(data[0] + " " + data[1]);
            }

        } catch(FileNotFoundException ex) {
            System.out.println("File not found");
        }

        return items;
    }

    public ArrayList<Rocket> loadU1(List<Item> items) {
        System.out.println("Loading U1...");
        rocketU1 = new ArrayList<>();
        Rocket rocket = new U1(100000000, 10000, 18000, 5, 1);

        Iterator iterator = items.iterator();

        while(iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if(rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU1.add(rocket);
                rocket = new U1(100000000, 10000, 18000, 5, 1);
                System.out.println("New rocket U1 created");
                rocket.carry(item);
            }

            if (!iterator.hasNext()) {
                rocketU1.add(rocket);
            }

        }

        return rocketU1;

    }

    public ArrayList<Rocket> loadU2(List<Item> items) {
        System.out.println("Loading U2...");
        rocketU2 = new ArrayList<>();
        Rocket rocket = new U2(120000000, 18000, 29000, 4, 8);

        Iterator iterator = items.iterator();

        while(iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if(rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU2.add(rocket);
                rocket = new  U2(120000000, 18000, 29000, 4, 8);
                System.out.println("New rocket U2 created");
                rocket.carry(item);
            }

            if (!iterator.hasNext()) {
                rocketU2.add(rocket);
            }

        }

        return rocketU2;

    }

    public static void main(String[] args) {
        Simulation s = new Simulation();
        List<Item> items = s.loadItems("phase1.txt");
    }

}
