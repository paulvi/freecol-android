/**
 *	Copyright (C) 2012   The FreeCol-Android Team
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

package org.freecolandroid.repackaged.javax.sound.sampled;

import java.io.BufferedInputStream;

import org.freecolandroid.repackaged.javax.sound.sampled.Mixer.Info;


public class AudioSystem {

	public static Mixer getMixer(Info mixerInfo) {
		return null;
	}

	public static AudioInputStream getAudioInputStream(BufferedInputStream bis) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public static AudioInputStream getAudioInputStream(
			AudioFormat decodedFormat, AudioInputStream in) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public static Mixer.Info[] getMixerInfo() {
		return new Mixer.Info[0];
	}

}
