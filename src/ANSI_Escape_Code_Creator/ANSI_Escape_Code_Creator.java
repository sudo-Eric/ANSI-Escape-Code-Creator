package ANSI_Escape_Code_Creator;

import java.util.HashMap;

/**
 * A class to aid in the creation of strings that use ANSI escape codes
 *
 * @author Eric Heinke (sudo-Eric)
 * @version 1.2.2
 */
@SuppressWarnings("unused")
public class ANSI_Escape_Code_Creator {
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
    private static final HashMap<String, Integer> _4bit_colors_foreground = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1308308999398579339L;

        {
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
        }
    };

    /**
     * Map background color names to color code
     */
    private static final HashMap<String, Integer> _4bit_colors_background = new HashMap<String, Integer>() {
        private static final long serialVersionUID = -8723803463655799231L;

        {
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
        }
    };

    /**
     * Control Sequence Introducer (SCI)
     */
    private static final String CSI = "\033[";

    /**
     * Select Graphics Rendition (SGR)
     */
    private static final String SGR = "m";

    private int colorMode = _4BIT_COLOR;
    private final StringBuilder command;
    private boolean _SGR = false;

    /**
     * The mode to use to display escaped characters (OCT or HEX)
     */
    public String escape_mode = "HEX";

    /**
     * Whether the escape codes should be shortened if possible
     */
    public boolean shorten_codes = false;

    /**
     * Default constructor
     */
    public ANSI_Escape_Code_Creator() {
        this.command = new StringBuilder();
    }

    /**
     * Constructor that sets the initial color mode
     * @param colorMode Color mode
     */
    public ANSI_Escape_Code_Creator(int colorMode) {
        this();
        this.colorMode = colorMode;
    }

    /**
     * Return the generated string
     * @return Generated string
     */
    @Override
    public String toString() {
        if (this._SGR) {
            this.end_SGR();
        }
        return this.command.toString();
    }

    /**
     * Return the generated string with all control codes escaped
     * @return Escaped generated string
     */
    public String toEscapedString() {
        if (this._SGR) {
            this.end_SGR();
        }
        return toPrintableRepresentation(this.toString(), this.escape_mode, this.shorten_codes);
    }

    /**
     * Set the color mode that should be used
     * @param mode Color mode
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator setColorMode(int mode) {
        if (mode == _4BIT_COLOR || mode == _8BIT_COLOR || mode == _24BIT_COLOR) {
            this.colorMode = mode;
        } else {
            System.out.println("ERROR: Invalid color mode.\nValid color modes are 4, 8, and 24");
        }
        return this;
    }

    /**
     * Append a string
     * @param str String to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(String str) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(str);
        return this;
    }

    /**
     * Append a character
     * @param chr Character to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(char chr) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(chr);
        return this;
    }

    /**
     * Append an integer
     * @param integer Integer to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(int integer) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(integer);
        return this;
    }

    /**
     * Append a long
     * @param lng Long to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(long lng) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(lng);
        return this;
    }

    /**
     * Append a float
     * @param flt Float to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(float flt) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(flt);
        return this;
    }

    /**
     * Append a double
     * @param dub Double to be appended
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator append(double dub) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(dub);
        return this;
    }

    /**
     * <p>Bell</p>
     * <p>This will trigger the ASCII bell.</p>
     * <ul>
     *     <li>Windows: this plays the alert sound</li>
     *     <li>OSX: ???</li>
     *     <li>Linux: ???</li>
     * </ul>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator bell() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\7");
        return this;
    }

    /**
     * <p>Backspace</p>
     * <p>This will add the ASCII backspace character<br>
     * Moves the cursor left (but may "backwards wrap" if cursor is at start of line)</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator backspace() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\10");
        return this;
    }

    /**
     * <p>Tab</p>
     * <p>This will add the ASCII tab character<br>
     * Moves the cursor right to next multiple of 8</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator tab() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\11");
        return this;
    }

    /**
     * <p>Line feed</p>
     * <p>This will add the ASCII line feed character<br>
     * Moves to next line, scrolls the display up if at bottom of the screen<br>
     * Usually does not move horizontally, though programs should not rely on this</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator line_feed() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\12");
        return this;
    }

    /**
     * <p>Form feed</p>
     * <p>This will add teh ASCII form feed character<br>
     * Move a printer to top of next page<br>
     * Usually does not move horizontally, though programs should not rely on this</p>
     * <p>Effect on video terminals varies</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator form_feed() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\14");
        return this;
    }

    /**
     * <p>Carriage return</p>
     * <p>This will add the ASCII carriage return character<br>
     * Moves the cursor to column zero</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator carriage_return() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\15");
        return this;
    }

    /**
     * <p>Escape</p>
     * <p>This will add the ASCII escape character<br>
     * Starts all the escape sequences</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator escape() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\33");
        return this;
    }

    private void createCSI(char C) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(CSI).append(C);
    }

    private void createCSI(char C, String n) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(CSI).append(n).append(C);
    }

    private void createCSI(char C, String n, String m) {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append(CSI).append(n).append(';').append(m).append(C);
    }

    /**
     * Move the cursor up one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_up()
    {return cursor_up(1);}

    /**
     * Move the cursor up n lines
     * @param lines Number of lines to move
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_up(int lines) {
        if (lines > -1) {
            createCSI('A', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move the cursor down one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_down()
    {return cursor_down(1);}

    /**
     * Move the cursor down n lines
     * @param lines Number of lines to move down
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_down(int lines) {
        if (lines > -1) {
            createCSI('B', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move the cursor right one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_forward()
    {return cursor_forward(1);}

    /**
     * Move the cursor right n lines
     * @param lines Number of lines to move right
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_forward(int lines) {
        if (lines > -1) {
            createCSI('C', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move the cursor left one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_back()
    {return cursor_back(1);}

    /**
     * Move the cursor left n lines
     * @param lines Number of lines to move left
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_back(int lines) {
        if (lines > -1) {
            createCSI('D', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move the cursor to the next line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_next_line()
    {return cursor_next_line(1);}

    /**
     * Move the cursor down n lines
     * @param lines Number of lines to move
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_next_line(int lines) {
        if (lines > -1) {
            createCSI('E', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move cursor to the previous line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_previous_line()
    {return cursor_previous_line(1);}

    /**
     * Move the cursor up n lines
     * @param lines Number of lines to move
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_previous_line(int lines) {
        if (lines > -1) {
            createCSI('F', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Move cursor to a specific column position
     * @param column Column to move to
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_horizontal_absolute(int column) {
        if (column > -1) {
            createCSI('G', Integer.toString(column));
        }
        return this;
    }

    /**
     * Move cursor to absolute position
     * @param row Row to move to
     * @param column Column to move to
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_position(int row, int column) {
        if (row > -1 && column > -1) {
            createCSI('H', Integer.toString(row), Integer.toString(column));
        }
        return this;
    }

    /**
     * <p>Cursor scroll up</p>
     * <p>Moves cursor one line up, scrolling if needed</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_scroll_up() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\033M");
        return this;
    }

    /**
     * <p>Save cursor position</p>
     * <p>Save cursor position (DEC) Device Control String</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_save_position_DEC() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\0337");
        return this;
    }

    /**
     * <p>Restore cursor position</p>
     * <p>Restores the cursor to the last saved position (DEC) Device Control String</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_restore_position_DEC() {
        if (this._SGR) {
            this.end_SGR();
        }
        this.command.append("\0338");
        return this;
    }

    /**
     * <p>Save cursor position</p>
     * <p>Save cursor position (OSC) Operating System Command</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_save_position_OSC() {
        createCSI('s');
        return this;
    }

    /**
     * <p>Restore cursor position</p>
     * <p>Restores the cursor to the last saved position (SCO) Operating System Command</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_restore_position_OSC() {
        createCSI('u');
        return this;
    }

    /**
     * Make cursor invisible
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_invisible() {
        createCSI('l', "?25");
        return this;
    }

    /**
     * Make cursor visible
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator cursor_visible() {
        createCSI('h', "?25");
        return this;
    }

    /**
     * <p>Erase part or the entirety of the display</p>
     * <p>n = 0 | clear from cursor to end of screen<br>
     * n = 1 | clear from cursor to beginning of the screen<br>
     * n = 2 | clear entire screen<br>
     * n = 3 | delete all lines saved in the scrollback buffer</p>
     * @param n Erasure mode
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator erase_display(int n) {
        if (n > -1 && n < 4) {
            createCSI('J', Integer.toString(n));
        }
        return this;
    }

    /**
     * Erase the entirety of the display (clear entire screen)
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator erase_display() {
        return erase_display(2);
    }

    /**
     * <p>Erase part ot the entirety of the present line</p>
     * <p>n = 0 | clear from cursor to the end of the line<br>
     * n = 1 | clear from cursor to beginning of the line<br>
     * n = 2 | clear entire line</p>
     * @param n Erasure mode
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator erase_line(int n) {
        if (n > -1 && n < 3) {
            createCSI('K', Integer.toString(n));
        }
        return this;
    }

    /**
     * Erasethe entirety of the present line (clear entire line)
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator erase_line() {
        return erase_display(2);
    }

    /**
     * Scroll display up one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator scroll_up()
    {return scroll_up(1);}

    /**
     * Scroll display up n lines
     * @param lines Number of lines to move
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator scroll_up(int lines) {
        if (lines > -1) {
            createCSI('S', Integer.toString(lines));
        }
        return this;
    }

    /**
     * Scroll display down one line
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator scroll_down()
    {return scroll_down(1);}

    /**
     * Scroll display down n lines
     * @param lines Number of lines to move
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator scroll_down(int lines) {
        if (lines > -1) {
            createCSI('T', Integer.toString(lines));
        }
        return this;
    }

    private void begin_SGR() {
        if (this._SGR) {
            this.command.append(";");
        } else {
            this.command.append(CSI);
        }
        this._SGR = true;
    }

    private void end_SGR() {
        this._SGR = false;
        this.command.append(SGR);
    }

    /**
     * Clear all graphical formatting
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_clear() {
        this.begin_SGR();
        this.command.append("0");
        return this;
    }

    /**
     * Bold
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_bold() {
        this.begin_SGR();
        this.command.append("1");
        return this;
    }

    /**
     * Faint (dim)
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_faint() {
        this.begin_SGR();
        this.command.append("2");
        return this;
    }

    /**
     * Dim (faint)
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_dim() {
        return SGR_faint();
    }

    /**
     * Italic
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_italic() {
        this.begin_SGR();
        this.command.append("3");
        return this;
    }

    /**
     * Underline
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_underline() {
        this.begin_SGR();
        this.command.append("4");
        return this;
    }

    /**
     * Slow blink
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_slow_blink() {
        this.begin_SGR();
        this.command.append("5");
        return this;
    }

    /**
     * Rapid blink
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_rapid_blink() {
        this.begin_SGR();
        this.command.append("6");
        return this;
    }

    /**
     * <p>Reverse video (invert)</p>
     * <p>Swap foreground and background colors; inconsistent emulation</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_reverse_video() {
        this.begin_SGR();
        this.command.append("7");
        return this;
    }

    /**
     * <p>Invert (reverse video)</p>
     * <p>Swap foreground and background colors; inconsistent emulation</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_invert()
    {return SGR_reverse_video();}

    /**
     * <p>Conceal (hide)</p>
     * <p>Text will no longer display, but will still be present</p>
     * <p>Not widely supported</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_conceal() {
        this.begin_SGR();
        this.command.append("8");
        return this;
    }

    /**
     * <p>Hide (conceal)</p>
     * <p>Text will no longer display, but will still be present</p>
     * <p>Not widely supported</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_hide()
    {return SGR_conceal();}

    /**
     * <p>Crossed out (strike)</p>
     * <p>Characters legible but marked as if for deletion</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_crossed_out() {
        this.begin_SGR();
        this.command.append("9");
        return this;
    }

    /**
     * <p>Strike (crossed out)</p>
     * <p>Characters legible but marked as if for deletion</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_strike()
    {return SGR_crossed_out();}

    /**
     * <p>Primary font (default font)</p>
     * <p>Use the primary font</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_primary_font() {
        this.begin_SGR();
        this.command.append("10");
        return this;
    }

    /**
     * <p>Default font (primary font)</p>
     * <p>Use the default font</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_default_font()
    {return SGR_primary_font();}

    /**
     * <p>Select alternative font</p>
     * <p>1 &lt;= n &lt;= 9</p>
     * @param n Font number
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_alternative_font(int n) {
        if (n > 0 && n < 10) {
            this.begin_SGR();
            this.command.append(n + 10);
        }
        return this;
    }

    /**
     * <p>Fraktur (Gothic)</p>
     * <p>Rarely supported</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_Fraktur() {
        this.begin_SGR();
        this.command.append("20");
        return this;
    }

    /**
     * <p>Double-underline</p>
     * <p>Disables bold on some terminals</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_doubly_underlined() {
        this.begin_SGR();
        this.command.append("21");
        return this;
    }

    /**
     * Neither bold nor faint; color changes where intensity is implemented as such.
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_normal_intensity() {
        this.begin_SGR();
        this.command.append("22");
        return this;
    }

    /**
     * Neither italic, nor blackletter
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_italic() {
        this.begin_SGR();
        this.command.append("23");
        return this;
    }

    /**
     * Neither singly nor doubly underlined
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_underlined() {
        this.begin_SGR();
        this.command.append("24");
        return this;
    }

    /**
     * Turn blinking off
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_blinking() {
        this.begin_SGR();
        this.command.append("25");
        return this;
    }

    /**
     * Unswap foreground and background colors
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_reversed() {
        this.begin_SGR();
        this.command.append("27");
        return this;
    }

    /**
     * <p>Not concealed</p>
     * <p>Reveal concealed characters</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_reveal() {
        this.begin_SGR();
        this.command.append("28");
        return this;
    }

    /**
     * Not crossed out
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_crossed_out() {
        this.begin_SGR();
        this.command.append("29");
        return this;
    }

    /**
     * <p>Set the foreground color</p>
     * <p>Works with 4-bit, 8-bit, and 24-bit color modes</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_foreground_color(int color) {
        if (this.colorMode == _4BIT_COLOR) {
            if ((color > 29 && color < 38) || (color > 89 && color < 98)) {
                this.begin_SGR();
                this.command.append(color);
            } else {
                throw new EscapeCodeColorError("Unknown color " + color);
            }
        } else if (this.colorMode == _8BIT_COLOR) {
            if (color > -1 && color < 256) {
                this.begin_SGR();
                this.command.append("38;5;").append(color);
            } else {
                throw new EscapeCodeColorError("Unknown color " + color);
            }
        } else {
            this.begin_SGR();
            this.command.append("38;2;").append((color >> 16) & 0xFF).append(';')
                    .append((color >> 8) & 0xFF).append(';')
                    .append(color & 0xFF);
        }
        return this;
    }

    /**
     * <p>Set the foreground color</p>
     * <p>Works with 4-bit and 24-bit color modes</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_foreground_color(String color) {
        if (this.colorMode == _4BIT_COLOR) {
            this.begin_SGR();
            Integer c = _4bit_colors_foreground.get(color);
            if (c == null) {
                throw new EscapeCodeColorError("Not a valid color name '" + color + "'");
            }
            this.command.append(c);
        } else if (this.colorMode == _8BIT_COLOR) {
            throw new EscapeCodeColorModeError("8-bit color can not be set by string");
        } else {
            if (color.length() == 7) {
                if (color.charAt(0) == '#') {
                    color = color.substring(1);
                }
            }
            if (color.length() == 6) {
                try {
                    int c = Integer.parseInt(color, 16);
                    this.begin_SGR();
                    this.command.append("38;2;").append((c >> 16) & 0xFF).append(';')
                            .append((c >> 8) & 0xFF).append(';')
                            .append(c & 0xFF);
                } catch (NumberFormatException e) {
                    throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
                }
            } else {
                throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
            }
        }
        return this;
    }

    /**
     * <p>Set the foreground color</p>
     * <p>Works with 24-bit color mode only</p>
     * @param r Color R
     * @param g Color G
     * @param b Color B
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_foreground_color(int r, int g, int b) {
        if (this.colorMode == _24BIT_COLOR) {
            if (r > -1 && g > -1 && b > -1 && r < 256 && g < 256 && b < 256) {
                this.begin_SGR();
                this.command.append("38;2;").append(r & 0xFF).append(';')
                        .append(g & 0xFF).append(';')
                        .append(b & 0xFF);
            }
            else {
                throw new EscapeCodeColorError("Color values must be in range 0-255");
            }
        } else {
            throw new EscapeCodeColorModeError("Color mode must be 24-bit for setting RGB color values");
        }
        return this;
    }

    /**
     * Restore default foreground color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_default_foreground_color() {
        this.begin_SGR();
        this.command.append("39");
        return this;
    }

    /**
     * <p>Set the background color</p>
     * <p>Works with 4-bit, 8-bit, and 24-bit color modes</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_background_color(int color) {
        if (this.colorMode == _4BIT_COLOR) {
            if ((color > 39 && color < 48) || (color > 99 && color < 108)) {
                this.begin_SGR();
                this.command.append(color);
            } else {
                throw new EscapeCodeColorError("Unknown color " + color);
            }
        } else if (this.colorMode == _8BIT_COLOR) {
            if (color > -1 && color < 256) {
                this.begin_SGR();
                this.command.append("48;5;").append(color);
            } else {
                throw new EscapeCodeColorError("Unknown color " + color);
            }
        } else {
            this.begin_SGR();
            this.command.append("48;2;").append((color >> 16) & 0xFF).append(';')
                    .append((color >> 8) & 0xFF).append(';')
                    .append(color & 0xFF);
        }
        return this;
    }

    /**
     * <p>Set the background color</p>
     * <p>Works with 4-bit and 24-bit color modes</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_background_color(String color) {
        if (this.colorMode == _4BIT_COLOR) {
            this.begin_SGR();
            Integer c = _4bit_colors_background.get(color);
            if (c == null) {
                throw new EscapeCodeColorError("Not a valid color name '" + color + "'");
            }
            this.command.append(c);
        } else if (this.colorMode == _8BIT_COLOR) {
            throw new EscapeCodeColorModeError("8-bit color can not be set by string");
        } else {
            if (color.length() == 7) {
                if (color.charAt(0) == '#') {
                    color = color.substring(1);
                }
            }
            if (color.length() == 6) {
                try {
                    int c = Integer.parseInt(color, 16);
                    this.begin_SGR();
                    this.command.append("48;2;").append((c >> 16) & 0xFF).append(';')
                            .append((c >> 8) & 0xFF).append(';')
                            .append(c & 0xFF);
                } catch (NumberFormatException e) {
                    throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
                }
            } else {
                throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
            }
        }
        return this;
    }

    /**
     * <p>Set the background color</p>
     * <p>Works with 24-bit color mode only</p>
     * @param r Color R
     * @param g Color G
     * @param b Color B
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_background_color(int r, int g, int b) {
        if (this.colorMode == _24BIT_COLOR) {
            if (r > -1 && g > -1 && b > -1 && r < 256 && g < 256 && b < 256) {
                this.begin_SGR();
                this.command.append("48;2;").append(r & 0xFF).append(';')
                        .append(g & 0xFF).append(';')
                        .append(b & 0xFF);
            }
            else {
                throw new EscapeCodeColorError("Color values must be in range 0-255");
            }
        } else {
            throw new EscapeCodeColorModeError("Color mode must be 24-bit for setting RGB color values");
        }
        return this;
    }

    /**
     * Restore default background color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_default_background_color() {
        this.begin_SGR();
        this.command.append("49");
        return this;
    }

    /**
     * Overlined
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_overlined() {
        this.begin_SGR();
        this.command.append("53");
        return this;
    }

    /**
     * Not overlined
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_not_overlined() {
        this.begin_SGR();
        this.command.append("55");
        return this;
    }

    /**
     * <p>Set the underline color</p>
     * <p>Works with 8-bit and 24-bit color modes</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_underline_color(int color) {
        if (this.colorMode == _4BIT_COLOR) {
            throw new EscapeCodeColorModeError("Underline color does not support 4-bit color");
        } else if (this.colorMode == _8BIT_COLOR) {
            if (color > -1 && color < 256) {
                this.begin_SGR();
                this.command.append("58;5;").append(color);
            } else {
                throw new EscapeCodeColorError("Unknown color " + color);
            }
        } else {
            this.begin_SGR();
            this.command.append("58;2;").append((color >> 16) & 0xFF).append(';')
                    .append((color >> 8) & 0xFF).append(';')
                    .append(color & 0xFF);
        }
        return this;
    }

    /**
     * <p>Set the underline color</p>
     * <p>Works with 4-bit color mode only</p>
     * @param color Color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_underline_color(String color) {
        if (this.colorMode == _4BIT_COLOR) {
            throw new EscapeCodeColorModeError("Underline color does not support 4-bit color");
        } else if (this.colorMode == _8BIT_COLOR) {
            throw new EscapeCodeColorModeError("8-bit color can not be set by string");
        } else {
            if (color.length() == 7) {
                if (color.charAt(0) == '#') {
                    color = color.substring(1);
                }
            }
            if (color.length() == 6) {
                try {
                    int c = Integer.parseInt(color, 16);
                    this.begin_SGR();
                    this.command.append("58;2;").append((c >> 16) & 0xFF).append(';')
                            .append((c >> 8) & 0xFF).append(';')
                            .append(c & 0xFF);
                } catch (NumberFormatException e) {
                    throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
                }
            } else {
                throw new EscapeCodeColorError("Not a valid hex color '" + color + "'");
            }
        }
        return this;
    }

    /**
     * <p>Set the underline color</p>
     * <p>Works with 24-bit color mode only</p>
     * @param r Color R
     * @param g Color G
     * @param b Color B
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_set_underline_color(int r, int g, int b) {
        if (this.colorMode == _24BIT_COLOR) {
            if (r > -1 && g > -1 && b > -1 && r < 256 && g < 256 && b < 256) {
                this.begin_SGR();
                this.command.append("58;2;").append(r & 0xFF).append(';')
                        .append(g & 0xFF).append(';')
                        .append(b & 0xFF);
            }
            else {
                throw new EscapeCodeColorError("Color values must be in range 0-255");
            }
        } else {
            throw new EscapeCodeColorModeError("Color mode must be 24-bit for setting RGB color values");
        }
        return this;
    }

    /**
     * Restore default underline color
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_default_underline_color() {
        this.begin_SGR();
        this.command.append("59");
        return this;
    }

    /**
     * <p>Superscript</p>
     * <p>Implemented only in mintty</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_superscript() {
        this.begin_SGR();
        this.command.append("73");
        return this;
    }

    /**
     * <p>Subscript</p>
     * <p>Implemented only in mintty</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_subscript() {
        this.begin_SGR();
        this.command.append("74");
        return this;
    }

    /**
     * <p>Neither superscript nor subscript</p>
     * <p>Implemented only in mintty</p>
     * @return Self reference
     */
    public ANSI_Escape_Code_Creator SGR_normal_script() {
        this.begin_SGR();
        this.command.append("75");
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final char CONTROL_LIMIT = ' ';
    private static final char PRINTABLE_LIMIT = '\u007e';
    private static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String toPrintableRepresentation(String source, String mode, boolean shorten_codes) {
        if (mode.equals("HEX")) {
            return toPrintableRepresentationHex(source, shorten_codes);
        } else if (mode.equals("OCT")) {
            return toPrintableRepresentationOctal(source, shorten_codes);
        } else {
            return source;
        }
    }

    /**
     * Convert a string to an escaped string using hex representation for non-printable characters
     * @param source String to escape
     * @param shorten_codes Whether to exclude leading 0's
     * @return Escaped string
     */
    private static String toPrintableRepresentationHex(String source, boolean shorten_codes) {

        if( source == null ) {
            return null;
        } else {

            final StringBuilder sb = new StringBuilder();
            final int limit = source.length();
            char[] hexbuf = null;

            int pointer = 0;

            sb.append('"');

            while( pointer < limit ) {

                int ch = source.charAt(pointer++);

                switch( ch ) {

                    case '\0': sb.append("\\0"); break;
                    case '\t': sb.append("\\t"); break;
                    case '\n': sb.append("\\n"); break;
                    case '\r': sb.append("\\r"); break;
                    case '\"': sb.append("\\\""); break;
                    case '\\': sb.append("\\\\"); break;

                    default:
                        if( CONTROL_LIMIT <= ch && ch <= PRINTABLE_LIMIT ) {
                            sb.append((char)ch);
                        } else {

                            sb.append("\\u");

                            if( hexbuf == null ) {
                                hexbuf = new char[4];
                            }

                            for( int offs = 4; offs > 0; ) {

                                hexbuf[--offs] = HEX_DIGITS[ch & 0xf];
                                ch >>>= 4;
                            }

                            int offs = 0;
                            if (shorten_codes) {
                                for (; offs < hexbuf.length; offs++) {
                                    if (hexbuf[offs] != '0') {
                                        break;
                                    }
                                }
                            }

                            sb.append(hexbuf, offs, 4-offs);
                        }
                }
            }

            return sb.append('"').toString();
        }
    }

    /**
     * <p>Convert a string to an escaped string using octal representation for non-printable characters</p>
     * <p>It is not recommended to use this function with shorten_codes set to true</p>
     * @param source String to escape
     * @param shorten_codes Whether to exclude leading 0's
     * @return Escaped string
     */
    private static String toPrintableRepresentationOctal(String source, boolean shorten_codes) {

        if( source == null ) {
            return null;
        } else {

            final StringBuilder sb = new StringBuilder();
            final int limit = source.length();
            String octbuf = "";

            int pointer = 0;

            sb.append('"');

            while( pointer < limit ) {

                int ch = source.charAt(pointer++);

                switch( ch ) {

                    case '\0': sb.append("\\0"); break;
                    case '\t': sb.append("\\t"); break;
                    case '\n': sb.append("\\n"); break;
                    case '\r': sb.append("\\r"); break;
                    case '\"': sb.append("\\\""); break;
                    case '\\': sb.append("\\\\"); break;

                    default:
                        if( CONTROL_LIMIT <= ch && ch <= PRINTABLE_LIMIT ) {
                            sb.append((char)ch);
                        } else {
                            octbuf = Integer.toOctalString(ch);
                            if (octbuf.length() < 4) {
                                sb.append("\\");
                                if (shorten_codes) {
                                    sb.append(octbuf);
                                }
                                else {
                                    sb.append(new String(new char[3 - octbuf.length()]).replace("\0", "0"));
                                    sb.append(octbuf);
                                }
                            }
                            else {
                                octbuf = toPrintableRepresentationHex(Character.toString((char)ch), shorten_codes);
                                sb.append(octbuf, 1, octbuf.length()-1);
                            }
                        }
                }
            }

            return sb.append('"').toString();
        }
    }
}

