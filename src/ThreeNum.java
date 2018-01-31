/**
 * Created by Daria on 2/13/17.
 *
 */
public class ThreeNum extends Locations {
    private ThreeNum () {
        super();
        add(30);
        add(80);
        add(130);
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */

    private static class ThreeNumHolder{
        private static final Locations INSTANCE = new ThreeNum();
    }


    public static Locations getInstance() {return ThreeNumHolder.INSTANCE;}



}
