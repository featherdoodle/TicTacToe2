/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe2;

import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class HumanPlayer extends Player{
    
    @Override
    public Move getMove(Board board){
        
        int xCoordinate = 0; //defaults to a1
        int yCoordinate = 0;
        
        System.out.print("Enter location: ");
        
        Scanner scan = new Scanner(System.in);
        String location;
        location = scan.nextLine();
        
        if(location.contains("a")){
            xCoordinate = 0;
        } else if(location.contains("b")){
            xCoordinate = 1;
        } else if(location.contains("c")){
            xCoordinate = 2;
        }
        
        if(location.contains("1")){
            yCoordinate = 0;
        } else if(location.contains("2")){
            yCoordinate = 1;
        } else if(location.contains("3")){
            yCoordinate = 2;
        }
        
        Move move = new Move(xCoordinate, yCoordinate);
        
        return move;
        
    }
    
    
}
