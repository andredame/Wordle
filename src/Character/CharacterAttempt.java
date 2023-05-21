package Character;
import java.awt.Color;

public class CharacterAttempt {
    private char element;
    private int pointer;
    private Color color;

    public CharacterAttempt(char element){
        this.element=element;
        this.pointer=0;
        color=new Color (49, 42, 44);
    }
    
    public char getElement() {
        return element;
    }
    public void setElement(char element) {
        this.element = element;
    }
    public void setPointer(int pointer) {
        this.pointer = pointer;
        if(pointer==1){
            color=new Color(0, 68, 49);
        }
        else if(pointer ==2){
            color=new Color(198, 159, 0);
        }
    }
    public int getPointer() {
        return pointer;
    }
    public Color getColor() {
        return color;
    }

}
