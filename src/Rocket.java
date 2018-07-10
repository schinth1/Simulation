import java.util.Random;

public class Rocket implements  SpaceShip {

    long cost;
    int rocketWeight;
    int maxWeight;
    int balanceWeight;
    int currentWeight;
    int cargoWeight;
    int cargoLimit;
//    float launchExplosionProbability;
//    float landingCrashProbability;
    int explosionPercentage;
    int crashPercentage;

    Rocket() {

    }

    Rocket(long cost, int rocket_weight, int max_weight, int explosion, int crash) {
        this.cost = cost;
        this.rocketWeight = rocket_weight;
        this.maxWeight = max_weight;
        this.currentWeight = rocket_weight;
        this.balanceWeight = max_weight - rocket_weight;
        this.cargoLimit = max_weight - rocket_weight;
        this.cargoWeight = 0;
        this.explosionPercentage = explosion;
        this.crashPercentage = crash;
    }

    public boolean launch() {
        double launchExplosionProbability = (this.explosionPercentage / 100) * (this.cargoWeight / this.cargoLimit);
        double rand = new Random().nextDouble();
        if (launchExplosionProbability >= rand)  return false;
        return true;
    }

    public boolean land() {
        double landingCrashProbability = (this.crashPercentage / 100) * (this.cargoWeight / this.cargoLimit);
        double rand = new Random().nextDouble();
        if (landingCrashProbability >= rand) return false;
        return true;
    }

    public boolean canCarry(Item item) {
        if(item.weight <= this.balanceWeight) return true;
        return false;
    }

    public void carry(Item item) {
        this.currentWeight = this.currentWeight + item.weight;
        this.cargoWeight = this.cargoWeight + item.weight;
        this.balanceWeight = this.balanceWeight - item.weight;
    }
}
