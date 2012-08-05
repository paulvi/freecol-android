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

import org.freecolandroid.repackaged.javax.swing.plaf.basic.BasicInternalFrameUI;

public class JInternalFrame extends JComponent {

	public static class JDesktopIcon extends JComponent {

	}

	private JComponent content;
	
	private String name;

	public void dispose() {
		// Does nothing
	}

	public JComponent getContentPane() {
		return content;
	}

	public JDesktopPane getDesktopPane() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");
	}

	public BasicInternalFrameUI getUI() {
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");
	}

	public void pack() {
		// Does nothing
	}

	public void setFrameIcon(Object object) {
		// Does nothing
	}

	public void setName(String simpleName) {
		this.name = simpleName;
	}

	public void setResizable(boolean b) {
		// Does nothing
	}

	public void setSelected(boolean b) {
		// Does nothing
	}

}
