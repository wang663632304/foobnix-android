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
package com.foobnix.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;

import com.foobnix.R;
import com.foobnix.util.pref.Pref;
import com.foobnix.util.pref.Prefs;

public class VersionHelper {

    public static String getDownloadTo(Context context) {
		String def = Environment.getExternalStorageDirectory().getPath() + Res.get(context, R.string.DOWLOAD_TO);
		return Pref.getStr(context, Prefs.DOWNLOAD_TO, def);
    }

    public static String getFoobnixVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("com.foobnix", 0);
            return String.format("%s: %s %s: %s", context.getString(R.string.Version), info.versionName,
                    context.getString(R.string.Code), info.versionCode);
        } catch (NameNotFoundException e) {
            LOG.e("version", e);
            return "version undefined";
        }
    }

}
