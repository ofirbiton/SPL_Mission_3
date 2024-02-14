//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_3;

public class SlicerMachine {

    int numOfCucumbers = 0;
    int numOfTomatoes = 0;
    int numOfPreparedSalads = 0;
    int numOfSalads;

    final int cucumbersNeededForOneSalad = 3;
    final int tomatoesNeededForOneSalad = 2;


    public SlicerMachine(int numOfSalads){
        this.numOfSalads = numOfSalads;
    }

    // add one cucumber into the slicer chamber
     synchronized void addOneCucumber() {
            while (!(numOfCucumbers < cucumbersNeededForOneSalad)) {
                try {
                    wait(); // Now safe to wait as lock is held
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("adding one cucumber to the machine");
            numOfCucumbers++;
            notifyAll();

     }

    // add one tomato into the slicer chamber
     synchronized void addOneTomato() {
        while (!(numOfTomatoes < tomatoesNeededForOneSalad)) {
            try {
                wait();
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("adding one tomato to the machine");
        numOfTomatoes++;
        notifyAll();

    }

    // if there are enough vegetables in the slicer
    // chamber, make another salad
    synchronized void sliceVegetables() {
        while (!(numOfCucumbers == cucumbersNeededForOneSalad) || !(numOfTomatoes == tomatoesNeededForOneSalad)) {
            try {
                wait();
            } catch (InterruptedException e) {
                break;
            }
        }
        makeNewSalad();
    }

    private synchronized void makeNewSalad() {
        System.out.println("== preparing one more salad ==");
        numOfPreparedSalads++;
        // update stock
        this.numOfTomatoes = 0;
        this.numOfCucumbers = 0;
        notifyAll();
    }

    synchronized int getNumOfPreparedSalads() {
        return numOfPreparedSalads;
    }

    synchronized int getNumOfSalads(){return numOfSalads;}

}
