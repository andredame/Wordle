package Frame;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Keyboard extends JButton {
    
    
    private  String character;
    private int pRow;
    private int numberOfArray;
    
    
   
    public Keyboard(String character, int pRow, int numberOfArray, Color color,ActionListener listener) {
        setForeground(Color.WHITE);
        addActionListener(listener);
        setName(character);
        setText(character);
        this.pRow = pRow;
        this.numberOfArray = numberOfArray;
        setBackground(color);
    }
    public String getCharacter() {
        return character;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
       
    }

}
