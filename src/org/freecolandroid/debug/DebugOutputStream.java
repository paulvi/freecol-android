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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DebugOutputStream extends OutputStream {

    private final OutputStream out;

    private ByteArrayOutputStream buffer;

    public DebugOutputStream(OutputStream out) {
        this.out = out;
        buffer = new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
        out.close();
        // System.out.println("CLOSE - Buffer contents = " + buffer.toString());
        buffer = new ByteArrayOutputStream();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
        // System.out.println("FLUSH - Buffer contents = " + buffer.toString());
        buffer = new ByteArrayOutputStream();
    }

    @Override
    public void write(byte[] buffer, int offset, int count) throws IOException {
        out.write(buffer, offset, count);
        this.buffer.write(buffer, offset, count);
    }

    @Override
    public void write(byte[] buffer) throws IOException {
        out.write(buffer);
        this.buffer.write(buffer);
    }

    @Override
    public void write(int oneByte) throws IOException {
        out.write(oneByte);
        buffer.write(oneByte);
    }

}
