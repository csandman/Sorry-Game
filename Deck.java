import java.util.Random;
import java.util.ArrayList;

public class Deck
{

       //constants:
       final static int CARDS_IN_DECK = 45;
       final static int SET_LENGTH = 12;
       //fields
       private ArrayList<Card> deck;

       /**
         Default constructor calls freshDeck method and creates
         a new deck of 45 cards
       */
       public Deck()
       {
           freshDeck();
       }

       /**
        freshDeck method creates new deck of 45 cards
        in an arrayList
       */
       public void freshDeck()
       {
           //initialize the deck array list
           deck = new ArrayList<Card>();

           //five 1 cards as well as four each of the other cards
           for (int other=0;other<5;other++){
              for (int set=0;set<=SET_LENGTH;set++)
              {
                  if(set!=6 && set!=9){
                     deck.add(new Card(set));
                  }
              }
           }
           int set=1;
           deck.add(new Card(set));
       }

       /**
        dealCard method removes a card from the deck
        @return the card
        */

        public Card dealCard()
        {
          if (this.isEmpty()) {
              this.freshDeck();
              this.shuffle();
              Card c = deck.remove(0);
              return c;
          } else {
              Card c = deck.remove(0);  //  remove it (returns removed object)
              return c;
          }
        }

       /**
        cardsRemaining returns num of cards remaining
        @return size of deck array list.
        */
       public int cardsRemaining()
      {
         return deck.size();
      }

       /**
        isEmpty checks deck status as empty true or false
        @return true or false...
        */
      public boolean isEmpty() {
           return cardsRemaining() == 0;
      }

       /**
        shuffle method sorts cards randomly.
     */
      public void shuffle()
      {
         int randNum;
         Card temp;
         Random r = new Random();
         for (int i = 0; i < deck.size(); i++)
         {
            randNum = r.nextInt(deck.size());
            temp = deck.get(i);
            deck.set(i,deck.get(randNum));
            deck.set(randNum,temp);
         }
      }
}
