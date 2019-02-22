public class Robocop extends Robot implements Police {
    public String className = "Robocop";

    @Override
    public void detain() {
        System.out.println("halt. citizen.");
    }

    @Override
    public void enervate(Robot r) {
        r.energy -= 20;
    }

    @Override
    public void reportEnergy() {
        System.out.println(energy);
    }

    @Override
    public void reportName() {
        System.out.println(className);
    }
}
