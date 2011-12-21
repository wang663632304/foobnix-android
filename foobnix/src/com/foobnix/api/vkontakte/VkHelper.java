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
package com.foobnix.api.vkontakte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foobnix.model.VkAudio;

public class VkHelper {

	public static VkAudio getMostRelevantSong(List<VkAudio> list) {
		Map<String, Integer> bestTimes = new HashMap<String, Integer>();

		int maxCount = 0;
		VkAudio result = null;

		// find best time
		for (VkAudio model : list) {
			String key = model.getDuration();
			if (bestTimes.containsKey(key)) {
				Integer value = bestTimes.get(key);
				value++;
				bestTimes.put(key, value);

				if (value > maxCount) {
					maxCount = value;
					result = model;
				}

			} else {
				bestTimes.put(key, 1);
			}

		}
		return result;

	}

}
