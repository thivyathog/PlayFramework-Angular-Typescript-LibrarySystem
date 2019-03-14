package Module;

import controllers.WestminsterLibraryManager;

public class WestminsterLibraryManagerSingleton {


    // creating object variable
    public WestminsterLibraryManager westminsterLibraryManager;
    // static variable single instance of WestminsterLibraryManagerSingleton
    private static WestminsterLibraryManagerSingleton singleInstance = null;


    // private constructor restricted to this class itself
    private WestminsterLibraryManagerSingleton()
    {
        westminsterLibraryManager = new WestminsterLibraryManager(20);
    }

    // static method to create instance of this class
    public static WestminsterLibraryManagerSingleton getInstance() {
        if (singleInstance == null)
            singleInstance = new WestminsterLibraryManagerSingleton();

        return singleInstance;
    }
}
