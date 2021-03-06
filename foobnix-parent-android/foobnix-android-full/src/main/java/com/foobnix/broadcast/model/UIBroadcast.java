/* Copyright (c) 2011 Ivan Ivanenko <ivan.ivanenko@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. */
package com.foobnix.broadcast.model;

import java.io.Serializable;

import com.foobnix.model.FModel;

public class UIBroadcast implements Serializable {

	private static final long serialVersionUID = 2470775933444271103L;

	private FModel model;
	private int position;
	private int duration;
	private boolean isPlaying;
	private int buffering;
	private int playlistLen;

	public UIBroadcast(FModel model) {
		this.model = model;

	}

	public UIBroadcast(FModel model, int position, int duration, boolean isPlaying, int bufferring, int playlistSize) {
		this.model = model;
		this.position = position;
		this.duration = duration;
		this.isPlaying = isPlaying;
		this.buffering = bufferring;
		this.playlistLen = playlistSize;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPlaying(boolean isPlaying) {
	    this.isPlaying = isPlaying;
    }
	public boolean isPlaying() {
	    return isPlaying;
    }
	public void setBuffering(int buffering) {
	    this.buffering = buffering;
    }
	public int getBuffering() {
	    return buffering;
    }
	public void setModel(FModel model) {
	    this.model = model;
    }
	public FModel getModel() {
	    return model;
    }

	public void setPlaylistLen(int playlistLen) {
		this.playlistLen = playlistLen;
	}

	public int getPlaylistLen() {
		return playlistLen;
	}
}
