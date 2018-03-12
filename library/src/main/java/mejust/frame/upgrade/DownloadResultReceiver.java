package mejust.frame.upgrade;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import conm.zhuazhu.common.utils.AppUtils;
import conm.zhuazhu.common.utils.Utils;

/**
 * 创建时间: 2018/03/09 17:35<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 17:35<br>
 * 描述:
 */
public class DownloadResultReceiver extends ResultReceiver {

    private ProgressHelper progressHelper;

    public DownloadResultReceiver(@Nullable ProgressHelper progressHelper) {
        super(new Handler(Looper.getMainLooper()));
        this.progressHelper = progressHelper;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (progressHelper == null) {
            return;
        }
        switch (resultCode) {
            case UpgradeDownloadService.DOWNLOAD_RESULT_SUCCESS:
                progressHelper.downloadSuccess();
                String path = resultData.getString(UpgradeDownloadService.DOWNLOAD_RESULT_PATH);
                String authority = Utils.getApp().getPackageName() + ".versionProvider";
                AppUtils.installApp(path, authority);
                break;
            case UpgradeDownloadService.DOWNLOAD_RESULT_FAIL:
                progressHelper.downloadFail();
                break;
            case UpgradeDownloadService.DOWNLOAD_RESULT_LOADING:
                int progress = resultData.getInt(UpgradeDownloadService.DOWNLOAD_RESULT_PROGRESS);
                progressHelper.updateProgress(progress);
                break;
            default:
                break;
        }
    }
}
