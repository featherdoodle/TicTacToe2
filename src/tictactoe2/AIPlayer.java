/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Owner
 */
public class AIPlayer extends Player {
    
    public List<History> histories;//skeptical
    
    AIPlayer(List<History> _histories){
        histories = _histories; //am i using this history in getMove()
    }
    
    @Override
    public Move getMove(Board board){ //check board
        
        if(histories != null){ //check the histories
            return chooseMove(possibleMoves(board, histories));//agaiiiinn
        }else{
            return randomMove(board); //if there aren't moves to choose from, choose randomly
        }
        
    }
    
    public Move randomMove(Board board){
        Random random = new Random();//like arrays with the 3?
        
        int xCoordinate = random.nextInt(3);//-1155099828
        int yCoordinate = random.nextInt(3);
        
        Move move = new Move(xCoordinate, yCoordinate);
        
        return move;
    }
    
    public List<Move> possibleMoves(Board board, List<History> histories){ //again changing the list type
        
        List<Move> possibleMoves = new ArrayList<>();
        
        for(History i : histories){
            if(i.containsBoard(board)){ //method to change the board to a string... //prints out reference point instead of the values
                if(i.winner == WinnerState.PLAYER_2_WINS){//changed from checking board to history
                    possibleMoves.add(board.nextMove(i));//add next move function to board
                }
            }
        }
        
        return possibleMoves;
    }
    
    public Move chooseMove(List<Move> possibleMoves){
        Random random = new Random(possibleMoves.size());
        return possibleMoves.get(random.nextInt());
    }
    
}
