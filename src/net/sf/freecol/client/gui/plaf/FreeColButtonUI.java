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

import org.freecolandroid.repackaged.java.awt.AlphaComposite;
import org.freecolandroid.repackaged.java.awt.Color;
import org.freecolandroid.repackaged.java.awt.Composite;
import org.freecolandroid.repackaged.java.awt.Dimension;
import org.freecolandroid.repackaged.java.awt.Graphics;
import org.freecolandroid.repackaged.java.awt.Graphics2D;
import org.freecolandroid.repackaged.java.awt.MouseInfo;
import org.freecolandroid.repackaged.java.awt.Point;
import org.freecolandroid.repackaged.javax.swing.AbstractButton;
import org.freecolandroid.repackaged.javax.swing.JComponent;
import org.freecolandroid.repackaged.javax.swing.SwingUtilities;
import org.freecolandroid.repackaged.javax.swing.plaf.ComponentUI;
import org.freecolandroid.repackaged.javax.swing.plaf.metal.MetalButtonUI;



import net.sf.freecol.client.gui.ImageLibrary;


/**
 * Sets the default opaque attribute to <i>false</i> and
 * uses a 10% black shading on the {@link #paintButtonPressed}.
 */
public class FreeColButtonUI extends MetalButtonUI {

    private static FreeColButtonUI sharedInstance = new FreeColButtonUI();


    public static ComponentUI createUI(JComponent c) {
        return sharedInstance;
    }

    public void installUI(JComponent c) {
        super.installUI(c);

        c.setOpaque(false);
    }

    public void paint(Graphics g, JComponent c) {
        LAFUtilities.setProperties(g, c);

        if (c.isOpaque()) {
            ImageLibrary.drawTiledImage("background.FreeColButton", g, c, null);
        }
        super.paint(g, c);

        AbstractButton a = (AbstractButton) c;
        if (a.isRolloverEnabled()) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, c);
            boolean rollover = c.contains(p);
            if (rollover) {
                paintButtonPressed(g, (AbstractButton) c);
            }
        }
    }

    protected void paintButtonPressed(Graphics g, AbstractButton c) {
        if (c.isContentAreaFilled()) {
            Graphics2D g2d = (Graphics2D) g;
            Dimension size = c.getSize();
            Composite oldComposite = g2d.getComposite();
            Color oldColor = g2d.getColor();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, size.width, size.height);
            g2d.setComposite(oldComposite);
            g2d.setColor(oldColor);

        }
    }
}
