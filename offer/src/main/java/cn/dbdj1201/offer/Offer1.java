package cn.dbdj1201.offer;

/**
 * @author yz1201
 * @date 2020-08-02 19:07
 **/
public class Offer1 {
    public static void main(String[] args) {
        System.out.println(Singleton.newInstance());
        System.out.println(Singleton.newInstance());
        System.out.println(Singleton.newInstance());
        System.out.println(Singleton.newInstance());
        System.out.println(Singleton.newInstance());
    }


}

class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton newInstance() {
        if (instance == null) {

            synchronized (Singleton.class) {

                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;

    }

}
