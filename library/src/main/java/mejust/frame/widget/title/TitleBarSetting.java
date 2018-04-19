package mejust.frame.widget.title;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import mejust.frame.annotation.TitleBarMenuLocation;

/**
 * @author wangpeifeng
 * @date 2018/04/18 11:07
 */
public class TitleBarSetting {

    /**
     * title背景颜色
     */
    private int backgroundColor;
    /**
     * 标题
     */
    private String titleTextContent;
    /**
     * 标题大小
     */
    private float titleTextSize;
    /**
     * 标题颜色
     */
    private int titleTextColor;
    /**
     * 菜单列表
     */
    private SparseArray<TitleMenu> menuArray;

    public TitleBarSetting(Builder builder) {
        this.backgroundColor = builder.backgroundColor;
        this.titleTextContent = builder.titleTextContent;
        this.titleTextSize = builder.titleTextSize;
        this.titleTextColor = builder.titleTextColor;
        this.menuArray = builder.menuArray;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.backgroundColor = this.backgroundColor;
        builder.titleTextContent = this.titleTextContent;
        builder.titleTextColor = this.titleTextColor;
        builder.titleTextSize = this.titleTextSize;
        builder.menuArray = this.menuArray;
        try {
            return (Builder) builder.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return builder;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getTitleTextContent() {
        return titleTextContent;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public SparseArray<TitleMenu> getMenuArray() {
        return menuArray;
    }

    @Override
    public String toString() {
        return "TitleBarSetting{"
                + "backgroundColor="
                + backgroundColor
                + ", titleTextContent='"
                + titleTextContent
                + '\''
                + ", titleTextSize="
                + titleTextSize
                + ", titleTextColor="
                + titleTextColor
                + ", menuArray="
                + menuArray
                + '}';
    }

    public static class Builder implements Cloneable {

        private int backgroundColor = 0;

        private String titleTextContent;

        private float titleTextSize = 0;

        private int titleTextColor = 0;

        private SparseArray<TitleMenu> menuArray;

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder setBackgroundColorRes(Context context, @ColorRes int color) {
            this.backgroundColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder setTitleTextContext(String content) {
            this.titleTextContent = content;
            return this;
        }

        public Builder setTitleTextSize(float size) {
            this.titleTextSize = size;
            return this;
        }

        public Builder setTitleTextColor(@ColorInt int color) {
            this.titleTextColor = color;
            return this;
        }

        public Builder setTitleTextColorRes(Context context, @ColorRes int color) {
            this.titleTextColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder addTitleMenu(TitleMenu titleMenu) {
            if (menuArray == null) {
                menuArray = new SparseArray<>(4);
            }
            if (menuArray.size() >= 4) {
                throw new IllegalArgumentException("title menu textSize more than four");
            }
            int location = titleMenu.getLocation();
            menuArray.put(location, titleMenu);
            return this;
        }

        public TitleBarSetting build() {
            return new TitleBarSetting(this);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Builder builder = (Builder) super.clone();
            builder.menuArray = menuArray.clone();
            return builder;
        }
    }

    public static class TitleMenu implements Cloneable {

        private int location;

        private Drawable iconDrawable;

        private String text;

        private float textSize = 0;

        private int textColor = 0;

        private View.OnClickListener clickListener;

        public TitleMenu(@TitleBarMenuLocation int location) {
            this.location = location;
        }

        @TitleBarMenuLocation
        public int getLocation() {
            return location;
        }

        public Drawable getIconDrawable() {
            return iconDrawable;
        }

        public void setIconDrawable(Drawable iconDrawable) {
            this.iconDrawable = iconDrawable;
        }

        public void setIconDrawableRes(Context context, @DrawableRes int drawableRes) {
            this.iconDrawable = ContextCompat.getDrawable(context, drawableRes);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(@ColorInt int textColor) {
            this.textColor = textColor;
        }

        public void setTextColorRes(Context context, @ColorRes int textColor) {
            this.textColor = ContextCompat.getColor(context, textColor);
        }

        public View.OnClickListener getClickListener() {
            return clickListener;
        }

        public void setClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "TitleMenu{"
                    + "iconDrawable="
                    + iconDrawable
                    + ", text='"
                    + text
                    + '\''
                    + ", textSize="
                    + textSize
                    + ", textColor="
                    + textColor
                    + ", clickListener="
                    + clickListener
                    + '}';
        }
    }
}
