/*
 * ___________________________________________________________________________
 * _____________________,#____________________________________________________
 * __________________#_#,'_;__________________________________________________
 * _______________;:+;+,;+,+__________________________________________________
 * _______________##'+:,@,,'+'_________________________________#+@____________
 * _______________'',@,;,,;+,.`_______________________________@++`__``________
 * _______________;:,+,,,,#,@:..@#_________________________@_#++@_:#++#,______
 * ______________@',,,,,,',,,,'@++@_______________________##,+++`@+++@________
 * _____________#@@,,,,,,,,,,,;++++:______________________#;#++@#++#'_________
 * _____________##,,,#,,,'##+,;+@@+@_____________________.+'+++#++@@##+@______
 * ___________++@',,,##'``,,,,,+#@+@_____________________:+@++@++++++++.______
 * ____________;;@,,,,:_:@:,,,,,++#._____________`.______'+#+++++++++++_______
 * ____________@#+,,,#___`@+++:,,;;,_______:#++';;;;#;___@+++++++++++@________
 * ___________:#++;,,+###++++++:,;;;'++++';;;;;;;;;;;;#._#++++++++++@_________
 * ___________@+++++++++++++++++,,;;;;;;;;;;;;;;;;;;;;;;##++++++++#'__________
 * ___________#+##@#+++++++++++++,,;;;;;;;;:,,,,,:;;;;;;::+++++#@;____________
 * __________`+@#####+++++++++++++,,;;;;;:,,,,;++,,,;;;:,,+++@#@:_____________
 * __________`+@##@#++++++++@++++++,,:;,,,,;++++++,,,,,,,,'+++++#.____________
 * ___________++#@++#@#+++##++++++++,,,,,,+++++++#+,,,,,,,++#,..______________
 * ___________#+++@#++++@,@+++++++++++''+++++++++++:,,,,,:+@';________________
 * ___________#+++++##,__++#++++++++++++++#+++++#+++;,,,;++@;_________________
 * ____________:::.______:,+++++++++++++++@+++++@+++++++++@#__________________
 * _______________________+##+++++++++++++@+++++#+++++++++.___________________
 * ________________________.++++++++#++++++#++++#++++++++#____________________
 * _________________________+##+#++++++++++#@+++++++++++#_____________________
 * _________________________+#@#@+@++#+++++@#+++++++++++:.____________________
 * _________________________++#@@#@,@@+++++@#@@@+++++++###.___________________
 * _________________________++++#@`__'+++++#`.++#+++++++++,___________________
 * _________________________+++++,___,+++++'_,++@+++++++++`___________________
 * _________________________+++++#___`+++++;_`#++@@###+++#____________________
 * ________________________.++++##____+++++,__'+++,__#+++@____________________
 * ________________________:++++._____+++++,__#++#___#+++'____________________
 * ________________________@##+#______+++++;##+++'__+++++,____________________
 * ________________________#+++#______+@@#+@'#;@'__`+++++`____________________
 * _____________________@+#++++;_____'+++++@__@`'::#++++#_____________________
 * ____________________#`#;@++#____@'##++@##_____:`@#:@#`:____________________
 * ___________________+''+``@:____;`,+,,#`+`____,.+`#`##`;____________________
 * _____________________+.+:______+,+@`:#,#_____,`__;;_:._____________________
 * _____________________;_________:__;'__'._________@___@_____________________
 * __________________________________,,__;____________________________________
 * ___________________________________________________________________________
 *`____``____``______```______``````````````````__```````______`````````````````````````````````__``
 *|_```||```_||_```_`\`|_```_``.```````````````[``|`````|_```_`\```````````````````````````````|``]`
 *``|`|__|`|````|`|_)`|``|`|``.`\`,--.```.--.```|`|--.````|`|_)`|```.--.```,--.```_`.--.```.--.|`|``
 *``|``__``|````|``__'.``|`|``|`|`'_\`:`(`(`\]``|`.-.`|```|``__'.`/`.'`\`\`'_\`:`[``/'`\]/`/'`\'`|``
 *`_|`|``|`|_``_|`|__)`|_|`|_.'`///`|`|,``'.'.``|`|`|`|``_|`|__)`||`\__.`|//`|`|,`|`|````|`\__/``|``
 *|____||____||_______/|______.'`\'-;__/[\__)`)[___]|__]|_______/``'.__.'`\'-;__/[___]````'.__.;__]`
 *``````````````````````````````````````````````````````````````````````````````````````````````````___________________________________________________________________________
 *
 * HB (HoneyBadger) Dashboard
 * @author S.Henderson  steven.henderson@us.army.mil
 * 
 */
package hbdashboard;

import hbdashboard.nodes.PImage;
import hbdashboard.util.PAffineTransform;
import hbdashboard.util.PBounds;
import hbdashboard.util.PPaintContext;
import hbdashboard.zoom.PNode;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OverviewNode")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 *
 * @author henderso
 */
public class OverviewNode extends ViewNode {

    
    //CORNERS preferredCorner; 
    
   
    
    /*
     * The row the overview node sits in
     */
    int row;
    
     /*
     * Thecolumn the overview nodes sits in
     */
    int column;
    
    @XmlElement(name="image_file_path") 
    String overviewImageFilename = "unset";
    
    @XmlElement(name="TableNode")
    private TableNode tableNode;
    
    public String getOverviewImageFilename() {
        return overviewImageFilename;
    }

    public void setOverviewImageFilename(String overviewImageFilename) {
        this.overviewImageFilename = overviewImageFilename;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }

    
   
    /*
    public CORNERS getCornerFoo() {
        return preferredCorner;
    }
     * */
         
    public TableNode getParentNode() {
        if (this.getParent() != null) {
            return (TableNode) this.getParent();
        } else {
            return null;
        }
    }

    public TableNode getTableNode() {
        return this.tableNode;
    }
    
    
    public ToolbarNode getToolbarNode() {
        if (this.getChildrenCount() > 1) {
            return (ToolbarNode) this.getChild(1);
        } else {
            //System.err.println("Tried to getTableNode() in OverviewNode but null!");
            return null;
        }
    }   
    
    
    public PImage getImage() {
        if (this.getChildrenCount() > 0) {
            return (PImage) this.getChild(0);
        } else {
            System.err.println("Tried to getImage() in OverviewNode but null!");
            return null;
        }
    }
    
     /**
     * Renders the wrapped Image, stretching it appropriately if the bounds of
     * this PImage doesn't match the bounds of the image.
     * 
     * @param paintContext context into which the rendering will occur
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        if (display) {
            super.paint(paintContext);
        }
    }

    public void setTableNode(TableNode tableNode) {
        //this.removeAllChildren();
        this.addChild(2, tableNode);
        tableNode.setPickable(false);
        tableNode.setCameraPose(this.getCameraPose());
        this.tableNode = tableNode;
        this.getToolbarNode().setQuadVisibility(true);
    }
      
  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OverviewNode [");
        sb.append("title=");
        sb.append(this.title);
        sb.append(",corner=");
        sb.append(",zoomlevel=");
        sb.append(this.zoomFactor);
        sb.append("]\n");
        return sb.toString();
    }
    
     public String toHTMLString() {
       StringBuilder sb = new StringBuilder();        
        sb.append(super.toHTMLString());
        if(this.getTableNode()!=null) {
            sb.append(this.getTableNode().toHTMLString());
        }
        return sb.toString();
    }
    
    public void setDisplay(boolean b) {
        this.display=b;
        this.setPickable(b);       
        this.setVisible(b);
        this.getImage().setVisible(b);
        this.getToolbarNode().setVisible(b);
    }
    

    public PAffineTransform zoomDown() {
        //Return the pose of the tableNode
        if (this.getChildrenCount() > 2) {
            this.getImage().setVisible(false);
            this.getToolbarNode().setVisible(false);
            TableNode qChild = this.getTableNode();
            PAffineTransform result = qChild.getCameraPose();            
            qChild.setDisplay(true);            
            return result;
        } else {
            return null;
        }
    }

    public PAffineTransform zoomUp() {
        if ((this.getParent() != null) && (this.getParent() instanceof ViewNode)) {            
            this.setDisplay(false);
            ViewNode pp = (ViewNode) this.getParent();
            pp.setDisplay(true);
            return pp.getCameraPose();
        } else {
            this.setDisplay(true);
            return null;
        }
    }
    
    /**
     * Convert a standard windows path into a java friendly URL!
     * 
     * @param s
     * @return 
     */
    private String URLize(String s) {         
        String g = s.replaceAll("\\\\", "/");
        return "file:///" + g;
    }
    
    
    public void initialize(TableNode aParent) {
        if(this.keywords==null) keywords=new Vector<String>();
        zoomFactor = 1;
        if (aParent != null) {
            this.zoomFactor = aParent.getZoomFactor() + 1;
            this.cameraPose = aParent.getCameraPose();
        } 

        //Load the overview as an image
        //TODO:  Allow some more flex here (text, etc)
        BufferedImage img = null;
        try {
            URL u = new URL(URLize(this.getOverviewImageFilename()));
            img = ImageIO.read(u);                        
            //img = ImageIO.read(HBDashboardApp.class.getResource("resources/" + imageFilename));                        
            PImage pi = new PImage();
            pi.setImage(img);            
            pi.setVisible(true);              
            this.addChild(0,pi);
            pi.setPickable(false);
            this.setPickable(true);
            this.setVisible(true);        

        } catch (IOException e) {
            System.err.println("Error loading file " + this.getOverviewImageFilename() + "\n\n");
            System.err.println(e);
            e.printStackTrace();
        }
        
        
        //For now -- set the bounds to match the parent or max (if no parent)
        PBounds  b = new PBounds(0,0,HBDashboardView.windowWidth, HBDashboardView.windowHeight);
        if (aParent != null) {
            b = aParent.getBounds();
        }
        this.getImage().setBounds(b);
        this.setBounds(b);
        
        ToolbarNode tbn = new ToolbarNode();
        this.addChild(1,tbn);
        if (aParent == null) {            
            this.getToolbarNode().setUpVisibility(false);
            this.getToolbarNode().setGlassVisibility(false);
            this.getToolbarNode().quadTool.setOffset(160, 5);
        }

        
        //Set the offset for the top level
        float toolbarScaleFactor = 1.0f;
        tbn.setOffset(HBDashboardView.windowWidth-(tbn.getWidth()*toolbarScaleFactor), HBDashboardView.windowHeight-(tbn.getHeight()*toolbarScaleFactor));        
        
        setDisplay(true);
    }

    public OverviewNode(TableNode aParent, String imageFilename, int preferredRow, int preferredColumn, String aTitle, String aBreadcrumb) {
        title = aTitle;
        setName(aTitle);
        this.column = preferredColumn;
        this.row = preferredRow;
        keywords = new Vector<String>();
        keywords.add(title);
        this.setBreadCrumb(aBreadcrumb);
        //TODO: String g = s.replaceAll("\\\\", "/");
        this.overviewImageFilename = imageFilename;        
        initialize(aParent);      
               
    }
    
    public OverviewNode() {
    
    }
}
