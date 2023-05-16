public class CharacterW {
    private char element;
    private boolean hasPointer;
    public CharacterW(char element){
        this.hasPointer=false;
        this.element=element;

    }
    public boolean hasPointer(){
        return hasPointer;
    }
    public char getElement() {
        return element;
    }
    public void setElement(char element) {
        this.element = element;
    }
    public void setHasPointer(boolean hasPointer){
        this.hasPointer=hasPointer;
    }
   
}
