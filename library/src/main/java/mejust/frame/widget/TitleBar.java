package mejust.frame.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import mejust.frame.R;

/**
 * 创建时间:2017-12-21 9:49<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 9:49<br/>
 * 描述: 自定义Title
 */

public class TitleBar extends FrameLayout {

    private TextView textTitle;
    private ImageView imageLeftMain;
    private ImageView imageLeftMinor;
    private ImageView imageRightMain;
    private ImageView imageRightMinor;
    private TextView textLeft;
    private TextView textRight;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.mframe_title_bar, this, true);
        textTitle = view.findViewById(R.id.mframe_titlebar_title);
        imageLeftMain = view.findViewById(R.id.mframe_titlebar_image_left_main);
        imageLeftMinor = view.findViewById(R.id.mframe_titlebar_image_left_minor);
        imageRightMain = view.findViewById(R.id.mframe_titlebar_image_right_main);
        imageRightMinor = view.findViewById(R.id.mframe_titlebar_image_right_minor);
        textLeft = view.findViewById(R.id.mframe_titlebar_text_left);
        textRight = view.findViewById(R.id.mframe_titlebar_text_right);
    }

    public void setOptions(@NonNull TitleBarOptions options) {
        setTextTitle(options);
        setTextLeft(options);
        setTextRight(options);
        setImageLeftMain(options);
        setImageLeftMinor(options);
        setImageRightMain(options);
        setImageRightMinor(options);
    }

    private void setTextTitle(TitleBarOptions options) {
        textTitle.setText(options.getTitleString());
        textTitle.setTextColor(options.getTitleStringColor());
        textTitle.setTextSize(options.getTitleStringSize());
    }

    private void setTextLeft(TitleBarOptions options) {
        if (TextUtils.isEmpty(options.getTextLeft())) {
            textLeft.setVisibility(GONE);
            return;
        }
        textLeft.setText(options.getTextLeft());
        textLeft.setTextColor(options.getTextLeftColor());
        textLeft.setTextSize(options.getTextLeftSize());
    }

    private void setTextRight(TitleBarOptions options) {
        if (TextUtils.isEmpty(options.getTextRight())) {
            textRight.setVisibility(GONE);
            return;
        }
        textRight.setText(options.getTextRight());
        textRight.setTextColor(options.getTextRightColor());
        textRight.setTextSize(options.getTextRightSize());
    }

    private void setImageLeftMain(TitleBarOptions options) {
        if (options.getImgLeftMainId() == 0) {
            imageLeftMain.setVisibility(GONE);
            return;
        }
        imageLeftMain.setImageResource(options.getImgLeftMainId());
    }

    private void setImageLeftMinor(TitleBarOptions options) {
        if (options.getImgLeftMinorId() == 0) {
            imageLeftMinor.setVisibility(GONE);
            return;
        }
        imageLeftMinor.setImageResource(options.getImgLeftMinorId());
    }

    private void setImageRightMain(TitleBarOptions options) {
        if (options.getImgRightMainId() == 0) {
            imageRightMain.setVisibility(GONE);
            return;
        }
        imageRightMain.setImageResource(options.getImgRightMainId());
    }

    private void setImageRightMinor(TitleBarOptions options) {
        if (options.getImgRightMinorId() == 0) {
            imageRightMinor.setVisibility(GONE);
            return;
        }
        imageRightMinor.setImageResource(options.getImgRightMinorId());
    }
}
