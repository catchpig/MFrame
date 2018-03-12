package mejust.frame.upgrade;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;
import mejust.frame.R;

/**
 * 创建时间: 2018/03/12 9:41<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/12 9:41<br>
 * 描述:
 */
public class DialogProgress extends DialogFragment implements ProgressHelper {

    private static final String PARAM_KEY = "param_key";

    public static DialogProgress getInstance(Context context,
            ProgressMessageBuilder progressMessageBuilder) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PARAM_KEY, progressMessageBuilder);
        DialogProgress dialogProgress = new DialogProgress();
        dialogProgress.setArguments(bundle);
        dialogProgress.show(((FragmentActivity) context).getSupportFragmentManager(),
                "progressDialog");
        return dialogProgress;
    }

    private ProgressMessageBuilder progressMessageBuilder;

    private ProgressBar progressBar;
    private TextView textContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        progressMessageBuilder = (ProgressMessageBuilder) bundle.getSerializable(PARAM_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_upgrade_progress, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        setCancelable(false);
        TextView textTitle = view.findViewById(R.id.text_upgrade_name);
        progressBar = view.findViewById(R.id.pro_upgrade);
        textContent = view.findViewById(R.id.text_content_progress);
        textTitle.setText(progressMessageBuilder.getContentTitle());
        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = ScreenUtils.getScreenWidth(getContext()) - ScreenUtils.dpToPxInt(20);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
        textContent.setText(getResources().getString(R.string.download_ing));
    }

    @Override
    public void downloadSuccess() {
        progressBar.setProgress(100);
        textContent.setText(getResources().getString(R.string.download_success));
        dismiss();
    }

    @Override
    public void downloadFail() {
        progressBar.setProgress(0);
        textContent.setText(getResources().getString(R.string.download_fail));
        progressBar.postDelayed(this::dismiss, 1000);
    }
}
