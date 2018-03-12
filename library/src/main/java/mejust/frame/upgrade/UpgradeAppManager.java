package mejust.frame.upgrade;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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

    public void start(FragmentActivity context) {
        String path = new File(createParentFile(), getFileName()).getAbsolutePath();
        DownloadResultReceiver resultReceiver =
                new DownloadResultReceiver(getProgressHelper(context));
        UpgradeDownloadService.start(context, url, path, resultReceiver);
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
}
