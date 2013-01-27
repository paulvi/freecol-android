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

import java.io.IOException;
import java.io.InputStream;

public class DebugInputstream extends InputStream {

    private final InputStream in;

    private final String threadName;

    public DebugInputstream(InputStream in, String threadName) {
        this.in = in;
        this.threadName = threadName;
    }

    @Override
    public int read() throws IOException {
        // FCLog.log(threadName + ".read");
        return in.read();
    }

    @Override
    public int available() throws IOException {
        // FCLog.log(threadName + ".available");
        return in.available();
    }

    @Override
    public void close() throws IOException {
        // FCLog.log(threadName + ".close");
        in.close();
    }

    @Override
    public void mark(int readlimit) {
        // FCLog.log(threadName + ".mark");
        in.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        // FCLog.log(threadName + ".marksupported");
        return in.markSupported();
    }

    @Override
    public int read(byte[] buffer, int offset, int length) throws IOException {
        // FCLog.log(threadName + ".read1");
        return in.read(buffer, offset, length);
    }

    @Override
    public int read(byte[] buffer) throws IOException {
        // FCLog.log(threadName + ".read2");
        return in.read(buffer);
    }

    @Override
    public synchronized void reset() throws IOException {
        // FCLog.log(threadName + ".reset");
        in.reset();
    }

    @Override
    public long skip(long byteCount) throws IOException {
        // FCLog.log(threadName + ".skip");
        return in.skip(byteCount);
    }

}
