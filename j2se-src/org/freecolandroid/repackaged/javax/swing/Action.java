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

import java.beans.PropertyChangeListener;

public interface Action {
	
	public static final String DEFAULT = "default";
	
	public static final String NAME = "name";
	
	public static final String SHORT_DESCRIPTION = "descr";
	
	public static final String LONG_DESCRIPTION = "long_descr";
	
	public static final String SMALL_ICON = "small_icon";
	
	public static final String ACTION_COMMAND_KEY = "command_key";
	
	public static final String ACCELERATOR_KEY = "acc_key";
	
	public static final String MNEMONIC_KEY = "mnem_key";
	
	public Object getValue(String key);
	
	public void putValue(String key,
            Object value);
	
	public void setEnabled(boolean b);
	
	public boolean isEnabled();
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
