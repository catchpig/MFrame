package mejust.frame.widget.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by CiferLiao on 2017/3/14.
 */

public class GridEntrust extends SpacesItemDecorationEntrust {
    /**
     * 最外层是否需要分割线
     */
    private boolean outermostBorder;

    public GridEntrust(int leftRight, int topBottom, int mColor, boolean outermostBorder) {
        super(leftRight, topBottom, mColor);
        this.outermostBorder = outermostBorder;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int count = parent.getAdapter().getItemCount();
        if (mDivider == null || count == 0) {
            return;
        }
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
            if(outermostBorder){
                drawVerticalOutlLine(c,parent);
            }else{
                drawVertical(c, parent);
            }
        } else {
            if(outermostBorder){
                drawHorizontalOutLine(c,parent);
            }else{
                drawHorizontal(c, parent);
            }
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int count = parent.getChildCount();
        int spanCount = layoutManager.getSpanCount();
        //总行数
        int rows = count / spanCount;
        if (count % spanCount != 0) {
            rows += 1;
        }
        int left;
        int right;
        int top;
        int bottom;
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = parent.getChildLayoutPosition(child);
            //所在行数
            int row = (position + 1) / spanCount;
            if ((position + 1) % spanCount != 0) {
                row += 1;
            }
            top = child.getTop() - params.topMargin;
            left = child.getLeft() - params.rightMargin;
            right = child.getRight()+params.rightMargin + leftRight;
            //最后一行
            if(row==rows){
                bottom = child.getBottom() + params.bottomMargin;
            }else{
                bottom = child.getBottom() + params.bottomMargin+topBottom;
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
    private void drawVerticalOutlLine(Canvas c, RecyclerView parent) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int count = parent.getChildCount();
        int spanCount = layoutManager.getSpanCount();
        int left;
        int right;
        int top;
        int bottom;
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = parent.getChildLayoutPosition(child);
            //所在行数
            int row = (position + 1) / spanCount;
            if ((position + 1) % spanCount != 0) {
                row += 1;
            }
            //第一行
            if(row==1){
                top = child.getTop() - params.topMargin-topBottom;
            }else{
                top = child.getTop() - params.topMargin;
            }
            left = child.getLeft() - params.rightMargin-leftRight;
            right = child.getRight()+params.rightMargin + leftRight;
            bottom = child.getBottom() + params.bottomMargin+topBottom;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int count = parent.getChildCount();
        int spanCount = layoutManager.getSpanCount();
        //总列数
        int columns = count / spanCount;
        if (count % spanCount != 0) {
            columns += 1;
        }
        int left;
        int right;
        int top;
        int bottom;
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = parent.getChildLayoutPosition(child);
            //所在列数
            int column = (position + 1) / spanCount;
            if ((position + 1) % spanCount != 0) {
                column += 1;
            }
            top = child.getTop() - params.topMargin-leftRight;
            left = child.getLeft() - params.rightMargin;
            bottom = child.getBottom() + params.bottomMargin+topBottom;
            //最后一列
            if(column==columns){
                right = child.getRight()+params.rightMargin;
            }else{
                right = child.getRight()+params.rightMargin + leftRight;
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
    private void drawHorizontalOutLine(Canvas c, RecyclerView parent) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int count = parent.getChildCount();
        int spanCount = layoutManager.getSpanCount();

        int left;
        int right;
        int top;
        int bottom;
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = parent.getChildLayoutPosition(child);
            //所在列数
            int column = (position + 1) / spanCount;
            if ((position + 1) % spanCount != 0) {
                column += 1;
            }
            top = child.getTop() - params.topMargin-leftRight;
            right = child.getRight()+params.rightMargin + leftRight;
            bottom = child.getBottom() + params.bottomMargin+topBottom;
            //第一列
            if(column==1){
                left = child.getLeft() - params.rightMargin-leftRight;
            }else{
                left = child.getLeft() - params.rightMargin;
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
            if (outermostBorder) {
                offsetsVerticalOutLine(outRect, view, parent);
            } else {
                offsetsVertical(outRect, view, parent);
            }
        } else {
            if (outermostBorder) {
                offsetsHorizontalOutLine(outRect, view, parent);
            } else {
                offsetsHorizontal(outRect, view, parent);
            }

        }
    }

    /**
     * 最外层不画分割线(横向)
     *
     * @param outRect
     * @param view
     * @param parent
     */
    private void offsetsVertical(Rect outRect, View view, RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int position = parent.getChildLayoutPosition(view);

        final int spanCount = layoutManager.getSpanCount();
        final int childCount = parent.getAdapter().getItemCount();
        //总行数
        int rows = childCount / spanCount;
        if (childCount % spanCount != 0) {
            rows += 1;
        }
        //所在行数
        int row = (position + 1) / spanCount;
        if ((position + 1) % spanCount != 0) {
            row += 1;
        }

        //每行只有两个
        if (spanCount == 2) {
            int average = leftRight / 2;
            if (average > 0) {
                //最左边的
                if (position % spanCount == 0) {
                    outRect.right = average;
                } else {//最右边的
                    outRect.left = average;
                }
            } else {
                //最左边的
                if (position % spanCount == 0) {
                    outRect.right = leftRight;
                }
            }
        } else {
            int average = leftRight / 3;
            if (average > 0) {
                if (position % spanCount == 0) {
                    //最左边的
                    outRect.right = average * 2;
                } else if (position % spanCount == (spanCount - 1)) {
                    //最右边的
                    outRect.left = average * 2;
                } else {
                    outRect.left = average;
                    outRect.right = average;
                }
            } else {
                if (position % spanCount != 0) {
                    //不是最左边的
                    outRect.left = leftRight;
                }
            }
        }
        if (row != rows) {
            outRect.bottom = topBottom;
        }

    }

    /**
     * 最外层画画分割线(横向)
     *
     * @param outRect
     * @param view
     * @param parent
     */
    private void offsetsVerticalOutLine(Rect outRect, View view, RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int position = parent.getChildAdapterPosition(view);
        final int spanCount = layoutManager.getSpanCount();
        //所在行数
        int row = (position + 1) / spanCount;
        if ((position + 1) % spanCount != 0) {
            row += 1;
        }
        //每行只有两个
        if (spanCount == 2) {
            int average = leftRight / 2;
            if (average > 0) {
                //最左边的
                if (position % spanCount == 0) {
                    outRect.left = leftRight;
                    outRect.right = average;
                } else {//最右边的
                    outRect.left = average;
                    outRect.right = leftRight;
                }
            } else {
                //最左边的
                if (position % spanCount == 0) {
                    outRect.right = leftRight;
                }
            }
        } else {
            int average = leftRight / 3;
            if (average > 0) {
                if (position % spanCount == 0) {
                    //最左边的
                    outRect.right = average;
                    outRect.left = leftRight;
                } else if (position % spanCount == (spanCount - 1)) {
                    //最右边的
                    outRect.left = average;
                    outRect.right = leftRight;
                } else {
                    outRect.left = average * 2;
                    outRect.right = average * 2;
                }
            } else {
                if (position % spanCount == 0) {
                    //不是最左边的
                    outRect.left = leftRight;
                }
                outRect.right = leftRight;
            }
        }
        if (row == 1) {
            outRect.top = topBottom;
        }
        outRect.bottom = topBottom;
    }

    /**
     * 最外层不画分割线(纵向)
     *
     * @param outRect
     * @param view
     * @param parent
     */
    private void offsetsHorizontal(Rect outRect, View view, RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int position = parent.getChildLayoutPosition(view);

        final int spanCount = layoutManager.getSpanCount();
        final int childCount = parent.getAdapter().getItemCount();
        //总行数
        int rows = childCount / spanCount;
        if (childCount % spanCount != 0) {
            rows += 1;
        }
        //所在行数
        int row = (position + 1) / spanCount;
        if ((position + 1) % spanCount != 0) {
            row += 1;
        }
        //每行只有两个
        if (spanCount == 2) {
            int average = topBottom / 2;
            if (average > 0) {
                //最上边的
                if (position % spanCount == 0) {
                    outRect.bottom = average;
                } else {//最下边的
                    outRect.top = average;
                }
            } else {
                //最上边的
                if (position % spanCount == 0) {
                    outRect.bottom = topBottom;
                }
            }
        } else {
            int average = topBottom / 3;
            if (average > 0) {
                if (position % spanCount == 0) {
                    //最上边的
                    outRect.bottom = average * 2;
                } else if (position % spanCount == (spanCount - 1)) {
                    //最下边的
                    outRect.top = average * 2;
                } else {
                    outRect.top = average;
                    outRect.bottom = average;
                }
            } else {
                if (position % spanCount != 0) {
                    //不是最上边的
                    outRect.bottom = topBottom;
                }
            }
        }
        if (row != rows) {
            outRect.right = leftRight;
        }
    }

    /**
     * 最外层画分割线(纵向)
     *
     * @param outRect
     * @param view
     * @param parent
     */
    private void offsetsHorizontalOutLine(Rect outRect, View view, RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int position = parent.getChildLayoutPosition(view);

        final int spanCount = layoutManager.getSpanCount();
        //所在行数
        int row = (position + 1) / spanCount;
        if ((position + 1) % spanCount != 0) {
            row += 1;
        }
        //每行只有两个
        if (spanCount == 2) {
            int average = topBottom / 2;
            if (average > 0) {
                //最上边的
                if (position % spanCount == 0) {
                    outRect.bottom = average;
                    outRect.top = topBottom;
                } else {//最下边的
                    outRect.top = average;
                    outRect.bottom = topBottom;
                }
            } else {
                //最上边的
                if (position % spanCount == 0) {
                    outRect.top = topBottom;
                }
                outRect.bottom = topBottom;
            }
        } else {
            int average = topBottom / 3;
            if (average > 0) {
                if (position % spanCount == 0) {
                    //最上边的
                    outRect.bottom = average;
                    outRect.top = topBottom;
                } else if (position % spanCount == (spanCount - 1)) {
                    //最下边的
                    outRect.top = average;
                    outRect.bottom = topBottom;
                } else {
                    outRect.top = average * 2;
                    outRect.bottom = average * 2;
                }
            } else {
                if (position % spanCount == 0) {
                    //最上边的
                    outRect.top = topBottom;
                }
                outRect.bottom = topBottom;
            }
        }
        if (row == 1) {
            outRect.left = leftRight;
        }
        outRect.right = leftRight;
    }

}
