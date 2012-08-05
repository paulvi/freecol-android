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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.freecolandroid.xml.stream.impl.XMLStreamReaderImpl;

public class XMLInputFactory {

	private XMLInputFactory() {
	}

	public static XMLInputFactory newInstance() {
		return new XMLInputFactory();
	}

	public XMLStreamReader createXMLStreamReader(InputStream in)
			throws XMLStreamException {
		return new XMLStreamReaderImpl(new InputStreamReader(in));
	}

	public XMLStreamReader createXMLStreamReader(Reader in)
			throws XMLStreamException {
		return new XMLStreamReaderImpl(in);
	}

	public XMLStreamReader createXMLStreamReader(InputStream in, String encoding)
			throws XMLStreamException {
		return new XMLStreamReaderImpl(new InputStreamReader(in));
	}

}
