//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_2;
import java.util.Random;

public class GamePlay {
    private boolean coin_available_;
    private int rounds_counter_ = 0;
    private final Judge judge;

    public GamePlay(){judge = new Judge(this);}

    protected synchronized void makeCoinAvail(boolean val) {
            if (val) notifyAll();
        this.coin_available_ = val;
    }

    protected synchronized boolean flipCoin() {
        try {
            while (!coin_available_) {
                System.out.println(Thread.currentThread().getName() + " is waiting for coin");
                this.wait();
            }

            if(Thread.currentThread().isInterrupted()) return false;

            makeCoinAvail(false);
            this.rounds_counter_++;


            System.out.println(Thread.currentThread().getName() + " is flipping coin");
            makeCoinAvail(true);

            Random random = new Random();
            return random.nextInt(0, 2) == 1;

        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    protected synchronized int getNumOfRounds() {
        return rounds_counter_;
    }

    public Judge getJudge(){
        return judge;
    }
}
