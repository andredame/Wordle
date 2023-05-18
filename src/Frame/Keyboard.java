package Frame;

import java.awt.Color;

import javax.swing.JButton;


public class Keyboard extends JButton {
    

    private  String character;
    private Color color;
    private JButton button;
    
    public Keyboard(String character){
        this.character=character;
        setBackground(new Color (9, 9, 9,255));
    }
    public String getCharacter() {
        return character;
    }
    public Color getColor() {
        return color;
    }
}
