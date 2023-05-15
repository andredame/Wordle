import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;

public class Guess extends FramePlay {
    private String attempt; //attempt

    public Guess(String attempt){
        this.attempt=attempt;
        checkWordAndPaintFrame();
    }
    public void checkWordAndPaintFrame(){
        String word=super.getWord(); //Word Game
        JButton[][] buttons=super.getButtons();

        for(int i=0;i<5;i++){
           System.out.print(buttons[super.getRound()][i]);
        }

    }

}
