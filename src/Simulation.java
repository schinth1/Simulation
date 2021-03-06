import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Simulation {

    public List<Item> loadItems(String textFile) {
        File file = new File(textFile);
        List<Item> items = new ArrayList<>();
        try{
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split("=");
                items.add(new Item(data[0], Integer.parseInt(data[1])));
            }

        } catch(FileNotFoundException ex) {
            System.out.println("File not found");
        }

        return items;
    }

    public List<Rocket> loadU1(List<Item> items) {
        System.out.println("Loading U1...");
        List<Rocket> rocketU1 = new ArrayList<>();
        Rocket rocket = new Rocket(100000000, 10000, 18000, 5, 1);

        int u1count = 1;
        boolean carried;
        rocketU1.add(rocket);

        for(Item item: items) {
            carried = false;
            for(Rocket rk: rocketU1) {
                if(rk.canCarry(item)){
                    rk.carry(item);
                    carried = true;
                    break;
                }
            }
            if(!carried) {
                rocket = new Rocket(100000000, 10000, 18000, 5, 1);
                rocket.carry(item);
                rocketU1.add(rocket);
                u1count++;
            }
        }

        System.out.println("Total U1 rockets loaded: " + u1count);

        return rocketU1;

    }

    public List<Rocket> loadU2(List<Item> items) {
        System.out.println("Loading U2...");
        List<Rocket> rocketU2 = new ArrayList<>();
        Rocket rocket = new Rocket(120000000, 18000, 29000, 4, 8);

        int u2count = 1;
        boolean carried;
        rocketU2.add(rocket);


        for(Item item: items) {
            carried = false;
            for(Rocket rk: rocketU2) {
                if(rk.canCarry(item)){
                    rk.carry(item);
                    carried = true;
                    break;
                }
            }
            if(!carried) {
                rocket = new Rocket(120000000, 18000, 29000, 4, 8);
                rocket.carry(item);
                rocketU2.add(rocket);
                u2count++;
            }
        }
        System.out.println("Total U2 rockets loaded: " + u2count);

        return rocketU2;

    }

    public long runSimulation(List<Rocket> rockets) {
        int failureCount = 0;
        long totalCost = 0;
        for(Rocket r: rockets) {
            while(!r.launch()){
                r.launch();
                failureCount++;
            }

            while(!r.land()) {
                r.land();
                failureCount++;
            }
        }

//        System.out.println("Failure Count:" + failureCount);
        totalCost = rockets.get(0).getCost() * (rockets.size() + failureCount);

        return totalCost;
    }

    public static void main(String[] args) {
        Simulation s = new Simulation();
        List<Item> phase1Items = s.loadItems("phase1.txt");
        List<Item> phase2Items = s.loadItems("phase2.txt");

        // U1 Rocket phase1 and phase2 fleet
        List<Rocket> u1FleetPhase1 = s.loadU1(phase1Items);
        List<Rocket> u1FleetPhase2 = s.loadU1(phase2Items);

        // U2 Rocket phase1 and phase2 fleet
        List<Rocket> u2FleetPhase1 = s.loadU2(phase1Items);
        List<Rocket> u2FleetPhase2 = s.loadU2(phase2Items);


        long u1BudgetPhase1 = s.runSimulation(u1FleetPhase1);
        long u1BudgetPhase2 = s.runSimulation(u1FleetPhase2);

        System.out.println("U1 fleet budget for phase 1: $" + u1BudgetPhase1/1000000 + " " + "millions");
        System.out.println("U1 fleet budget for phase 2: $" + u1BudgetPhase2/1000000 + " " + "millions");
        System.out.println("U1 fleet total budget: $" + (u1BudgetPhase1 + u1BudgetPhase2)/1000000 + " " + "millions");


        long u2BudgetPhase1 = s.runSimulation(u2FleetPhase1);
        long u2BudgetPhase2 = s.runSimulation(u2FleetPhase2);

        System.out.println("****************************************");
        System.out.println("U2 fleet budget for phase 1: $" + u2BudgetPhase1/1000000 + " " + "millions");
        System.out.println("U2 fleet budget for phase 2: $" + u2BudgetPhase2/1000000 + " " + "millions");
        System.out.println("U2 fleet total budget: $" + (u2BudgetPhase1 + u2BudgetPhase2)/1000000 + " " + "millions");
    }

}
