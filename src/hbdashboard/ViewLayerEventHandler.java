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

import hbdashboard.zoom.PCanvas;
import hbdashboard.zoom.PLayer;
import hbdashboard.event.PBasicInputEventHandler;
import hbdashboard.event.PInputEvent;
import hbdashboard.nodes.PPath;
import hbdashboard.util.PBounds;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;

/**
 *
 * @author henderso
 */
public class ViewLayerEventHandler extends PBasicInputEventHandler {
        // The rectangle that is currently getting created.
        protected PPath rectangle;
                        
        // The mouse press location for the current pressed, drag, release
        // sequence.
        protected Point2D pressPoint;
                        
        // The current drag location.
        protected Point2D dragPoint;
                
        public void mousePressed(PInputEvent e) {
                System.out.println("click!");
                super.mousePressed(e);                  
                
                PLayer layer = ((PCanvas)(e.getComponent())).getLayer();

                // Initialize the locations.
                pressPoint = e.getPosition();
                dragPoint = pressPoint;                         
                                
                // create a new rectangle and add it to the canvas layer so
                // that we can see it.
                rectangle = new PPath();
                rectangle.setStroke(new BasicStroke(
                        (float)(1/ e.getCamera().getViewScale())));
                layer.addChild(rectangle);
                                
                // update the rectangle shape.
                updateRectangle();
        }
                        
        public void mouseDragged(PInputEvent e) {
                super.mouseDragged(e);
                // update the drag point location.
                dragPoint = e.getPosition();    
                                
                // update the rectangle shape.
                updateRectangle();
        }
                
        public void mouseReleased(PInputEvent e) {
                super.mouseReleased(e);
                // update the rectangle shape.
                updateRectangle();
                rectangle = null;
        }       
                
        public void updateRectangle() {
                // create a new bounds that contains both the press and
                // current drag point.
                PBounds b = new PBounds();
                b.add(pressPoint);
                b.add(dragPoint);
                
                // Set the rectangles bounds.
                rectangle.setPathTo(b);
        }
}