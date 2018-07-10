public class Rocket implements  SpaceShip {

    public boolean launch() {
        return true;
    }

    public boolean land() {
        return true;
    }

    public boolean canCarry(Item item) {
        return true;
    }
    public void carry() {
        System.out.println("Carry method");
    }
}
