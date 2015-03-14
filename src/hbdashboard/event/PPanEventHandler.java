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
package hbdashboard.event;

import java.awt.event.InputEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import hbdashboard.zoom.PCamera;
import hbdashboard.util.PBounds;
import hbdashboard.util.PDimension;

/**
 * <b>PPanEventHandler</b> provides event handlers for basic panning of the
 * canvas view with the left mouse. The interaction is that clicking and
 * dragging the mouse translates the view so that the point on the surface stays
 * under the mouse.
 * <P>
 * 
 *
 * 
 */
public class PPanEventHandler extends PDragSequenceEventHandler {

    private static final int DEFAULT_MAX_AUTOPAN_SPEED = 750;
    private static final int DEFAULT_MIN_AUTOPAN_SPEED = 250;

    private boolean autopan;
    private double minAutopanSpeed = DEFAULT_MIN_AUTOPAN_SPEED;
    private double maxAutopanSpeed = DEFAULT_MAX_AUTOPAN_SPEED;

    /**
     * Constructs a Pan Event Handler that will by default perform auto-panning.
     */
    public PPanEventHandler() {
        super();
        setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK));
        setAutopan(true);
    }

    /**
     * Updates the view in response to a user initiated drag event.
     * 
     * @param event event responsible for the drag
     */
    protected void drag(final PInputEvent event) {
        super.drag(event);
        pan(event);
    }

    /**
     * Pans the camera in response to the pan event provided.
     * 
     * @param event contains details about the drag used to translate the view
     */
    protected void pan(final PInputEvent event) {
        final PCamera c = event.getCamera();
        final Point2D l = event.getPosition();

        if (c.getViewBounds().contains(l)) {
            final PDimension d = event.getDelta();
            c.translateView(d.getWidth(), d.getHeight());
        }
    }

    // ****************************************************************
    // Auto Pan
    // ****************************************************************

    /**
     * Determines if auto-panning will occur or not.
     * 
     * @param autopan true if auto-panning functionality will be active
     */
    public void setAutopan(final boolean autopan) {
        this.autopan = autopan;
    }

    /**
     * Returns whether the auto-panning functoinality is enabled.
     * 
     * @return true if auto-panning is enabled
     */
    public boolean getAutopan() {
        return autopan;
    }

    /**
     * Set the minAutoPan speed in pixels per second.
     * 
     * @param minAutopanSpeed number of pixels to assign as the minimum the
     *            autopan feature can pan the view
     */
    public void setMinAutopanSpeed(final double minAutopanSpeed) {
        this.minAutopanSpeed = minAutopanSpeed;
    }

    /**
     * Set the maxAutoPan speed in pixels per second.
     * 
     * @param maxAutopanSpeed number of pixels to assign as the maximum the
     *            autopan feature can pan the view
     */
    public void setMaxAutopanSpeed(final double maxAutopanSpeed) {
        this.maxAutopanSpeed = maxAutopanSpeed;
    }

    /**
     * Returns the minAutoPan speed in pixels per second.
     * 
     * @since 1.3
     * @return minimum distance the autopan feature can pan the view
     */
    public double getMinAutoPanSpeed() {
        return minAutopanSpeed;
    }

    /**
     * Returns the maxAutoPan speed in pixels per second.
     * 
     * @since 1.3
     * @return max distance the autopan feature can pan the view by
     */
    public double getMaxAutoPanSpeed() {
        return maxAutopanSpeed;
    }

    /**
     * Performs auto-panning if enabled, even when the mouse is not moving.
     * 
     * @param event current drag relevant details about the drag activity
     */
    protected void dragActivityStep(final PInputEvent event) {
        if (!autopan) {
            return;
        }

        final PCamera c = event.getCamera();
        final PBounds b = c.getBoundsReference();
        final Point2D l = event.getPositionRelativeTo(c);
        final int outcode = b.outcode(l);
        final PDimension delta = new PDimension();

        if ((outcode & Rectangle2D.OUT_TOP) != 0) {
            delta.height = validatePanningSpeed(-1.0 - 0.5 * Math.abs(l.getY() - b.getY()));
        }
        else if ((outcode & Rectangle2D.OUT_BOTTOM) != 0) {
            delta.height = validatePanningSpeed(1.0 + 0.5 * Math.abs(l.getY() - (b.getY() + b.getHeight())));
        }

        if ((outcode & Rectangle2D.OUT_RIGHT) != 0) {
            delta.width = validatePanningSpeed(1.0 + 0.5 * Math.abs(l.getX() - (b.getX() + b.getWidth())));
        }
        else if ((outcode & Rectangle2D.OUT_LEFT) != 0) {
            delta.width = validatePanningSpeed(-1.0 - 0.5 * Math.abs(l.getX() - b.getX()));
        }

        c.localToView(delta);

        if (delta.width != 0 || delta.height != 0) {
            c.translateView(delta.width, delta.height);
        }
    }

    /**
     * Clips the panning speed to the minimum and maximum auto-pan speeds
     * assigned. If delta is below the threshold, it will be increased. If
     * above, it will be decreased.
     * 
     * @param delta auto-pan delta to be clipped
     * @return clipped delta value.
     */
    protected double validatePanningSpeed(final double delta) {
        final double stepsPerSecond = 1000d / getDragActivity().getStepRate();
        final double minDelta = minAutopanSpeed / stepsPerSecond;
        final double maxDelta = maxAutopanSpeed / stepsPerSecond;

        final double absDelta = Math.abs(delta);
        
        final double clippedDelta;
        if (absDelta < minDelta) {
            clippedDelta = minDelta;
        }
        else if (absDelta > maxDelta) {
            clippedDelta = maxDelta;
        }
        else {
            clippedDelta = delta;
        }

        if (delta < 0) {
            return -clippedDelta;
        }
        else {
            return clippedDelta;
        }
    }
}
