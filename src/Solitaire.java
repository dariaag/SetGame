import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Daria on 2/20/17.
 * Solitaire.java
 * Mode class to set up and handle Solitaire Mode.
 *@see Mode
 */
public class Solitaire extends Mode{


    private JPanel cardPanel;

    private Deck deck;
    private Set test;
    private int size;
    private Card[] dealt;
    private SetFinder finder;
    private Point[] pt;
    private JPanel buttonPanel;
    private JButton hint;
    private JButton quit;

    int clickCounter;

    /**
     * constructor. Sets up the game
     * @param deck Deck
     * @param cardPanel JPanel
     * @param pt Point[]
     * @param hint JButton
     * @param buttonPanel JPanel
     * @param quit JButton
     */
    public Solitaire(Deck deck, JPanel cardPanel, Point[] pt, JButton hint,  JPanel buttonPanel, JButton quit){
        this.deck = deck;
        this.quit = quit;
        this.buttonPanel = buttonPanel;
        this.cardPanel = cardPanel;
        this.pt = pt;
        deck.shuffle();
        test = new Set();
        size = 0;
        dealt = new Card[15];
        clickCounter = 0;

        //displays first 12 cards
        for (int i = 0; i < 12; i++) {
            Card card = deck.deal();
            card.setLocation(pt[i]);
            dealt[i] = card;
            card.setIndex(i);
            cardPanel.add(card);
            card.addActionListener(new CardSolButtonListener());
            size++;
        }

        finder = new SetFinder(dealt, 12);
        finder.findSets();
        this.hint = hint;
        hint.addActionListener(new HintButtonListener());
    }




    /**
     * main game logic class. Handles events when user presses on the card.
     * When the mouse is clicked checks the validity of th set chosen by user, highlights and adds cards.
     */
    private class CardSolButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {


            Card c = (Card) event.getSource();
            c.choose();
            if (test.contains(c)) {
                test.remove(c);

            } else if (!test.isFull()) {
                test.add(c);
            }

            if (test.isFull()) {
                if (test.isValidSet()) {
                    test.displaySet();
                    clickCounter = 0;


                    if (deck.isEmpty() || size > 12) {
                        for (int k = 0; k < 3; k++) {
                            Card card1 = test.get(k);
                            int start = card1.getIndex();
                            cardPanel.remove(card1);
                            //pull the remaining cards back to the left
                            for (int l = start + 1; l < size; l++) {
                                Card card2 = dealt[l];
                                card2.setIndex(l - 1);
                                card2.setLocation(pt[l - 1]);
                                dealt[l - 1] = card2;
                                cardPanel.revalidate();
                                cardPanel.repaint();
                            }
                            size--;
                        }
                        //shrink
                    } else {
                        for (int k = 0; k < test.size(); k++) {
                            Card cd = test.get(k);
                            cardPanel.remove(cd);
                            Card next = deck.deal();
                            next.setLocation(cd.getLocation());
                            next.setIndex(cd.getIndex());
                            dealt[next.getIndex()] = next;
                            next.addActionListener(new CardSolButtonListener());
                            cardPanel.add(next);
                            next.paintImmediately(0, 0, 150, 200);
                        }
                    }


                } else {
                    test.displayRed();

                }
                test = new Set();
            }
            if (deck.isEmpty()) {
                for (int i = 0; i<size;i++){

                    cardPanel.remove(dealt[i]);
                    dealt[i] = null;
                }
                buttonPanel.remove(hint);

                buttonPanel.revalidate();
                buttonPanel.repaint();
                quit.doClick();




            }
            // Whether we found a set or not, we start again

        }
    }

    /**
     * Every time button is clicked, shows one of the available sets on the table. Cycles through all of them,
     * so the user can choose.
     */
    private class HintButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            //dealt = mode.returnDealt();
            finder = new SetFinder(dealt, size);
            finder.findSets();
            ArrayList<Card[]> validSets = finder.getSets();
            int stop = validSets.size();
            System.out.println(stop+ "size");
            //System.out.println(hintCounter);
            if (stop!=0) {
                if (clickCounter < stop) {
                    Set s = new Set();
                    for(int i = 0; i<3;i++){ s.add(validSets.get(clickCounter)[i]);}
                    s.displaySet();

                    clickCounter++;
                    System.out.println("hint" + clickCounter);
                }
                if(clickCounter >= stop){
                    clickCounter = 0;
                }
            }else if (!deck.isEmpty()){
                for (int i=0; i<3; i++) {
                    Card card = deck.deal();
                    card.setIndex(size);
                    card.setLocation(pt[size]);
                    System.out.println(size+ "len");
                    card.addActionListener(new CardSolButtonListener());
                    dealt[size] = card;
                    cardPanel.add(card);
                    cardPanel.revalidate();
                    cardPanel.repaint();
                    size++;
                }
            }

        }



    }
}



