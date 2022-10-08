package ANSI_Escape_Code_Creator;

import java.util.HashMap;

/**
 * A set of static functions to print ANSI escape codes for program use
 *
 * @author Eric Heinke (sudo-Eric)
 * @version 1.0.1
 */
public class ANSI_Escape_Code_Creator_Static {
    /**
     * 4-bit color mode
     */
    public static int _4BIT_COLOR = 4;

    /**
     * 8-bit color mode
     */
    public static int _8BIT_COLOR = 8;

    /**
     * 24-bit color mode
     */
    public static int _24BIT_COLOR = 24;

    /**
     * Map foreground color names to color code
     */
    private static final HashMap<String, Integer> _4bit_colors_foreground = new HashMap<>() {{
        put("Black", 30);
        put("Red", 31);
        put("Green", 32);
        put("Yellow", 33);
        put("Blue", 34);
        put("Magenta", 35);
        put("Cyan", 36);
        put("White", 37);
        put("Bright Black", 90);
        put("Bright Red", 91);
        put("Bright Green", 92);
        put("Bright Yellow", 93);
        put("Bright Blue", 94);
        put("Bright Magenta", 95);
        put("Bright Cyan", 96);
        put("Bright White", 97);
    }};

    /**
     * Map background color names to color code
     */
    private static final HashMap<String, Integer> _4bit_colors_background = new HashMap<>() {{
        put("Black", 40);
        put("Red", 41);
        put("Green", 42);
        put("Yellow", 43);
        put("Blue", 44);
        put("Magenta", 45);
        put("Cyan", 46);
        put("White", 47);
        put("Bright Black", 100);
        put("Bright Red", 101);
        put("Bright Green", 102);
        put("Bright Yellow", 103);
        put("Bright Blue", 104);
        put("Bright Magenta", 105);
        put("Bright Cyan", 106);
        put("Bright White", 107);
    }};

    /**
     * Control Sequence Introducer (SCI)
     */
    private static final String CSI = "\033[";

    /**
     * Select Graphics Rendition (SGR)
     */
    private static final String SGR = "m";

    /**
     * <p>Bell</p>
     * <p>This will trigger the ASCII bell.</p>
     * <ul>
     *     <li>Windows: this plays the alert sound</li>
     *     <li>OSX: ???</li>
     *     <li>Linux: ???</li>
     * </ul>
     */
    public static void bell() {
        System.out.print("\7");
    }

    /**
     * <p>Backspace</p>
     * <p>This will add the ASCII backspace character<br>
     * Moves the cursor left (but may "backwards wrap" if cursor is at start of line)</p>
     */
    public static void backspace() {
        System.out.print("\10");
    }

    /**
     * <p>Tab</p>
     * <p>This will add the ASCII tab character<br>
     * Moves the cursor right to next multiple of 8</p>
     */
    public static void tab() {
        System.out.print("\11");
    }

    /**
     * <p>Line feed</p>
     * <p>This will add the ASCII line feed character<br>
     * Moves to next line, scrolls the display up if at bottom of the screen<br>
     * Usually does not move horizontally, though programs should not rely on this</p>
     */
    public static void line_feed() {
        System.out.print("\12");
    }

    /**
     * <p>Form feed</p>
     * <p>This will add teh ASCII form feed character<br>
     * Move a printer to top of next page<br>
     * Usually does not move horizontally, though programs should not rely on this</p>
     * <p>Effect on video terminals varies</p>
     */
    public static void form_feed() {
        System.out.print("\14");
    }

    /**
     * <p>Carriage return</p>
     * <p>This will add the ASCII carriage return character<br>
     * Moves the cursor to column zero</p>
     */
    public static void carriage_return() {
        System.out.print("\15");
    }

    /**
     * <p>Escape</p>
     * <p>This will add the ASCII escape character<br>
     * Starts all the escape sequences</p>
     */
    public static void escape() {
        System.out.print("\33");
    }

    /**
     * Move the cursor up one line
     */
    public static void cursor_up()
    {
        cursor_up(1);
    }

    /**
     * Move the cursor up n lines
     * @param lines Number of lines to move
     */
    public static void cursor_up(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'A');
    }

    /**
     * Move the cursor down one line
     */
    public static void cursor_down()
    {cursor_down(1);}

    /**
     * Move the cursor down n lines
     * @param lines Number of lines to move down
     */
    public static void cursor_down(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'B');
    }

    /**
     * Move the cursor right one line
     */
    public static void cursor_forward()
    {cursor_forward(1);}

    /**
     * Move the cursor right n lines
     * @param lines Number of lines to move right
     */
    public static void cursor_forward(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'C');
    }

    /**
     * Move the cursor left one line
     */
    public static void cursor_back()
    {cursor_back(1);}

    /**
     * Move the cursor left n lines
     * @param lines Number of lines to move left
     */
    public static void cursor_back(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'D');
    }

    /**
     * Move the cursor to the next line
     */
    public static void cursor_next_line()
    {cursor_next_line(1);}

    /**
     * Move the cursor down n lines
     * @param lines Number of lines to move
     */
    public static void cursor_next_line(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'E');
    }

    /**
     * Move cursor to the previous line
     */
    public static void cursor_previous_line()
    {cursor_previous_line(1);}

    /**
     * Move the cursor up n lines
     * @param lines Number of lines to move
     */
    public static void cursor_previous_line(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'F');
    }

    /**
     * Move cursor to a specific column position
     * @param column Column to move to
     */
    public static void cursor_horizontal_absolute(int column) {
        if (column > -1)
            System.out.print(CSI + column + 'G');
    }

    /**
     * Move cursor to absolute position
     * @param row Row to move to
     * @param column Column to move to
     */
    public static void cursor_position(int row, int column) {
        if (row > -1 && column > -1)
            System.out.print(CSI + row + ';' + column + 'H');
    }

    /**
     * <p>Erase part or the entirety of the display</p>
     * <p>n = 0 | clear from cursor to end of screen<br>
     * n = 1 | clear from cursor to beginning of the screen<br>
     * n = 2 | clear entire screen<br>
     * n = 3 | clear entire screen and delete all lines saved in the scrollback buffer</p>
     * @param n Erasure mode
     */
    public static void erase_display(int n) {
        if (n > -1 && n < 4)
            System.out.print(CSI + n + 'J');
    }

    /**
     * Erase the entirety of the display (clear entire screen)
     */
    public static void erase_display() {
        erase_display(2);
    }

    /**
     * <p>Erase part ot the entirety of the present line</p>
     * <p>n = 0 | clear from cursor to the end of the line<br>
     * n = 1 | clear from cursor to beginning of the line<br>
     * n = 2 | clear entire line</p>
     * @param n Erasure mode
     */
    public static void erase_line(int n) {
        if (n > -1 && n < 3)
            System.out.print(CSI + n + 'K');
    }

    /**
     * Erasethe entirety of the present line (clear entire line)
     */
    public static void erase_line() {
        erase_display(2);
    }

    /**
     * Scroll display up one line
     */
    public static void scroll_up()
    {
        scroll_up(1);
    }

    /**
     * Scroll display up n lines
     * @param lines Number of lines to move
     */
    public static void scroll_up(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'S');
    }

    /**
     * Scroll display down one line
     */
    public static void scroll_down()
    {
        scroll_down(1);
    }

    /**
     * Scroll display down n lines
     * @param lines Number of lines to move
     */
    public static void scroll_down(int lines) {
        if (lines > -1)
            System.out.print(CSI + lines + 'T');
    }

    /**
     * Clear all graphical formatting
     */
    public static void SGR_clear() {
        System.out.print(CSI + '0' + SGR);
    }

    /**
     * Bold
     */
    public static void SGR_bold() {
        System.out.print(CSI + '1' + SGR);
    }

    /**
     * Faint (dim)
     */
    public static void SGR_faint() {
        System.out.print(CSI + '2' + SGR);
    }

    /**
     * Dim (faint)
     */
    public static void SGR_dim() {
        SGR_faint();
    }

    /**
     * Italic
     */
    public static void SGR_italic() {
        System.out.print(CSI + '3' + SGR);
    }

    /**
     * Underline
     */
    public static void SGR_underline() {
        System.out.print(CSI + '4' + SGR);
    }

    /**
     * Slow blink
     */
    public static void SGR_slow_blink() {
        System.out.print(CSI + '5' + SGR);
    }

    /**
     * Rapid blink
     */
    public static void SGR_rapid_blink() {
        System.out.print(CSI + '6' + SGR);
    }

    /////////

    /**
     * <p>Reverse video (invert)</p>
     * <p>Swap foreground and background colors; inconsistent emulation</p>
     */
    public static void SGR_reverse_video() {
        System.out.print(CSI + '7' + SGR);
    }

    /**
     * <p>Invert (reverse video)</p>
     * <p>Swap foreground and background colors; inconsistent emulation</p>
     */
    public static void SGR_invert()
    {
        SGR_reverse_video();
    }

    /**
     * <p>Conceal (hide)</p>
     * <p>Text will no longer display, but will still be present</p>
     * <p>Not widely supported</p>
     */
    public static void SGR_conceal() {
        System.out.print(CSI + '8' + SGR);
    }

    /**
     * <p>Hide (conceal)</p>
     * <p>Text will no longer display, but will still be present</p>
     * <p>Not widely supported</p>
     */
    public static void SGR_hide()
    {
        SGR_conceal();
    }

    /**
     * <p>Crossed out (strike)</p>
     * <p>Characters legible but marked as if for deletion</p>
     */
    public static void SGR_crossed_out() {
        System.out.print(CSI + '9' + SGR);
    }

    /**
     * <p>Strike (crossed out)</p>
     * <p>Characters legible but marked as if for deletion</p>
     */
    public static void SGR_strike()
    {
        SGR_crossed_out();
    }

    /**
     * <p>Primary font (default font)</p>
     * <p>Use the primary font</p>
     */
    public static void SGR_primary_font() {
        System.out.print(CSI + "10" + SGR);
    }

    /**
     * <p>Default font (primary font)</p>
     * <p>Use the default font</p>
     */
    public static void SGR_default_font()
    {
        SGR_primary_font();
    }

    /**
     * <p>Select alternative font</p>
     * <p>1 &lt;= n &lt;= 9</p>
     * @param n Font number
     */
    public static void SGR_alternative_font(int n) {
        if (n > 0 && n < 10)
            System.out.print(CSI + (n+10) + SGR);
    }

    /**
     * <p>Fraktur (Gothic)</p>
     * <p>Rarely supported</p>
     */
    public static void SGR_Fraktur() {
        System.out.print(CSI + "20" + SGR);
    }

    /**
     * <p>Double-underline</p>
     * <p>Disables bold on some terminals</p>
     */
    public static void SGR_doubly_underlined() {
        System.out.print(CSI + "21" + SGR);
    }

    /**
     * Neither bold nor faint; color changes where intensity is implemented as such.
     */
    public static void SGR_normal_intensity() {
        System.out.print(CSI + "22" + SGR);
    }

    /**
     * Neither italic, nor blackletter
     */
    public static void SGR_not_italic() {
        System.out.print(CSI + "23" + SGR);
    }

    /**
     * Neither singly nor doubly underlined
     */
    public static void SGR_not_underlined() {
        System.out.print(CSI + "24" + SGR);
    }

    /**
     * Turn blinking off
     */
    public static void SGR_not_blinking() {
        System.out.print(CSI + "25" + SGR);
    }

    /**
     * Unswap foreground and background colors
     */
    public static void SGR_not_reversed() {
        System.out.print(CSI + "27" + SGR);
    }

    /**
     * <p>Not concealed</p>
     * <p>Reveal concealed characters</p>
     */
    public static void SGR_reveal() {
        System.out.print(CSI + "28" + SGR);
    }

    /**
     * Not crossed out
     */
    public static void SGR_not_crossed_out() {
        System.out.print(CSI + "29" + SGR);
    }
}
