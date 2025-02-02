package threads;

import java.awt.image.TileObserver;

public class Plant implements Runnable {
    private int producedNumberOfComputers;
    private final Warehouse warehouse;
    private final int TARGET_AMOUNT;
    private final long SLEEP_TIME;
    private final int PRODUCE_AMOUNT;
    private final int REMAINDER;

    public Plant(Warehouse warehouse) {
        this.producedNumberOfComputers = 0;
        this.warehouse = warehouse;
        TARGET_AMOUNT = 20;
        SLEEP_TIME = 5000;
        PRODUCE_AMOUNT = 3;
        // here TARGET_AMOUNT % PRODUCE_AMOUNT = remainder ==> 20 % 3 == 2
        REMAINDER = TARGET_AMOUNT % PRODUCE_AMOUNT;
    }


    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while(producedNumberOfComputers < TARGET_AMOUNT) {
            // go sleep for 5 seconds...
            sleep(SLEEP_TIME);
            // here TARGET_AMOUNT - REMAINDER = 18
            if(producedNumberOfComputers == TARGET_AMOUNT - REMAINDER) {
                warehouse.addComputers(REMAINDER);
                producedNumberOfComputers += REMAINDER;
                System.out.print("Plant Produced Computers: 2  ::::: ");
            }
            else {
                warehouse.addComputers(PRODUCE_AMOUNT);
                producedNumberOfComputers += PRODUCE_AMOUNT;
                System.out.print("Plant Produced Computers: 3  ::::: ");

            }

            System.out.println("Total Produced Computers By Plant: " + producedNumberOfComputers);
        }

        System.out.println("Plant finished Producing Computers.");
    }


    private void sleep(long value) {
        try {
            Thread.sleep(value);
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Power plant's sleep interrupted");
        }
    }
}
