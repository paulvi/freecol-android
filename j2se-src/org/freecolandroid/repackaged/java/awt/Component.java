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

package org.freecolandroid.repackaged.java.awt;

import java.util.ArrayList;
import java.util.List;

import org.freecolandroid.repackaged.java.awt.event.ComponentListener;
import org.freecolandroid.repackaged.java.awt.event.KeyListener;
import org.freecolandroid.repackaged.java.awt.event.MouseListener;
import org.freecolandroid.repackaged.java.awt.event.MouseMotionListener;

import android.R.dimen;


public class Component {
	
	private Color background = Color.WHITE;
	
	private Color foreground = Color.BLACK;
	
	private List<KeyListener> keyListeners = new ArrayList<KeyListener>();
	
	private List<MouseListener> mouseListeners = new ArrayList<MouseListener>();
	
	private List<MouseMotionListener> mouseMotionListeners = new ArrayList<MouseMotionListener>();
	
	private List<ComponentListener> componentListeners = new ArrayList<ComponentListener>();
	
	private Dimension size = new Dimension(0, 0);
	
	private Container parent;

	public void addComponentListener(ComponentListener l) {
		componentListeners.add(l);
	}
	
	public void addKeyListener(KeyListener l) {
		keyListeners.add(l);
	}
	
	public void addMouseListener(MouseListener l) {
		mouseListeners.add(l);
	}

	public void addMouseMotionListener(MouseMotionListener l) {
		mouseMotionListeners.add(l);
	}
	
	public void dispatchEvent(AWTEvent event) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}
	
	public Color getBackground() {
		return background;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, size.width, size.height);
	}
	
	public Font getFont() {
		return new Font();
	}
	
	public FontMetrics getFontMetrics(Font font) {
		return new FontMetrics(null);
	}
	
	public Color getForeground() {
		return foreground;
	}
	
	public int getHeight() {
		return size.height;
	}
	
	public KeyListener[] getKeyListeners() {
		return (KeyListener[]) keyListeners.toArray();
	}
	
	public Point getLocation() {
		return new Point(0, 0);
	}
	
	public Dimension getMinimumSize() {
		return size;
	}

	public MouseListener[] getMouseListeners() {
		return (MouseListener[]) mouseListeners.toArray();
	}

	public MouseMotionListener[] getMouseMotionListeners() {
		return (MouseMotionListener[]) mouseMotionListeners.toArray();
	}

	public Point getMousePosition() {
		return new Point(0, 0);
	}

	public String getName() {
		return "Component";
	}
	
	public Container getParent() {
		return parent;
	}

	public Dimension getPreferredSize() {
		return size;
	}

	public Dimension getSize() {
		return size;
	}

	public Toolkit getToolkit() {
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public int getWidth() {
		return size.width;
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public boolean hasFocus() {
		return false;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isShowing() {
		return true;
	}

	public boolean isVisible() {
		return true;
	}

	public void paint(Graphics g) {
		// Does nothing
	}

	public void removeKeyListener(KeyListener l) {
		keyListeners.remove(l);
	}

	public void removeMouseListener(MouseListener l) {
		mouseListeners.remove(l);
	}

	public void removeMouseMotionListener(MouseMotionListener l) {
		mouseMotionListeners.remove(l);
	}

	public void repaint() {
		// Does nothing
	}

	public void repaint(int x, int y, int width, int height) {
		// Does nothing
	}

	public void requestFocus() {
		// Does nothing
	}

	public void setBackground(Color c) {
		background = c;
	}

	public void setBounds(int x, int y, int width, int height) {
		size.width = width;
		size.height = height;
	}

	public void setCursor(Cursor cursor) {
		// Does nothing
	}

	public void setEnabled(boolean enabled) {
		// Does nothing
	}

	public void setFocusable(boolean b) {
		// Does nothing
	}

	public void setFocusTraversalKeysEnabled(boolean focusTraversalKeysEnabled) {
		// Does nothing
	}
	
	public void setForeground(Color c) {
		foreground = c;
	}

	public void setLayout(Object mgr) {
		// Does nothing
	}

	public void setLocation(int x, int y) {
		// Does nothing
	}
	
	public void setLocation(Point p) {
		// Does nothing
	}
	
	public void setParent(Container parent) {
		this.parent = parent;
	}

	public void setSize(Dimension d) {
		size.width = d.width;
		size.height = d.height;
	}

	public void setSize(int width, int height) {
		size.width = width;
		size.height = height;
	}

	public void setVisible(boolean b) {
		// Does nothing
	}

	public void validate() {
		// Does nothing
	}
}
