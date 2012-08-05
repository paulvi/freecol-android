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

package org.freecolandroid.xml.stream.impl;

import java.io.IOException;
import java.io.Reader;

import static org.freecolandroid.xml.stream.XMLStreamConstants.*;

import org.freecolandroid.xml.stream.XMLStreamException;
import org.freecolandroid.xml.stream.XMLStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLStreamReaderImpl implements XMLStreamReader {

	private final XmlPullParser parser;

	public XMLStreamReaderImpl(Reader in) throws XMLStreamException {
		try {
			parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(in);
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public int nextTag() throws XMLStreamException {
		try {
			parser.nextTag();
			return parser.getEventType();
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public boolean hasNext() throws XMLStreamException {
		try {
			return parser.getEventType() != END_DOCUMENT;
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public int next() throws XMLStreamException {
		try {
			return parser.next();
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void close() {
		// Does nothing
	}

	@Override
	public String getAttributeValue(String nameSpace, String name) {
		for (int i = 0; i < parser.getAttributeCount(); i++) {
			if (parser.getAttributeName(i).equals(name)) {
				return parser.getAttributeValue(i);
			}
		}
		return null;
	}

	@Override
	public String getLocalName() {
		return parser.getName();
	}

	@Override
	public int getAttributeCount() {
		return parser.getAttributeCount();
	}

	@Override
	public String getAttributeLocalName(int index) {
		return parser.getAttributeName(index);
	}

	@Override
	public String getAttributeValue(int index) {
		return parser.getAttributeValue(index);
	}

	@Override
	public String getElementText() throws XMLStreamException {
		int eventType = getEventType();
		if (eventType != START_ELEMENT) {
			throw new XMLStreamException("Not at start of element");
		}
		StringBuffer buffer = new StringBuffer();

		eventType = next();
		while (eventType != END_ELEMENT) {
			if (eventType == END_DOCUMENT) {
				throw new XMLStreamException(
						"End of document reached unexpectedly");
			}
			if (eventType == START_ELEMENT) {
				throw new XMLStreamException(
						"Start element not allowed while reading element text");
			}
			buffer.append(parser.getText());
			eventType = next();
		}

		return buffer.toString();
	}

	@Override
	public int getEventType() throws XMLStreamException {
		try {
			return parser.getEventType();
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		}
	}

}
