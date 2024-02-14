//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_3;

public class SlicerThread extends Thread {
    private final SlicerMachine slicerMachine;

    public SlicerThread(SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }


    public void run(){
        while(!(slicerMachine.getNumOfPreparedSalads() == slicerMachine.getNumOfSalads())){
            slicerMachine.sliceVegetables();
        }
    }
}
