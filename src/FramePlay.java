import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import Character.*;


    public class FramePlay extends JFrame implements ActionListener,KeyListener{


        private JButton buttons[][]=new JButton[6][5];
        private JPanel buttonsPanel=new JPanel();
        private Label label;
        private Panel keyboard;
        private String word;
        private boolean gameIsOver;
        private int round; 
        private int letter; //index of the letter


        
        public FramePlay(){ 
            //FRAME 
            this.round=0;
            this.setBackground(Color.BLACK);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setSize(600, 600);

            //FRASE E FAIXA PRETA DE CIMA 
            label=new Label();
            label.setFont(new Font ("MV Boli",Font.BOLD,40));
            label.setAlignment(label.CENTER);
            label.setText("WORDLE");
            label.setBackground(Color.BLACK);
            label.setForeground(new Color(243, 195, 88));
            label.isOpaque();
            this.add(label,BorderLayout.NORTH);



            
            buttonsPanel.setVisible(true);
            
            buttonsPanel.setSize(new Dimension(100, 100));
            for (int i=0;i<6;i++){
                for (int j=0;j<5;j++){
                    buttons[i][j]=new JButton();
                    buttons[i][j].setBackground(Color.WHITE); 
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setFont(new Font ("MV Boli",Font.BOLD,20));
                    buttonsPanel.add(buttons[i][j]);
                    Dimension buttonSize = new Dimension(100, 50); // Set the preferred size for the button
                    buttons[i][j].setPreferredSize(buttonSize);
        
                    buttonsPanel.add(buttons[i][j]);
                }
                
            }
    
            

            // Add the panelForButtonsPanel to the frameS
            this.add(buttonsPanel);

            
            this.addKeyListener(this);
        
            this.setLocationRelativeTo(null);
            word=generateWord();
            addBorderInTheRound(new Color(51, 57, 57));
 
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int code=e.getKeyCode();
            
            if(!gameIsOver){
                addBorderInTheRound(new Color(51, 57, 57));
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
                                addBorderInTheRound(new Color(220, 0, 0));
                                round++;
                                letter=0;
                            }
                            if (round==6 && !gameIsOver){ 
                                FrameLost f= new FrameLost();
                                this.label.setText(getWord().toUpperCase());
                                gameIsOver=true; 
                            }
                        }
                    }
                }else if(code  == KeyEvent.VK_BACK_SPACE){
                    if(letter>0 && !gameIsOver){
                        buttons[round][--letter].setText(" ");
                        
                    }
                }

            }
            if(code == 27){
                dispose();
            }
            addBorderInTheRound(new Color(51, 57, 57));
            
        }
        public void addBorderInTheRound(Color c){
            if (round<6){
                Border roundBorder = BorderFactory.createLineBorder(c, 3);
                for (int j = 0; j < buttons[round].length; j++) {
                    buttons[round][j].setBorder(roundBorder);
                    buttons[round][j].setForeground(c);
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
        }
        @Override
        public void keyReleased(KeyEvent e) {
            
        }

}
