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

public class JLabel extends JComponent {
	
	public static final int BOTTOM = 4;
	public static final int CENTER = 1;
	public static final int LEADING = 3;
	public static final int LEFT = 2;
	public static final int RIGHT = 0;
	public static final int TRAILING = 5;
	
	private Icon icon;
	
	private Icon disabledIcon;
	
	private String text;
	
	private Component labelFor;

	public JLabel() {
		// Does nothing
	}

	public JLabel(ImageIcon imageIcon) {
		icon = imageIcon;
	}
	
	public JLabel(String text) {
		this.text = text;
	}
	
	public JLabel(String text, ImageIcon icon, int flags) {
		this.text = text;
		this.icon = icon;
	}
	
	public JLabel(String text, int flags) {
		this.text = text;
	}

	public Icon getDisabledIcon() {
		return disabledIcon;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getText() {
		return text;
	}

	public void setDisabledIcon(Icon disabledIcon) {
		this.disabledIcon = disabledIcon;
	}

	public void setHorizontalAlignment(int trailing) {
		// Does nothing
	}

	public void setHorizontalTextPosition(int pos) {
		// Does nothing
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void setIconTextGap(int i) {
		// Does nothing
	}

	public void setLabelFor(Component component) {
		labelFor = component;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setVerticalTextPosition(int textPosition) {
		// Does nothing
	}

}
