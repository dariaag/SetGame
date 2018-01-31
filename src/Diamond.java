import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 *@see Symbol
 */
public class Diamond extends Symbol {
    /**
     * private constructor prevents outside classes from making new instances
     */

    private Diamond(){ }
    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class DiamondHolder{
        private static final Symbol INSTANCE = new Diamond();

    }
    /**
     * returns static instance of squiggle, singleton pattern
     * @return Symbol
     */
    public static Symbol getInstance() {return DiamondHolder.INSTANCE;}


    /**
     * Draws the diamond shape
      * @param g Graphics
     * @param location int
     * @param color Color
     * @param backGround Color
     * @param shading Shading
     */
    public void draw  (Graphics g, int location, Color color, Color backGround, Shading shading) {

        // Draw the diamond
        Polygon p = new Polygon();
        p.addPoint (75, location);
        p.addPoint (110, location+20);
        p.addPoint (75, location+40);
        p.addPoint (40, location+20);

        // Fill the diamond
        Graphics2D graph = (Graphics2D) g;
        graph.setColor(color);
        graph.setStroke(new BasicStroke(5.0f));
        graph.drawPolygon (p);
        graph.setPaint(shading.getPaint(color, backGround));
        graph.fillPolygon(p);
    }
}
