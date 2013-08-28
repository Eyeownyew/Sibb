package com.sibb.visual;

public class Panel {
	int maxHeight, currentHeight = 0, initialHeight = 0;

	public Panel(int maxHeight, int initialHeight) {
		this.maxHeight = maxHeight;
		this.currentHeight = initialHeight;
		this.initialHeight = initialHeight;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getInitialHeight() {
		return initialHeight;
	}

	public void setInitialHeight(int initialHeight) {
		this.initialHeight = initialHeight;
	}
}
