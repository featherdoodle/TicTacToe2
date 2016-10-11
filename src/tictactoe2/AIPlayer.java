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
    
    AIPlayer(List<History> _histories){ //this is the wrong history...
        histories = _histories; //am i using this history in getMove()
    }
    
    @Override
    public Move getMove(Board board){ //check board
        
        if(histories != null){ //check the histories
            return chooseMove(board, histories);//agaiiiinn
        }else{
            return randomMove(board); //if there aren't moves to choose from, choose randomly
        }
        
    }
    
    public Move randomMove(Board board){
        Random random = new Random();
        
        int xCoordinate = random.nextInt(3);
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
    
    public Move chooseMove(Board board, List<History> histories){//changed from inporting possible moves to board, history
        //make it so it chooses random every once and a while, and so it is win rates instead of what it is
        //account for cat games (0.5)
        Random random = new Random();
        List<Move> moves = possibleMoves(board, histories);
        if(moves.size() == 0){
            return randomMove(board);
        }else{
            int index = random.nextInt(moves.size()); //size is not positive
            Move result = moves.get(index);
            return result;
        }
        
    }
    
}
