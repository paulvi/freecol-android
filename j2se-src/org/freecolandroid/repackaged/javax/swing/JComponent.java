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



import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.freecolandroid.repackaged.java.awt.Component;
import org.freecolandroid.repackaged.java.awt.Container;
import org.freecolandroid.repackaged.java.awt.Dimension;
import org.freecolandroid.repackaged.java.awt.Font;
import org.freecolandroid.repackaged.java.awt.Graphics;
import org.freecolandroid.repackaged.java.awt.Insets;
import org.freecolandroid.repackaged.java.awt.Point;
import org.freecolandroid.repackaged.java.awt.Rectangle;
import org.freecolandroid.repackaged.java.awt.event.ActionEvent;
import org.freecolandroid.repackaged.java.awt.event.ActionListener;
import org.freecolandroid.repackaged.javax.swing.border.Border;

import android.util.Log;


public class JComponent extends Container {

	public static final int WHEN_ANCESTOR_OF_FOCUSED_COMPONENT = 3;
	public static final int WHEN_FOCUSED = 2;
	public static final int WHEN_IN_FOCUSED_WINDOW = 1;
	
	private Border border;
	
	private List<PropertyChangeListener> propertyChangedListeners = new ArrayList<PropertyChangeListener>();
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangedListeners.add(listener);
	}
	
	public boolean contains(int px, int py) {
		return false;
	}
	
	public boolean contains(Point p) {
		return false;
	}
	
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
	public void firePropertyChange(String propertyName,
            boolean oldValue,
            boolean newValue) {
		for (PropertyChangeListener l : propertyChangedListeners) {
			l.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
		}
	}
	
	public ActionListener getActionForKeyStroke(KeyStroke keyStroke) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Log.w("Help!", "What's going on!?");
			}
		};
	}
	
	public final ActionMap getActionMap() {
		return new ActionMap();
	}
	
	public boolean getAutoscrolls() {
		return true;
	}

	public Border getBorder() {
		return new Border() {

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y,
					int width, int height) {
				// does nothing
				
			}

			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(0, 0, 0, 0);
			}
			
		};
	}

	public Object getClientProperty(String keyTextAntialiasing) {
		return null;
	}

	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public final InputMap getInputMap() {
		return new InputMap();
	}
	
	public final InputMap getInputMap(int condition) {
		return new InputMap();
	}
	
	public Insets getInsets() {
		return new Insets(0, 0, 0, 0);
	}
	
	public String getToolTipText() {
		return "Tooltip!";
	}
	
	public TransferHandler getTransferHandler() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}
	
	public boolean isOpaque() {
		return false; 
	}
	
	protected void paintComponent(Graphics g) {
		// Does nothing
	}
	
	public void paintImmediately(int x,
            int y,
            int w,
            int h) {
		// Does nothing
	}
  
	
	public void paintImmediately(Rectangle r) {
		// Does nothing
	}

	public void registerKeyboardAction(ActionListener anAction,
            KeyStroke aKeyStroke,
            int aCondition) {
		// Does nothing
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangedListeners.remove(listener);
	}
	
	public void repaint(long tm, int x, int y, int width, int height) {
		// Does nothing
	}

	public void repaint(Rectangle r) {
		// Does nothing
	}
	
	public boolean requestFocusInWindow() {
		// Does nothing
		return false;
	}
	
	public void revalidate() {
		// Does nothing
	}

	public void setAutoscrolls(boolean b) {
		// Does nothing
	}
	
	public void setBorder(Border border) {
		this.border = border;
	}
	
	public void setDoubleBuffered(boolean aFlag) {
		// Does nothing
	}
	
	public void setEnabled(boolean enabled) {
		// Does nothing
	}

	public void setFont(Font font) {
		// Does nothing
	}

	public void setOpaque(boolean isOpaque) {
		// Does nothing
	}
	
	public void setPreferredSize(Dimension preferredSize) {
		// Does nothing
	}
	
	public void setToolTipText(String text) {
		// Does nothing
	}

	public void setTransferHandler(Object newHandler) {
		// Does nothing
	}

	public void setVisible(boolean aFlag) {
		// Does nothing
	}

}
