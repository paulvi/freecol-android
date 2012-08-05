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
import java.io.Writer;
import java.util.Stack;

import org.freecolandroid.xml.stream.XMLStreamException;
import org.freecolandroid.xml.stream.XMLStreamWriter;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class XMLStreamWriterImpl implements XMLStreamWriter {

	private final XmlSerializer serializer;

	private final Stack<String> tagStack = new Stack<String>();

	public XMLStreamWriterImpl(Writer out) throws XMLStreamException {
		try {
			serializer = XmlPullParserFactory.newInstance().newSerializer();
			serializer.setOutput(out);
		} catch (XmlPullParserException e) {
			throw new XMLStreamException(e);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void close() throws XMLStreamException {
		flush();
	}

	@Override
	public void flush() throws XMLStreamException {
		try {
			serializer.flush();
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeAttribute(String name, String value)
			throws XMLStreamException {
		try {
			serializer.attribute(null, name, value);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeCharacters(String chars) throws XMLStreamException {
		try {
			serializer.text(chars);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeEndElement() throws XMLStreamException {
		try {
			serializer.endTag(null, tagStack.pop());
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeStartElement(String name) throws XMLStreamException {
		try {
			serializer.startTag(null, name);
			tagStack.push(name);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeComment(String comment) throws XMLStreamException {
		try {
			serializer.comment(comment);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeEndDocument() throws XMLStreamException {
		try {
			serializer.endDocument();
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

	@Override
	public void writeStartDocument(String encoding, String version)
			throws XMLStreamException {
		try {
			serializer.startDocument(encoding, true);
		} catch (IllegalArgumentException e) {
			throw new XMLStreamException(e);
		} catch (IllegalStateException e) {
			throw new XMLStreamException(e);
		} catch (IOException e) {
			throw new XMLStreamException(e);
		}
	}

}
