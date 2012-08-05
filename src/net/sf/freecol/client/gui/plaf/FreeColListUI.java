/**
 *  Copyright (C) 2002-2012   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
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
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.freecol.client.gui.plaf;

import org.freecolandroid.repackaged.java.awt.Graphics;
import org.freecolandroid.repackaged.javax.swing.JComponent;
import org.freecolandroid.repackaged.javax.swing.JList;
import org.freecolandroid.repackaged.javax.swing.ListCellRenderer;
import org.freecolandroid.repackaged.javax.swing.plaf.ComponentUI;
import org.freecolandroid.repackaged.javax.swing.plaf.basic.BasicListUI;



import net.sf.freecol.client.gui.ImageLibrary;


/**
 * UI-class for lists, such as the drop down list of colonies in the
 * Colony panel.
 */
public class FreeColListUI extends BasicListUI {

    public static ComponentUI createUI(JComponent c) {
        return new FreeColListUI();
    }


    public void installUI(JComponent c) {
        super.installUI(c);
        ((JList) c).setCellRenderer(createRenderer());
    }

    public void paint(Graphics g, JComponent c) {
        ImageLibrary.drawTiledImage("background.FreeColList", g, c, null);
        LAFUtilities.setProperties(g, c);
        super.paint(g, c);
    }

    protected ListCellRenderer createRenderer() {
        return new FreeColComboBoxRenderer();
    }
}
