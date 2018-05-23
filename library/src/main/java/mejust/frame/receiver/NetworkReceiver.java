package mejust.frame.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import conm.zhuazhu.common.utils.NetworkUtils;

/**
 * 创建时间:2018-01-25 9:57<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018-01-25 9:57<br/>
 * 描述:网络监听接收器
 */
public class NetworkReceiver extends BroadcastReceiver {

    public OnNetworkListener mOnNetworkListener;

    public void setOnNetworkListener(OnNetworkListener listener) {
        mOnNetworkListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        switch (action) {
            case ConnectivityManager.CONNECTIVITY_ACTION:
                if (mOnNetworkListener != null) {
                    mOnNetworkListener.onNetwork(NetworkUtils.isConnected());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 网络监听器
     */
    public interface OnNetworkListener {

        void onNetwork(boolean network);
    }
}
