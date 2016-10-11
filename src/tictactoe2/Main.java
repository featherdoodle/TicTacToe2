/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Owner
 */
public class Main {

    //file isn't adding newest gamess
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //creating a new history list
        List<History> histories = History.load("previousGames"); //changed History list to String
        //creating two new players
        Player human = new HumanPlayer();
        Player computer = new AIPlayer(histories);
        
        History history = new History();
        //creating a new game... can change if it is human or ai
        Game game = new Game(human, computer);
        //printing out the first board
        game.board.boardPrint();
        //setting the game to unfinished to start
        WinnerState gameFinished = WinnerState.UNFINISHED;
        
        while(gameFinished == WinnerState.UNFINISHED){
            gameFinished = game.step();
            history.addBoardToList(game.board);
        }
        
        if(gameFinished != WinnerState.UNFINISHED){
            history.addToFile(game);
        }
    }
    
}

