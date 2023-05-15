import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FramePlay extends JFrame implements ActionListener, KeyListener{
    private JButton buttons[][]=new JButton[6][5];
    private JPanel buttonsPanel=new JPanel();
    private String word;
    private int round; 
    private int letter; //index of the letter

    public FramePlay(){  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setSize(500, 600);
        buttonsPanel.setLayout(new GridLayout(6, 5, 5, 5));
        buttonsPanel.setVisible(true);
       

        for (int i=0;i<buttons.length;i++){
            for (int j=0;j<buttons[0].length;j++){
                buttons[i][j]=new JButton();
                buttons[i][j].setBackground(Color.WHITE); 
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setEnabled(false);
                buttons[i][j].setFont(new Font ("MV Boli",Font.BOLD,20));
                
                buttonsPanel.add(buttons[i][j]);
            }
        }
        this.add(buttonsPanel);
        this.repaint();
        this.revalidate();
        this.addKeyListener(this);
        word=generateWord();

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if (code>=65 && code<=90){ //Verify if the input is in the alphabet
            if (letter<5){ 
                buttons[round][letter].setText(String.valueOf((char)code));
                letter++;
            }
        }
        else if(code==10){
            if(round<6){
                if (letter==5){
                    String attemptWord="";
                    for(int i=0;i<5;i++){
                        attemptWord+=buttons[round][i].getText();
                    }
                    Guess guess= new Guess(attemptWord);
                    
                }
            }
        }
        else if(code == KeyEvent.VK_BACK_SPACE){
            if(letter>0){
                buttons[round][--letter].setText(" ");
            }
        }
        
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public void checkWord(String attempt){
        

    }
    @Override
    public void keyTyped(KeyEvent e) {
      
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    public boolean validWord(String attempt){
        return false;
    }
    public String generateWord(){
        String n="vazar";
        return n;
    }

    public String getWord() {
        return word;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public int getRound() {
        return round;
    }
}
