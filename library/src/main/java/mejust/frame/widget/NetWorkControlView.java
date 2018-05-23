package mejust.frame.widget;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import conm.zhuazhu.common.utils.NetworkUtils;
import mejust.frame.R;
import mejust.frame.app.AppConfig;
import mejust.frame.receiver.NetworkReceiver;

/**
 * @author wangpeifeng
 * @date 2018/05/23 15:33
 */
public class NetWorkControlView extends FrameLayout implements NetworkReceiver.OnNetworkListener {

    private NetworkReceiver mNetworkReceiver;

    public NetWorkControlView(@NonNull Context context) {
        this(context, null);
    }

    public NetWorkControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetWorkControlView(@NonNull Context context, @Nullable AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (AppConfig.NETWORK_STATUS_MONITORING) {
            View statusView = LayoutInflater.from(context)
                    .inflate(R.layout.layout_network_tip_frame, this, true);
            statusView.setOnClickListener(v -> NetworkUtils.openWifiSettings());
        }
        setVisibility(GONE);
    }

    /**
     * 注册网络变化广播监听,{@link AppConfig#NETWORK_STATUS_MONITORING}标志位默认监听
     */
    public void registerNetChangeListener() {
        if (AppConfig.NETWORK_STATUS_MONITORING && mNetworkReceiver == null) {
            mNetworkReceiver = new NetworkReceiver();
            mNetworkReceiver.setOnNetworkListener(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            getContext().registerReceiver(mNetworkReceiver, filter);
        }
    }

    /**
     * 取消网络注册监听
     */
    public void unRegisterNetChangeListener() {
        if (mNetworkReceiver != null) {
            getContext().unregisterReceiver(mNetworkReceiver);
            mNetworkReceiver = null;
        }
    }

    @Override
    public void onNetwork(boolean network) {
        setVisibility(network ? View.GONE : View.VISIBLE);
    }
}
