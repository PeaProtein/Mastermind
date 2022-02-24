/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import constants.Constants;
import static constants.Constants.MAX_PEGS;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Erman Aydin
 */
public class Codemaker implements ICodemaker {

    // Declarations
    public Set<Color> secretCode;
    public ArrayList<Color> codemakerResponse;
    public boolean guessed = false;

    // Essential constructor, generateSecretCode is important to drive code
    public Codemaker() {
        this.secretCode = new HashSet<>();
        this.codemakerResponse = new ArrayList<>();
        this.generateSecretCode();
    }

    @Override
    public void generateSecretCode() {

        Random random = new Random();
        
        // Loop until 4 random colors are added to secretCode
        while(secretCode.size() < MAX_PEGS) {
            Color randomColor = Constants.codeColors.get(random.nextInt(Constants.COLORS));
            secretCode.add(randomColor);
        }
        
        // Output the contents of secretCode
        // Could be <= 4 unique colors
        System.out.println("Generated the secret code!");
        for (Color color : secretCode) {
            System.out.println(color);
        }
    }

    @Override
    public void checkAttemptedCode(ArrayList<Color> attempt) {
        // The instructions didn't include the following statement, but it was necessary to
        // prevent the previous responses from being displayed
        codemakerResponse.clear();
        int redPegsScored = 0;
        int whitePegsScored = 0;
        int i;
        ArrayList<Color> evaluatedPegs = new ArrayList<>(); // I thought about using a boolean array, but an ArrayList seemed better
        ArrayList<Color> secretCodeList = new ArrayList<>(secretCode);

        System.out.println("\nCodemaker is checking codebreaker attempt");
        
        if(secretCodeList.equals(attempt)) { // if 100% correct condition
            redPegsScored = 4;
            guessed = true;
            System.out.println("You guessed it!");
        }
        else {
            // The following loop will check which colors are correct and in order
            for(i = 0; i < MAX_PEGS; i++) {
                if(secretCodeList.get(i).equals(attempt.get(i))) {
                    redPegsScored++;
                    evaluatedPegs.add(attempt.get(i));
                    System.out.println("Found correct color in correct position!");
                }
            }
            // This loop tests the user's color guesses one at a time
            // If the code contains it another loop will execute
            // The inner loop seems unnecessary when using an ArrayList for "evaluated pegs"
            // It will run until the condition is met and then save currentColor to the evaluatedPegs ArrayList
            for(Color currentColor: attempt) {
                if(secretCodeList.contains(currentColor) && !evaluatedPegs.contains(currentColor)) {
                    whitePegsScored++;
                    evaluatedPegs.add(currentColor);
                }
            }
        }
        System.out.println("Red pegs " + redPegsScored + " white pegs " + whitePegsScored); // Output pegs

        for(i = 0; i < redPegsScored; i++) { // Load response with number of red pegs
            codemakerResponse.add(Color.RED);
        }
        for(i = 0; i < whitePegsScored; i++) { // Load response with number of white pegs
            codemakerResponse.add(Color.WHITE);
        }
        
        System.out.println("\nCodemaker's response"); // Output codemaker's response
        for(Color c: codemakerResponse) {
            System.out.println(c);
        }
    }

    public boolean isGuessed() {
        return guessed;
    }
    
    // Getter
    public Set<Color> getSecretCode() {
        return secretCode;
    }

    // Setter
    public void setSecretCode(Set<Color> secretCode) {
        this.secretCode = secretCode;
    }
    
    // Getter
    public ArrayList<Color> getCodemakerResponse() {
        return codemakerResponse;
    }

    // Setter
    public void setCodemakerResponse(ArrayList<Color> codemakerResponse) {
        this.codemakerResponse = codemakerResponse;
    }
    
    public void removeAll() {
        secretCode.clear();
    }
}
