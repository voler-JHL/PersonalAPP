package com.voler.person.app.shortcut;

import android.content.Context;
import android.content.Intent;

import com.voler.person.app.MainActivity;
import com.voler.person.app.R;

/**
 * ShortUtil Created by voler on 2017/6/9.
 * 说明：
 */

public class ShortUtil {
    public static void addShortcut(Context context){
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "我的快捷入口");
//  shortcut.putExtra("duplicate", false); //不允许重复创建
        //指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        //注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
        // ComponentName comp = new ComponentName(this.getPackageName(), "."+this.getLocalClassName());
        // shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(context,MainActivity.class));
        //快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher_);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
       context.sendBroadcast(shortcut);
    }
}
