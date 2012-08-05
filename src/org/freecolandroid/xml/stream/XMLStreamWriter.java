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

public interface XMLStreamWriter {

	void close() throws XMLStreamException;

	void flush() throws XMLStreamException;

	void writeAttribute(String name, String value) throws XMLStreamException;

	void writeCharacters(String chars) throws XMLStreamException;

	void writeComment(String comment) throws XMLStreamException;

	void writeEndDocument() throws XMLStreamException;

	void writeEndElement() throws XMLStreamException;

	void writeStartDocument(String encoding, String version) throws XMLStreamException;

	void writeStartElement(String name) throws XMLStreamException;

}
