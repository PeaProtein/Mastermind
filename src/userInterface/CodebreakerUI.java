/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import constants.Constants;
import static constants.Constants.MAX_PEGS;
import static constants.Constants.codeColors;
import core.Codebreaker;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Erman Aydin
 */
public class CodebreakerUI {
    
    JPanel codebreakerAttempt;
    JPanel codebreakerColors;
    Codebreaker codebreaker;
    RoundButton[] buttons;
    RoundButton[][] attempts;
    Color colorSelected = null;
    
    public CodebreakerUI(Codebreaker breaker) {
        codebreaker = breaker;
        initComponents();
    }
    
    public void initComponents() {
        codebreakerAttempt = new JPanel();
        codebreakerColors = new JPanel();
        codebreakerAttempt.setMinimumSize(new Dimension(100, 100));
        codebreakerColors.setMinimumSize(new Dimension(200, 65));
        codebreakerAttempt.setPreferredSize(new Dimension(100, 100));
        codebreakerColors.setPreferredSize(new Dimension(200, 65));
        codebreakerAttempt.setBorder(BorderFactory.createTitledBorder("Codebreaker Attempt"));
        codebreakerColors.setBorder(BorderFactory.createTitledBorder("Codebreaker Colors"));
        codebreakerAttempt.setLayout(new GridLayout(10, 4));
        
        attempts = new RoundButton[10][4];
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 4; j++) {
                attempts[i][j] = new RoundButton();
                attempts[i][j].setBackground(Color.LIGHT_GRAY);
                attempts[i][j].putClientProperty("row", i);
                attempts[i][j].addActionListener(new otherButtonListener());
                if(i != Constants.MAX_ATTEMPTS - 1)
                    attempts[i][j].setEnabled(false);
                codebreakerAttempt.add(attempts[i][j]);
            }
        }
        
        buttons = new RoundButton[Constants.COLORS];
        int colorIndex = 0;
        
        for(RoundButton button: buttons) {
            button = new RoundButton();
            Color color = codeColors.get(colorIndex);
            button.setBackground(color);
            button.putClientProperty("color", color);
            button.setToolTipText(color.toString());
            button.addActionListener(new colorButtonListener());
            codebreakerColors.add(button);
            colorIndex++;
        }
    }
    
    public class colorButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            RoundButton rbutton = (RoundButton)ae.getSource();
            colorSelected = (Color)rbutton.getClientProperty("color");
        }
    }
    
    public class otherButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            RoundButton rbutton = (RoundButton)ae.getSource();

            if(!codebreaker.getCodebreakerAttempt().contains(colorSelected)) {
                codebreaker.getCodebreakerAttempt().add(colorSelected);
                if(!rbutton.getBackground().equals(Color.LIGHT_GRAY))
                    codebreaker.getCodebreakerAttempt().remove(rbutton.getBackground());
                rbutton.setBackground(colorSelected);
            }
            
            if(codebreaker.getCodebreakerAttempt().size() == MAX_PEGS) {
                int row = (int)rbutton.getClientProperty("row");
                sortAttempt(codebreaker.getCodebreakerAttempt(), row);
                enableDisableButtons(row);
            }
        }
    }

    private void sortAttempt(ArrayList<Color> attemptList, int row) {
        for(int i = 0; i < 4; i++)
            attemptList.set(i, attempts[row][i].getBackground());
    }
    
    private void enableDisableButtons(int row) {
        int j;

        for(j = 0; j < MAX_PEGS; j++)
            attempts[row][j].setEnabled(false);
        
        if(row != 0)
            for(j = 0; j < MAX_PEGS; j++)
                attempts[row - 1][j].setEnabled(true);
    }

    public JPanel getCodebreakerAttempt() {
        return codebreakerAttempt;
    }

    public JPanel getCodebreakerColors() {
        return codebreakerColors;
    }
}
