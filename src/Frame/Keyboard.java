package Frame;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Keyboard extends JButton {
    
    
    private  String character;
    private int pointer;
    private Color color;
    private int pRow;
    private int numberOfArray;
    private int gridx;
    
   
    public Keyboard(String character, int pRow, int numberOfArray, Color color,ActionListener listener,int gridx) {
        setForeground(Color.WHITE);
        addActionListener(listener);
        setName(character);
        this.pointer=0;
        setText(character);
        this.color=color;
        this.character=character;
        this.pRow = pRow;
        this.numberOfArray = numberOfArray;
        setBackground(color);
        this.gridx=gridx;
    }
    public int getPointer() {
        return pointer;
    }
    public Color getColor(){
        return color;
    }
    public String getCharacter() {
        return character;
    }
    public int getPRow(){
        return pRow;
    }
    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
    public void setColor(Color color) {
       this.color=color;
    }

}
