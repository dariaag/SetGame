
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.*;

/**
 * Created by Daria on 2/12/17.
 *
 *
 */
//
public class SetGame extends JApplet {

    private static final long serialVersionUID = 1L;

    private final int APPLET_WIDTH_WELCOME = 500, APPLET_HEIGHT_WELCOME = 500;
    private final int APPLET_WIDTH = 800, APPLET_HEIGHT = 750;
    private final int DELTA = 160;
    private Deck deck;

    private Mode mode;

    private Container cp;//container where other panels are placed
    private JPanel cardPanel; // displays cards currently that player can interact with
    private JPanel buttonPanel; // panel with navigation buttons
    private JButton quitButton;//button that leads to the main welcome screen
    private JPanel welcome; //main welcome screen
    private JButton hintButton; //button that shows hints for solitaire mode
    private JButton showSetsButton;//hints for tutorial mode
    private Point pt[] = {
            new Point(10, 10), new Point(10, 220), new Point(10, 430),
            new Point(170, 10), new Point(170, 220), new Point(170, 430),
            new Point(330, 10), new Point(330, 220), new Point(330, 430),
            new Point(490, 10), new Point(490, 220), new Point(490, 430),
            new Point(650, 10), new Point(650, 220), new Point(650, 430),};


    public void init() {


        //buttons for buttonPanel
        quitButton = new JButton("Quit");
        hintButton = new JButton("Hint");
        showSetsButton = new JButton("Show Sets");

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(229, 255, 204));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));





        //card display panel
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.white);
        deck = new Deck();//singleton
        deck.shuffle();


        //end card display*/

        //Buttons and Cards will appear in the container
        cp = getContentPane();

        //initializing buttons for modes and adding buttonListeners
        JButton solitaireButton = new JButton("Solitaire");
        JButton tutorialButton = new JButton("Tutorial");

        solitaireButton.addActionListener(new SolitaireButtonListener());
        quitButton.addActionListener(new QuitButtonListener());
        tutorialButton.addActionListener(new TutorialButtonListener());

        //initializing and displaying welcome message with icon
        JLabel label = new JLabel("Welcome to the Game of Set! Choose game mode:");
        JLabel icon = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("resources/poker.png"));
            icon.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //initializing panel with the picture
        JPanel pic = new JPanel();
        pic.setBackground(Color.white);
        pic.setLayout(new FlowLayout());
        pic.add(icon);


        //initializing panel with welcome message label
        JPanel greet = new JPanel();
        greet.setBackground((Color.white));
        greet.setLayout(new FlowLayout());
        greet.add(label);


        //initializing panel with mode buttons
        JPanel modePanel = new JPanel();
        modePanel.setBackground(Color.white);
        modePanel.setLayout(new FlowLayout());
        modePanel.add(tutorialButton);
        modePanel.add(solitaireButton);

        //adding elements to the welcome panel
        welcome = new JPanel();
        welcome.setBackground((Color.white));
        welcome.setLayout((new GridLayout(3, 1)));
        welcome.add(greet);
        welcome.add(modePanel);
        welcome.add(pic);



        cp.setBackground(Color.white);
        //adding welcome panel to the container
        cp.add(welcome, BorderLayout.CENTER);

        setSize(APPLET_WIDTH_WELCOME, APPLET_HEIGHT_WELCOME);


    }

    /**
     * action listener for tutorial button. Creates mode object that handles logic
     */
    private class TutorialButtonListener implements ActionListener {//set up is the same so maybe use for both modes to setup

        public void actionPerformed(ActionEvent event) {

            setSize(APPLET_WIDTH, APPLET_HEIGHT);
            cp.remove(welcome);
            cp.add(cardPanel, BorderLayout.CENTER);

            cp.add(buttonPanel, BorderLayout.LINE_START);
            buttonPanel.remove(hintButton);
            buttonPanel.add(showSetsButton);
            buttonPanel.add(quitButton);

            cardPanel.revalidate();
            cardPanel.repaint();
            deck.restart();//singleton
            mode = new Tutorial(deck, cardPanel, pt, showSetsButton,buttonPanel, quitButton);

        }

    }
    /**
     * action listener for solitaire button. Creates mode object that handles logic
     */

    private class SolitaireButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            setSize(APPLET_WIDTH+DELTA, APPLET_HEIGHT);
            cp.remove(welcome);
            cp.add(cardPanel, BorderLayout.CENTER);
            cp.add(buttonPanel, BorderLayout.LINE_START);
            buttonPanel.remove(showSetsButton);
            buttonPanel.add(hintButton);
            buttonPanel.add(quitButton);
            //hintButton.removeActionListener(new ShowSetsButtonListener());
            // hintButton.addActionListener(new hintButtonListener());
            cardPanel.revalidate();
            cardPanel.repaint();
            deck.restart();//singleton
            // hintCounter = 0;
            mode = new Solitaire(deck, cardPanel, pt, hintButton, buttonPanel,quitButton);


        }
    }

    /**
     * button allows player quit current game at any time and change modes easily
     */

    private class QuitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            cp.remove(buttonPanel);
            cp.remove(cardPanel);
      //      cp.revalidate();
            cp.repaint();
            init();
        }
    }


}