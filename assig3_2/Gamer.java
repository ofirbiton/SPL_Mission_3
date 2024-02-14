//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_2;

public class Gamer extends Thread {
    private int goodFlipsCounter = 0;
    private final GamePlay gamePlay;

    public Gamer(GamePlay gamePlay, String name){
        super(name);
        this.gamePlay = gamePlay;
    }
    @Override
    public void run(){
        play();
    }
    private void play(){
        while(!isInterrupted() && gamePlay.getNumOfRounds() < 10){
            synchronized (gamePlay){
                if(gamePlay.flipCoin()){
                    goodFlipsCounter++;
                }
            }
            if(gamePlay.getNumOfRounds() == 10) interrupt();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public int getGoodFlipsCounter(){
        return goodFlipsCounter;
    }

}
