//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_2;

public class Judge extends Thread{
    private final GamePlay gamePlay;
    public Judge(GamePlay gamePlay){
        this.gamePlay = gamePlay;
    }
    @Override
    public void run(){
        while (!isInterrupted()) {
            try {
                gamePlay.makeCoinAvail(false);
                Thread.sleep(1000);

            } catch (InterruptedException exception) {
                break;
            }
            try {
                gamePlay.makeCoinAvail(true);
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                break;
            }
        }
    }

}
