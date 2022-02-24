/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mastermind;

import core.Game;
import javax.swing.JOptionPane;
import userinterface.MastermindUI;

/**
 *
 * @author Erman Aydin
 */

public class Mastermind {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Introduction
        System.out.println("Welcome to Mastermind!");
        JOptionPane.showMessageDialog(null, "Let's Play Mastermind!");
        
        // Instantiation to drive the program
        Game game = new Game();
        
        // Instantiating UI with argument "game"
        MastermindUI mmUI = new MastermindUI(game);
    }
    
}
