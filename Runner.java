/**
 * @author Paul D'Amora
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Runner {
	public static String lowerColor;
    public static String lowerColorComp;
	static MyFrame frame;

    /**
    * Runs the Sorry program through the command line
    *
    * @param args none
    * @throws IOException
    */
    public static void main(String[] args) throws IOException, InterruptedException {
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    	frame = new MyFrame("Sorry!");
		frame.pack();
		frame.setSize(720, 750);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);

		JFrame frame1 = new MyFrame1("Help!");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.pack();
		frame1.setSize(250,200);
		frame1.setVisible(true);

		String playerName = (String)JOptionPane.showInputDialog(null,
                    "Enter your name:\n", "Player Name", JOptionPane.PLAIN_MESSAGE,
                    null, null, null);

        // Create game objects
        Deck deck = new Deck();
        //Board board = new Board();
        Stats statTracker = new Stats();
        Pawn[][] pawns = createPawns();
        Pawn[] p = pawns[0];
        Pawn[] c = pawns[1];
        String difficulty = getDifficulty();
        Card pCard;
        Card cCard;

		boolean sorry = false;

        // The main game loop
        while (!gameOver(p, c)) {

        	// Player's turn

        	// Draw Card
        	frame.cardClickable = true;
        	frame.squareClickable = false;

        	while(frame.cardClickable)
        	{
        	  Thread.sleep(500);
        	}

            pCard = frame.currentCard;

            // Pick Pawn to move
            frame.currentColor = lowerColor;
            frame.squareClickable = true;

            while(frame.squareClickable)
        	{
        	  Thread.sleep(500);
        	}
            while (pCard.getValue() == 2) {
            	frame.cardClickable = true;
            	frame.squareClickable = false;

            	while(frame.cardClickable)
            	{

            	  Thread.sleep(500);

            	}

                pCard = frame.currentCard;

                // Pick Pawn to move
                frame.currentColor = lowerColor;
                frame.squareClickable = true;

                while(frame.squareClickable)
            	{
            	  Thread.sleep(500);
            	}
            }

            //other player turn

        	// Draw Card
        	frame.cardClickable = true;
        	frame.squareClickable = false;
         
         
         // frame.panel.drawPile.doClick();
         
        	while(frame.cardClickable)
        	{
        	  Thread.sleep(500);
        	}

            cCard = frame.currentCard;

            // Pick Pawn to move
            frame.currentColor = lowerColorComp;
            frame.squareClickable = true;

            while(frame.squareClickable)
        	{
        	  Thread.sleep(500);
        	}

            while (cCard.getValue() == 2) {
            	frame.cardClickable = true;
            	frame.squareClickable = false;

            	while(frame.cardClickable)
            	{
            	  Thread.sleep(500);
            	}

                cCard = frame.currentCard;

                // Pick Pawn to move
                frame.currentColor = lowerColorComp;
                frame.squareClickable = true;

                while(frame.squareClickable)
            	{
            	  Thread.sleep(500);
            	}
            }

            // Player decides in the GUI what they would like to do and returns either Sorry or an int to move

            // Also we get which pawn they want to move
            //if(sorry) {
                //board.sorry();
            //} else {
                //board.movePawn(p[0], 5 // the number of spaces to move forward)
            //}

            // Computer's turn
            //cCard = deck.dealCard();

            // Display the card
            //JOptionPane.showMessageDialog(null, cCard.getValueAsString(), "Computer Card", JOptionPane.PLAIN_MESSAGE);

            // Computer what they would like to do and returns either Sorry or an int to move
            // Also we get which pawn they want to move
            //if(sorry) {
                //board.sorry();
            //} else {
                //board.movePawn(p[0], 5 // the number of spaces to move forward)
            //}
        }

		statTracker.add(playerName, null, lowerColor, "win");
    }

	/**
	 * Runs the "computer" opponents
	 * @params int v, Pawn[] p, Pawn[] c
	 */
 	public static void compLogic(int v,Pawn[] p,Pawn[] c){
        //The important logic here is picking the best pawn to move.
        for(int i=0;i<4;i++){
            if(v==4 && c[i].getPosition()<=c[i].getStartPosition()+4){ //if your pawn is within the range
                                                     //where you can move back into the home spot
                //move back 4
                c[i].setPosition((c[i].getPosition())-4);
            }
            else if(v==10 && c[i].getPosition()<=c[i].getStartPosition()+1){
                //move back 1
                c[i].setPosition((c[i].getPosition())-1);
            }
            else if(v==11 || v==0){//if we get to swap pawns with opponent find the optimal pawn to swap
                int closestToHome=0;
                for(int j=0;i<4;i++){
                    if(p[j].getPosition()-c[i].getStartPosition()<closestToHome&&p[j].getPosition()-c[i].getStartPosition()!=c[i].getPosition()){//if you pull an 11 bump the player that has the smallest distance from your home
                        closestToHome=j;//0,1,2, or 3
                    }
                }
                //swap pawns
                int temp = c[i].getPosition();
                c[i].setPosition(p[closestToHome].getPosition());
                p[closestToHome].setPosition(temp);
            }
            else if(c[i].getStartPosition()-c[i].getPosition()==v){
                c[i].setPosition(c[i].getPosition()+v);
            }
            else{
                int move = randMove(v);
                c[move].setPosition(c[move].getPosition()+v);
            }
        }
    }

	/**
	 *	Returns a random number for a random pawn to move
	 *
	 */
    public static int randMove(int move){
        Random ran = new Random();
        int x = ran.nextInt(4);
        return x; //returns a random number- this will be the index of the pawn you choose to move.
    }


    /**
     * This function removes an element from an array and shrinks it
     *
     * @param String[] input The input array
     * @param String deleteMe The item to be deleted
     */
    public static String[] removeElements(String[] input, String deleteMe) {
        List<String> result = new ArrayList<String>();

        for(String item : input)
            if(!deleteMe.equals(item))
                result.add(item);

        String[] a = new String[result.size()];
        a = result.toArray(a);

        return a;
    }

    /**
     * Create all of the pawns and return them
     *
     * @return and array of arrays of pawns (8 total)
     */
    public static Pawn[][] createPawns() {
        String[] colorChoices = {"Red", "Green", "Blue", "Yellow"};

        // Get the users' color choice
        String color = (String)JOptionPane.showInputDialog(null,
                    "Choose a color to play as:\n", "Sorry! : Color Choice", JOptionPane.PLAIN_MESSAGE,
                    null, colorChoices, "Red");

        lowerColor = color.toLowerCase();



        // Create the players pawns
        Pawn p1;
        Pawn p2;
        Pawn p3;
        Pawn p4;
        // Create a container for the pawns


        // create the player pawns in the GUI
        if(lowerColor.equals("yellow")){
        	// Create the players pawns
            p1 = new Pawn(lowerColor,111);
            p2 = new Pawn(lowerColor,112);
            p3 = new Pawn(lowerColor,113);
            p4 = new Pawn(lowerColor,114);
        	frame.addPawn(p1,111);
        	frame.addPawn(p2,112);
        	frame.addPawn(p3,113);
        	frame.addPawn(p4,114);
        } else if(lowerColor.equals("green")) {
        	p1 = new Pawn(lowerColor,211);
            p2 = new Pawn(lowerColor,212);
            p3 = new Pawn(lowerColor,213);
            p4 = new Pawn(lowerColor,214);
        	frame.addPawn(p1,211);
        	frame.addPawn(p2,212);
        	frame.addPawn(p3,213);
        	frame.addPawn(p4,214);
        } else if(lowerColor.equals("red")) {
        	p1 = new Pawn(lowerColor,311);
            p2 = new Pawn(lowerColor,312);
            p3 = new Pawn(lowerColor,313);
            p4 = new Pawn(lowerColor,314);
        	frame.addPawn(p1,311);
        	frame.addPawn(p2,312);
        	frame.addPawn(p3,313);
        	frame.addPawn(p4,314);
        } else {
        	p1 = new Pawn(lowerColor,411);
            p2 = new Pawn(lowerColor,412);
            p3 = new Pawn(lowerColor,413);
            p4 = new Pawn(lowerColor,414);
        	frame.addPawn(p1,411);
        	frame.addPawn(p2,412);
        	frame.addPawn(p3,413);
        	frame.addPawn(p4,414);
        }
        Pawn[] player = {p1, p2, p3, p4};

        // Create an array of the remaining colors
        colorChoices = removeElements(colorChoices, color);
        int idx = new Random().nextInt(colorChoices.length);
        String colorComp = (colorChoices[idx]);
        lowerColorComp = colorComp.toLowerCase();

        // Create the computer's pawns
        Pawn c1;
        Pawn c2;
        Pawn c3;
        Pawn c4;



        // create the computer pawns in the GUI
        if(lowerColorComp.equals("yellow")){
        	c1 = new Pawn(lowerColorComp,111);
            c2 = new Pawn(lowerColorComp,112);
            c3 = new Pawn(lowerColorComp,113);
            c4 = new Pawn(lowerColorComp,114);
        	frame.addPawn(c1,111);
        	frame.addPawn(c2,112);
        	frame.addPawn(c3,113);
        	frame.addPawn(c4,114);
        } else if(lowerColorComp.equals("green")) {
        	c1 = new Pawn(lowerColorComp,211);
            c2 = new Pawn(lowerColorComp,212);
            c3 = new Pawn(lowerColorComp,213);
            c4 = new Pawn(lowerColorComp,214);
        	frame.addPawn(c1,211);
        	frame.addPawn(c2,212);
        	frame.addPawn(c3,213);
        	frame.addPawn(c4,214);
        } else if(lowerColorComp.equals("red")) {
        	c1 = new Pawn(lowerColorComp,311);
            c2 = new Pawn(lowerColorComp,312);
            c3 = new Pawn(lowerColorComp,313);
            c4 = new Pawn(lowerColorComp,314);
        	frame.addPawn(c1,311);
        	frame.addPawn(c2,312);
        	frame.addPawn(c3,313);
        	frame.addPawn(c4,314);
        } else {
        	c1 = new Pawn(lowerColorComp,411);
            c2 = new Pawn(lowerColorComp,412);
            c3 = new Pawn(lowerColorComp,413);
            c4 = new Pawn(lowerColorComp,414);
        	frame.addPawn(c1,411);
        	frame.addPawn(c2,412);
        	frame.addPawn(c3,413);
        	frame.addPawn(c4,414);
        }

     // Create a container for the pawns
        Pawn[] computer = {c1, c2, c3, c4};


        Pawn[][] pawns = {player, computer};

        return pawns;
    }

    public static String getDifficulty() {
        String[] kindChoices = {"Mean", "Nice"};

        // Get the users' kindness choice
        String kindness = (String)JOptionPane.showInputDialog(null,
                    "Choose a kindless level for the computer opponent:\n", "Sorry! : Kindness Choice", JOptionPane.PLAIN_MESSAGE,
                    null, kindChoices, "Nice");

        return kindness;
    }

    /**
     * Determines if the conditions to end the game have happened
     *
     * @param Pawn[] p The player's pawns
     * @param Pawn[] c The computer's pawns
     * @return boolean Is the game over
     */
    public static boolean gameOver(Pawn[] p, Pawn[] c) {
        boolean pwinFlag = true;
        boolean cwinFlag = true;

        // Loop through the pawns and check if any aren't home
        for(Pawn pawn : p)
            if(!pawn.isHome()) {
                // One of the pawns isn't home
                pwinFlag = false;
                break;
            }
        for(Pawn pawn: c)
            if(!pawn.isHome()) {
                // One of the pawns isn't home
                cwinFlag = false;
                break;
            }

        return pwinFlag ^ cwinFlag;
    }
 }
