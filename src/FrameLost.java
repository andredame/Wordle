import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameLost extends FramePlay {
    public FrameLost(){
        JFrame lost= new JFrame("LOSER");
        JLabel message=new JLabel("A palavra era:"+super.getWord());
        message.setBackground(new Color (1, 54, 91));
        lost.setSize(100, 100);
        lost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lost.add(message);
        lost.setVisible(true);
    }
}
