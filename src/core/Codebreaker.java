/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static constants.Constants.MAX_PEGS;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Erman Aydin
 */
public class Codebreaker implements ICodebreaker {

    // Single declaration
    public ArrayList<Color> codebreakerAttempt;

    // Constructor
    public Codebreaker() {
        this.codebreakerAttempt = new ArrayList<>();
    }

    // Method that has yet to be used
    @Override
    public void checkCode(ArrayList<Color> attempt) {
    }

    // Setter
    public void setCodebreakerAttempt(ArrayList<Color> codebreakerAttempt) {
        this.codebreakerAttempt = codebreakerAttempt;
    }

    // Getter
    public ArrayList<Color> getCodebreakerAttempt() {
        //consoleAttempt();
        return codebreakerAttempt;
    }

    private void consoleAttempt() {
        codebreakerAttempt.removeAll(codebreakerAttempt);
        Scanner scnr = new Scanner(System.in);
        String str;
        
        System.out.println("\nEnter your colors in left to right order\n" + "Use BLUE, BLACK, ORANGE, WHITE, YELLOW, RED, GREEN, PINK:");
        
        // I went with a switch statement with the uppercase string as an argument so as to preserve the original string
        while(codebreakerAttempt.size() < MAX_PEGS) {
            str = scnr.next();
            switch(str.toUpperCase()) {
                case "BLUE":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.BLUE)) // To prevent repeats
                        codebreakerAttempt.add(Color.BLUE);
                    break;
                case "BLACK":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.BLACK))
                        codebreakerAttempt.add(Color.BLACK);
                    break;
                case "ORANGE":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.ORANGE))
                        codebreakerAttempt.add(Color.ORANGE);
                    break;
                case "WHITE":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.WHITE))
                        codebreakerAttempt.add(Color.WHITE);
                    break;
                case "YELLOW":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.YELLOW))
                        codebreakerAttempt.add(Color.YELLOW);
                    break;
                case "RED":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.RED))
                        codebreakerAttempt.add(Color.RED);
                    break;
                case "GREEN":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.GREEN))
                        codebreakerAttempt.add(Color.GREEN);
                    break;
                case "PINK":
                    System.out.println("You entered " + str);
                    if(!codebreakerAttempt.contains(Color.PINK))
                        codebreakerAttempt.add(Color.PINK);
                    break;
            // I didn't feel that a default case was necessary here
            }
            
            if(codebreakerAttempt.size() < MAX_PEGS) { // This prompt will occur prior to a repetition
                System.out.println("Next color");
            }
        }
        
        System.out.println("\nCodebreaker's attempt"); // Print the user's guess
        for(Color c: codebreakerAttempt)
            System.out.println(c);
    }
    
    public void removeAll() {
        codebreakerAttempt.clear();
    }
}
