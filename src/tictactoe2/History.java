/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author Owner
 */
public class History {
    
    public WinnerState winner;//check the public
    private List<Board> boards;
    
    
    public History(){
        boards = new ArrayList<>();
    }
    
    public void addBoardToList(Board board){
        boards.add(new Board(board));
    }
    
    public void addToFile(Game game) throws IOException{ 
        
        try(FileWriter fileWriter = new FileWriter("previousGames", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bufferedWriter))
        {
            out.println("New Game");
            for(Board i : boards){
                out.println(i.ToString(i));
            }
            winner = game.board.winnerState;
            if(winner == WinnerState.PLAYER_1_WINS){ 
                out.println("Player Wins");
                System.out.println("Player Wins");
            }else if(winner == WinnerState.PLAYER_2_WINS){ 
                out.println("Computer Wins");
                System.out.println("Computer Wins");
            }else if(winner == WinnerState.TIE){ 
                out.println("Tie");
                System.out.println("Tie");
            }
        }
    }
    
    public static List<History> load(String fileName){
        
        List<History> histories = null;
        
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while(true){
                History history = historyReader(bufferedReader);
                if(history != null){
                    histories.add(history);
                }else{
                    break;
                }
            }
            bufferedReader.close();
        }catch(FileNotFoundException e){
            
        }catch(IOException e){
            
        }
        return histories;
    }
    
    private static History historyReader(BufferedReader bufferedReader) throws IOException{
        History history = new History();
        String line;
        
        while(true){
            
            line = bufferedReader.readLine();
            if(line == null){
                return null;//returns null for the check above
            }else if(line.equals("Player Win")){
                history.winner = WinnerState.PLAYER_1_WINS;
                break;
            }else if(line.equals("Computer Win")){
                history.winner = WinnerState.PLAYER_2_WINS;
                break;
            }else if(line.equals("Tie")){
                history.winner = WinnerState.TIE;
                break;
            }else{//check for new game...
                Board board = new Board();
                history.boards.add(board.stringToBoard(line));

            }
        }
        return history;
    }
    
    public boolean containsBoard(Board board){
        return boards.contains(board);
    }
    
    
}
