package mejust.frame.widget.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;
import mejust.frame.R;

/**
 * @author wangpeifeng
 * @date 2018/04/18 11:07
 */
public class TitleBar extends LinearLayout {

    /**
     * 标题栏左边菜单区域
     */
    private LinearLayout layoutToolLeft;
    /**
     * 标题栏右边菜单区域
     */
    private LinearLayout layoutToolRight;
    /**
     * 中心标题内容
     */
    private FrameLayout layoutToolCenter;
    /**
     * 标题栏背景颜色
     */
    private int barColor;
    /**
     * 标题栏高度
     */
    private int barHeight;
    /**
     * 中心文本颜色
     */
    private int centerTitleColor;
    /**
     * 中心文本大小
     */
    private float centerTitleSize;
    /**
     * 菜单图标大小
     */
    private int menuIconSize;
    /**
     * 菜单文本颜色
     */
    private int menuTextColor;
    /**
     * 菜单文本大小
     */
    private float menuTextSize;
    /**
     * 传入的配置
     */
    private TitleBarSetting titleBarSetting;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.title_bar_style);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        layoutToolLeft = buildToolLayout();
        addView(layoutToolLeft);
        layoutToolCenter = buildCenterLayout();
        addView(layoutToolCenter);
        layoutToolRight = buildToolLayout();
        addView(layoutToolRight);
        getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int leftWidth = layoutToolLeft.getMeasuredWidth();
            int rightWidth = layoutToolRight.getMeasuredWidth();
            int width = Math.max(leftWidth, rightWidth);
            layoutToolLeft.setLayoutParams(
                    new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
            layoutToolRight.setLayoutParams(
                    new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        });
    }

    /**
     * 设置当前标题栏配置
     */
    public void setTitleBarSetting(TitleBarSetting titleBarSetting) {
        this.titleBarSetting = titleBarSetting;
        if (titleBarSetting == null) {
            setVisibility(GONE);
            return;
        }
        if (titleBarSetting.getBackgroundColor() != 0) {
            setBackgroundColor(titleBarSetting.getBackgroundColor());
        }
        setTitleCenter(titleBarSetting.getTitleTextContent(), titleBarSetting.getTitleTextColor(),
                titleBarSetting.getTitleTextSize());
        SparseArray<TitleBarSetting.TitleMenu> titleMenuSparseArray =
                titleBarSetting.getMenuArray();
        if (titleMenuSparseArray == null || titleMenuSparseArray.size() == 0) {
            return;
        }
        layoutToolLeft.removeAllViews();
        layoutToolRight.removeAllViews();
        // 位置的确定，不能用这种方式
        for (int i = 0; i < titleMenuSparseArray.size(); i++) {
            TitleBarSetting.TitleMenu titleMenu = titleMenuSparseArray.valueAt(i);
            if (titleMenu != null) {
                setTitleMenu(titleMenu);
            }
        }
    }

    /**
     * 获取当前标题栏配置
     */
    public TitleBarSetting getTitleBarSetting() {
        final TitleBarSetting setting = this.titleBarSetting;
        TitleBarSetting.Builder builder = new TitleBarSetting.Builder();
        if (setting == null) {
            return builder.setTitleTextColor(centerTitleColor)
                    .setTitleTextSize(centerTitleSize)
                    .setBackgroundColor(barColor)
                    .build();
        }
        builder.setBackgroundColor((int) checkValue(setting.getBackgroundColor(), barColor))
                .setTitleTextContext(setting.getTitleTextContent())
                .setTitleTextColor((int) checkValue(setting.getTitleTextColor(), centerTitleColor))
                .setTitleTextSize(checkValue(setting.getTitleTextSize(), centerTitleSize));
        SparseArray<TitleBarSetting.TitleMenu> titleMenuSparseArray = setting.getMenuArray();
        if (titleMenuSparseArray == null || titleMenuSparseArray.size() == 0) {
            return builder.build();
        }
        for (int i = 0; i < titleMenuSparseArray.size(); i++) {
            TitleBarSetting.TitleMenu titleMenu = titleMenuSparseArray.valueAt(i);
            if (titleMenu != null) {
                titleMenu.setTextColor((int) checkValue(titleMenu.getTextColor(), menuTextColor));
                titleMenu.setTextSize(checkValue(titleMenu.getTextSize(), menuTextSize));
                builder.addTitleMenu(titleMenu);
            }
        }
        return builder.build();
    }

    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
        barHeight = typedArray.getDimensionPixelSize(R.styleable.TitleBar_frame_title_height,
                ScreenUtils.dpToPxInt(56));
        barColor =
                typedArray.getColor(R.styleable.TitleBar_frame_title_background_color, Color.WHITE);
        int paddingSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_frame_title_padding,
                ScreenUtils.dpToPxInt(16));
        centerTitleColor =
                typedArray.getColor(R.styleable.TitleBar_frame_title_title_color, Color.BLACK);
        centerTitleSize = typedArray.getDimension(R.styleable.TitleBar_frame_title_title_size, 18);
        menuIconSize =
                typedArray.getDimensionPixelSize(R.styleable.TitleBar_frame_title_menu_icon_size,
                        ScreenUtils.dpToPxInt(24));
        menuTextColor =
                typedArray.getColor(R.styleable.TitleBar_frame_title_menu_text_color, Color.BLACK);
        menuTextSize = typedArray.getDimension(R.styleable.TitleBar_frame_title_menu_text_size, 16);
        typedArray.recycle();
        setBackgroundColor(barColor);
        setPadding(paddingSize, 0, paddingSize, 0);
    }

    /**
     * 生成中心标题容器
     */
    private FrameLayout buildCenterLayout() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        frameLayout.setLayoutParams(layoutParams);
        return frameLayout;
    }

    /**
     * 生成菜单区域容器
     */
    private LinearLayout buildToolLayout() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(HORIZONTAL);
        return layout;
    }

    /**
     * 设置标题主题
     *
     * @param titleTextContent 内容
     * @param titleTextColor 颜色
     * @param titleTextSize 大小
     */
    private void setTitleCenter(String titleTextContent, int titleTextColor, float titleTextSize) {
        layoutToolCenter.removeAllViews();
        if (TextUtils.isEmpty(titleTextContent)) {
            return;
        }
        int color = (int) checkValue(titleTextColor, this.centerTitleColor);
        float size = checkValue(titleTextSize, this.centerTitleSize);
        TextView textView = new TextView(getContext());
        textView.setText(titleTextContent);
        textView.setTextColor(color);
        textView.setTextSize(size);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        textView.setMaxLines(1);
        textView.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        layoutToolCenter.addView(textView, layoutParams);
    }

    /**
     * 设置标题菜单
     */
    private void setTitleMenu(TitleBarSetting.TitleMenu titleMenu) {
        int location = titleMenu.getLocation();
        Drawable drawable = titleMenu.getIconDrawable();
        FrameLayout frameLayout;
        if (drawable == null) {
            frameLayout = buildToolTextLayout(titleMenu.getText(), titleMenu.getTextSize(),
                    titleMenu.getTextColor(), titleMenu.getClickListener());
        } else {
            frameLayout =
                    buildToolImageLayout(titleMenu.getIconDrawable(), titleMenu.getClickListener());
        }
        int value = location >> 4;
        if (value == 0) {
            layoutToolLeft.addView(frameLayout);
        } else if (value == 1) {
            layoutToolRight.addView(frameLayout);
        }
    }

    /**
     * 生成菜单图标按钮
     *
     * @param drawable 图标
     * @param clickListener 点击事件
     * @return 图标菜单
     */
    private FrameLayout buildToolImageLayout(Drawable drawable, OnClickListener clickListener) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        FrameLayout.LayoutParams imageLayoutParams =
                new FrameLayout.LayoutParams(menuIconSize, menuIconSize);
        imageLayoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(imageLayoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackground(
                ContextCompat.getDrawable(getContext(), R.drawable.selector_trans_frame));
        frameLayout.setClickable(true);
        int touchWidth = menuIconSize + ScreenUtils.dpToPxInt(16);
        LayoutParams layoutParams =
                new LayoutParams(touchWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.addView(imageView);
        frameLayout.setOnClickListener(clickListener);
        return frameLayout;
    }

    /**
     * 生成文字菜单按钮
     *
     * @param text 文字内容
     * @param textSize 文字大小
     * @param textColor 文字颜色
     * @param clickListener 按钮点击事件
     * @return 文字菜单按钮
     */
    private FrameLayout buildToolTextLayout(String text, float textSize, int textColor,
            OnClickListener clickListener) {
        String content = text == null ? "" : text;
        float size = checkValue(textSize, this.menuTextSize);
        int color = (int) checkValue(textColor, this.menuTextColor);
        TextView textView = new TextView(getContext());
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
        textView.setMaxLines(1);
        textView.setPadding(20, 20, 20, 20);
        FrameLayout.LayoutParams textLayoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(textLayoutParams);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackground(
                ContextCompat.getDrawable(getContext(), R.drawable.selector_trans_frame));
        frameLayout.setClickable(true);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.addView(textView);
        frameLayout.setOnClickListener(clickListener);
        return frameLayout;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = barHeight;
        }
        int heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    private float checkValue(float value, float defaultValue) {
        return value == 0 ? defaultValue : value;
    }
}
