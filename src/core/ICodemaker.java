/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Erman Aydin
 */
public interface ICodemaker {
    
    // Key method signatures
    public void generateSecretCode();
    public void checkAttemptedCode(ArrayList<Color> attempt);
}
