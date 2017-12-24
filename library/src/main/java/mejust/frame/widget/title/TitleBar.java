package mejust.frame.widget.title;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mejust.frame.R;

/**
 * 创建时间:2017-12-21 9:49<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017年12月24日12:56:41<br/>
 * 描述: 自定义TitleBar,默认按钮被隐藏了的
 */

public class TitleBar extends FrameLayout {

    private FrameLayout layoutTitle;
    private LinearLayout layoutBack;
    private LinearLayout layoutRight;
    private ImageView imageBack;
    private TextView textBack;
    private TextView textTitle;
    private ImageView imageRight1;
    private ImageView imageRight2;
    private TextView textRight1;
    private TextView textRight2;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.mframe_title_bar, this, true);
        layoutTitle = view.findViewById(R.id.layout_title);
        layoutBack = view.findViewById(R.id.mframe_back_layout);
        layoutRight = view.findViewById(R.id.mframe_layout_right);
        textTitle = view.findViewById(R.id.mframe_text_title);
        imageBack = view.findViewById(R.id.mframe_back_image);
        imageRight1 = view.findViewById(R.id.mframe_image_right1);
        imageRight2 = view.findViewById(R.id.mframe_image_right2);
        textBack = view.findViewById(R.id.mframe_back_text);
        textRight1 = view.findViewById(R.id.mframe_text_right1);
        textRight2 = view.findViewById(R.id.mframe_text_right2);
        //隐藏右边的按钮和文字
        textRight1.setVisibility(GONE);
        textRight2.setVisibility(GONE);
        imageRight1.setVisibility(GONE);
        imageRight2.setVisibility(GONE);
    }
    /**
     * 设置右边第二个按钮的监听
     * @param listener
     */
    public void setImageRightSecondListener(OnClickListener listener){
        imageRight2.setVisibility(VISIBLE);
        imageRight2.setOnClickListener(listener);
    }
    /**
     * 设置右边第一个按钮的监听
     * @param listener
     */
    public void setImageRightFirstListener(OnClickListener listener){
        imageRight1.setVisibility(VISIBLE);
        imageRight1.setOnClickListener(listener);
    }
    /**
     * 设置右边第一个文字按钮的监听
     * @param listener
     */
    public void setTextRightFirstListener(OnClickListener listener){
        textRight1.setVisibility(VISIBLE);
        textRight1.setOnClickListener(listener);
    }
    /**
     * 设置右边第二个文字按钮的监听
     * @param listener
     */
    public void setTextRightSecondListener(OnClickListener listener){
        textRight2.setVisibility(VISIBLE);
        textRight2.setOnClickListener(listener);
    }
    /**
     * 设置返回按钮的监听
     * @param listener
     */
    public void setBackListener(OnClickListener listener){
        layoutBack.setOnClickListener(listener);
    }
    /**
     * 隐藏返回按钮
     */
    public void hiddenBackButton(){
        layoutBack.setVisibility(GONE);
    }
    /**
     * 设置基本属性
     * @param options
     */
    public void setOptions(@NonNull TitleBarOptions options) {
        //背景
        setTitleBarBackgroundColor(options.getBackgroundColor());
        //返回
        setBackImage(options.getBackImage());
        setBackText(options.getBackText());
        setBackTextSize(options.getBactTextSize());
        //文字颜色
        setTextColor(colorRes(options.getTextColor()));
        //标题
        setTitleTextSize(options.getTitleTextSize());
        //右边按钮
        setRightTextSize(options.getRightTextSize());
    }

    /**
     * 设置文字颜色
     * @param color
     */
    public void setTextColor(int color){
        textBack.setTextColor(color);
        textRight1.setTextColor(color);
        textRight2.setTextColor(color);
        textTitle.setTextColor(color);

    }

    /**
     * 设置标题的文字
     * @param titleText
     */
    public void setTitleText(String titleText){
        textTitle.setText(titleText);
    }

    /**
     * 设置标题的文字大小
     * @param titleSize
     */
    private void setTitleTextSize(float titleSize){
        textTitle.setTextSize(titleSize);
    }

    /**
     * 设置返回文字的大小
     * @param textBackSize
     */
    private void setBackTextSize(int textBackSize){
        textBack.setTextSize(TypedValue.COMPLEX_UNIT_SP,textBackSize);
    }

    /**
     * 设置返回的文字
     * @param backText
     */
    public void setBackText(String backText){
        textBack.setText(backText);
    }

    /**
     * 设置返回的图片
     * @param backImage
     */
    private void setBackImage(@DrawableRes int backImage){
        imageBack.setImageResource(backImage);
    }
    /**
     * 设置右边按钮文字的大小
     * @param textRightSize
     */
    private void setRightTextSize(int textRightSize){
        textRight1.setTextSize(TypedValue.COMPLEX_UNIT_SP,textRightSize);
        textRight2.setTextSize(TypedValue.COMPLEX_UNIT_SP,textRightSize);
    }

    /**
     * 设置右边第一个文字
     * @param rightText1
     */
    public void setRightText1(String rightText1){
        textRight1.setText(rightText1);
    }
    /**
     * 设置右边第二个文字
     * @param rightText2
     */
    public void setRightText2(String rightText2){
        textRight2.setText(rightText2);
    }

    /**
     * 设置右边第一张图片
     * @param rightImage1
     */
    public void setRightImage1(@DrawableRes int rightImage1){
        imageRight1.setVisibility(VISIBLE);
        imageRight1.setImageResource(rightImage1);
    }

    /**
     * 设置右边第二张图片
     * @param rightImage2
     */
    public void setRightImage2(@DrawableRes int rightImage2){
        imageRight2.setVisibility(VISIBLE);
        imageRight2.setImageResource(rightImage2);
    }

    /**
     * 设置背景颜色
     * @param backgroundColor
     */
    public void setTitleBarBackgroundColor(@ColorRes int backgroundColor){
        layoutTitle.setBackgroundResource(backgroundColor);
        layoutBack.setBackgroundResource(backgroundColor);
        layoutRight.setBackgroundResource(backgroundColor);

    }
    /**
     * 转换颜色值
     * @param color
     * @return
     */
    public int colorRes(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }
}
