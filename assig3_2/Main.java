//Ofir Biton 208582494 & Naim Moshe 315852269
package assig3_2;

public class Main {
    public static void main(String[] args) {
        GamePlay gamePlay = new GamePlay();
        Gamer player1 = new Gamer(gamePlay, "player 1");
        Gamer player2 = new Gamer(gamePlay, "player 2");

        gamePlay.getJudge().start();
        player1.start();
        player2.start();
        try{
            player1.join();
            player2.join();
            gamePlay.getJudge().interrupt();
            gamePlay.getJudge().join();
        }
        catch (InterruptedException exception){
            System.out.println("Game Over");
        }

        System.out.println("\nGame Over");
        if(player1.getGoodFlipsCounter() > player2.getGoodFlipsCounter()) System.out.println("Player 1 wins");
        else if(player1.getGoodFlipsCounter() < player2.getGoodFlipsCounter()) System.out.println("Player 2 wins");
        else System.out.println("tie");



    }
}
