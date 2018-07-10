import java.util.Random;

public class Rocket implements  SpaceShip {

    private final long cost;
    private final int rocketWeight;
    private final int maxWeight;
    private int balanceWeight;
    private int currentWeight;
    private int cargoWeight;
    private final int cargoLimit;
//    float launchExplosionProbability;
//    float landingCrashProbability;
    int explosionPercentage;
    int crashPercentage;
    Random random = new Random();
    double launchExplosionProbability = -1.0;
    double landingCrashProbability = -1.0;

//    Rocket() {
//
//    }

    Rocket(long cost, int rocketWeight, int maxWeight, int explosion, int crash) {
        this.cost = cost;
        this.rocketWeight = rocketWeight;
        this.maxWeight = maxWeight;
        this.currentWeight = rocketWeight;
        this.balanceWeight = maxWeight - rocketWeight;
        this.cargoLimit = maxWeight - rocketWeight;
        this.cargoWeight = 0;
        this.explosionPercentage = explosion;
        this.crashPercentage = crash;
    }

    public boolean launch() {
        if(launchExplosionProbability == -1) {
            launchExplosionProbability = ((double)explosionPercentage / 100) * ((double)cargoWeight / (double)cargoLimit);
        }
        double rand = random.nextDouble();
        System.out.println("Random Number" + rand + " "+ launchExplosionProbability);
        if (launchExplosionProbability >= rand)  {
            System.out.println("Launch Failed");
            return false;
        }
        System.out.println("Launch success");
        return true;
    }

    public boolean land() {
        if (landingCrashProbability == -1 )  {
            landingCrashProbability = ((double)crashPercentage / 100) * ((double)cargoWeight / (double)cargoLimit);
        }
        double rand = random.nextDouble();
//        System.out.println("Random Number" + rand + " "+ landingCrashProbability);
        if (landingCrashProbability >= rand) return false;
        return true;
    }

    public boolean canCarry(Item item) {
        if(item.weight <= balanceWeight) return true;
        return false;
    }

    public void carry(Item item) {
        currentWeight = currentWeight + item.weight;
        cargoWeight = cargoWeight + item.weight;
        balanceWeight = balanceWeight - item.weight;
    }

    public int getCargoLimit() {
        return cargoLimit;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getBalanceWeight() {
        return balanceWeight;
    }
    public long getCost() {
        return cost;
    }
}
