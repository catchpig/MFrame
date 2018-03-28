package mejust.frame.upgrade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import conm.zhuazhu.common.utils.FileUtils;
import conm.zhuazhu.common.utils.Utils;
import java.io.File;

/**
 * 创建时间: 2018/03/09 10:38<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 10:38<br>
 * 描述: app下载更新模块，展示app更新进度、状态，完成下载后调用app安装
 * <p/>
 * 示例：
 * new UpgradeAppManager("URL").setProgressManager(
 * ProgressType.DIALOG,new ProgressMessageBuilder(R.mipmap.ic_launcher, "app更新"))
 * .start(this);
 */
public class UpgradeAppManager {

    /* 更新APP下载地址 */
    private String url;
    /* 通知方式 */
    private int progressType;
    /* 进度通知相关参数 */
    private ProgressMessageBuilder progressMessageBuilder;

    public UpgradeAppManager(@NonNull String url) {
        this.url = url;
    }

    public UpgradeAppManager setProgressManager(@ProgressType int progressType,
            ProgressMessageBuilder progressMessageBuilder) {
        this.progressType = progressType;
        this.progressMessageBuilder = progressMessageBuilder;
        return this;
    }

    public void start(@NonNull FragmentActivity context) {
        // 检查通知栏进度显示，是否有权限
        if (progressType == ProgressType.NOTIFICATION && !checkNotificationPermission(context)) {
            showNotificationAsk(context);
            return;
        }
        startDownload(context);
    }

    /**
     * 创建APP下载文件夹
     */
    private File createParentFile() {
        File externalFile = Utils.getApp().getExternalFilesDir("");
        if (externalFile == null) {
            externalFile = new File(Environment.getExternalStorageDirectory(),
                    "/Android/data/" + Utils.getApp().getPackageName() + "/files");
        }
        File parentFile = new File(externalFile, "apk");
        FileUtils.createOrExistsDir(parentFile);
        return parentFile;
    }

    /**
     * 从url地址中获取文件名
     */
    private String getFileName() {
        int index = url.lastIndexOf(File.separator);
        return url.substring(index + 1, url.length());
    }

    /**
     * 选取进度显示样式
     */
    private ProgressHelper getProgressHelper(Context context) {
        ProgressHelper progressHelper;
        switch (progressType) {
            case ProgressType.DIALOG:
                progressHelper = DialogProgress.getInstance(context, progressMessageBuilder);
                break;
            case ProgressType.NOTIFICATION:
                progressHelper = new NotificationProgress(context, progressMessageBuilder);
                break;
            default:
                progressHelper = null;
                break;
        }
        return progressHelper;
    }

    /**
     * 开启IntentService进行下载任务
     */
    private void startDownload(Context context) {
        String path = new File(createParentFile(), getFileName()).getAbsolutePath();
        DownloadResultReceiver resultReceiver =
                new DownloadResultReceiver(getProgressHelper(context));
        UpgradeDownloadService.start(context, url, path, resultReceiver);
    }

    /**
     * 检查是否开启通知栏通知
     */
    private boolean checkNotificationPermission(Context context) {
        boolean ret = true;
        try {
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            ret = manager.areNotificationsEnabled();
        } catch (Exception e) {
            // ignore
        }
        return ret;
    }

    /**
     * 显示获取通知栏权限dialog
     * <p/>
     * 确定：跳转音乐设置
     * 取消：直接开始下载
     */
    private void showNotificationAsk(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("通知权限")
                .setMessage("通知权限的关闭，会影响进度通知信息的显示，是否要打开通知？")
                .setPositiveButton("打开", (dialog, which) -> startAppSetting(context))
                .setNegativeButton("取消", (dialog, which) -> startDownload(context));
        builder.show();
    }

    /**
     * 打开音乐设置界面
     */
    private void startAppSetting(Context context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(localIntent);
        } catch (Exception e) {
            // ignore
        }
    }
}
