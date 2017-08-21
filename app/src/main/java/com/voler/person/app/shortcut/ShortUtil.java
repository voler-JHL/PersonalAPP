package com.voler.person.app.shortcut;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.voler.person.app.home.MainActivity;
import com.voler.person.app.R;

/**
 * ShortUtil Created by voler on 2017/6/9.
 * 说明：
 */

public class ShortUtil {
    public static void addShortcut(Context context) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "我的快捷入口");
        shortcut.putExtra("duplicate", false); //不允许重复创建
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);//设置第一个页面
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        //指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        //注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
        // ComponentName comp = new ComponentName(this.getPackageName(), "."+this.getLocalClassName());
//         shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
//        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(context, MainActivity.class));
        //快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher_);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        context.sendBroadcast(shortcut);
    }

    public static void toggleFlowEntrance(Context context, Class launcherClass) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, launcherClass);
        int res = packageManager.getComponentEnabledSetting(componentName);
        if (res == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT ||
                res == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            // 隐藏应用图标
            packageManager.setComponentEnabledSetting(
                    componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        } else {
            // 显示应用图标
            packageManager.setComponentEnabledSetting(
                    componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                    PackageManager.DONT_KILL_APP);
        }
    }
}
//
//    /**
//            * 更新桌面快捷方式图标，不一定所有图标都有效<br/>
//            * 如果快捷方式不存在，则不更新<br/>.
//            */
//    public static void updateShortcutIcon(Context context, String title, Intent intent,Bitmap bitmap) {
//        if(bitmap==null){
////            XLog.i(TAG, "update shortcut icon,bitmap empty");
//            return;
//        }
//        try{
//            final ContentResolver cr = context.getContentResolver();
//            StringBuilder uriStr = new StringBuilder();
//            String urlTemp="";
//            String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
//            if(authority==null||authority.trim().equals("")){
//                authority = LauncherUtil.getAuthorityFromPermission(context,LauncherUtil.getCurrentLauncherPackageName(context)+".permission.READ_SETTINGS");
//            }
//            uriStr.append("content://");
//            if (TextUtils.isEmpty(authority)) {
//                int sdkInt = android.os.Build.VERSION.SDK_INT;
//                if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
//                    uriStr.append("com.android.launcher.settings");
//                } else if (sdkInt < 19) {// Android 4.4以下
//                    uriStr.append("com.android.launcher2.settings");
//                } else {// 4.4以及以上
//                    uriStr.append("com.android.launcher3.settings");
//                }
//            } else {
//                uriStr.append(authority);
//            }
//            urlTemp=uriStr.toString();
//            uriStr.append("/favorites?notify=true");
//            Uri uri = Uri.parse(uriStr.toString());
//            Cursor c = cr.query(uri, new String[] {"_id", "title", "intent" },
//                    "title=?  and intent=? ",
//                    new String[] { title, intent.toUri(0) }, null);
//            int index=-1;
//            if (c != null && c.getCount() > 0) {
//                c.moveToFirst();
//                index=c.getInt(0);//获得图标索引
//                ContentValues cv=new ContentValues();
//                cv.put("icon", flattenBitmap(bitmap));
//                Uri uri2=Uri.parse(urlTemp+"/favorites/"+index+"?notify=true");
//                int i=context.getContentResolver().update(uri2, cv, null,null);
//                context.getContentResolver().notifyChange(uri,null);//此处不能用uri2，是个坑
////                XLog.i(TAG, "update ok: affected "+i+" rows,index is"+index);
//            }else{
////                XLog.i(TAG, "update result failed");
//            }
//            if (c != null && !c.isClosed()) {
//                c.close();
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
////            XLog.i(TAG, "update shortcut icon,get errors:"+ex.getMessage());
//        }
//    }
//    private static byte[] flattenBitmap(Bitmap bitmap) {
//        // Try go guesstimate how much space the icon will take when serialized
//        // to avoid unnecessary allocations/copies during the write.
//        int size = bitmap.getWidth() * bitmap.getHeight() * 4;
//        ByteArrayOutputStream out = new ByteArrayOutputStream(size);
//        try {
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//            out.flush();
//            out.close();
//            return out.toByteArray();
//        } catch (IOException e) {
////            XLog.ReportParameter(TAG, "Could not write icon");
//            return null;
//        }
//    }
//
//    /**
//     * 检查快捷方式是否存在 <br/>
//     * <font color=red>注意：</font> 有些手机无法判断是否已经创建过快捷方式<br/>
//     * 因此，在创建快捷方式时，请添加<br/>
//     * shortcutIntent.putExtra("duplicate", false);// 不允许重复创建<br/>
//     * 最好使用{@link #isShortCutExist(Context, String, Intent)}
//     * 进行判断，因为可能有些应用生成的快捷方式名称是一样的的<br/>
//     * 此处需要在AndroidManifest.xml中配置相关的桌面权限信息<br/>
//     * 错误信息已捕获<br/>
//     */
//    public static boolean isShortCutExist(Context context, String title) {
//        boolean result = false;
//        try {
//            final ContentResolver cr = context.getContentResolver();
//            StringBuilder uriStr = new StringBuilder();
//            String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
//            if(authority==null||authority.trim().equals("")){
//                authority = LauncherUtil.getAuthorityFromPermission(context,LauncherUtil.getCurrentLauncherPackageName(context)+".permission.READ_SETTINGS");
//            }
//            uriStr.append("content://");
//            if (TextUtils.isEmpty(authority)) {
//                int sdkInt = android.os.Build.VERSION.SDK_INT;
//                if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
//                    uriStr.append("com.android.launcher.settings");
//                } else if (sdkInt < 19) {// Android 4.4以下
//                    uriStr.append("com.android.launcher2.settings");
//                } else {// 4.4以及以上
//                    uriStr.append("com.android.launcher3.settings");
//                }
//            } else {
//                uriStr.append(authority);
//            }
//            uriStr.append("/favorites?notify=true");
//            Uri uri = Uri.parse(uriStr.toString());
//            Cursor c = cr.query(uri, new String[] { "title" },
//                    "title=? ",
//                    new String[] { title }, null);
//            if (c != null && c.getCount() > 0) {
//                result = true;
//            }
//            if (c != null && !c.isClosed()) {
//                c.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result=false;
//        }
//        return result;
//    }
//    /**
//     * 不一定所有的手机都有效，因为国内大部分手机的桌面不是系统原生的<br/>
//     * 更多请参考{@link #isShortCutExist(Context, String)}<br/>
//     * 桌面有两种，系统桌面(ROM自带)与第三方桌面，一般只考虑系统自带<br/>
//     * 第三方桌面如果没有实现系统响应的方法是无法判断的，比如GO桌面<br/>
//     * 此处需要在AndroidManifest.xml中配置相关的桌面权限信息<br/>
//     * 错误信息已捕获<br/>
//     */
//    public static boolean isShortCutExist(Context context, String title, Intent intent) {
//        boolean result = false;
//        try{
//            final ContentResolver cr = context.getContentResolver();
//            StringBuilder uriStr = new StringBuilder();
//            String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
//            if(authority==null||authority.trim().equals("")){
//                authority = LauncherUtil.getAuthorityFromPermission(context,LauncherUtil.getCurrentLauncherPackageName(context)+".permission.READ_SETTINGS");
//            }
//            uriStr.append("content://");
//            if (TextUtils.isEmpty(authority)) {
//                int sdkInt = android.os.Build.VERSION.SDK_INT;
//                if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
//                    uriStr.append("com.android.launcher.settings");
//                } else if (sdkInt < 19) {// Android 4.4以下
//                    uriStr.append("com.android.launcher2.settings");
//                } else {// 4.4以及以上
//                    uriStr.append("com.android.launcher3.settings");
//                }
//            } else {
//                uriStr.append(authority);
//            }
//            uriStr.append("/favorites?notify=true");
//            Uri uri = Uri.parse(uriStr.toString());
//            Cursor c = cr.query(uri, new String[] { "title", "intent" },
//                    "title=?  and intent=?",
//                    new String[] { title, intent.toUri(0) }, null);
//            if (c != null && c.getCount() > 0) {
//                result = true;
//            }
//            if (c != null && !c.isClosed()) {
//                c.close();
//            }
//        }catch(Exception ex){
//            result=false;
//            ex.printStackTrace();
//        }
//        return result;
//    }
//}
