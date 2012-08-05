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

import org.freecolandroid.repackaged.java.awt.Dimension;

public class JScrollPane extends JComponent {

	public static final String HORIZONTAL_SCROLLBAR_AS_NEEDED = null;

	public static final String VERTICAL_SCROLLBAR_ALWAYS = null;

	public static final String VERTICAL_SCROLLBAR_AS_NEEDED = null;

	public static final String HORIZONTAL_SCROLLBAR_NEVER = null;

	public static final String VERTICAL_SCROLLBAR_NEVER = null;
	
	public JScrollPane(JPanel listPanel, String verticalScrollbarAlways,
			String horizontalScrollbarAsNeeded) {
		// Does nothing
	}

	public JScrollPane(JTextArea textArea, String verticalScrollbarAsNeeded,
			String horizontalScrollbarNever) {
		// Does nothing
	}

	public JScrollPane(JComponent c) {
		// Does nothing
	}

	public JScrollPane(JComponent log, String verticalScrollbarAsNeeded,
			String horizontalScrollbarNever) {
		// Does nothing
	}

	public JScrollBar getVerticalScrollBar() {
		return new JScrollBar();
	}

	public JComponent getViewport() {
		return this;
	}

	public void setColumnHeaderView(JPanel headerRow) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public JComponent getColumnHeader() {
		return this;
	}

	public void setMinimumSize(Dimension dimension) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

}
