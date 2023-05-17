package Frame;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameLost extends JFrame{
    public FrameLost(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        
        JLabel label = new JLabel("Você perdeu o jogo!");
        label.setHorizontalAlignment(JLabel.CENTER);
        
        this.add(label);
        
        this.setVisible(true);
    }
}
