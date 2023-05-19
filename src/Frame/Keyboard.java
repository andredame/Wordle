package Frame;

import java.awt.Color;

import javax.swing.JButton;


public class Keyboard extends JButton {
    

    private  String character;
    private Color color;
    
    
    public Keyboard(String character,Color c){
        this.character=character;
        this.color=color;
    }
    public String getCharacter() {
        return character;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

}
