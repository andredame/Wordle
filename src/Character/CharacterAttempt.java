package Character;
import java.awt.Color;

public class CharacterAttempt {
    private char element;
    private int pointer;
    private Color color;

    public CharacterAttempt(char element){
        this.element=element;
        this.pointer=0;
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
            color=new Color(117, 219, 146);
        }
        else if(pointer ==2){
            color=new Color(243, 195, 88);
        }else{
            color=Color.RED;
        }
    }
    public int getPointer() {
        return pointer;
    }
    public Color getColor() {
        return color;
    }

}
