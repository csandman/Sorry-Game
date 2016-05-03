import javax.swing.*;
import java.awt.*; // for layout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpGUI
{
   public static void main(String [] args) 
{
      JFrame frame = new MyFrame1("Help!");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setSize(200,200);
      frame.setVisible(true);
      

   }
}

//Small GUI with 3 buttons to help with any questions

class MyFrame1 extends JFrame implements ActionListener
{
   private JLabel label;
   private JButton button1,button2, button3;
   MyFrame1(String s) 
   {
      super (s);
      setLayout(new FlowLayout());
      
      label = new JLabel("Help and Instructions for:");
      add(label);
      
      button3 = new JButton("Overview");
      add(button3);
      
      button1 = new JButton("Gameplay/Rules");
      add(button1);
      
      button2 = new JButton("Card Instructions");
      add(button2);
      
      
      button1.addActionListener(new MyAction());
      button2.addActionListener(new MyAction2());
      button3.addActionListener(new MyAction3());
      
      
   }
   @Override
   public void actionPerformed(ActionEvent e) {
   	
   }

   //Actions for the 3 buttons, description/summary of each button title
   public class MyAction implements ActionListener{
	   public void actionPerformed(ActionEvent ae){
	     JOptionPane.showMessageDialog(null, "OBJECTIVE: The First Player to get all four of your pawns from your color START to your color HOME."
	     		+ "\nGAMEPLAY: \n-Note: if it's your first turn and you do not draw a card that lets you start a pawn out, you forfeit(skip) your turn\n-On all turns, take the top card on the Draw pile and move accordingly."
	     		+ "\nTO START A PAWN: to move a pawn from your START out onto the track, you must draw either a 1 or a 2. If it is a 2, do as it says, then draw again and move if possible. You may not start a pawn out with and other cards!"
	     		+ "\nJUMPING AND BUMBING: You may jump over your own or another player's pawn that's in your way, counting it as one space. BUT...if you land on a space that's already occupied by an opponent's pawn, BUMP that pawn back to its own color START space."
	     		+ "\nMOVEING BACKWARDS: 4 and 10 cards move you backwards. If you have successfully moved a pawn backwards atleast two spaces beyond your own START space, you may, on a subsequent turn, move into you own SAFETY ZONE without moving all the way around the board."
	     		+ "\nEXTRA NOTES: \n-Two pawns of the same color may never occupy the same space. If your only possible move would make you do so, you forfeit your turn."
	     		+ "\n-If at anytime you cannot move, you forfeit your turn."
	     		+ "\n-But if at any time you can move, you must move, even if it's to your disadvantage."
	     		+ "\n-If you run out of cards in the Draw Pile, shuffle the Discards and use them.",
	     		"Gameplay", 1);
	   }
	 }
   public class MyAction2 implements ActionListener{
	   public void actionPerformed(ActionEvent ae){
	     JOptionPane.showMessageDialog(null, "1: Either start a pawn out OR move one pawn forward 1 space."
	     		+ "\n2: Either start a pawn out OR move one pawn forward 2 spaces. Choose one action then DRAW AGAIN and move accordingly."
	     		+ "\n3: Move one pawn forward 3 spaces."
	     		+ "\n4: Move one pawn backwards 4 spaces."
	     		+ "\n5: Move one pawn forward 5 spaces."
	     		+ "\n7: Either move one pawn forward 7 spaces, OR split the forward move between any two pawns."
	     		+ "\n8: Move one pawn forward 8 spaces."
	     		+ "\n10: Either move one pawn forward 10 spaces, OR move one pawn backwards 1 space."
	     		+ "\n11: Move one pawn forward 11 spaces, OR switch any one of your pawns with one of any opponent's."
	     		+ "\n12: Move one Pawn forwards 12 spaces."
	     		+ "\nSORRY!: Take one pawn from your START, place it on any space that is occupied by any opponent, and bump that opponent's pawn back to its START. If there is no pawn on you START or no opponent's pawn on any space you can move to, you forfeit your move.", "Card Instructions", 1);
	   }
	 }
   public class MyAction3 implements ActionListener{
	   public void actionPerformed(ActionEvent ae){
	     JOptionPane.showMessageDialog(null, "Our version of Sorry! is a board game for one user. "
	     		+ "\nIn this version of Sorry the user will be against a computer. Both the computer and yourself will take turns moving your four pawns to see which"
	     		+ "\nof you can meet the win conditions first; The first to move all four pawns to their respective end zone, first wins."
	     		+ "\nGUI Tips:"
	     		+ "\n-To draw a card click the deck in the middle of the board"
	     		+ "\n-If a move can not be made click the pass button.", "Overview", 1);
	   }
	 }


}
