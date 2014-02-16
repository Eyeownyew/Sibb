package com.sibb.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class ImageLoader {

    /**
     * Method loadInterface.
     *
     * @param interfaceName String
     * @return Image
     */
    public static Image loadInterface(String interfaceName) {
        try {
            return ImageIO.read(new File("data/images/interfaces/" + interfaceName
                    + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method loadImage.
     *
     * @param imageName String
     * @return Image
     */
    public static Image loadImage(String imageName) {
        try {
            return ImageIO.read(new File("data/images/" + imageName
                    + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
