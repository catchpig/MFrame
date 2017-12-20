package mejust.frame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.zhuazhu.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import mejust.frame.R;
import mejust.frame.image.ImageUtils;
import mejust.frame.widget.MutipleTouchViewPager;

/**
 * 创建时间:2017/12/20 17:00<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 17:00<br/>
 * 描述:
 */

public class ImageDialog extends Dialog implements View.OnClickListener,OnPageChangeListener {
    /**
     * 图片地址的集合
     */
    private List<String> mUrls;
    /**
     * 图片的张数
     */
    private int mUrlLength;
    /**
     * 单张图片使用
     * @param context
     * @param url
     */
    public ImageDialog(Context context, String url) {
        super(context, R.style.mframe_imagedialog);
        mUrls = new ArrayList<>();
        mUrls.add(url);
    }
    /**
     * 多张图片使用
     * @param context
     * @param urls
     */
    public ImageDialog(Context context, String... urls) {
        super(context, R.style.mframe_imagedialog);
        mUrls = new ArrayList<>();
        for (String url:urls){
            mUrls.add(url);
        }
    }



    /**
     * 多张图片使用
     * @param context
     * @param urls
     */
    public ImageDialog(Context context, List<String> urls) {
        super(context, R.style.mframe_imagedialog);
        mUrls = urls;
    }
    private String mHostUrl = "";

    /**
     * 设置图片地址前缀
     * @param host
     */
    public void setHost(String host){
        if(StringUtils.isNotEmpty(host)){
            this.mHostUrl = host;
        }
    }
    private MutipleTouchViewPager mViewPager;
    private TextView mIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image);
		/*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置,
         * 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        ImageView close =  findViewById(R.id.close);
        FrameLayout frameLayout =  findViewById(R.id.frame);
        mViewPager = findViewById(R.id.viewPager);
        mIndex = findViewById(R.id.index);
        close.setOnClickListener(this);
        mUrlLength = mUrls.size();
        if(mUrls.size()>0){
            frameLayout.setVisibility(View.VISIBLE);
            initViewPage();
        }
    }
    private void initViewPage(){
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mUrls.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView v = new PhotoView(getContext());
                String url = mUrls.get(position);
                if(StringUtils.isNotEmpty(mHostUrl)){
                    url = mHostUrl+url;
                }
                ImageUtils.show(v,url);
                ViewParent vp = v.getParent();
                if (vp!=null){
                    ViewGroup parent = (ViewGroup)vp;
                    parent.removeView(v);
                }
                container.addView(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                v.setOnClickListener(ImageDialog.this);
                return v;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });
        mViewPager.setCurrentItem(0);
        mIndex.setText("1/"+ mUrlLength);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        cancel();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mIndex.setText((position+1)+"/"+ mUrlLength);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
