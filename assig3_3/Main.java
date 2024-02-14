//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please Type How Many Salads To Prepare:");
        Scanner scan = new Scanner(System.in);
        final int numOfSaladsToPrepare = scan.nextInt();
        System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");
        
        SlicerMachine slicerMachine = new SlicerMachine(numOfSaladsToPrepare);
        CucumbersThread cucumbersThread = new CucumbersThread(slicerMachine);
        TomatoesThread tomatoesThread = new TomatoesThread(slicerMachine);
        SlicerThread slicerThread = new SlicerThread(slicerMachine);

        // Start threads
        slicerThread.start();
        cucumbersThread.start();
        tomatoesThread.start();

        // Join threads to wait for completion
        try {
            slicerThread.join();
            cucumbersThread.join();
            tomatoesThread.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for threads: " + e.getMessage());
        }

        System.out.println("Done");
        scan.close();


    }
}


