/**
 * Class containing example code/uses for ANSI escape codes
 */
public class example {
    /**
     * This main function is used to demonstrate the library's functions
     * and how the ANSI control codes effect the display of text
     * @param args None
     * @throws InterruptedException When Thread.sleep() throws an error
     */
    public static void main(String[] args) throws InterruptedException {
        demo1();
//        ANSI_Escape_Code_Creator gen = new ANSI_Escape_Code_Creator();
//        gen.erase_display();
//
//        System.out.println(gen.toEscapedString());
//        System.out.println(gen);
    }

    private static void demo1() throws InterruptedException {
        ANSI_Escape_Code_Creator gen = new ANSI_Escape_Code_Creator();
        gen.erase_display().cursor_position(1,1);
        gen.append("This is a test of the ANCI escape code control characters:").line_feed();
        gen.append("This is some normal text.").line_feed();
        gen.SGR_bold().append("This is some bold text.").SGR_clear().line_feed();
        gen.SGR_italic().append("This is some italic text.").SGR_clear().line_feed();
        gen.SGR_underline().append("This is some underlined text.").SGR_clear().line_feed();
        gen.SGR_doubly_underlined().append("This is some double underlined text.").SGR_clear().line_feed();
        gen.SGR_crossed_out().append("This is some crossed out text.").SGR_clear().line_feed();
        gen.SGR_set_foreground_color("Red").append("This is some red text.").SGR_clear().line_feed();
        gen.SGR_set_foreground_color("Bright White").SGR_set_background_color("Red")
                .append("This is some bright white text on red background.").SGR_clear().line_feed();
        gen.SGR_slow_blink().append("This is some blinking text.").SGR_clear().line_feed();

        System.out.println(gen);

        gen = new ANSI_Escape_Code_Creator();
        gen.cursor_position(28,1).append("\u2554");
        for (int i = 0; i < 100; i++)
            gen.append("\u2550");
        gen.append("\u2557").line_feed().append("\u2551").cursor_forward(100)
                .append("\u2551").line_feed().append("\u255a");
        for (int i = 0; i < 100; i++)
            gen.append("\u2550");
        gen.append("\u255d").cursor_position(29,2);
        System.out.print(gen);

        for (int i = 1; i < 101; i++) {
            gen = new ANSI_Escape_Code_Creator()
                    .append("█").cursor_position(29, 104)
                    .append(i).append("%").cursor_position(29, 2+i);
            System.out.print(gen);
            Thread.sleep(100);
        }
        System.out.println("\n");
    }
}