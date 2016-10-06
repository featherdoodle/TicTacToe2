/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;

/**
 *
 * @author Owner
 */
public class Game {
    
    private boolean turn; //true is player1
    private Player player1, player2;
    public Board board;
    
    public Game(Player _player1, Player _player2){
        player1 = _player1;
        player2 = _player2;
        board = new Board();
        turn = true; //start with player 1's turn...
    }
    
    public WinnerState step(){
        //try to organise better
        Move move = new Move(0, 0);//coordinates get overwritten
        if(turn){ //player 1 turn
            while(turn){ ///lllooooop
                move = player1.getMove(board);
                if(board.isPossible(move, turn)){
                    board.boardUpdate(move, turn);
                    turn = false;
                }
            }
        }else if(!turn){ //player 2
            while(!turn){
                move = player2.getMove(board);
                if(board.isPossible(move, turn)){
                    board.boardUpdate(move, turn);
                    turn = true;
                }
            }
        }
        return board.checkWin();
    }
    
}
