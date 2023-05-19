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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import Character.*;
import Words.*;



    public class FramePlay extends JFrame implements ActionListener,KeyListener{


        private JButton buttons[][]=new JButton[6][5];
        private JPanel buttonsPanel=new JPanel();
        private Label label;
        private JPanel mainPanel=new JPanel();
        private String word;
        private boolean gameIsOver;
        private int round; 
        private int letter; //index of the letter


        //KEYBOARD ATRIBUTES
        private JFrame f = new JFrame("Keyboard");
        private JPanel keyboard = new JPanel();
        

        private static final String[] keyA = 
            { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
            
        private static final String[] keyB = 
            { "A", "S", "D", "F", "G", "H", "J", "K", "L", "Enter"};
        private static final String[] keyC = 
            { "Z", "X", "C", "V", "B", "N", "M", };

        private ArrayList<Keyboard> keysAbove= new ArrayList<>();
        private ArrayList<Keyboard> keysMiddle=new ArrayList<>();
        private ArrayList<Keyboard> keysBelow= new ArrayList<>();
        private Color background;
        private JPanel[] pRow;
        private GridBagConstraints c;

        
        public FramePlay(){ 
             background=new Color (113, 66, 55);
            

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
                    buttons[i][j].setForeground(Color.BLUE);
                    buttonsPanel.add(buttons[i][j]);
                    Dimension buttonSize = new Dimension(150, 50); // Set the preferred size for the button
                    buttons[i][j].setPreferredSize(buttonSize);
                    buttons[i][j].addActionListener(null);
                    buttonsPanel.add(buttons[i][j]);
                }    
            }


            this.setResizable(false);//prevent a Java frame from being displayed in full screen
            this.add(buttonsPanel);
            this.addKeyListener(this);
            this.setLocationRelativeTo(null); // Set the frame at the middle of the screen
            word=generateWord(); // generate the word
            addBorderInTheRound(new Color(51, 57, 57));
        

            //Atributes of the keyboard
            
        keyboard.setLayout(new GridBagLayout());
        
         c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.;
        //c.weightx = 1f;
        pRow=new JPanel[3];

        for (int row = 0; row < keyA.length; ++row) {
            pRow[0] = new JPanel(new GridBagLayout());
            c.gridx = row+2;
            Keyboard j = new Keyboard(keyA[row],row,1,background,listener,c.gridx);
            keysAbove.add(j);
            pRow[0].add(j);
            keyboard.add(pRow[0], c);
        }
        for (int row = 0; row < keyB.length; ++row) {
            pRow[1] = new JPanel(new GridBagLayout());
            c.gridx = row+2;
            Keyboard j = new Keyboard(keyB[row],row,2,background,listener,c.gridx);
            keysMiddle.add(j);
            pRow[1].add(j);
            keyboard.add(pRow[1], c);   
        }
        for (int row = 0; row < keyC.length; ++row) {
            pRow[2] = new JPanel(new GridBagLayout());
            c.gridx = row+2;
            Keyboard j = new Keyboard(keyC[row],row,3,background,listener,c.gridx);
            keysBelow .add(j);
            pRow[2].add(j);
            keyboard.add(pRow[2], c);  
            this.setForeground(Color.BLACK);
        } 
        keyboard.setBackground(background);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.add(keyboard);
        f.addKeyListener(this);
        this.add(mainPanel,BorderLayout.SOUTH);


        }



        @Override
        public void keyPressed(KeyEvent e) {
            /* */
            int code=e.getKeyCode();
           
           
            if(!gameIsOver){
                addBorderInTheRound(new Color(51, 57, 57));
                    if (code>=65 && code<=90){ //Verify if the input is in the alphabet
                        if (letter<5){ 
                            fillButton(String.valueOf((char)code));
                        }
                        
                }
                else if(code==10){              //if the user pressed ENTER, it means that he wants to submit his word to check 
                   enterPressed();
                }else if(code  == KeyEvent.VK_BACK_SPACE){
                    if(letter>0 && !gameIsOver){
                        backSpace();
                    }
                }
            }
            if(code == 27){
                dispose();
            }
            addBorderInTheRound(new Color(51, 57, 57));
            
        }
        public void fillButton(String code){
            buttons[round][letter].setText(code);
            letter++;
        }
        public void backSpace(){
            buttons[round][--letter].setText(" ");
        }

        public void enterPressed(){
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
        }
        public void PaintKeyboard(char c,int pointer){
            
            Color green = new Color(117, 219, 146);
            Color red=Color.RED;
            Color yellow =new Color(243, 195, 88);
            System.out.println(pointer);
            
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
                            System.out.print(objeto.getCharacter());
                            return;
                        }else{
                            if (pointer==1){
                           
                                objeto.setColor(green);
                                objeto.setPointer(pointer);
                                System.out.print(objeto.getCharacter());
                                return;
                            }else{
                                if(pointer==2 && objeto.getPointer()==0 ){
                                    objeto.setColor(yellow);
                                    pRow[1].setBackground(yellow);
                                    objeto.setPointer(pointer);
                                    System.out.print(objeto.getCharacter());
                                    
                                    return;
                                }
                            }
                        }
                    }
                }
            
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
            }
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
                PaintKeyboard(charOfAttempt[i].getElement(),charOfAttempt[i].getPointer());
                
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
        
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sasdasdSystem.out.print(e.getActionCommand());
                String nomeBotao = ((JButton) e.getSource()).getName();
                if (letter<5 && !nomeBotao.equals("Enter")){
                    fillButton(nomeBotao);
                // Coloque aqui o código que deseja executar quando o botão for clicado
                }
                if(nomeBotao.equals("Enter")){
                    enterPressed();
                }
               
            }
        };
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }


        





