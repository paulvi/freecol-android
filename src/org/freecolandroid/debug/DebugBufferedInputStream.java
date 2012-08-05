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

package org.freecolandroid.debug;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DebugBufferedInputStream extends BufferedInputStream {

	public DebugBufferedInputStream(InputStream in, int size) {
		super(in, size);
	}

	@Override
	public synchronized int available() throws IOException {
		return super.available();
	}

	@Override
	public void close() throws IOException {
//		FCLog.log(new Exception("Close called!"));
		// There is something wrong with the JSR-173(?) Streaming XML
		// library that causes it to close the input stream after
		// it finishes reading a message. This way we might leak resources
		// but at least the connection stays up.
//		super.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		super.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		return super.markSupported();
	}

	@Override
	public synchronized int read() throws IOException {
		return super.read();
	}

	@Override
	public synchronized int read(byte[] buffer, int offset, int byteCount)
			throws IOException {
		return super.read(buffer, offset, byteCount);
	}

	@Override
	public synchronized void reset() throws IOException {
		super.reset();
	}

	@Override
	public synchronized long skip(long byteCount) throws IOException {
		return super.skip(byteCount);
	}

	@Override
	public int read(byte[] buffer) throws IOException {
		return super.read(buffer);
	}

}
