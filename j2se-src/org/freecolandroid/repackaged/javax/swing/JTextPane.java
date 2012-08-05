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

import org.freecolandroid.repackaged.javax.swing.text.DefaultStyledDocument;
import org.freecolandroid.repackaged.javax.swing.text.SimpleAttributeSet;
import org.freecolandroid.repackaged.javax.swing.text.StyledDocument;

public class JTextPane extends JComponent {
	
	private String text;

	public JTextPane(DefaultStyledDocument document) {
		// Does nothing
	}
	
	public JTextPane() {
		// Does nothing
	}

	public StyledDocument getStyledDocument() {
		return new DefaultStyledDocument();
	}
	
	public void setParagraphAttributes(SimpleAttributeSet attr, boolean replace) {
		// Does nothing
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void setEditable(boolean b) {
		// Does nothing
	}

	public void setLogicalStyle(Object style) {
		// Does nothing
	}

	public void setCaretPosition(int i) {
		// Does nothing
	}

}
