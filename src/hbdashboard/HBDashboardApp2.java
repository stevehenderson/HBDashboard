/*
 * HBDashboardApp2.java
 */

package hbdashboard;



import javax.swing.JFrame;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class HBDashboardApp2 extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        
        HBDashboardView hb= new HBDashboardView(this);        
        show(hb);
        //this.getMainFrame().setSize(1800, 800);
        this.getMainFrame().setBounds(0, 0, HBDashboardView.windowWidth+235, HBDashboardView.windowHeight+100);
        
       
    }

    @Override
    protected void shutdown() {
       
        super.shutdown();
    }
    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of HBDashboardApp2
     */
    public static HBDashboardApp2 getApplication() {
        return Application.getInstance(HBDashboardApp2.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        
        String firstArg;
        if (args.length > 0) {
            try {
                firstArg = args[0];
                HBDashboardView.propertiesFileName = firstArg;
            } catch (Exception  e) {
                System.err.println(e);
                System.exit(1);
            }
        }        
        launch(HBDashboardApp2.class, args);
    }
}
