import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*; // for layout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
	private JLabel label;
	private JButton button;
	private JButton button1;

	private JPanel panel;

	Board newBoard = new Board();
	square newSquare;
	squareButton[] buttonArray = new squareButton[500];
	Deck deck = new Deck();

	JButton discardPile;
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

	int cardNumber;

	int tileNumber;

	public Card currentCard;

	public String currentColor;

	boolean squareClickable = false;
	boolean cardClickable = false;

	MyFrame(String s) throws IOException {
		super(s);
		// implicit this
		setLayout(new FlowLayout());

		deck.shuffle();

		panel = new JPanel();

		panel.setBackground(Color.CYAN);
		panel.setLayout(new GridLayout(16, 16));

		// BufferedImage large = ImageIO.read(new File("src/icons/blank.png"));
		// BufferedImage small = ImageIO.read(new
		// File("src/icons/bluePawn.png"));
		// BufferedImage combined = new BufferedImage(40, 40,
		// BufferedImage.TYPE_INT_ARGB);
		// paint both images, preserving the alpha channels
		// Graphics g = combined.getGraphics();
		// g.drawImage(large, 0, 0, null);
		// g.drawImage(small, 0, 0, null);

		// ImageIO.write(combined, "PNG", new File("twoInOne.png"));

		// ImageIcon icon1 = new ImageIcon(combined);

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

		panel.add(makeSquare("none", "blank", blankIcon, 59));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 101));
		addSpace(1);
		panel.add(makeSquare("yellow", "start", endZone, 111));
		panel.add(makeSquare("yellow", "start", endZone, 112));
		addSpace(9);
		panel.add(makeSquare("green", "arrow", greenArrow, 16));

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

		panel.add(makeSquare("blue", "line", blueLine, 57));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 103));
		addSpace(5);
		panel.add(makeSquare("green", "end", endZone, 208));
		panel.add(makeSquare("green", "end", endZone, 209));
		addSpace(5);
		panel.add(makeSquare("green", "line", greenLine, 18));

		panel.add(makeSquare("blue", "line", blueLine, 56));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 104));
		addSpace(10);
		panel.add(makeSquare("green", "start", endZone, 213));
		panel.add(makeSquare("green", "start", endZone, 211));
		panel.add(makeSquare("green", "circle", greenCircle, 19));

		panel.add(makeSquare("blue", "line", blueLine, 55));
		addSpace(1);
		panel.add(makeSquare("yellow", "safe", yellowSafeZone, 105));
		addSpace(10);
		panel.add(makeSquare("green", "start", endZone, 214));
		panel.add(makeSquare("green", "start", endZone, 212));
		panel.add(makeSquare("none", "blank", blankIcon, 20));

		panel.add(makeSquare("blue", "arrow", blueArrow, 54));
		addSpace(1);
		panel.add(makeSquare("yellow", "end", endZone, 106));
		panel.add(makeSquare("yellow", "end", endZone, 109));
		addSpace(3);

		// cards
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

		panel.add(makeSquare("none", "blank", blankIcon, 53));
		addSpace(1);
		panel.add(makeSquare("yellow", "end", endZone, 107));
		panel.add(makeSquare("yellow", "end", endZone, 108));
		addSpace(11);
		panel.add(makeSquare("none", "blank", blankIcon, 22));

		panel.add(makeSquare("none", "blank", blankIcon, 52));
		addSpace(11);
		panel.add(makeSquare("red", "end", endZone, 308));
		panel.add(makeSquare("red", "end", endZone, 307));
		addSpace(1);
		panel.add(makeSquare("none", "blank", blankIcon, 23));

		panel.add(makeSquare("none", "blank", blankIcon, 51));
		addSpace(11);
		panel.add(makeSquare("red", "end", endZone, 309));
		panel.add(makeSquare("red", "end", endZone, 306));
		addSpace(1);
		panel.add(makeSquare("green", "arrow", greenArrow, 24));

		panel.add(makeSquare("none", "blank", blankIcon, 50));
		panel.add(makeSquare("blue", "start", endZone, 412));
		panel.add(makeSquare("blue", "start", endZone, 414));
		addSpace(10);
		panel.add(makeSquare("red", "safe", redSafeZone, 305));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 25));

		panel.add(makeSquare("blue", "circle", blueCircle, 49));
		panel.add(makeSquare("blue", "start", endZone, 411));
		panel.add(makeSquare("blue", "start", endZone, 413));
		addSpace(10);
		panel.add(makeSquare("red", "safe", redSafeZone, 304));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 26));

		panel.add(makeSquare("blue", "line", blueLine, 48));
		addSpace(5);
		panel.add(makeSquare("blue", "end", endZone, 409));
		panel.add(makeSquare("blue", "end", endZone, 408));
		addSpace(5);
		panel.add(makeSquare("red", "safe", redSafeZone, 303));
		addSpace(1);
		panel.add(makeSquare("green", "line", greenLine, 27));

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

		panel.add(makeSquare("blue", "arrow", blueArrow, 46));
		addSpace(9);
		panel.add(makeSquare("red", "start", endZone, 312));
		panel.add(makeSquare("red", "start", endZone, 311));
		addSpace(1);
		panel.add(makeSquare("red", "safe", redSafeZone, 301));
		addSpace(1);
		panel.add(makeSquare("none", "blank", blankIcon, 29));

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

		label = new JLabel("Click on the deck to start");
		add(label);

		button1 = new JButton("Pass");
		button1.addActionListener(new ButtonListener3());
		add(button1);

		add(panel);

		// Pawn yellow1 = new Pawn("yellow");
		// square temp = newBoard.getSquare(111);
		// temp.setPawn(yellow1);
		// newBoard.setSquare(temp);

	}

	public squareButton makeSquare(String color, String type, ImageIcon icon,
			int number) throws IOException {
		newSquare = new square(type, color, number);
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
		button.addActionListener(new ButtonListener(number));
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

	class ButtonListener implements ActionListener {
		public int TileNumber;

		ButtonListener(int number) {
			TileNumber = number;
		}

		public void actionPerformed(ActionEvent e) {
			if (squareClickable) {

				square tempSquare = newBoard.getSquare(TileNumber);

				tileNumber = TileNumber;

				if (cardNumber == 4)
					cardNumber = -4;

				if (tempSquare.hasPawn()) {
					if (tempSquare.getPawnColor().equals(currentColor)) {

						//test to see if
						if (tempSquare.getPosition() > 410
								&& tempSquare.getPosition() < 420) {
							if (!(cardNumber == 1 || cardNumber == 2)) {
								label.setText("You need a 1, 2, or sorry to exit");
							} else {
								int newPosition = 48 + cardNumber;
								movePawn(tempSquare, newPosition);
							}
						} else if (tempSquare.getPosition() > 310
								&& tempSquare.getPosition() < 320) {
							if (!(cardNumber == 1 || cardNumber == 2)) {
								label.setText("You need a 1, 2, or sorry to exit");
							} else {
								int newPosition = 33 + cardNumber;
								movePawn(tempSquare, newPosition);
							}
						} else if (tempSquare.getPosition() > 210
								&& tempSquare.getPosition() < 220) {
							if (!(cardNumber == 1 || cardNumber == 2)) {
								label.setText("You need a 1, 2, or sorry to exit");
							} else {
								int newPosition = 18 + cardNumber;
								movePawn(tempSquare, newPosition);
							}
						} else if (tempSquare.getPosition() > 110
								&& tempSquare.getPosition() < 120) {
							if (!(cardNumber == 1 || cardNumber == 2)) {
								label.setText("You need a 1, 2, or sorry to exit");
							} else {
								int newPosition = 3 + cardNumber;
								movePawn(tempSquare, newPosition);
							}
						} else {
							int currentPosition = tempSquare.getPosition();

							int newPosition = tempSquare.getPosition()
									+ cardNumber;



							//if going around the board
							if (currentPosition < 100) {
								if (newPosition > 59) {
									newPosition -= 60;
									currentPosition -= 60;
								}
								// test to see if past endzone
								for (int i = currentPosition; i < newPosition; i++) {
									if (i == 2
											&& tempSquare.getPawnColor().equals("yellow")) {
										int length = newPosition - i;
										newPosition = 100 + length;
									}
								}
								for (int i = currentPosition; i < newPosition; i++) {
									if (i == 17 && tempSquare.getPawnColor().equals("green")) {
										int length = newPosition - i;
										newPosition = 200 + length;
									}
								}
								for (int i = currentPosition; i < newPosition; i++) {
									if (i == 32 && tempSquare.getPawnColor().equals("red")) {
										int length = newPosition - i;
										newPosition = 300 + length;
									}
								}
								for (int i = currentPosition; i < newPosition; i++) {
									if (i == 47 && tempSquare.getPawnColor().equals("blue")) {
										int length = newPosition - i;
										newPosition = 400 + length;
									}
								}
							}

							if (newPosition > 106 && newPosition < 200) {
								label.setText("You can't go this far");
							} else if (newPosition > 206 && newPosition < 300) {
								label.setText("You can't go this far");
							} else if (newPosition > 306 && newPosition < 400) {
								label.setText("You can't go this far");
							} else if (newPosition > 406 && newPosition < 500) {
								label.setText("You can't go this far");
							} else {
								// check if pawn in endzone
								if (newPosition == 106 || newPosition == 206 || newPosition == 306 || newPosition == 406) {
									newPosition = tempSquare.getPawn().getStartPosition() - 5;
								}
								movePawn(tempSquare, newPosition);
							}

						}
					} else {
						label.setText("Incorrect Color");
					}

				} else {
					label.setText("There is no pawn here");
				}

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

			//check to see if someone wins
			if (newBoard.getSquare(106).hasPawn() && newBoard.getSquare(107).hasPawn() && newBoard.getSquare(108).hasPawn() && newBoard.getSquare(109).hasPawn()) {
				label.setText("Yellow wins!");
				JOptionPane.showMessageDialog(null,
						"Yellow Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(206).hasPawn() && newBoard.getSquare(207).hasPawn() && newBoard.getSquare(208).hasPawn() && newBoard.getSquare(209).hasPawn()){
				label.setText("Green wins!");
				JOptionPane.showMessageDialog(null,
						"Green Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(306).hasPawn() && newBoard.getSquare(307).hasPawn() && newBoard.getSquare(308).hasPawn() && newBoard.getSquare(309).hasPawn()){
				label.setText("Red wins!");
				JOptionPane.showMessageDialog(null,
						"Red Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
			} else if (newBoard.getSquare(406).hasPawn() && newBoard.getSquare(407).hasPawn() && newBoard.getSquare(408).hasPawn() && newBoard.getSquare(409).hasPawn()){
				label.setText("Blue wins!");
				JOptionPane.showMessageDialog(null,
						"Blue Wins!", "Winner",
						JOptionPane.PLAIN_MESSAGE);
				cardClickable = false;
				squareClickable = false;
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
							tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+4);
						} else {
							tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+3);
						}
					}

					tempSquare2.setPawn(tempPawn);
					label.setText("Sorry!");
					squareClickable = false;
				} else {
					//return pawn to original square
					label.setText("You already have a pawn there");
					tempSquare.setPawn(tempPawn);
				}
			} else {
				//if not occupied, move to new location
				if (tempSquare2.getType().equals("arrow") && !(tempPawn.getColor().equals(tempSquare2.getColor()))) {
					if (tempSquare2.getPosition()==9 || tempSquare2.getPosition()==24 || tempSquare2.getPosition()==39 || tempSquare2.getPosition()==44){
						tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+4);
					} else {
						tempSquare2 = newBoard.getSquare(tempSquare2.getPosition()+3);
					}
				}
				tempSquare2.setPawn(tempPawn);
				squareClickable = false;
			}
		}
	}

	class ButtonListener2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (cardClickable) {
				currentCard = deck.dealCard();
				switch (currentCard.getValue()) {
				// there are special rules for numbers 2,7,10,11, and 0-->
				// sorry!
				// card
				case 0:
					discardPile.setIcon(cardSorry);
					label.setText("You drew a Sorry");
					break;
				case 1:
					discardPile.setIcon(card1);
					label.setText("You drew a 1");
					break;
				case 2:
					discardPile.setIcon(card2);
					label.setText("You drew a 2");
					break;
				case 3:
					discardPile.setIcon(card3);
					label.setText("You drew a 3");
					break;
				case 4:
					discardPile.setIcon(card4);
					label.setText("You drew a 4");
					break;
				case 5:
					discardPile.setIcon(card5);
					label.setText("You drew a 5");
					break;
				case 7:
					discardPile.setIcon(card7);
					label.setText("You drew a 7");
					break;
				case 8:
					discardPile.setIcon(card8);
					label.setText("You drew a 8");
					break;
				case 10:
					discardPile.setIcon(card10);
					label.setText("You drew a 10");
					break;
				case 11:
					discardPile.setIcon(card11);
					label.setText("You drew an 11");
					break;
				case 12:
					discardPile.setIcon(card12);
					label.setText("You drew a 12");
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

	class ButtonListener3 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (squareClickable) {
				squareClickable = false;
				label.setText("You have given up your turn");
			}

		}
	}
}
