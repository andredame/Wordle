package Frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class LaunchPage extends JFrame implements ActionListener{
    JFrame frame=new JFrame();
    JButton playButton = new JButton("Play");
    JLabel titulo=new JLabel("TERMO");
    public LaunchPage(){

        
        JFrame frame=new JFrame();
        playButton.setBounds(100, 160, 200, 40);
        Font f=new Font("Serif", Font.ITALIC, 24);
        playButton.setFont(f);
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        Color customColorButton = new Color(243, 195, 88);
        playButton.setBackground(customColorButton);
        Color customColor = new Color(1, 54, 91);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(playButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLayout(null);
        frame.setVisible(true);
        

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() ==playButton){
            FramePlay p=new FramePlay();
            dispose();
       }
    }
}
