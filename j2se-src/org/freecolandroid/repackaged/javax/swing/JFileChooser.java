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

import java.io.File;

import org.freecolandroid.repackaged.java.awt.event.ActionListener;
import org.freecolandroid.repackaged.javax.swing.filechooser.FileFilter;


public class JFileChooser extends JComponent {

	public static final Object APPROVE_SELECTION = null;
	public static final Object CANCEL_SELECTION = null;
	public static final String FILES_ONLY = null;
	public static final String OPEN_DIALOG = null;
	public static final String SAVE_DIALOG = null;
	private File selectedFile;

	public JFileChooser(File directory) {
		// Does nothing
	}

	public void addActionListener(ActionListener actionListener) {
		// Does nothing
	}

	public void addChoosableFileFilter(FileFilter fileFilter) {
		// Does nothing
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setAcceptAllFileFilterUsed(boolean b) {
		// Does nothing
	}

	public void setControlButtonsAreShown(boolean b) {
		// Does nothing
	}

	public void setDialogType(String openDialog) {
		// Does nothing
	}

	public void setFileFilter(FileFilter fileFilter) {
		// Does nothing
	}

	public void setFileHidingEnabled(boolean b) {
		// Does nothing
	}

	public void setFileSelectionMode(String filesOnly) {
		// Does nothing
	}

	public void setSelectedFile(File defaultFile) {
		this.selectedFile = defaultFile;
	}

}
