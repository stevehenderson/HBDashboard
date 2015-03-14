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
package hbdashboard.util;

/**
 * This class is used to encapsulate exceptions that may occur while performing transform operations.
 *
 * @since 1.3
 */
public class PAffineTransformException extends RuntimeException {
    /**
     * Allows for future serialization code to understand versioned binary
     * formats.
     */
    private static final long serialVersionUID = 1L;

    private final PAffineTransform errantTransform;

    /**
     * Constructs an Exception that represents an error with the
     * errantTransform.
     * 
     * @param errantTransform transform that caused the error
     */
    public PAffineTransformException(final PAffineTransform errantTransform) {
        this.errantTransform = errantTransform;
    }

    /**
     * Constructs an Exception that represents an error with the
     * errantTransform.
     * 
     * @param message Text message provided by the programmer about the context
     *            of the error
     * @param errantTransform transform that caused the error
     */
    public PAffineTransformException(final String message, final PAffineTransform errantTransform) {
        super(message);
        this.errantTransform = errantTransform;
    }

    /**
     * Constructs an Exception that wraps another and records the errant
     * transform.
     * 
     * @param throwable the root cause of the exception
     * @param errantTransform transform that's related to the error
     */
    public PAffineTransformException(final Throwable throwable, final PAffineTransform errantTransform) {
        super(throwable);
        this.errantTransform = errantTransform;
    }

    /**
     * Constructs an Exception that wraps another and records the errant
     * transform and provides a human readable message about the exception's
     * context.
     * 
     * @param message Text message provided by the programmer about the context
     *            of the error
     * @param throwable the root cause of the exception
     * @param errantTransform transform that's related to the error
     */
    public PAffineTransformException(final String message, final Throwable throwable,
            final PAffineTransform errantTransform) {
        super(message, throwable);
        this.errantTransform = errantTransform;
    }

    /**
     * Used to access the transform related to this exception.
     * 
     * @return transform related to the exception
     */
    public PAffineTransform getErrantTransform() {
        return errantTransform;
    }

}
