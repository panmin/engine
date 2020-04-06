// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.util;

import android.content.Context;
import android.os.Build;
import java.io.File;
import io.flutter.embedding.engine.loader.FlutterLoader;

public final class PathUtils {
  public static String getFilesDir(Context applicationContext) {
    return applicationContext.getFilesDir().getPath();
  }

  public static String getDataDirectory(Context applicationContext) {
    return applicationContext.getDir("flutter", Context.MODE_PRIVATE).getPath();
  }

  public static String getCacheDirectory(Context applicationContext) {
    if (Build.VERSION.SDK_INT >= 21) {
      return applicationContext.getCodeCacheDir().getPath();
    } else {
      return applicationContext.getCacheDir().getPath();
    }
  }

  // 获取动态化资源文件路径
  public static String getDynamicPath(Context applicationContext){
    String packagePath = getDataDirectory(applicationContext);
    String aotLibFile = packagePath + File.separator + FlutterLoader.DEFAULT_AOT_SHARED_LIBRARY_NAME;
    String flutterAssetsPath = packagePath + File.separator + FlutterLoader.DEFAULT_FLUTTER_ASSETS_DIR;
    File aotFile = new File(aotLibFile);
    File flutterAssetsFile = new File(flutterAssetsPath);
    if (!aotFile.exists() && !flutterAssetsFile.exists()) {
      packagePath = "";
    }
    return packagePath;
  }
}
