package threads;

public class Consumer implements Runnable {
    private static int id;
    private int consumedNumberOfComputer;

    private final int CONSUMER_ID;
    private final long SLEEP_TIME;
    private final int CONSUMER_COUNT;
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
        SLEEP_TIME = 7000;
        CONSUMER_COUNT = 2;
        CONSUMER_ID = ++id;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while(consumedNumberOfComputer < 10) {
            try {
                Thread.sleep(SLEEP_TIME);
                synchronized (this) {
                    boolean flag = warehouse.takeComputers(CONSUMER_COUNT);
                    if(flag) {
                        consumedNumberOfComputer += CONSUMER_COUNT;
                        System.out.print("Consumed_" + CONSUMER_ID + " consumed '2' Computers ==== ");
                        System.out.println("Total Computers consumed by Consumer_" + CONSUMER_ID + ": " + consumedNumberOfComputer);
                    }
                }
            }
            catch(InterruptedException e) {
                throw new RuntimeException("The consumer was interrupted while sleeping");
            }
        }

        System.out.println("Consumed_" + CONSUMER_ID + " Finished Consuming Computers.");
    }
}
