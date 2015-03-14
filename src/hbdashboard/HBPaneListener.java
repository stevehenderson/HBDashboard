/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hbdashboard;


import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.Style;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author henderso
 */
public class HBPaneListener implements HyperlinkListener {

    HBDashboardView app;
    JEditorPane ep;
    String  lastStyleName="a";
    String mapPath="unset";   
    
    private void openPDFMap() {
        try {
           Runtime rt = Runtime.getRuntime();
           rt.exec("cmd.exe /C start " + mapPath); 
           //Runtime.getRuntime().exec( new String[] { "open", "c:\\tmp\\part.jpg" } );
        } catch (IOException ex) {
            System.err.println(ex);
        } 
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        HyperlinkEvent.EventType type = e.getEventType();
        if (type== HyperlinkEvent.EventType.ACTIVATED) {
            System.out.println(e.getDescription().toString());
            if(e.getDescription().toString().trim().equals(":openmap")){
                openPDFMap();
                return;
            }
            app.linkToNode(e.getDescription().toString());
            return;
        }
       
        
        String styleName = null;
        if (type == HyperlinkEvent.EventType.ENTERED) {
            styleName = "a.hover";
        } else if (type == HyperlinkEvent.EventType.EXITED) {
            styleName = lastStyleName;
        }
        if (styleName != null) {            
            HTMLDocument doc = (HTMLDocument) ep.getDocument();
            //Style newStyle = doc.getStyleSheet().getStyle(styleName);
            HTMLEditorKit kit = (HTMLEditorKit) ep.getEditorKit();
            StyleSheet sh = kit.getStyleSheet();
           // System.out.println("stylesheet:" + sh.toString());
            Style newStyle = sh.getStyle(styleName);
            if(newStyle!=null) {
                AttributeSet a = e.getSourceElement().getAttributes();
                AttributeSet anchor = (AttributeSet) a.getAttribute(HTML.Tag.A); 
                String nm = (String) anchor.getAttribute(HTML.Attribute.CLASS);
                lastStyleName = "a."+ nm;                
                int start = e.getSourceElement().getStartOffset();                
                int end = e.getSourceElement().getEndOffset();
                doc.setCharacterAttributes(start, end - start, newStyle, false);
            } else {
                System.err.println("Didn't find style " + styleName);
            }
        } 
    }

    public HBPaneListener(HBDashboardView theApp, JEditorPane thePane, String aFunctionMapPath) {
        mapPath = aFunctionMapPath;
        app = theApp;
        ep= thePane;
    }
}
