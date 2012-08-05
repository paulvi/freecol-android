/**
 *  Copyright (C) 2012   The FreeCol-Android Team
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

package org.freecolandroid.xml.stream;

import org.xmlpull.v1.XmlPullParser;

public interface XMLStreamConstants {

	static int CDATA = XmlPullParser.CDSECT;

	static int CHARACTERS = XmlPullParser.TEXT;

	static int COMMENT = XmlPullParser.COMMENT;

	static int END_DOCUMENT = XmlPullParser.END_DOCUMENT;

	static int END_ELEMENT = XmlPullParser.END_TAG;

	static int START_DOCUMENT = XmlPullParser.START_DOCUMENT;

	static int START_ELEMENT = XmlPullParser.START_TAG;

}
