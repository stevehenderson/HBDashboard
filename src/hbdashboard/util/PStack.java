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

import java.util.ArrayList;

/**
 * <b>PStack</b> this class should be removed when a non thread safe stack is
 * added to the java class libraries.
 * <p>
 * 
 *
 * 
 */
public class PStack extends ArrayList {
    /**
     * Allows for future serialization code to understand versioned binary
     * formats.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an empty stack.
     */
    public PStack() {
    }

    /**
     * Pushes the provided object onto the top of the stack.
     * 
     * @param o object to add to the stack
     */
    public void push(final Object o) {
        add(o);
    }

    /**
     * Returns  topmost element on the stack, or null if stack is empty.
     * 
     * @return topmost element on the stack, or null if empty
     */
    public Object peek() {
        final int s = size();
        if (s == 0) {
            return null;
        }
        else {
            return get(s - 1);
        }
    }

    /**
     * Removes top element on the stack and returns it.
     * 
     * @return topmost element on stack.
     */
    public Object pop() {
        return remove(size() - 1);
    }
}