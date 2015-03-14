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
import hbdashboard.util.PPaintContext;
import hbdashboard.zoom.PNode;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TableNode")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 *
 * @author henderso
 */
public class TableNode extends ViewNode {

    int rowCount = 0;
    int columnCount = 0;

    @XmlElementWrapper(name="OverviewNodes")   
    @XmlElement(name="OverviewNode") 
    Vector<OverviewNode> overviewNodes;   
    
    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public Vector<OverviewNode> getOverviewNodes() {
        return overviewNodes;
    }

    public void setOverviewNodes(Vector<OverviewNode> overviewNodes) {
        this.overviewNodes = overviewNodes;
    }
    
    
    public OverviewNode getTableChild(int row, int column) {
        OverviewNode result = null;
        Iterator it = this.getChildrenIterator();
        while (it.hasNext()) {
            PNode p = (PNode) it.next();
            if (p instanceof OverviewNode) {
                OverviewNode nextOne = (OverviewNode) p;
                if ((nextOne.getRow() == row) && (nextOne.getColumn() == column)) {
                    result = nextOne;
                }
            }
        }
        if (result == null) {
            System.err.println("WARNING:  TableNode getTableChild Could not find a child in cell  " + row + "," + column);
        }
        return result;
    }

    private float getScaleFactor(OverviewNode aChild) {
        float subFactor = -1;
        if (this.getColumnCount() == 3) {
            subFactor = 1;
        }
        if (this.getColumnCount() == 4) {
            subFactor = 3;
        }
        subFactor = -1 + (2 * (getColumnCount() - 2));
        return 1.0f * ((float) aChild.getZoomFactor() + subFactor);
    }

    public void addTableChild(OverviewNode aChild) {

        if(!overviewNodes.contains(aChild)) {
            overviewNodes.add(aChild);  
        }
        //Calculate position in viewport        
        int row = aChild.getRow();
        int column = aChild.getColumn();

        double nx, ny, nh, nw;

        double ww = this.getBounds().width;//ViewNode.MAX_WIDTH;//this.getBounds().width;
        double hh = this.getBounds().height;//ViewNode.MAX_HEIGHT;//this.getBounds().height;

        //double ww =this.getBounds().width;
        //double hh =this.getBounds().height;

        //double childW = (1.0) * (ww + 2 * this.CHILDBORDER) / (2.0*level);
        //double childH = (1.0) * (hh + 2 * this.CHILDBORDER) / (2.0*level);

        double childW = (1.0) * (ww) / (1.0 * getColumnCount() * 1);
        double childH = (1.0) * (hh) / (1.0 * getRowCount() * 1);

        double baseX = this.getBounds().getX() + childW * column;
        double baseY = this.getBounds().getY() + childH * row;

        nx = baseX + this.CHILDBORDER;
        ny = baseY + this.CHILDBORDER;
        nh = childH;
        nw = childW;

        PAffineTransform pp = new PAffineTransform();
        pp.setToTranslation(-nx, -ny);
        PAffineTransform pps = new PAffineTransform();
        pps.setScale(getScaleFactor(aChild));
        pps.concatenate(pp);
       

        //Set image bounds here
        PImage po = (PImage) aChild.getChild(0);
        double aspectRatio = po.getHeight() / po.getWidth();
        double rh = aspectRatio * nw;
        
        
        aChild.setCameraPose(pps);
        aChild.setBounds(nx, ny, nw,rh);
        
        
        float toolbarScaleFactor = 1/(getScaleFactor(aChild)+1);
        
        PNode toolbar = aChild.getChild(1);
        aChild.getChild(0).setBounds(nx, ny, nw, rh);
        toolbar.setOffset(nx+nw-(toolbar.getWidth()*toolbarScaleFactor), ny+rh-(toolbar.getHeight()*toolbarScaleFactor));        
        toolbar.scale(toolbarScaleFactor);
        aChild.setVisible(false);
        aChild.setPickable(false);

        debug("child bounds adding:" + aChild.getBounds());

        //aChild.translate(nx, ny);
        this.addChild(aChild);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QuadNode [");
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
        Iterator<OverviewNode> it = this.getOverviewNodes().iterator();
        while(it.hasNext()) {
            sb.append(it.next().toHTMLString());
        }        
        return sb.toString();
    }

    public void setDisplay(boolean b) {
        this.display = b;
        ListIterator it = this.getChildrenIterator();
        while (it.hasNext()) {
            OverviewNode nextNode = (OverviewNode) it.next();
            nextNode.setDisplay(b);
            nextNode.setPickable(!b);
        }
        this.setPickable(b);
        this.setVisible(true);
    }

    public PAffineTransform zoomDown(int row, int column) {
        //Return the pose of the overview in cell

        OverviewNode on = null;
        ListIterator it = this.getChildrenIterator();
        while (it.hasNext()) {
            OverviewNode nextNode = (OverviewNode) it.next();
            if ((nextNode.getRow() == row) && (nextNode.getColumn() == column)) {
                on = nextNode;
            }
            //nextNode.setDisplay(false);
        }
        if (on != null) {
            on.setDisplay(true);
            return on.getCameraPose();
        } else {
            System.err.println("QuadNode zoomDown() error..Can't fnd a corner node");
            return null;
        }
    }

    /**
     * Returns true if the target is a descendent of the source node.
     * 
     * The recursive function walks up from the target to a null root
     * and returns true if it encounters the source.
     * 
     * @param source
     * @param target
     * @return 
     */
    private boolean isDescendent(ViewNode source, ViewNode target) {
        if (source == null) {
            return false;
        }
        if (target == null) {
            return false;
        }
        if (target.getTitle().equals(source.getTitle())) {
            return true;
        }
        if ((target.getParent() != null) && (target.getParent() instanceof ViewNode)) {
            return (isDescendent(source, (ViewNode) target.getParent()));
        }
        return false;
    }

    /**
     * Zooms (one level) in the semantic direction of the supplied target.
     * The supplied target is assumed to be somewhere in one of the cell (or nested)
     * @param target
     * @return 
     */
    public PAffineTransform zoomDown(ViewNode target) {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (isDescendent(getTableChild(i, j), target)) {
                    return zoomDown(i, j);
                }
            }
        }
        return null;
    }

    public PAffineTransform zoomUp() {
        if (this.getParent() != null) {
            this.setDisplay(false);
            ViewNode p = (ViewNode) this.getParent();
            p.setDisplay(true);
            return p.getCameraPose();
        } else {
            return null;
        }
    }
        
    public void initialize(OverviewNode aParent) {        
        if(this.keywords==null) keywords=new Vector<String>();
        setBounds(0, 0, HBDashboardView.windowWidth, HBDashboardView.windowHeight);
        zoomFactor = 1;
        if (aParent != null) {
            this.zoomFactor = aParent.getZoomFactor() + 1;
            this.cameraPose = aParent.getCameraPose();
            this.setBounds(aParent.getBounds());
        }
        this.setVisible(true);
        this.setPickable(true);
        setDisplay(false);               
    }

    public TableNode(OverviewNode aParent, String aTitle, String aBreadcrumb, int rows, int cols) {
        columnCount = cols;
        rowCount = rows;
        title = aTitle;
        setName(aTitle);
        overviewNodes = new Vector<OverviewNode>();        
        this.setBreadCrumb(aBreadcrumb);
        keywords = new Vector<String>();        
        initialize(aParent);
    }

    public TableNode() {
        this.setVisible(true);
        this.setPickable(true);
        setDisplay(false);
    }
}
