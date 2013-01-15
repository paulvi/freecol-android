package org.freecolandroid.ui.colony;

import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.model.WorkLocation;

public interface OnUnitLocationUpdatedListener {
    
    void unitLocationUpdated(Unit unit, WorkLocation location);

}
