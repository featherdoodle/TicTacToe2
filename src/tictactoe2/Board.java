/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;

import java.util.Arrays;

/**
 *
 * @author Owner
 */
public class Board {
    
    public Board(){
        //yay
    }
    
    public Board(Board board){
        boardStates = new BoardState[3][3]; //array copy
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardStates[i][j] = board.boardStates[i][j];
            }
        }
    }
    
    public static enum BoardState{
        X, O, EMPTY
    }
    
    public BoardState[][] boardStates = {{BoardState.EMPTY, BoardState.EMPTY, 
        BoardState.EMPTY}, {BoardState.EMPTY, BoardState.EMPTY, BoardState.EMPTY}, 
        {BoardState.EMPTY, BoardState.EMPTY, BoardState.EMPTY}};
        
    public WinnerState checkWin(){
        WinnerState winnerState = WinnerState.UNFINISHED;

        //horizontal check
        for(int i = 0; i < 3; i++){                
            if((boardStates[i][0] == boardStates[i][1])&&(boardStates[i][0] == boardStates[i][2])){
                if(boardStates[i][0] == BoardState.X){
                    winnerState = WinnerState.PLAYER_1_WINS; //x wins (human)
                } else if(boardStates[i][0] == BoardState.O){
                    winnerState = WinnerState.PLAYER_2_WINS; //o wins (computer)
                } //vertical check
            }
            if((boardStates[0][i] == boardStates[1][i])&&(boardStates[0][i] == boardStates[2][i])){
                if(boardStates[0][i] == BoardState.X){
                    winnerState = WinnerState.PLAYER_1_WINS; //x wins (human)
                } else if(boardStates[0][i] == BoardState.O){
                    winnerState = WinnerState.PLAYER_2_WINS; //o wins (computer)
                }
            }
        }
        //diagonal check top left to bottom right
        if(boardStates[1][1] == boardStates[0][0]){
            if(boardStates[1][1] == boardStates[2][2]){
                if(boardStates[1][1] == BoardState.X){
                    winnerState = WinnerState.PLAYER_1_WINS; //x wins(human)
                } else if(boardStates[1][1] == BoardState.O){
                    winnerState = WinnerState.PLAYER_2_WINS; //o wins (computer)
                }
            } //top right to bottom left
        }else if(boardStates[1][1] == boardStates[0][2]){
            if(boardStates[1][1] == boardStates[2][0]){
                if(boardStates[1][1] == BoardState.X){
                    winnerState = WinnerState.PLAYER_1_WINS; //x wins(human)
                } else if(boardStates[1][1] == BoardState.O){
                    winnerState = WinnerState.PLAYER_2_WINS; //o wins (computer)
                }
            }
        }
        return winnerState;
    }
    
    public boolean isPossible(Move move, boolean turn){
        return boardStates[move.xCoordinate][move.yCoordinate] == BoardState.EMPTY;
    }
    
    public void boardUpdate(Move move, boolean turn){ //put turn in move
        if(turn){
            boardStates[move.xCoordinate][move.yCoordinate] = BoardState.X;
        }else if(!turn){
            boardStates[move.xCoordinate][move.yCoordinate] = BoardState.O;
        }
        boardPrint();
    }
    
    public void boardPrint(){
        
        String[][] boardString = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(boardStates[i][j] == BoardState.EMPTY){
                    boardString[i][j] = " ";
                }else if(boardStates[i][j] == BoardState.X){
                    boardString[i][j] = "x";
                }else if(boardStates[i][j] == BoardState.O){
                    boardString[i][j] = "o";
                }
            }
        }
        System.out.println("  1   2   3");
        System.out.println("a " + boardString[0][0] + " | " + boardString[0][1] + " | " + boardString[0][2]);
        System.out.println(" ----------- ");
        System.out.println("b " + boardString[1][0] + " | " + boardString[1][1] + " | " + boardString[1][2]);
        System.out.println(" ----------- ");
        System.out.println("c " + boardString[2][0] + " | " + boardString[2][1] + " | " + boardString[2][2] + "\n\n");
        
    }
    //@Override //why isn't the override supposed to be heeeeeeeeeeeere
    public String ToString(Board board){//i know im not supposed to put a board here, but idk how else to do it
        //changing the board to a string that can be returned
        String[][] result = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board.boardStates[i][j] == BoardState.EMPTY){ //check what board this is supposed to use
                    result[i][j] = "EMPTY";
                }else if(board.boardStates[i][j] == BoardState.X){
                    result[i][j] = "X";
                }else if(board.boardStates[i][j] == BoardState.O){
                    result[i][j] = "O";
                }
            }
        }
        return Arrays.deepToString(result);//changing the string array to a string
    }
    
    public Move nextMove(History history){//take in the move or a line number??
        //take in history, change it to a move
        //this needdddsss to be fixed
        Move move = new Move(0, 0);//fix. this.
        return move;
    }
    
    
    public Board stringToBoard(String text){
        Board board = new Board();//do i have an old board to put in there?
        int listIndex = 0;
        char index;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                index = text.charAt(ordinalIndexOf(text, ",", listIndex)+1);//find what class
                //the comma thing needs a little adjusting
                if(index == 'E'){
                    board.boardStates[i][j] = BoardState.EMPTY;
                }else if(index == 'X'){
                    board.boardStates[i][j] = BoardState.X;
                }else if(index == 'O'){
                    board.boardStates[i][j] = BoardState.O;
                }
                
                listIndex++;//which comma we are at
            }
        }
        return board;
    }
    //this should probs go somewhere bettwe
    //copying of stringUtils
    public static int ordinalIndexOf(String str, String s, int n) {
        int pos = str.indexOf(s, 0);
        while (n-- > 0 && pos != -1)
            pos = str.indexOf(s, pos+1);
        return pos;
    }
    
    public static boolean isBoard(String text){ //this is like the worst method... but
        //boolean board;
        if(text == null){
            return false;
        }else if(text.equals("Player Win")){
            return false;
        }else if(text.equals("Computer Win")){
            return false;
        }else if(text.equals("Tie")){
            return false;
        }else if(text.equals("New Game")){
            return false;
        }else{
            return true;
        }
        
    }
    
    
}
