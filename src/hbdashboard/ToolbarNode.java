/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hbdashboard;

import hbdashboard.zoom.PNode;

/**
 *
 * @author henderso
 */
public class ToolbarNode extends PNode {
        
    ToolNode glassTool;
    ToolNode upTool;
    ToolNode quadTool;
    ToolNode backTool;
    
    
    
    public void setGlassVisibility(boolean b) {
        this.glassTool.setVisible(b);
        this.glassTool.setPickable(b);
    }
    
    public void setUpVisibility(boolean b) {
        this.upTool.setVisible(b);
        this.upTool.setPickable(b);
    }
    
    public void setQuadVisibility(boolean b) {
        this.quadTool.setVisible(b);
        this.quadTool.setPickable(b);
    }   
    
        
    public ToolbarNode() {        
        glassTool = new ToolNode(0,0,ToolNode.TOOLS.GLASS);
        upTool = new ToolNode(0,0,ToolNode.TOOLS.UP);
        quadTool = new ToolNode(0,0,ToolNode.TOOLS.QUAD);
        backTool = new ToolNode(0,0,ToolNode.TOOLS.BACKGROUND);        
        this.addChild(backTool);        
        this.addChild(glassTool);
        this.addChild(upTool);
        this.addChild(quadTool);        
        this.setPickable(false);
        this.setChildrenPickable(true);
        this.setVisible(true);          
        this.setWidth(200);
        this.setHeight(40);
        this.setTransparency(0.5f);
        this.setGlassVisibility(true);
        this.setUpVisibility(true);
        this.setQuadVisibility(false);
    }  
    
}
