package mejust.frame.upgrade;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;

/**
 * 创建时间: 2018/03/09 17:35<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 17:35<br>
 * 描述:
 */
public class DownloadResultReceiver extends ResultReceiver {

    private ProgressHelper progressHelper;

    public DownloadResultReceiver(ProgressHelper progressHelper) {
        super(new Handler(Looper.getMainLooper()));
        this.progressHelper = progressHelper;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadIntentService.DOWNLOAD_RESULT_SUCCESS:
                progressHelper.downloadSuccess();
                break;
            case DownloadIntentService.DOWNLOAD_RESULT_FAIL:
                progressHelper.downloadFail();
                break;
            case DownloadIntentService.DOWNLOAD_RESULT_LOADING:
                int progress = resultData.getInt(DownloadIntentService.DOWNLOAD_RESULT_PROGRESS);
                progressHelper.updateProgress(progress);
                break;
            default:
                break;
        }
    }
}
