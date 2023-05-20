package Frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import Character.*;
import Words.*;



    public class FramePlay extends JFrame implements ActionListener,KeyListener{


        private JButton buttons[][]=new JButton[6][5];
        private JPanel buttonsPanel=new JPanel();
        private Label label;

        private String word;
        private boolean gameIsOver;
        private int round; 
        private int letter; //index of the letter


        //KEYBOARD ATRIBUTES
        private JFrame f = new JFrame("Keyboard");
        private JPanel keyboard = new JPanel();
        
        private static final String[][] key = {
            { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P","BACK_SPACE"},
            { "A", "S", "D", "F", "G", "H", "J", "K", "L",  "ENTER"},
            { "Z", "X", "C", "V", "B", "N", "M"},

    };

        private Color background;
        private GridBagConstraints c;

        
        public FramePlay(){ 
             background=new Color (78, 44, 67);
            

            //FRAME 
            this.round=0;
            this.setBackground(background);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setSize(800, 515);

            //FRASE E FAIXA PRETA DE CIMA 
            label=new Label();
            label.setFont(new Font ("Bodoni MT",Font.BOLD,40));
            label.setAlignment(label.CENTER);
            label.setText("WORDLE");
            label.setBackground(background);
            label.setForeground(Color.WHITE);
            label.isOpaque();
            this.add(label,BorderLayout.NORTH);

            //TENTATIVAS 
            buttonsPanel.setBackground(background);
            buttonsPanel.setVisible(true);
            buttonsPanel.setSize(new Dimension(100, 100));
            for (int i=0;i<6;i++){
                for (int j=0;j<5;j++){
                    buttons[i][j]=new JButton();
                    buttons[i][j].setBackground(background); 
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setFont(new Font ("Apple",Font.BOLD,20));
                    buttonsPanel.add(buttons[i][j]);
                    Dimension buttonSize = new Dimension(150, 50); // Set the preferred size for the button
                    buttons[i][j].setPreferredSize(buttonSize);
                    buttonsPanel.add(buttons[i][j]);
                }    
            }


            this.setResizable(false);//prevent a Java frame from being displayed in full screen
            this.add(buttonsPanel);
            
            this.setLocationRelativeTo(null); // Set the frame at the middle of the screen
            word=generateWord(); // generate the word
            addBorderInTheRound(new Color(51, 57, 57));
        

            //Atributes of the keyboard
            
            keyboard.setLayout(new GridBagLayout());

            JPanel pRow;
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.WEST;
            c.weightx = 1d;
    
            for (int row = 0; row < key.length; ++row) {
                pRow = new JPanel(new GridBagLayout());

                c.gridy = row;

                for (int col = 0; col < key[row].length; ++col) {
                    JButton button = new JButton(key[row][col]);
                    button.addActionListener(this::buttonPressed);
                    button.setBackground(new Color(225, 221, 220));
                    button.setForeground(Color.BLACK);
                    String actionCommand = "key_" + key[row][col];
                    button.setActionCommand(actionCommand);
                    button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key[row][col]), actionCommand);
                    button.getActionMap().put(actionCommand, new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonPressed(e);
                        }
                    });
                    pRow.add(button);
                }

                keyboard.add(pRow, c);
            }

            f.add(keyboard);
        
        keyboard.setBackground(background);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.add(keyboard);
        this.add(mainPanel,BorderLayout.SOUTH);
        this.addKeyListener(null);


        }
        private void buttonPressed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String text = button.getText();
            System.out.println(text);
            if (!gameIsOver){
                addBorderInTheRound(new Color(51, 57, 57));
                if (text.equals("ENTER")) {
                    enterPressed();
                } else if (text.equals("BACK_SPACE")) {
                    if (letter > 0 && !gameIsOver) {
                        backSpace();
                    }
                }
                else{
                    fillButton(text);
                }
            addBorderInTheRound(new Color(51, 57, 57));
           }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == 27) {
                dispose();
            }
    
        }
        public void fillButton(String code){
            if (letter<5){
                buttons[round][letter].setText(code);
                letter++;
            }
            
        }
        public void backSpace(){
            buttons[round][--letter].setText(" ");
        }

        public void enterPressed(){
            
            if(round<6){                //check the round 
                String attemptWord="";
                if (letter==5){ //check wheter user submitted a 5 letter word or not
                    for(int i=0;i<5;i++){
                        System.out.println(attemptWord+=buttons[round][i].getText());
                        //attemptWord+=buttons[round][i].getText(); //add in AttemptWord the characters of the Word
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
        }
        public void PaintKeyboard(char c,int pointer){
            
            Color green = new Color(117, 219, 146);
            Color red=Color.RED;
            Color yellow =new Color(243, 195, 88);
            
            /* 
            for (Keyboard objeto : keysAbove) {
                
                String charString = String.valueOf(Character.toUpperCase(c));
                
                if (objeto.getCharacter().equals(charString)) {
                    if(objeto.getPointer()==1){
                        return;
                    }else{
                        if (pointer==1){
                            objeto.setPointer(pointer);
                            objeto.setColor(green);
                            return;
                        }else{
                            if(pointer==2 && objeto.getPointer()==0 ){
                                return;
                            }
                        }
                    }
                    
                }
            }
            for (Keyboard objeto : keysMiddle) {

                    String charString = String.valueOf(Character.toUpperCase(c));
                    if (objeto.getCharacter().equals(charString)) {
                        if(objeto.getPointer()==1){

                            return;
                        }else{
                            if (pointer==1){
                           
                                objeto.setColor(green);
                                objeto.setPointer(pointer);
                                return;
                            }else{
                                if(pointer==2 && objeto.getPointer()==0 ){
                                    objeto.setColor(yellow);
                                    pRow[1].setBackground(yellow);
                                    objeto.setPointer(pointer);
                                    
                                    
                                    return;
                                }
                            }
                        }
                    }
                }
            ,
            for (Keyboard objeto : keysBelow) {
                
                String charString = String.valueOf(Character.toUpperCase(c));
                if (objeto.getCharacter().equals(charString)) {
                    if(objeto.getPointer()==1){
                        return;
                    }else{
                        if (pointer==1){
                       
                            objeto.setColor(green);
                            objeto.setPointer(pointer);
                            return;
                        }else{
                            if(pointer==2 && objeto.getPointer()==0 ){
                                objeto.setColor(yellow);
                                pRow[0].setBackground(yellow);
                                objeto.setPointer(pointer);
                               
                                
                                return;
                            }
                        }
                    }
                }
            } */
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
        
        public String getWord() {
            return word;
        }
        public boolean validWord(String attempt){
            return false;
        }
        public String generateWord(){
            WordGenerator w = new WordGenerator();
            String word = w.generateWord();
            System.out.println(word);
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
            
            for (int i = 0; i < 5; i++) {

                if(charOfAttempt[i].getPointer()!=1){

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
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }


        





