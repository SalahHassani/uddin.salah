package threads;

public class Warehouse {
    private int numberOfComputers;

    public Warehouse() {
        this.numberOfComputers = 0;
    }

    public synchronized void addComputers(int number) {
        numberOfComputers += number;
    }

    public synchronized boolean takeComputers(int number) {
        if(numberOfComputers < number) {
            System.out.println("Warehouse is Empty, waiting for Power Plant to Produce Computers");
            return false;
        }
        numberOfComputers -= number;
        return true;
    }

}
