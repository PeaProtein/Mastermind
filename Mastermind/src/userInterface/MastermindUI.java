/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import static constants.Constants.MAX_PEGS;
import core.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Erman Aydin
 */
public class MastermindUI {
    
    Game game;
    CodebreakerUI codebreakerUI;
    CodemakerUI codemakerUI;
    JFrame frame;
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenu helpMenu;
    JMenuItem newGameMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem aboutMenuItem;
    JMenuItem rulesMenuItem;
    
    public MastermindUI(Game g) {
        game = g;
        initComponents();
        play();
    }
    
    public void initComponents() {
        codebreakerUI = new CodebreakerUI(game.getCodebreaker());
        codemakerUI = new CodemakerUI(game.getCodemaker());
        frame = new JFrame("Mastermind");
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        helpMenu = new JMenu("Help");
        newGameMenuItem = new JMenuItem("New Game");
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");
        rulesMenuItem = new JMenuItem("Game Rules");
        frame.setSize(new Dimension(530, 830));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(menuBar);
        frame.add(codebreakerUI.getCodebreakerColors(), BorderLayout.SOUTH);
        frame.add(codebreakerUI.getCodebreakerAttempt(), BorderLayout.CENTER);
        frame.add(codemakerUI.getSecretCode(), BorderLayout.NORTH);
        frame.add(codemakerUI.getCodemakerResponse(), BorderLayout.EAST);
        exitMenuItem.addActionListener(new exitListener());
        aboutMenuItem.addActionListener(new aboutListener());
        rulesMenuItem.addActionListener(new rulesListener());
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        gameMenu.add(newGameMenuItem);
        gameMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        frame.setVisible(true);
    }
    
    public void play() {
        for(int i = 9; i > -1; i--) {
            codemakerUI.setCheck(false);
            codemakerUI.setCheckClicked(false);
            JOptionPane.showMessageDialog(frame, "Codebreaker, enter your guess");
            
            while(game.getCodebreaker().getCodebreakerAttempt().size() < MAX_PEGS) {
                System.out.print("");
            }
            
            codemakerUI.setCheck(true);
            JOptionPane.showMessageDialog(frame, "Codebreaker, click the Check button");
            while(!codemakerUI.isCheckClicked()) {
                System.out.print("");
            }
            
            JOptionPane.showMessageDialog(frame, "Codemaker checking attempt");
            ArrayList<Color> attempt = new ArrayList<>(game.getCodebreaker().getCodebreakerAttempt());
            game.getCodemaker().checkAttemptedCode(attempt);
            ArrayList<Color> response = new ArrayList<>(game.getCodemaker().getCodemakerResponse());
            codemakerUI.displayCodemakerResponse(i);
            game.codebreaker.removeAll();
            
            if(game.getCodemaker().isGuessed()) {
                JOptionPane.showMessageDialog(frame, "Congratulations! You won!");
                codemakerUI.displaySecretCode();
                break;
            }
        }
        
        if(!game.getCodemaker().isGuessed()) {
            JOptionPane.showMessageDialog(frame, "Sorry! You lost!");
            codemakerUI.displaySecretCode();
        }
    }
    
    public class exitListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int response = JOptionPane.showConfirmDialog(frame, "Confirm to exit Mastermind?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION)
                System.exit(0);	
        }        
    }
    
    public class aboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(frame, "Mastermind version 1.0\nErman Aydin\nSummer 2019");
        }
    }
    
    public class rulesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(frame, "Step 1: The codemaker selects a four color secret code, in any order, no duplicate colors.\n\n"
                    + "Step 2: The codebreaker places a guess in the bottom row, no duplicate colors.\n\n"
                    + "Step 3: The codemaker gives feedback next to each guess row with four pegs\n"
                    + "~ Each red peg means that one of the guessed colors is correct, and is in the right location.\n"
                    + "~ Each white peg means that one of the guessed colors is correct, but is in the wrong location.\n\n"
                    + "Step 4: Repeat with the next row, unless the secret code was guessed on the first turn\n\n"
                    + "Step 5: Continue until the secret code is guessed or there are no more guesses left, there are 10 attempts\n");
        }
    }
}
