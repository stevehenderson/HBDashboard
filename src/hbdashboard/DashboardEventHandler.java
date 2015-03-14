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

import hbdashboard.zoom.PCamera;
import hbdashboard.zoom.PInputManager;
import hbdashboard.zoom.PNode;
import hbdashboard.event.PInputEvent;
import hbdashboard.nodes.PPath;
import hbdashboard.util.PAffineTransform;
import hbdashboard.util.PPickPath;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

/**
 *
 * @author henderso
 */
public class DashboardEventHandler extends PInputManager {

    /**
     * A constant used to adjust how sensitive the zooming will be to mouse
     * movement. The larger the number, the more each delta pixel will affect zooming.
     */
    private static final double ZOOM_SENSITIVITY = 0.001;
    private double minScale = 0.001;
    private double maxScale = Double.MAX_VALUE;
    private Point2D viewZoomPoint;
    ViewNode root;
    ViewNode focus;
    private transient Point2D mousePressedCanvasPoint;
    //Point2D lastTranslateShift;
    Stack<Point2D> lastTranslateShift;
    Stack<Double> lastZoom;
    int zoomfactor = 0;
    PAffineTransform lastGoodPose;
    PAffineTransform startingPose;
    SLabel lastSLabel;
    PCamera theCamera;
    Stack<String> breadHistory;
    JEditorPane historyPane;
    ViewNode lastSelectedNode;
    public static int ANIME_SPEED = 300;
    public static int FADE_SPEED = 500;
    public static float ZOOM_STEP = 1.05f;

    
    private final static boolean DEBUG = false;    
    private void debug(String s) {
       if(DEBUG) System.out.println(this.getClass() + ":" + s);
    }
    
    /**
     * Return the path from the root to the supplied node
     * @param v
     * @return 
     */
    public Vector<ViewNode> pathFromRoot(ViewNode v) {
        Vector<ViewNode> result = new Vector<ViewNode>();
        while (v != null) {
            if (v.getParent() instanceof ViewNode) {
                v = (ViewNode) v.getParent();
                result.insertElementAt(v, 0);
            } else {
                v = null;
            }
        }
        return result;
    }

    private void zoomOutToRoot(ViewNode vn) {
        debug("zoomingtoroot at:" + vn.toString());
        if (vn instanceof TableNode) {
            ((TableNode) vn).zoomUp();
        } else {
            ((OverviewNode) vn).zoomUp();
        }
        if ((vn.getParent() != null) && (vn.getParent() instanceof ViewNode)) {
            zoomOutToRoot((ViewNode) vn.getParent());
        }
    }

    /**
     * Find and zoom into a node having the prescribed keyword
     * @param keyword 
     */
    public void findKeyWord(String keyword) {
        PAffineTransform at = new PAffineTransform();
        debug("Looking for keyword: " + keyword);
        debug("TODO  Code: " + keyword);
        ViewNode nodeToCheck = bfs(root, keyword);
        if (lastGoodPose != null) {
            theCamera.setViewTransform(lastGoodPose);
        }
        if (nodeToCheck != null) {
            debug("Found the keyword on node " + nodeToCheck);
            //Hode what we were doing
            if (lastSelectedNode != null) {
                zoomOutToRoot(lastSelectedNode);
            }
            theCamera.setTransform(startingPose);
            lastGoodPose = theCamera.getViewTransform();
            Vector<ViewNode> zoomPath = pathFromRoot(nodeToCheck);
            Iterator<ViewNode> it = zoomPath.iterator();
            while (it.hasNext()) {
                ViewNode nextNode = it.next();
                lastSelectedNode = nextNode;
                debug("Zooming in on " + nextNode.getTitle());
                if (nextNode instanceof OverviewNode) {
                    OverviewNode on = (OverviewNode) nextNode;
                    at = on.zoomDown();
                    if (at != null) {
                        theCamera.setTransform(at);
                        lastGoodPose = theCamera.getViewTransform();
                        lastSelectedNode = on.getTableNode();
                    }
                }
                if (nextNode instanceof TableNode) {
                    TableNode qn = (TableNode) nextNode;
                    at = qn.zoomDown(nodeToCheck);
                    if (at != null) {
                        theCamera.setTransform(at);
                        lastGoodPose = theCamera.getViewTransform();
                    }
                }
            }
            historyPane.setText(constructBreadCrumb(nodeToCheck));
        } else {
            System.err.println("Failed to finf keyword");
        }
    }

    /**
     * A BreadthFirstSearch to find the node associated with a keyword..
     * 
     * @param n:  The starting point for the search
     * @param key:  The string to search for
     * @return The first ViewNode containing the keyword, else NULL if not found
     */
    public ViewNode bfs(ViewNode n, String key) {
        if (n instanceof OverviewNode) {
            OverviewNode o = (OverviewNode) n;
            if (o.getKeywords().contains(key)) {
                return o;
            }
            if (o.getTableNode() != null) {
                return bfs(o.getTableNode(), key);
            } else {
                return null;
            }
        }
        if (n instanceof TableNode) {
            TableNode qn = (TableNode) n;
            if (qn.getKeywords().contains(key)) {
                return qn;
            }
            int j = qn.getChildrenCount();
            for (int i = 0; i < j; i++) {
                ViewNode nextNode = (ViewNode) qn.getChild(i);
                ViewNode check = bfs(nextNode, key);
                if (check != null) {
                    return check;
                }
            }
        }
        return null;
    }

    // ****************************************************************
    // Zooming
    // ****************************************************************
    /**
     * Returns the minimum view magnification factor that this event handler is
     * bound by. The default is 0.
     * 
     * @return the minimum camera view scale
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * Sets the minimum view magnification factor that this event handler is
     * bound by. The camera is left at its current scale even if
     * <code>minScale</code> is larger than the current scale.
     * 
     * @param minScale the minimum scale, must not be negative.
     */
    public void setMinScale(final double minScale) {
        this.minScale = minScale;
    }

    /**
     * Returns the maximum view magnification factor that this event handler is
     * bound by. The default is Double.MAX_VALUE.
     * 
     * @return the maximum camera view scale
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * Sets the maximum view magnification factor that this event handler is
     * bound by. The camera is left at its current scale even if
     * <code>maxScale</code> is smaller than the current scale. Use
     * Double.MAX_VALUE to specify the largest possible scale.
     * 
     * @param maxScale the maximum scale, must not be negative.
     */
    public void setMaxScale(final double maxScale) {
        this.maxScale = maxScale;
    }
    // The mouse press location for the current pressed, drag, release
    // sequence.
    protected Point2D pressPoint;
    // The current drag location.
    protected Point2D dragPoint;

    /**
     * Return the breadcrumb trail from the root to the supplied node 
     * @param v
     * @return 
     */
    public String constructBreadCrumb(ViewNode v) {

        Stack<String> sstack = new Stack<String>();
        Stack<String> kstack = new Stack<String>();

        while (v != null) {
            sstack.push(v.getBreadCrumb());
            if (v.getKeywords().size() > 0) {
                kstack.push(v.getKeywords().elementAt(0));
            } else {
                kstack.push("nokeywords");
            }
            if (v.getParent() instanceof ViewNode) {
                v = (ViewNode) v.getParent();
            } else {
                v = null;
            }
        }
        StringWriter sb = new StringWriter();

        while (sstack.size() > 0) {
            String g = sstack.pop();
            String kw = kstack.pop();
            sb.append("<a class='level1' href='" + kw + "'>" + g + "</a>");
            if (sstack.size() > 0) {
                //Unicode spacer/arrow
                //sb.append("<font size=24pt>&#8594;</font>");
                sb.append("<font size=5>&#8658;</font>");
            }
        }
        return sb.toString();

    }

    private void hideGrandChildren(OverviewNode vn) {
        if((vn.getParent()!=null) && (vn.getParent() instanceof TableNode)) {
            TableNode tn = (TableNode) vn.getParentNode();
            Iterator<OverviewNode> it = tn.getOverviewNodes().iterator();
            while(it.hasNext()) {
                OverviewNode on=it.next();
                if(on.equals(vn)) continue;
                if(on.getTableNode()!=null){                    
                    on.getTableNode().setDisplay(false);
                }
            }
        }        
    }
    
    public void mousePressed(PInputEvent e) {

        PNode testNode = e.getPickedNode();
        PCamera fake;
        final PCamera camera = e.getCamera();

        viewZoomPoint = e.getPosition();
        int clickCount = e.getClickCount();
        PAffineTransform at = null;

        debug("click!==>" + e.getButton());
        debug(e.getClickCount() + " clicks");

        super.mousePressed(e);
        int buttonPressed = e.getButton();

        debug("picked node=" + testNode);


        //If there is only one click then check for tool buttons
        if (clickCount < 2) {
            if (testNode instanceof ToolNode) {
                ToolNode tn = (ToolNode) testNode;
                ToolbarNode tbn = (ToolbarNode) tn.getParent();
                if (tbn.getParent() instanceof OverviewNode) {
                    OverviewNode on = (OverviewNode) tbn.getParent();
                    if (buttonPressed == 1) {
                        //Zoom in
                        if (tn.toolType == ToolNode.TOOLS.QUAD) {
                            at = on.zoomDown();
                            if (on.getTableNode() != null) {
                                historyPane.setText(constructBreadCrumb(on.getTableNode()));
                                lastSelectedNode = on.getTableNode();
                            }
                        } else if (tn.toolType == ToolNode.TOOLS.UP) {
                            at = on.zoomUp();
                            if ((on.getParent() != null) && (on.getParent() instanceof ViewNode)) {
                                historyPane.setText(constructBreadCrumb((ViewNode) on.getParent()));
                                lastSelectedNode = on;
                            } else {
                                historyPane.setText("(TOP)");
                            }
                        } else if (tn.toolType == ToolNode.TOOLS.GLASS) {
                            //if((on.getParent()!=null) && (on.getParent() instanceof TableNode)) {
                            at =on.getCameraPose();
                        }
                    }
                }
                if (at != null) {
                    camera.animateToTransform(at, ANIME_SPEED);
                    lastGoodPose = camera.getViewTransform();
                }
               
            }
            return;  //TODO:  Is this OK?
        }

        //RestorePose
        if (lastGoodPose != null) {
            //If they are coming off a non-standard pose (i.e. zoomed or panned)
            //allow any double left or right click to return them to last home
            //position.                        
            if (!camera.getViewTransform().equals(lastGoodPose)) {
                camera.setViewTransform(lastGoodPose);
                //camera.animateToTransform(lastGoodPose, ANIME_SPEED/2);
                return;
            }
        }


        Point2D pt = e.getCanvasPosition();

        PPickPath pn = camera.pick(pt.getX(), pt.getY(), 10);



        if (testNode instanceof OverviewNode) {
            OverviewNode vn = (OverviewNode) testNode;
            //debug("Viewlayer node=" + vn);
            if (buttonPressed == 1) {
                at = vn.zoomDown();
                if (vn.getTableNode() != null) {
                    historyPane.setText(constructBreadCrumb(vn.getTableNode()));
                    lastSelectedNode = vn.getTableNode();
                }
            } else if (buttonPressed == 3) {
                at = vn.zoomUp();                      
                if ((vn.getParent() != null) && (vn.getParent() instanceof ViewNode)) {
                    historyPane.setText(constructBreadCrumb((ViewNode) vn.getParent()));
                    lastSelectedNode = vn;
                } else {
                    historyPane.setText("(TOP)");
                }
            }
            if (at != null) {
                camera.animateToTransform(at, ANIME_SPEED);
                lastGoodPose = camera.getViewTransform();
            }
            hideGrandChildren(vn);
        }

        if (testNode instanceof TableNode) {
            TableNode vn = (TableNode) testNode;
            Point2D pt2 = e.getPosition();

            //Get the corner
            double x = pt2.getX();
            double y = pt2.getY();
            debug("Clicked point " + x + "," + y);
            debug("vnBounds " + vn.getBounds());

            int selectedTableCol = (int) Math.floor((x - vn.getBounds().getX()) / (vn.getBounds().width / vn.getColumnCount()));
            int selectedTableRow = (int) Math.floor((y - vn.getBounds().getY()) / (vn.getBounds().height / vn.getRowCount()));
            debug("Clicked cell " + selectedTableRow + "," + selectedTableCol);

            OverviewNode selectedNode = vn.getTableChild(selectedTableRow, selectedTableCol);
            //debug("Viewlayer node=" + vn);
            if (buttonPressed == 1) {
                at = vn.zoomDown(selectedTableRow, selectedTableCol);
                if (selectedNode != null) {
                    historyPane.setText(constructBreadCrumb(selectedNode));
                    lastSelectedNode = selectedNode;
                }
            } else if (buttonPressed == 3) {
                at = vn.zoomUp();
                if (vn.getParent() instanceof ViewNode) {
                    historyPane.setText(constructBreadCrumb((ViewNode) vn.getParent()));
                    lastSelectedNode = vn;
                } else {
                    historyPane.setText("(TOP)");
                }
            }
            if (at != null) {
                camera.animateToTransform(at, ANIME_SPEED);
                lastGoodPose = camera.getViewTransform();
            }
        }
       
    }

    public void mouseDragged(PInputEvent e) {
        //debug("mouseDrag!");
        super.mouseDragged(e);
        // update the drag point location.
        dragPoint = e.getPosition();
    }

    public void mouseReleased(PInputEvent e) {
        //debug("mouse released!");
        super.mouseReleased(e);
    }

    public Point2D getMousePressedCanvasPoint() {
        if (mousePressedCanvasPoint == null) {
            mousePressedCanvasPoint = new Point2D.Double();
        }
        return mousePressedCanvasPoint;
    }

    /**
     * This method is invoked when the mouse wheel is rotated by a block.
     * Subclasses should override this method to implement their own behavior.
     * 
     * @param event an object that can be queries to discover the event's
     *            details
     */
    public void mouseWheelRotatedByBlock(final PInputEvent event) {
        //debug("mouse rotated BLOCK!!!");
    }

    public void mouseWheelRotated(final PInputEvent event) {
        Point2D pt = event.getCanvasPosition();
        //debug("mouse rotated !!! "+ event.getWheelRotation() + " at " + pt);
        int dir = event.getWheelRotation();
        final PCamera camera = event.getCamera();
        float sc = 1;
        if (dir > 0) {
            sc = 1.0f / ZOOM_STEP;
        } else {
            sc = ZOOM_STEP;
        }
        Point2D pt_global = camera.globalToLocal(pt);
        pt = pt_global;
        camera.scaleAboutPoint(sc, pt.getX(), pt.getY());
    }

    public DashboardEventHandler(ViewNode aRoot, PCamera pc, JEditorPane aHistoryPane) {

        root = aRoot;
        focus = aRoot;
        theCamera = pc;
        lastZoom = new Stack<Double>();
        lastTranslateShift = new Stack<Point2D>();
        lastZoom.push(new Double(1.0));
        lastTranslateShift.push(new Point2D.Double(0, 0));
        breadHistory = new Stack<String>();
        breadHistory.push("(Top)");
        historyPane = aHistoryPane;
        lastGoodPose = aRoot.getCameraPose();
        startingPose = aRoot.getCameraPose();

    }

    private DashboardEventHandler() {
        //not allowed!
    }
}
