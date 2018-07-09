package mejust.frame.widget.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;
import mejust.frame.R;

/**
 * @author wangpeifeng
 * @date 2018/06/28 10:27
 */
public class TitleBar extends LinearLayout {

    private static final String TAG = "TitleBar";
    public static final int TITLE_BAR_NO_ID = -1;

    /** 状态栏高度 */
    private int titleBarHeight;
    /** 状态栏背景颜色 */
    private int titleBarBackgroundColor;
    /** 状态栏标题文字颜色 */
    private int titleBarMainTextColor;
    /** 状态栏标题文字尺寸 */
    private int titleBarMainTextSize;
    /** 状态栏标题文字居中 */
    private boolean titleBarMainTextCenter;
    /** 状态栏菜单文字颜色 */
    private int titleBarMenuTextColor;
    /** 状态栏菜单文字尺寸 */
    private int titleBarMenuTextSize;
    /** 返回图标resId */
    private Drawable backIconDrawable;

    /** 左菜单layout */
    private LinearLayout layoutMenuLeft;
    /** 标题layout */
    private LinearLayout layoutMain;
    /** 右标题layout */
    private LinearLayout layoutMenuRight;
    /** 标题View */
    private TextView mainTextView;
    /** 返回view */
    private AppCompatImageView imageBack;

    /** 限定右边菜单做多可以添加的数量 */
    private int rightMenuCountMax = 2;
    /** 标题栏左右两边间距 */
    private int titleBarPaddingSize = 16;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.title_bar_style);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
    }

    /**
     * 设置标题栏背景颜色
     */
    public void setTitleBarBackgroundColor(@ColorInt int color) {
        this.titleBarBackgroundColor = color;
        setBackgroundColor(color);
    }

    /**
     * 设置标题栏左右间距
     */
    public void setTitleBarPaddingSize(int size) {
        this.titleBarPaddingSize = size;
        setPadding(ScreenUtils.dpToPxInt(titleBarPaddingSize), 0,
                ScreenUtils.dpToPxInt(titleBarPaddingSize), 0);
    }

    /**
     * 设置标题栏
     */
    public void setMainTitle(String title) {
        mainTextView.setText(title);
    }

    /**
     * 设置标题文字主题
     *
     * @param mainTextCenter 标题是否居中,false 位于坐标
     * @param mainTextColor 标题文字颜色，不设置时值为-1
     * @param mainTextSize 标题文字大小，不设置时值为-1
     */
    @SuppressLint("RtlHardcoded")
    public void setTitleBarMainTextStyle(@ColorRes int mainTextColor, int mainTextSize,
            boolean mainTextCenter) {
        if (mainTextColor != TITLE_BAR_NO_ID) {
            this.titleBarMainTextColor = ContextCompat.getColor(getContext(), mainTextColor);
            mainTextView.setTextColor(titleBarMainTextColor);
        }
        if (mainTextSize != TITLE_BAR_NO_ID) {
            this.titleBarMainTextSize = mainTextSize;
            mainTextView.setTextSize(mainTextSize);
        }
        this.titleBarMainTextCenter = mainTextCenter;
        if (mainTextCenter) {
            mainTextView.setGravity(Gravity.CENTER);
        } else {
            mainTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        }
        changeMenuLayoutCenter();
    }

    /**
     * 设置返回按钮是否显示
     */
    public void setBackMenuVisible(boolean visible) {
        imageBack.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置返回按钮点击事件
     */
    public void setBackMenu(View.OnClickListener clickListener) {
        setBackMenu(TITLE_BAR_NO_ID, clickListener);
    }

    /**
     * 设置返回按钮
     *
     * @param backIconId 返回按钮图标
     * @param clickListener 点击事件
     */
    public void setBackMenu(@DrawableRes int backIconId, View.OnClickListener clickListener) {
        if (backIconId != TITLE_BAR_NO_ID) {
            imageBack.setImageDrawable(ContextCompat.getDrawable(getContext(), backIconId));
        }
        imageBack.setOnClickListener(clickListener);
    }

    /**
     * 右菜单添加文字菜单
     */
    public void addRightTextMenu(@IdRes int viewId, String text, int textSize,
            @ColorRes int textColor, int marginLeft, int marginRight) {
        TextView textView = new TextView(getContext());
        if (viewId != TITLE_BAR_NO_ID) {
            textView.setId(viewId);
        }
        textView.setText(text);
        int size = textSize == TITLE_BAR_NO_ID ? titleBarMenuTextSize : textSize;
        textView.setTextSize(size);
        if (textColor == TITLE_BAR_NO_ID) {
            textView.setTextColor(titleBarMenuTextColor);
        } else {
            textView.setTextColor(ContextCompat.getColor(getContext(), textColor));
        }
        textView.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = marginLeft;
        layoutParams.rightMargin = marginRight;
        textView.setLayoutParams(layoutParams);
        addRightMenu(textView);
    }

    /**
     * 右菜单添加图片菜单
     */
    public void addRightImageMenu(@IdRes int viewId, @DrawableRes int resId, int marginLeft,
            int marginRight) {
        ImageView imageView = new ImageView(getContext());
        if (viewId != TITLE_BAR_NO_ID) {
            imageView.setId(viewId);
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = marginLeft;
        layoutParams.rightMargin = marginRight;
        imageView.setLayoutParams(layoutParams);
        addRightMenu(imageView);
    }

    /**
     * 右边菜单绑定点击事件
     */
    public void bindRightMenuClickListener(@IdRes int viewId, View.OnClickListener clickListener) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
    }

    /**
     * 设置默认右边最多添加菜单数量
     */
    public void setRightMenuCountMax(int rightMenuCountMax) {
        this.rightMenuCountMax = rightMenuCountMax;
    }

    /**
     * 清除右菜单选项
     */
    public void clearRightMenu() {
        layoutMenuRight.removeAllViews();
    }

    /**
     * 属性值初始化
     */
    private void initAttr(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray array =
                context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
        this.titleBarHeight = array.getDimensionPixelSize(R.styleable.TitleBar_title_bar_height,
                ScreenUtils.dpToPxInt(56));
        this.titleBarBackgroundColor =
                array.getColor(R.styleable.TitleBar_title_bar_background, Color.BLUE);
        this.titleBarMainTextColor =
                array.getColor(R.styleable.TitleBar_title_bar_main_text_color, Color.WHITE);
        this.titleBarMainTextSize =
                array.getDimensionPixelSize(R.styleable.TitleBar_title_bar_main_text_size, 18);
        this.titleBarMainTextCenter =
                array.getBoolean(R.styleable.TitleBar_title_bar_main_text_center, true);
        this.titleBarMenuTextColor =
                array.getColor(R.styleable.TitleBar_title_bar_menu_text_color, Color.WHITE);
        this.titleBarMenuTextSize =
                array.getDimensionPixelSize(R.styleable.TitleBar_title_bar_menu_text_size, 16);
        this.backIconDrawable =
                array.getDrawable(R.styleable.TitleBar_title_bar_menu_back_icon_res);
        array.recycle();
        if (backIconDrawable == null) {
            backIconDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.close);
        }
    }

    /**
     * 标题栏所有属性初始化
     */
    private void initView() {
        setOrientation(HORIZONTAL);
        setBackgroundColor(titleBarBackgroundColor);
        setPadding(ScreenUtils.dpToPxInt(titleBarPaddingSize), 0,
                ScreenUtils.dpToPxInt(titleBarPaddingSize), 0);
        removeAllViews();
        initMenuLayout();
        addMainText();
        addBackMenu();
    }

    /**
     * 标题栏，标题、菜单layout初始化
     */
    @SuppressLint("RtlHardcoded")
    private void initMenuLayout() {
        layoutMenuLeft = new LinearLayout(getContext());
        layoutMenuLeft.setOrientation(HORIZONTAL);
        addView(layoutMenuLeft);
        layoutMain = new LinearLayout(getContext());
        layoutMain.setOrientation(HORIZONTAL);
        addView(layoutMain);
        layoutMenuRight = new LinearLayout(getContext());
        layoutMenuRight.setOrientation(HORIZONTAL);
        layoutMenuRight.setGravity(Gravity.RIGHT);
        addView(layoutMenuRight);
        changeMenuLayoutCenter();
    }

    /**
     * 修改布局，使标题居中或偏左
     */
    private void changeMenuLayoutCenter() {
        if (titleBarMainTextCenter) {
            layoutMenuLeft.setLayoutParams(
                    new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            layoutMain.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            layoutMenuRight.setLayoutParams(
                    new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        } else {
            layoutMenuLeft.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            layoutMain.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            layoutMenuRight.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    /**
     * 初始化标题文本
     */
    @SuppressLint("RtlHardcoded")
    private void addMainText() {
        mainTextView = new TextView(getContext());
        mainTextView.setMaxLines(1);
        mainTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        mainTextView.setTextSize(titleBarMainTextSize);
        mainTextView.setTextColor(titleBarMainTextColor);
        if (titleBarMainTextCenter) {
            mainTextView.setGravity(Gravity.CENTER);
        } else {
            mainTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        }
        mainTextView.setPadding(ScreenUtils.dpToPxInt(3), 0, ScreenUtils.dpToPxInt(3), 0);
        mainTextView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layoutMain.addView(mainTextView);
    }

    /**
     * 初始化添加返回按钮
     */
    private void addBackMenu() {
        imageBack = new AppCompatImageView(getContext());
        imageBack.setImageDrawable(backIconDrawable);
        imageBack.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageBack.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageBack.setPadding(ScreenUtils.dpToPxInt(3), 0, ScreenUtils.dpToPxInt(3), 0);
        layoutMenuLeft.addView(imageBack);
    }

    /**
     * 右菜单添加
     */
    private void addRightMenu(View view) {
        if (rightMenuCountMax - layoutMenuRight.getChildCount() > 0) {
            layoutMenuRight.addView(view);
        } else {
            throw new IllegalArgumentException("right menu count greater than the max count");
        }
    }
}
