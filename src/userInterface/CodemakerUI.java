/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import core.Codemaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import userinterface.RoundButton;

/**
 *
 * @author Erman Aydin
 */
public class CodemakerUI {
    
    JPanel codemakerResponse;
    JPanel secretCode;
    Codemaker codemaker;
    JLabel[] secretLabels;
    JLabel[][] responseLabels;
    ImageIcon question;
    JButton check;
    boolean checkClicked;
    
    public CodemakerUI(Codemaker codemaker) {
        this.codemaker = codemaker;
        initComponents();
    }
    
    public void initComponents() {
        codemakerResponse = new JPanel();
        secretCode = new JPanel();
        secretCode.setMinimumSize(new Dimension(200, 60));
        codemakerResponse.setMinimumSize(new Dimension(150, 100));
        secretCode.setPreferredSize(new Dimension(200, 60));
        codemakerResponse.setPreferredSize(new Dimension(150, 100));
        secretCode.setBorder(BorderFactory.createTitledBorder("Secret Code"));
        codemakerResponse.setBorder(BorderFactory.createTitledBorder("Codemaker Response"));
        codemakerResponse.setLayout(new GridLayout(10, 4));
        secretCode.setLayout(new FlowLayout());
        
        secretLabels = new JLabel[4];

        question = new ImageIcon(getClass().getResource("/images/question.jpg"));
        question = imageResize(question);
        
        for(JLabel label: secretLabels) {
            label = new JLabel();
            label.setIcon(imageResize(question));
            secretCode.add(label);
        }
        
        check = new JButton("Check");
        check.addActionListener(new checkListener());
        secretCode.add(check);
        
        responseLabels = new JLabel[10][4];
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 4; j++) {
                responseLabels[i][j] = new JLabel();
                responseLabels[i][j].setBackground(Color.LIGHT_GRAY);
                responseLabels[i][j].setOpaque(true);
                responseLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                codemakerResponse.add(responseLabels[i][j]);
            }
        }
    }
    
    public void displaySecretCode() {
        JLabel codeLabel = new JLabel("The secret code was");
        List<Color> secretList = new ArrayList<>(codemaker.getSecretCode());

        secretCode.add(codeLabel);
        
        for(Color color: secretList) {
            RoundButton button = new RoundButton();
            button.setBackground(color);
            secretCode.add(button);
        }

        secretCode.revalidate();
        secretCode.repaint();

        // I made a removeAll method within codemaker.java in addition to the one in codebreaker.java
        codemaker.removeAll();
    }
    
    public void displayCodemakerResponse(int row) {
        ArrayList<Color> makerResponse = new ArrayList<>(codemaker.getCodemakerResponse());
        
        for(int col = 0; col < makerResponse.size(); col++) {
            Color color = makerResponse.get(col);
            if(color != null)
                responseLabels[row][col].setBackground(color);
        }
        
        makerResponse.clear();
    }
    
    public class checkListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            checkClicked = true;
        }
    }

    public void setCheck(boolean value) {
        check.setEnabled(value);
    }

    public void setCheckClicked(boolean checkClicked) {
        this.checkClicked = checkClicked;
    }

    public JButton getCheck() {
        return check;
    }

    public boolean isCheckClicked() {
        return checkClicked;
    }
    
    private ImageIcon imageResize(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
    
    public JPanel getCodemakerResponse() {
        return codemakerResponse;
    }

    public JPanel getSecretCode() {
        return secretCode;
    }
}
