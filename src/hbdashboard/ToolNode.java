/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hbdashboard;

import hbdashboard.nodes.PImage;
import hbdashboard.zoom.PNode;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author henderso
 */
public class ToolNode extends PImage {
       
      
    public enum TOOLS  {
        BACKGROUND, GLASS, UP, QUAD, NONE;
    }
       
    protected TOOLS toolType = TOOLS.NONE;
    
    @Override
    public String toString() {
        String typename = "unset";
        if(toolType==TOOLS.GLASS) typename = "glass";
        if(toolType==TOOLS.QUAD) typename = "quad";
        if(toolType==TOOLS.UP) typename = "up";
        if(toolType==TOOLS.BACKGROUND) typename = "tool";
        return "ToolNode type:" + toolType;       
    }
    
    public ToolNode(int x, int y, TOOLS type) {        
        toolType = type;
        String filename = "unset";
        if(type==TOOLS.GLASS) filename = "glass.jpg";
        if(type==TOOLS.QUAD) filename = "quad.jpg";
        if(type==TOOLS.UP) filename = "up.jpg";
        if(type==TOOLS.BACKGROUND) filename = "toolbar_back.jpg";
        
        //Load the overview as an image
        //TODO:  Allow some more flex here (text, etc)
        BufferedImage img = null;
        try {
            img = ImageIO.read(HBDashboardApp2.class.getResource("resources/" + filename));                        
            
            this.setImage(img);                        
            this.setVisible(true);            
            if(type != TOOLS.BACKGROUND) { 
                if(type==TOOLS.QUAD) setOffset(80,4);                
                if(type==TOOLS.UP) setOffset(120,4);
                if(type==TOOLS.GLASS) setOffset(160,4);
                
            } else {                
                this.setTransparency(0.5f);                
            }                          
            //this.addChild(pi);
            //pi.setPickable(false);
            this.setPickable(false);
            this.setVisible(false);        
        } catch (IOException e) {
            System.err.println("Error loading file " + filename + "\n\n");
            System.err.println(e);
            e.printStackTrace();
        }        
    }    
}
