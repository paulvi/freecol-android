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

import org.freecolandroid.repackaged.javax.swing.event.TreeSelectionListener;
import org.freecolandroid.repackaged.javax.swing.tree.DefaultMutableTreeNode;
import org.freecolandroid.repackaged.javax.swing.tree.DefaultTreeCellRenderer;
import org.freecolandroid.repackaged.javax.swing.tree.DefaultTreeModel;
import org.freecolandroid.repackaged.javax.swing.tree.TreePath;

public class JTree extends JComponent {

	private DefaultTreeCellRenderer cellRenderer;
	private TreeSelectionListener listener;

	public JTree(DefaultTreeModel treeModel) {
		// Does nothing
	}

	public void addTreeSelectionListener(TreeSelectionListener colopediaPanel) {
		this.listener = colopediaPanel;
	}

	public void collapsePath(Object parentPath) {
		// Does nothing
	}

	public void expandPath(TreePath newPath) {
		// Does nothing
	}

	public DefaultMutableTreeNode getLastSelectedPathComponent() {
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");
	}

	public TreePath getSelectionPath() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");
	}

	public void scrollPathToVisible(TreePath newPath) {
		// Does nothing
	}

	public void setCellRenderer(
			DefaultTreeCellRenderer colopediaTreeCellRenderer) {
		this.cellRenderer = colopediaTreeCellRenderer;
	}

	public void setRootVisible(boolean b) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");

	}

}
