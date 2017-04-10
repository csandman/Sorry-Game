import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*; // for layout
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {

	private JLabel infoText;
	private JButton passButton;
	public JPanel panel;

	Board newBoard = new Board();
	squareButton[] buttonArray = new squareButton[500];
	Deck deck = new Deck();
   
   JButton discardPile;
   
   boolean shouldSwitch = false;
   square pawnToSwitch;
   square firstPawnForSplit;
   boolean sevenCard = false;
   boolean sorryCard = false;
   boolean tenCard = false;
   boolean elevenCard = false;
   boolean shouldSplit = false;

   //initialize icons for the cards
	ImageIcon blankCard = new ImageIcon("icons/blankcard.png");
	ImageIcon card1 = new ImageIcon("icons/1card.png");
	ImageIcon card2 = new ImageIcon("icons/2card.png");
	ImageIcon card3 = new ImageIcon("icons/3card.png");
	ImageIcon card4 = new ImageIcon("icons/4card.png");
	ImageIcon card5 = new ImageIcon("icons/5card.png");
	ImageIcon card7 = new ImageIcon("icons/7card.png");
	ImageIcon card8 = new ImageIcon("icons/8card.png");
	ImageIcon card10 = new ImageIcon("icons/10card.png");
	ImageIcon card11 = new ImageIcon("icons/11card.png");
	ImageIcon card12 = new ImageIcon("icons/12card.png");
	ImageIcon cardSorry = new ImageIcon("icons/sorrycard.png");
	ImageIcon card = new ImageIcon("icons/card.png");

   
   //global value for the number of the current card drawn
	public int cardNumber;

   //global value for the number of the most recently clicked tile
	public int tileNumber;

   
	public Card currentCard;

	public String currentColor;

	boolean squareClickable = false;
	boolean cardClickable = false;

	MyFrame(String s) throws IOException {
		super(s);
		setLayout(new FlowLayout());

		deck.shuffle();

		ImageIcon blankIcon = new ImageIcon("icons/blank.png");
		ImageIcon endZone = new ImageIcon("icons/endZone.png");

		ImageIcon blueSafeZone = new ImageIcon("icons/blueSafeZone.png");
		ImageIcon blueArrow = new ImageIcon("icons/blueArrow.png");
		ImageIcon blueLine = new ImageIcon("icons/blueLine.png");
		ImageIcon blueCircle = new ImageIcon("icons/blueCircle.png");
		ImageIcon redSafeZone = new ImageIcon("icons/redSafeZone.png");
		ImageIcon redArrow = new ImageIcon("icons/redArrow.png");
		ImageIcon redLine = new ImageIcon("icons/redLine.png");
		ImageIcon redCircle = new ImageIcon("icons/redCircle.png");
		ImageIcon greenSafeZone = new ImageIcon("icons/greenSafeZone.png");
		ImageIcon greenArrow = new ImageIcon("icons/greenArrow.png");
		ImageIcon greenLine = new ImageIcon("icons/greenLine.png");
		ImageIcon greenCircle = new ImageIcon("icons/greenCircle.png");
		ImageIcon yellowSafeZone = new ImageIcon("icons/yellowSafeZone.png");
		ImageIcon yellowArrow = new ImageIcon("icons/yellowArrow.png");
		ImageIcon yellowLine = new ImageIcon("icons/yellowLine.png");
		ImageIcon yellowCircle = new ImageIcon("icons/yellowCircle.png");
      
      //initialize panel settings
      panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(16, 16));

      //add row 1 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 0));
		panel.add(makeSquare("yellow", "arrow", yellowArrow, 1));
		panel.add(makeSquare("yellow", "line", yellowLine, 2));
		panel.add(makeSquare("yellow", "line", yellowLine, 3));
		panel.add(makeSquare("yellow", "circle", yellowCircle, 4));
		panel.add(makeSquare("none", "blank", blankIcon, 5));
		panel.add(makeSquare("none", "blank", blankIcon, 6));
		panel.add(makeSquare("none", "blank", blankIcon, 7));
		panel.add(makeSquare("none", "blank", blankIcon, 8));
		panel.add(makeSquare("yellow", "arrow", yellowArrow, 9));
		panel.add(makeSquare("yellow", "line", yellowLine, 10));
		panel.add(makeSquare("yellow", "line", yellowLine, 11));
		panel.add(makeSquare("yellow", "line", yellowLine, 12));
		panel.add(makeSquare("yellow", "circle", yellowCircle, 13));
		panel.add(makeSquare("none", "blank", blankIcon, 14));
		panel.add(makeSquare("none", "blank", blankIcon, 15));
      
      //add row 2 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 59));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 101));
		addSpace(1);
		panel.add(makeSquare("yellow", "start", endZone, 111));
		panel.add(makeSquare("yellow", "start", endZone, 112));
		addSpace(9);
		panel.add(makeSquare("green", "arrow", greenArrow, 16));
      
      //add row 3 of tiles
		panel.add(makeSquare("blue", "circle", blueCircle, 58));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 102));
		addSpace(1);
		panel.add(makeSquare("yellow", "start", endZone, 113));
		panel.add(makeSquare("yellow", "start", endZone, 114));
		addSpace(2);
		panel.add(makeSquare("green", "end", endZone, 207));
		panel.add(makeSquare("green", "end", endZone, 206));
		panel.add(makeSquare("green", "safe", greenSafeZone, 205));
		panel.add(makeSquare("green", "safe", greenSafeZone, 204));
		panel.add(makeSquare("green", "safe", greenSafeZone, 203));
		panel.add(makeSquare("green", "safe", greenSafeZone, 202));
		panel.add(makeSquare("green", "safe", greenSafeZone, 201));
		panel.add(makeSquare("green", "line", greenLine, 17));

      //add row 4 of tiles
		panel.add(makeSquare("blue", "line", blueLine, 57));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 103));
		addSpace(5);
		panel.add(makeSquare("green", "end", endZone, 208));
		panel.add(makeSquare("green", "end", endZone, 209));
		addSpace(5);
		panel.add(makeSquare("green", "line", greenLine, 18));
      
      //add row 5 of tiles
		panel.add(makeSquare("blue", "line", blueLine, 56));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 104));
		addSpace(10);
		panel.add(makeSquare("green", "start", endZone, 213));
		panel.add(makeSquare("green", "start", endZone, 211));
		panel.add(makeSquare("green", "circle", greenCircle, 19));

      //add row 6 of tiles
		panel.add(makeSquare("blue", "line", blueLine, 55));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 105));
		addSpace(10);
		panel.add(makeSquare("green", "start", endZone, 214));
		panel.add(makeSquare("green", "start", endZone, 212));
		panel.add(makeSquare("none", "blank", blankIcon, 20));

      //add row 7 of tiles
		panel.add(makeSquare("blue", "arrow", blueArrow, 54));
		addSpace(1);
		panel.add(makeSquare("yellow", "end", endZone, 106));
		panel.add(makeSquare("yellow", "end", endZone, 109));
		addSpace(3);

		//add drawpile and discard pile tiles
		Border emptyBorder = BorderFactory.createEmptyBorder();
		JButton drawPile = new JButton();
		drawPile.setIcon(card);
		drawPile.setMargin(new Insets(0, 0, 0, 0));
		drawPile.setContentAreaFilled(false);
		drawPile.setBorder(emptyBorder);
		drawPile.setPreferredSize(new Dimension(42, 42));
		drawPile.addActionListener(new ButtonListener2());
		panel.add(drawPile);

		discardPile = new JButton();
		discardPile.setIcon(blankCard);
		discardPile.setMargin(new Insets(0, 0, 0, 0));
		discardPile.setContentAreaFilled(false);
		discardPile.setBorder(emptyBorder);
		discardPile.setPreferredSize(new Dimension(42, 42));
		panel.add(discardPile);

		addSpace(6);
		panel.add(makeSquare("none", "blank", blankIcon, 21));

      //add row 8 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 53));
		addSpace(1);
		panel.add(makeSquare("yellow", "end", endZone, 107));
		panel.add(makeSquare("yellow", "end", endZone, 108));
		addSpace(11);
		panel.add(makeSquare("none", "blank", blankIcon, 22));

      //add row 9 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 52));
		addSpace(11);
		panel.add(makeSquare("red", "end", endZone, 308));
		panel.add(makeSquare("red", "end", endZone, 307));
		addSpace(1);
		panel.add(makeSquare("none", "blank", blankIcon, 23));

      //add row 10 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 51));
		addSpace(11);
		panel.add(makeSquare("red", "end", endZone, 309));
		panel.add(makeSquare("red", "end", endZone, 306));
		addSpace(1);
		panel.add(makeSquare("green", "arrow", greenArrow, 24));
      
      //add row 11 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 50));
		panel.add(makeSquare("blue", "start", endZone, 412));
		panel.add(makeSquare("blue", "start", endZone, 414));
		addSpace(10);
		panel.add(makeSquare("red", "safe", redSafeZone, 305));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 25));

      //add row 12 of tiles
		panel.add(makeSquare("blue", "circle", blueCircle, 49));
		panel.add(makeSquare("blue", "start", endZone, 411));
		panel.add(makeSquare("blue", "start", endZone, 413));
		addSpace(10);
		panel.add(makeSquare("red", "safe", redSafeZone, 304));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 26));

      //add row 13 of tiles
		panel.add(makeSquare("blue", "line", blueLine, 48));
		addSpace(5);
		panel.add(makeSquare("blue", "end", endZone, 409));
		panel.add(makeSquare("blue", "end", endZone, 408));
		addSpace(5);
		panel.add(makeSquare("red", "safe", redSafeZone, 303));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 27));

      //add row 14 of tiles
		panel.add(makeSquare("blue", "line", blueLine, 47));
		panel.add(makeSquare("blue", "safe", blueSafeZone, 401));
		panel.add(makeSquare("blue", "safe", blueSafeZone, 402));
		panel.add(makeSquare("blue", "safe", blueSafeZone, 403));
		panel.add(makeSquare("blue", "safe", blueSafeZone, 404));
		panel.add(makeSquare("blue", "safe", blueSafeZone, 405));
		panel.add(makeSquare("blue", "end", endZone, 406));
		panel.add(makeSquare("blue", "end", endZone, 407));
		addSpace(2);
		panel.add(makeSquare("red", "start", endZone, 314));
		panel.add(makeSquare("red", "start", endZone, 313));
		addSpace(1);
		panel.add(makeSquare("red", "safe", redSafeZone, 302));
		addSpace(1);
		panel.add(makeSquare("green", "circle", greenCircle, 28));

      //add row 15 of tiles
		panel.add(makeSquare("blue", "arrow", blueArrow, 46));
		addSpace(9);
		panel.add(makeSquare("red", "start", endZone, 312));
		panel.add(makeSquare("red", "start", endZone, 311));
		addSpace(1);
		panel.add(makeSquare("red", "safe", redSafeZone, 301));
		addSpace(1);
		panel.add(makeSquare("none", "blank", blankIcon, 29));

      //add row 16 of tiles
		panel.add(makeSquare("none", "blank", blankIcon, 45));
		panel.add(makeSquare("none", "blank", blankIcon, 44));
		panel.add(makeSquare("red", "circle", redCircle, 43));
		panel.add(makeSquare("red", "line", redLine, 42));
		panel.add(makeSquare("red", "line", redLine, 41));
		panel.add(makeSquare("red", "line", redLine, 40));
		panel.add(makeSquare("red", "arrow", redArrow, 39));
		panel.add(makeSquare("none", "blank", blankIcon, 38));
		panel.add(makeSquare("none", "blank", blankIcon, 37));
		panel.add(makeSquare("none", "blank", blankIcon, 36));
		panel.add(makeSquare("none", "blank", blankIcon, 35));
		panel.add(makeSquare("red", "circle", redCircle, 34));
		panel.add(makeSquare("red", "line", redLine, 33));
		panel.add(makeSquare("red", "line", redLine, 32));
		panel.add(makeSquare("red", "arrow", redArrow, 31));
		panel.add(makeSquare("none", "blank", blankIcon, 30));

		infoText = new JLabel("Click on the deck to start");
		passButton = new JButton("Pass");
		passButton.addActionListener(new PassButtonListener());
      
      //add all elements to the frame
      add(infoText);
		add(passButton);
		add(panel);
	}

	public squareButton makeSquare(String color, String type, ImageIcon icon,
			int number) throws IOException {
		square newSquare = new square(type, color, number);
		newBoard.setSquare(newSquare);

		Border emptyBorder = BorderFactory.createEmptyBorder();
		squareButton button = new squareButton(number);

		button.setSquare(newSquare);

		button.setBlue(makePawnIcon(icon, "blue"));
		button.setRed(makePawnIcon(icon, "red"));
		button.setYellow(makePawnIcon(icon, "yellow"));
		button.setGreen(makePawnIcon(icon, "green"));
		button.setOriginal(icon);

		button.setIcon(icon);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorder(emptyBorder);
		button.setPreferredSize(new Dimension(42, 42));
		button.addActionListener(new TileButtonListener(number));
		buttonArray[number] = button;
		return button;
	}

	public void addPawn(Pawn pawn, int index) {
		square temp = newBoard.getSquare(index);
		squareButton button = buttonArray[index];
		temp.setPawn(pawn);
		String color = temp.getPawn().getColor();
		button.setIcon(button.getPawn(color));
		newBoard.setSquare(temp);
	}

	public Pawn removePawn(int index) {
		square temp = newBoard.getSquare(index);
		Pawn tempPawn = temp.removePawn();
		squareButton button = buttonArray[index];
		button.setIcon(button.getOriginal());
		buttonArray[index] = button;
		return tempPawn;
	}

	private void addSpace(int space) {
		for (int j = 1; j <= space; j++) {
			JLabel blank = new JLabel();
			blank.setPreferredSize(new Dimension(42, 42));
			panel.add(blank);
		}
	}

	// creates icons for each of the pawns given an original image and a color
	// for the pawn to be
	public ImageIcon makePawnIcon(ImageIcon square, String color)
			throws IOException {

		BufferedImage large = new BufferedImage(square.getIconWidth(),
				square.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = large.createGraphics();
		// paint the Icon to the BufferedImage.
		square.paintIcon(null, g, 0, 0);
		g.dispose();

		BufferedImage small = ImageIO.read(new File("icons/" + color
				+ "Pawn.png"));
		BufferedImage combined = new BufferedImage(40, 40,
				BufferedImage.TYPE_INT_ARGB);
		// paint both images, preserving the alpha channels
		Graphics g1 = combined.getGraphics();
		g1.drawImage(large, 0, 0, null);
		g1.drawImage(small, 0, 0, null);

		// ImageIO.write(combined, "PNG", new File("twoInOne.png"));

		ImageIcon icon1 = new ImageIcon(combined);
		return icon1;
	}

	class squareButton extends JButton {
		ImageIcon original;
		ImageIcon bluePawn;
		ImageIcon redPawn;
		ImageIcon yellowPawn;
		ImageIcon greenPawn;
		square sq;
		int TileNumber;

		squareButton(int number) {
			TileNumber = number;
		}

		public int getNumber() {
			return TileNumber;
		}

		public void setSquare(square sq) {
			this.sq = sq;
		}

		public square getSquare() {
			return sq;
		}

		public void setOriginal(ImageIcon original) {
			this.original = original;
		}

		public ImageIcon getOriginal() {
			return original;
		}

		public void setBlue(ImageIcon blue) {
			bluePawn = blue;
		}

		public void setRed(ImageIcon red) {
			redPawn = red;
		}

		public void setYellow(ImageIcon yellow) {
			yellowPawn = yellow;
		}

		public void setGreen(ImageIcon green) {
			greenPawn = green;
		}

		public ImageIcon getPawn(String color) {
			if (color.equals("blue"))
				return bluePawn;
			else if (color.equals("red"))
				return redPawn;
			else if (color.equals("yellow"))
				return yellowPawn;
			else
				return greenPawn;
		}

		public ImageIcon getRed() {
			return redPawn;
		}

		public ImageIcon getYellow() {
			return yellowPawn;
		}

		public ImageIcon getGreen() {
			return greenPawn;
		}

	}

	class TileButtonListener implements ActionListener {
		public int TileNumber;
      
      int firstSplit = 0;
      int secondSplit = 0;

		TileButtonListener(int number) {
			TileNumber = number;
		}

		public void actionPerformed(ActionEvent e) {
         
			if (squareClickable) {

				square tempSquare = newBoard.getSquare(TileNumber);

				tileNumber = TileNumber;

				if (cardNumber == 4)
					cardNumber = -4;
               
            if (cardNumber == 7)
               sevenCard = true;
               
            if (cardNumber == 0)
               sorryCard = true;
               
            if (cardNumber == 10)
               tenCard = true;
               
            if (cardNumber == 11)
               elevenCard = true;
              
            // if the button is set to shouldSwitch then the user should select the piece they want to switch with
            if (shouldSwitch) {
               if (tempSquare.hasPawn()) {
                  if (!(tempSquare.getPawnColor().equals(currentColor))) {
                     if (tempSquare.getPosition() > 100) {
   							infoText.setText("Your pawn cannot be placed here");
   						} else {
                        int tempPosition1 = pawnToSwitch.getPosition();
                        int tempPosition2 = tempSquare.getPawn().getStartPosition();
                        movePawn(tempSquare,tempPosition2);
                        movePawn(pawnToSwitch,tempSquare.getPosition());
                        movePawn(newBoard.getSquare(tempPosition2),tempPosition1);
                        shouldSwitch = false;
                        elevenCard = false;
                     }
                     
                     
                  } else {
                     infoText.setText("This is your own pawn");
                  }
               } else {
                  infoText.setText("There is no pawn here");
               }
            
            } else if (shouldSplit) {
               if (tempSquare.hasPawn()) {
                  if (tempSquare.getPawnColor().equals(currentColor)) {
                     if (tempSquare.getPosition() != firstPawnForSplit.getPosition()) {
                        String[] buttons = { "1/6", "2/5", "3/4", "4/3","5/2","6/1"};    
                        int returnValue = JOptionPane.showOptionDialog(null, "Select the split between the first and second pawn selected respectively", "Select a Split",
                           JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                        int firstPawnSplit = 1 + returnValue;
                        int secondPawnSplit = 6 - returnValue;
                        while (findNewPosition(firstPawnForSplit,firstPawnSplit) == -1 || findNewPosition(tempSquare,secondPawnSplit) == -1) {
                           returnValue = JOptionPane.showOptionDialog(null, "This split did not work, try a different one", "Select a Split",
                              JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                           firstPawnSplit = 1 + returnValue;
                           secondPawnSplit = 6 - returnValue;
                        }
                        movePawn(firstPawnForSplit,findNewPosition(firstPawnForSplit,firstPawnSplit));
                        movePawn(tempSquare,findNewPosition(tempSquare,secondPawnSplit));
                        shouldSplit = false;
                        sevenCard = false;
                     } else {
                        infoText.setText("You already selected this pawn");
                     }
   					} else {
   						infoText.setText("Incorrect Color");
   					}
               } else {
                  infoText.setText("There is no pawn here");
               }
               
            //if the card is a sorry card, the user should select the pawn they want to bump
            } else if (sorryCard) {
               if (tempSquare.hasPawn()) {
                  if (!(tempSquare.getPawnColor().equals(currentColor))) {
                     if (tempSquare.getPosition() > 100) {
   							infoText.setText("Your pawn cannot be placed here");
   						} else {
                        square pawnToMove = null;
                        int startingNumber = 0;
                        if (currentColor.equals("yellow")) {
                           startingNumber = 111;
                        } else if (currentColor.equals("green")) {
                           startingNumber = 211;
                        } else if (currentColor.equals("red")) {
                           startingNumber = 311;
                        } else if (currentColor.equals("blue")) {
                           startingNumber = 411;
                        }
                        for (int i = startingNumber; i < startingNumber+4; i++) {
                           if (newBoard.getSquare(i).hasPawn()) {
                              pawnToMove = newBoard.getSquare(i);
                           }
                        }
                        if (pawnToMove == null) {
                           infoText.setText("You have no pawns in your home");
                        } else {
                           movePawn(pawnToMove,tempSquare.getPosition());
                        }
                        sorryCard = false;
                     }
                     
                     
                  } else {
                     infoText.setText("This is your own pawn");
                  }
               } else {
                  infoText.setText("There is no pawn here");
               }
            //if there is no special rule, handle the piece movement normally
            } else {

   				if (tempSquare.hasPawn()) {
   					if (tempSquare.getPawnColor().equals(currentColor)) {
                     int newPosition = findNewPosition(tempSquare,cardNumber);
                     if (newPosition != -1) {
                        movePawn(tempSquare,newPosition);
                     }
   					} else {
   						infoText.setText("Incorrect Color");
   					}
   
   				} else {
   					infoText.setText("There is no pawn here");
   				}
            
            }
				updateIcons();
			}
         checkForWinner();
		}
      
      public int findNewPosition (square tempSquare, int cardNumber) {
         int newPosition = -1;
         //test to see if pawn is in start
   		if (tempSquare.getPosition() > 410
   				&& tempSquare.getPosition() < 420) {
   			if (!(cardNumber == 1 || cardNumber == 2)) {
   				infoText.setText("You need a 1, 2, or sorry to exit");
   			} else {
   				newPosition = 48 + cardNumber;
   			}
   		} else if (tempSquare.getPosition() > 310
   				&& tempSquare.getPosition() < 320) {
   			if (!(cardNumber == 1 || cardNumber == 2)) {
   				infoText.setText("You need a 1, 2, or sorry to exit");
   			} else {
   				newPosition = 33 + cardNumber;
   			}
   		} else if (tempSquare.getPosition() > 210
   				&& tempSquare.getPosition() < 220) {
   			if (!(cardNumber == 1 || cardNumber == 2)) {
   				infoText.setText("You need a 1, 2, or sorry to exit");
   			} else {
   				newPosition = 18 + cardNumber;
   			}
   		} else if (tempSquare.getPosition() > 110
   				&& tempSquare.getPosition() < 120) {
   			if (!(cardNumber == 1 || cardNumber == 2)) {
   				infoText.setText("You need a 1, 2, or sorry to exit");
   			} else {
   				newPosition = 3 + cardNumber;
   			}
   		} else {
            //if the pawn is not in the start
   			int currentPosition = tempSquare.getPosition();
            
            if (sevenCard && !shouldSplit) {
               int n = JOptionPane.showOptionDialog(new JFrame(), "Would you like to move 7 spaces or split with another pawn?", 
               "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
               null, new Object[] {"Forward 7", "Split"}, JOptionPane.YES_OPTION);
               if (n == 1) {
                  boolean hasAnotherPawn = false;
                  for (int i = 0; i < 59; i++) {
                     if (newBoard.getSquare(i).hasPawn()) {
                        if (newBoard.getSquare(i).getPawnColor().equals(currentColor) && i != tempSquare.getPosition()) {
                           hasAnotherPawn = true;
                        }
                     }
                  }
                  if (hasAnotherPawn) {
                     infoText.setText("Select a pawn to split with");
                     firstPawnForSplit = tempSquare;
                     shouldSplit = true;
                     newPosition = -1;
                  } else {
                     infoText.setText("You don't have a pawn available to split with");
                     newPosition = currentPosition + cardNumber;
                  }
               } else {
                  newPosition = currentPosition + cardNumber;
               }
               sevenCard = false;
            } else if (tenCard) {
               int n = JOptionPane.showOptionDialog(new JFrame(), "Would you like to move 10 spaces forward or one space backward?", 
               "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
               null, new Object[] {"Forward 10", "Backward 1"}, JOptionPane.YES_OPTION);
               if (n == 1) {
                  cardNumber = -1;
               }
               newPosition = currentPosition + cardNumber;
               tenCard = false;
            } else if (elevenCard && !shouldSwitch) {
               int n = JOptionPane.showOptionDialog(new JFrame(), "Would you like to move 11 spaces forward or switch spaces with an opponent's pawn?", 
               "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
               null, new Object[] {"Forward 11", "Switch"}, JOptionPane.YES_OPTION);
               if (n == 1) {
                  if (currentPosition > 100) {
                     infoText.setText("You are not in a valid location to switch");
                     newPosition = currentPosition + cardNumber;
                  } else {
                     boolean pawnAvailableToSwitch = false;
                     for (int i = 0; i < 59; i++) {
                        if (newBoard.getSquare(i).hasPawn()) {
                           if (!newBoard.getSquare(i).getPawnColor().equals(currentColor)) {
                              pawnAvailableToSwitch = true;
                           }
                        }
                     }
                     if (pawnAvailableToSwitch) {
                        shouldSwitch = true;
                        pawnToSwitch = tempSquare;
                        infoText.setText("Select a pawn to switch with");
                        newPosition = -1;
                     } else {
                        infoText.setText("There is no pawn to switch with");
                        newPosition = currentPosition + cardNumber;
                     }
                  }
               } else {
                  newPosition = currentPosition + cardNumber;
               }
               elevenCard = false;
            } else {
   			   newPosition = currentPosition + cardNumber;
            }
   
   
   
   			//check to see if pawn is on the outside of the board
   			if (currentPosition < 100) {
               //if the pawn passes start, adjust location to begining of the loop
   				if (newPosition > 59) {
   					newPosition -= 60;
   					currentPosition -= 60;
   				}
   				// test to see if past endzone and adjust to enter endzone
   				for (int i = currentPosition; i < newPosition; i++) {
   					if (i == 2 && tempSquare.getPawnColor().equals("yellow")) {
   						int length = newPosition - i;
   						newPosition = 100 + length;
   					} else if (i == 17 && tempSquare.getPawnColor().equals("green")) {
   						int length = newPosition - i;
   						newPosition = 200 + length;
   					} else if (i == 32 && tempSquare.getPawnColor().equals("red")) {
   						int length = newPosition - i;
   						newPosition = 300 + length;
   					} else if (i == 47 && tempSquare.getPawnColor().equals("blue")) {
   						int length = newPosition - i;
   						newPosition = 400 + length;
   					}
   				}
   			}
            
            //check to see if pawn is past their home
   			if (newPosition > 106 && newPosition < 200) {
   				infoText.setText("You can't go this far");
   			} else if (newPosition > 206 && newPosition < 300) {
   				infoText.setText("You can't go this far");
   			} else if (newPosition > 306 && newPosition < 400) {
   				infoText.setText("You can't go this far");
   			} else if (newPosition > 406 && newPosition < 500) {
   				infoText.setText("You can't go this far");
   			} else {
   				// check if pawn is in home and place in the appropriate home spot
   				if (newPosition == 106 || newPosition == 206 || newPosition == 306 || newPosition == 406) {
   					newPosition = tempSquare.getPawn().getStartPosition() - 5;
   				}
   			}
		   }
         System.out.println(newPosition);
         return newPosition;
      }
      
      //method to check for a winner
      public void checkForWinner() {
         if (newBoard.getSquare(106).hasPawn() && newBoard.getSquare(107).hasPawn() && newBoard.getSquare(108).hasPawn() && newBoard.getSquare(109).hasPawn()) {
				infoText.setText("Yellow wins!");
				JOptionPane.showMessageDialog(null,
						"Yellow Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(206).hasPawn() && newBoard.getSquare(207).hasPawn() && newBoard.getSquare(208).hasPawn() && newBoard.getSquare(209).hasPawn()){
				infoText.setText("Green wins!");
				JOptionPane.showMessageDialog(null,
						"Green Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(306).hasPawn() && newBoard.getSquare(307).hasPawn() && newBoard.getSquare(308).hasPawn() && newBoard.getSquare(309).hasPawn()){
				infoText.setText("Red wins!");
				JOptionPane.showMessageDialog(null,
						"Red Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(406).hasPawn() && newBoard.getSquare(407).hasPawn() && newBoard.getSquare(408).hasPawn() && newBoard.getSquare(409).hasPawn()){
				infoText.setText("Blue wins!");
				JOptionPane.showMessageDialog(null,
						"Blue Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			}
      }
      //method to update all icons to reflect the pawns they have
      public void updateIcons() {
         Component[] component = panel.getComponents();
			for (int i = 0; i < 256; i++) {
				if (component[i] instanceof squareButton) {
					squareButton button = (squareButton) component[i];
					if (button.getSquare().hasPawn()) {
						button.setIcon(button.getPawn(button.getSquare()
								.getPawnColor()));
					} else {
						button.setIcon(button.getOriginal());
					}
				}
			}
      }
		//method for moving pawn correctly
		public void movePawn(square tempSquare, int position) {
			Pawn tempPawn = tempSquare.removePawn();
			if (position > 59 && position < 100) {
				position -= 60;
			}
			if (position < 0)
				position += 60;
			square tempSquare2 = newBoard.getSquare(position);

			if (tempSquare.getPosition() < 100) {

			}
			//check if square is occupied
			if (tempSquare2.hasPawn()) {
				//executes sorry if not the players color
				if (!(tempPawn.getColor().equals(tempSquare2.getPawnColor()))) {
					square tempSquare3 = newBoard.getSquare(tempSquare2
							.getPawn().getStartPosition());
					tempSquare3.setPawn(tempSquare2.removePawn());

					if (tempSquare2.getType().equals("arrow") && !(tempPawn.getColor().equals(tempSquare2.getColor()))) {
						if (tempSquare2.getPosition()==9 || tempSquare2.getPosition()==24 || tempSquare2.getPosition()==39 || tempSquare2.getPosition()==44){
                     slideBump(tempSquare2.getPosition(),4);
							tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+4);
						} else {
                     slideBump(tempSquare2.getPosition(),3);
							tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+3);
						}
					}

					tempSquare2.setPawn(tempPawn);
					infoText.setText("Sorry!");
					squareClickable = false;
				} else {
					//return pawn to original square
					infoText.setText("You already have a pawn there");
					tempSquare.setPawn(tempPawn);
				}
			} else {
				//if not occupied, move to new location
				if (tempSquare2.getType().equals("arrow") && !(tempPawn.getColor().equals(tempSquare2.getColor()))) {
					if (tempSquare2.getPosition()==9 || tempSquare2.getPosition()==24 || tempSquare2.getPosition()==39 || tempSquare2.getPosition()==44){
						slideBump(tempSquare2.getPosition(),4);
                  tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+4);
					} else {
                  slideBump(tempSquare2.getPosition(),3);
						tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+3);
					}
				}
				tempSquare2.setPawn(tempPawn);
				squareClickable = false;
			}
		}
      //method to bump all pieces in the way of a slide
      public void slideBump(int startPosition, int slideLength) {
         for (int i = startPosition; i < startPosition+slideLength; i++) {
            if (newBoard.getSquare(i).hasPawn()) {
               square tempSquare = newBoard.getSquare(newBoard.getSquare(i).getPawn().getStartPosition());
	            tempSquare.setPawn(newBoard.getSquare(i).removePawn());
            }
         }
      }
	}
   
   

	class ButtonListener2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (cardClickable) {
				currentCard = deck.dealCard();
				switch (currentCard.getValue()) {
				// there are special rules for numbers 2,7,10,11, and 0 (sorry! card)
				case 0:
					discardPile.setIcon(cardSorry);
					infoText.setText("You drew a Sorry");
					break;
				case 1:
					discardPile.setIcon(card1);
					infoText.setText("You drew a 1");
					break;
				case 2:
					discardPile.setIcon(card2);
					infoText.setText("You drew a 2");
					break;
				case 3:
					discardPile.setIcon(card3);
					infoText.setText("You drew a 3");
					break;
				case 4:
					discardPile.setIcon(card4);
					infoText.setText("You drew a 4");
					break;
				case 5:
					discardPile.setIcon(card5);
					infoText.setText("You drew a 5");
					break;
				case 7:
					discardPile.setIcon(card7);
					infoText.setText("You drew a 7");
					break;
				case 8:
					discardPile.setIcon(card8);
					infoText.setText("You drew a 8");
					break;
				case 10:
					discardPile.setIcon(card10);
					infoText.setText("You drew a 10");
					break;
				case 11:
					discardPile.setIcon(card11);
					infoText.setText("You drew an 11");
					break;
				case 12:
					discardPile.setIcon(card12);
					infoText.setText("You drew a 12");
					break;
				default:
					break;
				}
				cardNumber = currentCard.getValue();
				cardClickable = false;

				// Display the card
				JOptionPane.showMessageDialog(null,
						currentCard.getValueAsString(), "Player Card",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
   //button listener for pass button
	class PassButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (squareClickable) {
				squareClickable = false;
				infoText.setText("You have given up your turn");
            tenCard = false;
            sorryCard = false;
            elevenCard = false;
            sevenCard = false;
            shouldSplit = false;
			}

		}
	}
}
