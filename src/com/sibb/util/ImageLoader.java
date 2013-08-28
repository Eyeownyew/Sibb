package com.sibb.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {

	public static Image loadInterface(String interfaceName) {
		try {
			return new Image("data/images/interfaces/"+interfaceName+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}
