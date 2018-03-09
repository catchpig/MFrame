package mejust.frame.upgrade;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import conm.zhuazhu.common.utils.FileUtils;
import conm.zhuazhu.common.utils.Utils;
import java.io.File;
import mejust.frame.utils.log.Logger;

/**
 * 创建时间: 2018/03/09 10:38<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 10:38<br>
 * 描述:
 */
public class UpgradeAppManager {

    /* 更新APP下载地址 */
    private String url;
    /* 是否显示通知栏进度 */
    private boolean isShowNotification;
    /* 是否显示dialog进度 */
    private boolean isShowDialog;
    /* 进度通知相关参数 */
    private ProgressMessageBuilder progressMessageBuilder;

    public UpgradeAppManager(@NonNull String url) {
        this.url = url;
    }

    public UpgradeAppManager setNotificationProgress(boolean isShowNotification,
            ProgressMessageBuilder progressMessageBuilder) {
        this.isShowNotification = isShowNotification;
        this.progressMessageBuilder = progressMessageBuilder;
        return this;
    }

    public UpgradeAppManager setDialogProgress(boolean isShowDialog,
            ProgressMessageBuilder progressMessageBuilder) {
        this.isShowDialog = isShowDialog;
        this.progressMessageBuilder = progressMessageBuilder;
        return this;
    }

    public void start(Context context) {
        String path = new File(createParentFile(), getFileName()).getAbsolutePath();
        // TODO: 2018/03/09 这里放入
        DownloadResultReceiver resultReceiver = new DownloadResultReceiver(null);
        DownloadIntentService.start(context, url, path, resultReceiver);
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
        boolean b = FileUtils.createOrExistsDir(parentFile);
        Logger.d("apk下载文件夹创建--" + b + ",path:" + parentFile.getAbsolutePath());
        return parentFile;
    }

    /**
     * 从url地址中获取文件名
     */
    private String getFileName() {
        int index = url.lastIndexOf(File.separator);
        return url.substring(index + 1, url.length());
    }
}
