package wzorceprojektowe;

public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton(){
        if(instance !=null){
            throw new RuntimeException("Not allowed. Please use getInstance() method");
        }
    }
    public static Singleton getItance(){

        if(instance == null) {
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
