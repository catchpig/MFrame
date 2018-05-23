package mejust.frame.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gcssloop.widget.RCRelativeLayout;
import conm.zhuazhu.common.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;
import mejust.frame.R;

/**
 * @author wangpeifeng
 * @date 2018/05/21 17:07
 */
public abstract class BaseFrameDialogBuilder<T extends BaseFrameDialogBuilder> {

    private Context context;
    private LinearLayout rootLayout;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;
    /**
     * 操作栏集合
     */
    private List<FrameDialogAction> actionList = new ArrayList<>();

    private String titleText;

    public BaseFrameDialogBuilder(Context context) {
        this.context = context;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return this
     */
    @SuppressWarnings("unchecked")
    public T setTitle(String title) {
        this.titleText = title;
        return (T) this;
    }

    public T setTitle(int titleRes) {
        return setTitle(context.getResources().getString(titleRes));
    }

    @SuppressWarnings("unchecked")
    public T setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return (T) this;
    }

    /**
     * 增加操作控制
     */
    @SuppressWarnings("unchecked")
    public T addAction(FrameDialogAction action) {
        if (action != null) {
            actionList.add(action);
        }
        return (T) this;
    }

    /**
     * dialog 创建
     */
    public FrameDialog create() {
        FrameDialog frameDialog = new FrameDialog(context);
        View rootView = inflateRootView();
        createTitle();
        createContent(frameDialog, rootLayout, context);
        createActionHandler(frameDialog);
        frameDialog.addContentView(rootView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        frameDialog.setCancelable(mCancelable);
        frameDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        return frameDialog;
    }

    /**
     * 创建底部操作视图
     */
    private void createActionHandler(FrameDialog frameDialog) {
        LinearLayout actionLayout = new LinearLayout(context);
        actionLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < actionList.size(); i++) {
            View view = actionList.get(i).buildActionView(frameDialog, i);
            actionLayout.addView(view);
        }
        rootLayout.addView(actionLayout,
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 根视图创建
     */
    private View inflateRootView() {
        rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        RCRelativeLayout relativeLayout = new RCRelativeLayout(context);
        relativeLayout.setRadius(ScreenUtils.dpToPxInt(5));
        relativeLayout.addView(rootLayout,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        return relativeLayout;
    }

    /**
     * 创建标题
     */
    private void createTitle() {
        if (!TextUtils.isEmpty(titleText)) {
            TextView titleTextView = new TextView(context);
            titleTextView.setText(titleText);
            configTitleView(titleTextView);
            titleTextView.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            rootLayout.addView(titleTextView);
        }
    }

    /**
     * 创建dialog视图内容
     */
    protected abstract void createContent(FrameDialog frameDialog, LinearLayout rootLayout,
            Context dialogContext);

    /**
     * 设置TitleView
     *
     * @param titleTextView 标题栏view
     */
    protected void configTitleView(TextView titleTextView) {
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setTextSize(20f);
        titleTextView.setTextColor(Color.BLACK);
        int padding =
                context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        titleTextView.setPadding(padding, padding, padding, ScreenUtils.dpToPxInt(6));
        titleTextView.setMaxLines(1);
        titleTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    }
}
