package mejust.frame.widget.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import conm.zhuazhu.common.utils.ScreenUtils;

/**
 * Created by CiferLiao on 2017/3/14.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private SpacesItemDecorationEntrust mEntrust;
    private int mColor;
    private float leftRight;
    private float topBottom;
    /**
     * 最外层是否需要边框(默认需要)
     */
    private boolean outermostBorder = true;

    /**
     * 自定义分割线
     *
     * @param leftRightDp 左右分割线宽度(单位dp)
     * @param topBottomDp 上下分割线的高度(单位dp)
     */
    public SpacesItemDecoration(float leftRightDp, float topBottomDp) {
        this.leftRight = leftRightDp;
        this.topBottom = topBottomDp;
    }

    /**
     * 自定义分割线
     *
     * @param leftRightDp 左右分割线宽度(单位dp)
     * @param topBottomDp 上下分割线的高度(单位dp)
     * @param mColor      分割线的颜色
     */
    public SpacesItemDecoration(float leftRightDp, float topBottomDp, @ColorInt int mColor) {
        this(leftRightDp, topBottomDp);
        this.mColor = mColor;
    }

    /**
     * grid外边框是否设置分割线
     *
     * @param outermostBorder
     */
    public void setOutermostBorder(boolean outermostBorder) {
        this.outermostBorder = outermostBorder;
    }

    public void setColor(int color) {
        mColor = color;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mEntrust == null) {
            mEntrust = getEntrust(parent);
        }
        mEntrust.onDraw(c, parent, state);
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (mEntrust == null) {
            mEntrust = getEntrust(parent);
        }
        mEntrust.getItemOffsets(outRect, view, parent, state);

    }

    private SpacesItemDecorationEntrust getEntrust(RecyclerView recycler) {
        RecyclerView.LayoutManager manager = recycler.getLayoutManager();
        Context context = recycler.getContext();
        float leftRightPx = ScreenUtils.dpToPx(context, leftRight);
        float topBottomPx = ScreenUtils.dpToPx(context, topBottom);
        int lrpx = (int) leftRightPx;
        int tbpx = (int) topBottomPx;
        if (leftRightPx >= 0 && leftRightPx < 1) {
            lrpx = 1;
        }
        if (topBottomPx >= 0 && topBottomPx < 1) {
            tbpx = 1;
        }
        SpacesItemDecorationEntrust entrust = null;
        //要注意这边的GridLayoutManager是继承LinearLayoutManager，所以要先判断GridLayoutManager
        if (manager instanceof GridLayoutManager) {
            entrust = new GridEntrust(lrpx, tbpx, mColor, outermostBorder);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            entrust = new StaggeredGridEntrust(lrpx, tbpx, mColor);
        } else {//其他的都当做Linear来进行计算
            entrust = new LinearEntrust(lrpx, tbpx, mColor);
        }
        return entrust;
    }

}
