/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static constants.Constants.MAX_ATTEMPTS;
import static constants.Constants.MAX_PEGS;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Erman Aydin
 */
public class Game implements IGame {
    
    // Declarations
    public int attempt;
    public Codebreaker codebreaker;
    public Codemaker codemaker;

    // Constructor, codemaker is driving the code as of now
    public Game() {
        this.codebreaker = new Codebreaker();
        this.codemaker = new Codemaker();
        //play();
    }

    @Override
    public void play() {
        int i;
        for(i = 0; i < MAX_ATTEMPTS; i++) {
            ArrayList<Color> codeBreakersAttempt = codebreaker.getCodebreakerAttempt(); // Pull user attempt
            codemaker.checkAttemptedCode(codeBreakersAttempt); // Check user attempt
            ArrayList<Color> codemakersResponse = codemaker.getCodemakerResponse(); // Provide user with feedback
            // Since the instruction for the codemaker's response output was written in two places,
            // I chose to output the response within the codemaker class at the end of the checkAttemptedCode method
            if(codemaker.isGuessed()) { // Intuitive break condition
                break;
            }
        }
    }
    
    // Getter
    public int getAttempt() {
        return attempt;
    }

    // Setter
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    // Getter
    public Codebreaker getCodebreaker() {
        return codebreaker;
    }

    // Setter
    public void setCodebreaker(Codebreaker codebreaker) {
        this.codebreaker = codebreaker;
    }

    public Codemaker getCodemaker() {
        return codemaker;
    }

    public void setCodemaker(Codemaker codemaker) {
        this.codemaker = codemaker;
    }
}
