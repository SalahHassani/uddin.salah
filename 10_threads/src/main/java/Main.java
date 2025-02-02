import threads.Consumer;
import threads.MatrixMultiplication;
import threads.Plant;
import threads.Warehouse;

public class Main {

    public static void main(String[] args) {

        MatrixMultiplication mtx = new MatrixMultiplication();

        // Matrix A with size (m X n) = (5 X 100)
        mtx.setMatrixA(5, 100);

        // Matrix B with size (n X k) = (100 X 5)
        mtx.setMatrixB(100, 5);

        // Matrix C with size (m X k) = (5 X 5)
        try {
            mtx.multiplyMatrices();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nMatrix A  *  Matrix B = Matrix C : ");
        mtx.print("C");


        System.out.println("\nHandling Plant and Consumer Threads.\n");

        Warehouse warehouse = new Warehouse();
        Thread plantThread = new Thread(new Plant(warehouse));

        Thread[] consumerThreads = new Thread[2];
        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i] = new Thread(new Consumer(warehouse));
        }

        plantThread.start();
        for (Thread thread : consumerThreads) {
            thread.start();
        }

        try {
            plantThread.join();
            for (Thread consumerThread : consumerThreads) {
                consumerThread.join();
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Threads are interrupted in main");
        }

    }
}
