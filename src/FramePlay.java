import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


    public class FramePlay extends JFrame implements ActionListener,KeyListener{


        private JButton buttons[][]=new JButton[6][5];
        private JPanel buttonsPanel=new JPanel();
        private String word;
        private boolean gameIsOver;
        private int round; 
        private int letter; //index of the letter

        public FramePlay(){ 
            this.round=0;
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

            if(!gameIsOver){
                    if (code>=65 && code<=90){ //Verify if the input is in the alphabet
                        if (letter<5){ 
                            buttons[round][letter].setText(String.valueOf((char)code));
                            letter++;
                        }
                }
                else if(code==10){              //if the user pressed ENTER, it means that he wants to submit his word to check 
                    if(round<6){                //check the round 
                        String attemptWord="";
                        if (letter==5){ //check wheter user submitted a 5 letter word or not
                            for(int i=0;i<5;i++){
                                attemptWord+=buttons[round][i].getText(); //add in AttemptWord the characters of the Word
                            }
                            PaintFrame(attemptWord.toLowerCase()); //set the pointers
                            gameIsOver=checkWord(attemptWord.toLowerCase());
                            if (!gameIsOver){ //If the game is not over goes to the next round 
                                round++;
                                letter=0;
                            }
                        }
                    }
                }else if(code  == KeyEvent.VK_BACK_SPACE){
                    if(letter>0 && !gameIsOver){
                        buttons[round][--letter].setText(" ");
                    }
                }
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        public String getWord() {
            return word;
        }
        public boolean validWord(String attempt){
            return false;
        }
        public String generateWord(){
            String word = "vazar";
            
            return word;
        } 
        public JButton[][] getButtons() {
            return buttons;
        }
        /**
         * @param attemptWord
         */
        public void PaintFrame(String attemptWord){
            //Word Game
            CharacterWord[] charOfWord= new CharacterWord[5];
            CharacterAttempt[] charOfAttempt=new CharacterAttempt[5];


            for (int i = 0; i < charOfWord.length; i++) {
                charOfWord[i]= new CharacterWord (word.charAt(i));
                charOfAttempt[i]=new CharacterAttempt (attemptWord.charAt(i));
            }
            
            for (int i = 0; i < charOfWord.length; i++) {

                if(!charOfWord[i].hasPointer()){

                    if(charOfWord[i].getElement() == charOfAttempt[i].getElement()){
                        
                        charOfAttempt[i].setPointer(1);
                        charOfWord[i].setHasPointer(true);

                    }
                    else{ //search in the array the same letter 
                        for (int j=0;j<5;j++){
                            if (!charOfWord[j].hasPointer()){


                                if ( charOfAttempt[i].getElement() == charOfWord[j].getElement()){
                                    if(charOfWord[j].getElement() == charOfAttempt[j].getElement()){ //check wheter 
                                        charOfWord[j].setHasPointer(true);
                                        charOfAttempt[j].setPointer(1);
                                        break;
                                    }


                                    else{
                                        charOfWord[j].setHasPointer(true);
                                        charOfAttempt[i].setPointer(2);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
    
            }
            for(int i=0;i<5;i++){
                buttons[round][i].setBackground(charOfAttempt[i].getColor());
            }
        }
        public boolean checkWord(String attempt){
            return attempt.equals(word);
        }
        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }
        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
        }

}
