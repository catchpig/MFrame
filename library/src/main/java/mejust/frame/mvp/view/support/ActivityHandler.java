package mejust.frame.mvp.view.support;

import android.os.Handler;
import android.os.Message;

/**
 * @author wangpeifeng
 * @date 2018/06/01 17:03
 */
public class ActivityHandler extends Handler {

    public static final int HANDLER_MSG_LOADING_DIALOG_OPEN = 0x110;
    public static final int HANDLER_MSG_LOADING_VIEW_OPEN = 0x111;
    public static final int HANDLER_MSG_LOADING_CLOSE = 0x112;

    private ActivityStateViewControl statusViewControl;

    public ActivityHandler(ActivityStateViewControl statusViewControl) {
        this.statusViewControl = statusViewControl;
    }

    @Override
    public void handleMessage(Message msg) {
        if (statusViewControl == null) {
            return;
        }
        switch (msg.what) {
            case HANDLER_MSG_LOADING_DIALOG_OPEN:
                statusViewControl.showLoading(true);
                break;
            case HANDLER_MSG_LOADING_VIEW_OPEN:
                statusViewControl.showLoading(false);
                break;
            case HANDLER_MSG_LOADING_CLOSE:
                statusViewControl.hideLoading();
                break;
            default:
                super.handleMessage(msg);
        }
    }
}
