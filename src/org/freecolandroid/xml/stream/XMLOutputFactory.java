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

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.freecolandroid.xml.stream.impl.XMLStreamWriterImpl;

public class XMLOutputFactory {

	private XMLOutputFactory() {
	}

	public static XMLOutputFactory newInstance() {
		return new XMLOutputFactory();
	}

	public XMLStreamWriter createXMLStreamWriter(OutputStream out)
			throws XMLStreamException {
		return new XMLStreamWriterImpl(new OutputStreamWriter(out));
	}

	public XMLStreamWriter createXMLStreamWriter(OutputStream out,
			String encoding) throws XMLStreamException {
		return new XMLStreamWriterImpl(new OutputStreamWriter(out));
	}

	public XMLStreamWriter createXMLStreamWriter(Writer out)
			throws XMLStreamException {
		return new XMLStreamWriterImpl(out);
	}

}
