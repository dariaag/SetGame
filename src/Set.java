import java.util.Vector;

/**
 *
 * @author garrettfitzgerald /2/18/17
 * Project 2 SET
 *
 * Set class. Returns True if passed a valid set.
 * Main logic of Set game.
 *
 */
public class Set {


	private Vector<Card> cards;

	public Set(){
		cards = new Vector<Card>(3);
	}




	public void add(Card c){
		cards.add(c);
	}

	public void remove(Card c){
		cards.remove(c);
	}

	public void remove(){
		cards.remove(cards.size()-1);

	}




	public int size(){
		return cards.size();
	}

	public boolean isFull(){
		return cards.size() == 3;
	}

	public Card get(int i){
		return cards.get(i);
	}


	public boolean contains(Card c){
		return cards.contains(c);
	}


	/**
	 *returns true if the set is valid, handles all cases
	 * @return boolean
	 */
	public boolean isValidSet(){

		return isShapeSet() && isShadingSet() && isNumberSet() && isColorSet();


	}
	public void displayRed(){
		for (int j=0; j<4; j++){
			for (int i=0; i<3; i++){
				Card cd =  get(i);
				cd.invalidChoose();
				cd.paintImmediately(0, 0, 150, 200);

			}
			try {Thread.sleep(250);} catch (Exception ex) {}
		}
		for (int i=0; i<3; i++){
			Card cd =  get(i);
			cd.invalidChoose();
			cd.paintImmediately(0, 0, 150, 200);
		}
	}



	/**
	 * Highlights cards
	 */
	public void displaySet(){
		for (int j=0; j<3; j++){
			for (int i=0; i<3; i++){
				Card cd =  get(i);
				cd.choose();
				cd.paintImmediately(0, 0, 150, 200);

			}
			try {Thread.sleep(250);} catch (Exception ex) {}
		}
		for (int i=0; i<3; i++){
			Card cd =  get(i);
			cd.choose();
			cd.paintImmediately(0, 0, 150, 200);
		}
	}

	/**
	 * If 3 cards are same shape || 3 cards different shape
	 * @return boolean
	 */
	private boolean isShapeSet(){

		return (cards.get(0).getSymbol() == cards.get(1).getSymbol() && cards.get(1).getSymbol() == cards.get(2).getSymbol())
				||
				(cards.get(0).getSymbol() != cards.get(1).getSymbol() && cards.get(1).getSymbol() != cards.get(2).getSymbol() && cards.get(0).getSymbol()!=cards.get(2).getSymbol())
				;
	}

	/**
	 * If 3 cards are same shade || 3 cards different shade
	 * @return boolean
	 */
	private boolean isShadingSet(){
		return (cards.get(0).getShading() == cards.get(1).getShading() && cards.get(1).getShading() == cards.get(2).getShading())
				||
				(cards.get(0).getShading() != cards.get(1).getShading() && cards.get(1).getShading() != cards.get(2).getShading() && cards.get(0).getShading()!=cards.get(2).getShading())
				;

	}

	/**
	 * If 3 cards are same number || 3 cards different number
	 * @return boolean
	 */
	private boolean isNumberSet(){
		return (cards.get(0).getNumber() == cards.get(1).getNumber() && cards.get(1).getNumber() == cards.get(2).getNumber())
				||
				(cards.get(0).getNumber() != cards.get(1).getNumber() && cards.get(1).getNumber() != cards.get(2).getNumber() && cards.get(0).getNumber()!=cards.get(2).getNumber())
				;

	}

	/**
	 * If 3 cards are same color || 3 cards different color
	 * @return boolean
	 */
	private boolean isColorSet(){
		return (cards.get(0).getColor() == cards.get(1).getColor() && cards.get(1).getColor() == cards.get(2).getColor())
				||
				(cards.get(0).getColor() != cards.get(1).getColor() && cards.get(1).getColor() != cards.get(2).getColor() && cards.get(0).getColor()!=cards.get(2).getColor())
				;

	}



}
