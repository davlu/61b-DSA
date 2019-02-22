public class Robot {
    public int energy = 0;
    public String className = "Robot";

    public void enervate(Robot r) {
        r.energy -= 5;
    }

    public void reportEnergy() {
        System.out.println(energy);
    }

    public void reportName() {
        System.out.println(className);
    }
}