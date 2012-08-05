/**
 *	Copyright (C) 2012   The FreeCol-Android Team
 *
 *  This file is part of FreeCol-Android.
 *
 *  FreeCol-Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol-Android.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.freecolandroid.repackaged.javax.swing;

import org.freecolandroid.repackaged.java.awt.Component;
import org.freecolandroid.repackaged.java.awt.Container;
import org.freecolandroid.repackaged.java.awt.GraphicsConfiguration;
import org.freecolandroid.repackaged.java.awt.Image;
import org.freecolandroid.repackaged.java.awt.Rectangle;
import org.freecolandroid.repackaged.java.awt.event.WindowListener;

public class JFrame extends Component {

	public static final int DO_NOTHING_ON_CLOSE = 1;
	private Rectangle bounds;
	private JMenuBar menu;
	private Container content;
	
	public JFrame(String string) {
		// Does nothing
		content = new Container();
	}
	
	public JFrame(String string, GraphicsConfiguration defaultConfiguration) {
		// Does nothing
		content = new Container();
	}
	
	public JMenuBar getJMenuBar() {
		return menu;
	}
	
	public Container getContentPane() {
		return content;
	}
	
	public void setUndecorated(boolean undecorated) {
		// Does nothing
	}
	
	public void dispose() {
		// Does nothing
	}

	public void addWindowListener(WindowListener l) {
		// Does nothing
	}

	public void setDefaultCloseOperation(int op) {
		// Does nothing
	}
	
	public void setJMenuBar(JMenuBar menubar) {
		this.menu = menubar;
	}
	
	public void setContentPane(Container contentPane) {
		this.content = contentPane;
	}
	
	public void setResizable(boolean resizable) {
		// Does nothing
	}

	public void setBounds(Rectangle windowBounds) {
		this.bounds = windowBounds;
	}

	public void pack() {
		// Does nothing
	}

	public void setIconImage(Image image) {
		// Does nothing
	}

}
