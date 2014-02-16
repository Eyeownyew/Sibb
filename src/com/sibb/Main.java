package com.sibb;

import java.awt.*;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Main {
    /**
     *

     */
    public static Window app = null;

    /**
     * Method main.
     *
     * @param argv String[]
     */
    public static void main(String[] argv) {
        boolean borderless = true;
        if (argv.length > 0)
            if (argv[0].startsWith("-e"))
                borderless = false;

        app = new Window("Sibb", (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), borderless);
    }
}
