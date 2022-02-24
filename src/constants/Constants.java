/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;
/**
 *
 * @author Erman Aydin
 */
public class Constants {
    
    // Declaration of public static final constants
    public static final ArrayList<Color> codeColors = new ArrayList<Color>(Arrays.asList(
            Color.BLUE, Color.BLACK, Color.ORANGE, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN, Color.PINK));
    public static final ArrayList<Color> responseColors = new ArrayList<Color>(Arrays.asList(Color.RED, Color.WHITE));
    public static final int MAX_ATTEMPTS = 10;
    public static final int MAX_PEGS = 4;
    public static final int COLORS = 8;
}
