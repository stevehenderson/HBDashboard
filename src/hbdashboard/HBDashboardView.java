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

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;




import hbdashboard.zoom.PCanvas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * The application's main frame.
 */
public class HBDashboardView extends FrameView {
    
    JLabel topFill;
    SLabel tbDTD;
    SLabel tbATN;
    SLabel tbTTF;
    SLabel tbPart;
    PCanvas aCanvas;
    JLabel mouseFill;
    JLabel bottomFill;
    DashboardEventHandler dashboardEventHandler;
    Properties myProp;
    
    public static int windowWidth = 1024;
    public static int windowHeight = 768;
    
    String functionMapPath = "unset";
    
    public static String propertiesFileName = "quads.properties";
    
    private final static boolean DEBUG = false;    
    private void debug(String s) {
        if(DEBUG) System.out.println(this.getClass() + ":" + s);
    }
    
    
    private void addButtonLabel(JLabel j, int row) {
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = row;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        //gridBagConstraints.ipadx = 150;
        // gridBagConstraints.ipady = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pLeft.add(j, gridBagConstraints);
    }
    
    public void saveTableNodeAsXML(TableNode tn, String filename) {
        // create JAXB context and instantiate marshaller
        Marshaller m = null;
        try {
            JAXBContext context = JAXBContext.newInstance(TableNode.class);
            m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(tn, System.out);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        Writer w = null;
        try {
            w = new FileWriter(filename);
            m.marshal(tn, w);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                w.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    private void initializeNode(OverviewNode parent, TableNode vn) {        
        vn.initialize(parent);        
        parent.setTableNode(vn);

        //Walk each overviewnode
        Iterator<OverviewNode> it = vn.getOverviewNodes().iterator();
        while (it.hasNext()) {
            OverviewNode on = it.next();
            on.initialize(vn);
            vn.addTableChild(on);            
            if (on.getTableNode() != null) {
                initializeNode(on, on.getTableNode());
            }
        }        
    }
    
    private void loadTableNodeFromXML(OverviewNode root, String filename) {        
        TableNode tempQ = null;
        try {
            JAXBContext context = JAXBContext.newInstance(TableNode.class);
            Unmarshaller um = context.createUnmarshaller();
            tempQ = (TableNode) um.unmarshal(new FileReader(filename));
            
        } catch (Exception e) {
            System.err.println(e);
        }
        //Now walk the table and reconnect everything
        debug("Loaded table node from XML");
        initializeNode(root, tempQ);
        //root.setTableNode(tempQ);   
        //root.addChild(tempQ);
        
    }
    
    public void resetButtons() {
        tbDTD.reset();
        tbATN.reset();
        tbTTF.reset();
        tbPart.reset();        
    }
    
    private void setupButtons() {
        
        
        ImageIcon topImg = new ImageIcon(this.getClass().getResource("resources/filltop.jpg"));
        topFill = new JLabel(topImg);
        
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pLeft.add(topFill, gridBagConstraints);
        
        tbDTD = new SLabel("dtd", dashboardEventHandler, this);
        //this.tbPart = new JLabel(img);
        addButtonLabel(tbDTD, 1);
        
        tbATN = new SLabel("atn", dashboardEventHandler, this);
        //this.tbPart = new JLabel(img);
        addButtonLabel(tbATN, 2);
        
        tbTTF = new SLabel("ttf", dashboardEventHandler, this);
        //this.tbPart = new JLabel(img);
        addButtonLabel(tbTTF, 3);
        
        tbPart = new SLabel("part", dashboardEventHandler, this);
        //this.tbPart = new JLabel(img);
        addButtonLabel(tbPart, 4);
        
        ImageIcon mouseImg = new ImageIcon(this.getClass().getResource("resources/mousekey.jpg"));
        mouseFill = new JLabel(mouseImg);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pLeft.add(mouseFill, gridBagConstraints);
        
        ImageIcon bottomImg = new ImageIcon(this.getClass().getResource("resources/fill.jpg"));
        bottomFill = new JLabel(bottomImg);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pLeft.add(bottomFill, gridBagConstraints);
    }
    
    public void linkToNode(String keyword) {
        this.dashboardEventHandler.findKeyWord(keyword);
    }
    
    
      
    private void setupLeftPane(OverviewNode root) {
        leftPane.setEditable(false);
        leftPane.setContentType("text/html");        
        leftPane.setBackground(new Color(235,235,235));
        HTMLEditorKit kit = new HTMLEditorKit();
        HBPaneListener lpl = new HBPaneListener(this,leftPane,functionMapPath);
        leftPane.addHyperlinkListener(lpl);
        StringWriter sb = new StringWriter();
        String mouseImageLink =  this.getClass().getResource("resources/mousekey.jpg").toString();
        
        
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {font-family:Arial, serif; font-size:large; color:red; text-decoration:none;};");        
        styleSheet.addRule("a.hover {color:#0000FF;  font-weight:800;};");
        styleSheet.addRule("a.level1 {color:#000022; font-size:20pt; font-weight:800;};");
        styleSheet.addRule("a.level2 {color:#000022; font-size:18pt; font-weight:800;};");
        styleSheet.addRule("a.level3 {color:#000022; font-size:16pt; font-weight:400;};");
        styleSheet.addRule("a.level4 {color:#000022; font-size:12pt; font-weight:400;};");
        styleSheet.addRule("a.other {color:#000022; font-size:12pt; font-weight:800;};");
        styleSheet.addRule("h1 {color:#000077; font-family:Arial, serif; font-size:22pt; font-weight:800;};");
        
        
        leftPane.setEditorKit(kit);
        sb.append("<html border=0>");
        sb.append("<table>");
        sb.append("<body alink='#FFFFFF'>");
        sb.append("<tr><td width=200>");
        //Header
        sb.append("<h1><hr></h1>");
        sb.append("</td></tr>");
        //End Header
        //Mid        
        sb.append("<tr><td>");        
        sb.append(root.getTableNode().toHTMLString());
        
        sb.append("</td></tr>");        
        sb.append("<tr><td>");
        sb.append("<h1><hr></h1>");
        sb.append("</td></tr>");
        sb.append("<tr><td>");
        sb.append("<a class='other' href=':openmap'>&nbsp;&nbsp;&nbsp;Functional Mapping</a>");
        sb.append("</td></tr>");        
        //End mid
        
        sb.append("<tr><td>");
        sb.append("<h1><hr></h1>");        
        sb.append("<center><img src='" + mouseImageLink + "'></center>");
        sb.append("</tr></td>");
        
        sb.append("</table>");
        sb.append("</body></html>");
        this.leftPane.setText(sb.toString());
        
    }
    
    
    private void setupHistoryPane() {
        historyPane.setEditable(false);
        historyPane.setContentType("text/html");        
        historyPane.setBackground(new Color(235,235,235));
        HTMLEditorKit kit = new HTMLEditorKit();
        HBPaneListener lpl = new HBPaneListener(this,historyPane,functionMapPath);
        historyPane.addHyperlinkListener(lpl);
        StringWriter sb = new StringWriter();
        
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a.level1 {font-family:Arial, serif; font-size:14pt; color:#0000FF; text-decoration:none;};");        
        styleSheet.addRule("a.hover {color:#0000FF;  font-weight:800;};");
        styleSheet.addRule("h1 {color:#000077; font-family:Arial, serif; font-size:14pt; font-weight:800;};");                
        styleSheet.addRule("h2 {color:#000077; font-family:Arial, serif; font-size:24pt; font-weight:400;};");                
        historyPane.setEditorKit(kit);
        historyPane.setText("<h1>(TOP)</h1>");        
        this.historyPane.setText(sb.toString());
        
    }

    /**
     * This is the method for setting all the custom non-NetBeans
     * GUI code
     */
    /**
     * This is the method for setting all the custom non-NetBeans
     * GUI code
     */
    private void setupInterface() {
      
        
        aCanvas = new PCanvas();
        //this.getFrame().setUndecorated(true);
        this.getFrame().setTitle("Commanders Dashboard");

        //OverviewNode(TableNode aParent, String imageFilename, CORNERS aCorner, String aTitle, String aBreadcrumb) 
        OverviewNode root = new OverviewNode(null, gp("top.image"), 0, 0, gp("top.title"), gp("top.crumb"));
        root.setBounds(0, 0, HBDashboardView.windowWidth, HBDashboardView.windowHeight);
        aCanvas.getLayer().addChild(root);        
        root.setCameraPose(aCanvas.getCamera().getTransform());
        
        
        pMain.add(aCanvas, BorderLayout.NORTH);
        aCanvas.setSize(12800, 10240);
        aCanvas.setPreferredSize(new Dimension(12800, 10240));
        aCanvas.setBackground(Color.WHITE);
        
        aCanvas.removeInputEventListener(aCanvas.getZoomEventHandler());
        //aCanvas.removeInputEventListener(aCanvas.getPanEventHandler());
        
        dashboardEventHandler = new DashboardEventHandler(root, aCanvas.getCamera(), historyPane);
        aCanvas.addInputEventListener(dashboardEventHandler);


        //saveTableNodeAsXML(rootQuad,"rootQuad.xml");         
        loadTableNodeFromXML(root, gp("dashboard.file"));
        //setupButtons();
        functionMapPath = gp("function.map");
        setupLeftPane(root);
        setupHistoryPane();
        
        
    }
    
    public String gp(String s) {
        String result = null;
        try {
            result = myProp.getProperty(s);
            if (result == null) {
                System.err.append("MISSING PROPERTY!!!!!  :" + s + "\n");            
            }   
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return result;
    }
    
    public HBDashboardView(SingleFrameApplication app) {
        super(app);
        
               
        
        myProp = new Properties();
        // Read properties file.      

        try {
            File ff= new File(new File("."), propertiesFileName); 
            FileInputStream is = new FileInputStream(ff);
            //myProp.load (this.getClass().getResourceAsStream("resources/quads.properties"));
            myProp.load (is);
            
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();;
        }
        
        String widthString = gp("window.width").trim();
        String heightString = gp("window.height").trim();
        
        if(widthString !=null) {
            windowWidth=Integer.parseInt(widthString);
            if(windowWidth < 640) windowWidth=640;
        }
        
        if(heightString !=null) {
            windowHeight=Integer.parseInt(heightString);
            if(windowHeight < 480) windowWidth=480;
        }
        
     
        initComponents();
        setupInterface();

      
       //his.getFrame().setResizable(false);   
       
   

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        
            
       this.getFrame().pack();   
               
        
        
    }
    
    @Action
    public void showAboutBox() {
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        pLeft = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leftPane = new javax.swing.JEditorPane();
        pRight = new javax.swing.JPanel();
        pTop = new javax.swing.JPanel();
        pBreadcrumb = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyPane = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        pMain = new javax.swing.JPanel();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(HBDashboardView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setMaximumSize(new java.awt.Dimension(1280, 790));
        mainPanel.setMinimumSize(new java.awt.Dimension(1280, 790));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new Dimension(windowWidth+200,windowHeight+40));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        pLeft.setBackground(resourceMap.getColor("pLeft.background")); // NOI18N
        pLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        pLeft.setMaximumSize(new java.awt.Dimension(200, 768));
        pLeft.setMinimumSize(new java.awt.Dimension(200, 768));
        pLeft.setName("pLeft"); // NOI18N
        pLeft.setPreferredSize(new Dimension(200,windowHeight));
        pLeft.setLayout(new java.awt.BorderLayout());

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new Dimension(200,windowHeight));

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new Dimension(200,windowHeight));

        leftPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        leftPane.setName("leftPane"); // NOI18N
        leftPane.setPreferredSize(new Dimension(200,windowHeight));
        jScrollPane1.setViewportView(leftPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        pLeft.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        mainPanel.add(pLeft);

        pRight.setName("pRight"); // NOI18N
        pRight.setPreferredSize(new Dimension(windowWidth,windowHeight+40));
        pRight.setLayout(new javax.swing.BoxLayout(pRight, javax.swing.BoxLayout.Y_AXIS));

        pTop.setBackground(resourceMap.getColor("pTop.background")); // NOI18N
        pTop.setName("pTop"); // NOI18N
        pTop.setPreferredSize(new Dimension(windowWidth,40));

        pBreadcrumb.setMaximumSize(new java.awt.Dimension(windowWidth,40));
        pBreadcrumb.setMinimumSize(new java.awt.Dimension(windowWidth,40));
        pBreadcrumb.setName("pBreadcrumb"); // NOI18N
        pBreadcrumb.setPreferredSize(new java.awt.Dimension(1024, 4));
        pBreadcrumb.setRequestFocusEnabled(false);
        pBreadcrumb.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(windowWidth,50));

        historyPane.setMinimumSize(new java.awt.Dimension(windowWidth,40));
        historyPane.setName("historyPane"); // NOI18N
        historyPane.setPreferredSize(new java.awt.Dimension(windowWidth,40));
        jScrollPane2.setViewportView(historyPane);

        pBreadcrumb.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pTopLayout = new javax.swing.GroupLayout(pTop);
        pTop.setLayout(pTopLayout);
        pTopLayout.setHorizontalGroup(
            pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1474, Short.MAX_VALUE)
            .addGroup(pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pBreadcrumb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1474, Short.MAX_VALUE))
        );
        pTopLayout.setVerticalGroup(
            pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
            .addGroup(pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pBreadcrumb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE))
        );

        pRight.add(pTop);

        jScrollPane3.setName("jScrollPane3"); // NOI18N
        jScrollPane3.setPreferredSize(new Dimension(windowWidth,windowHeight));

        pMain.setBackground(resourceMap.getColor("pMain.background")); // NOI18N
        pMain.setMaximumSize(new java.awt.Dimension(windowWidth,windowHeight));
        pMain.setMinimumSize(new java.awt.Dimension(0, 0));
        pMain.setName("pMain"); // NOI18N
        pMain.setPreferredSize(new Dimension(windowWidth,windowHeight));
        pMain.setLayout(new java.awt.BorderLayout());
        jScrollPane3.setViewportView(pMain);

        pRight.add(jScrollPane3);

        mainPanel.add(pRight);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(1280, 25));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1486, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1316, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane historyPane;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JEditorPane leftPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel pBreadcrumb;
    private javax.swing.JPanel pLeft;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pRight;
    private javax.swing.JPanel pTop;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
