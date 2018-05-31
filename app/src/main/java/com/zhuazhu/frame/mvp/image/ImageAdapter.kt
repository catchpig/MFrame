package com.zhuazhu.frame.mvp.image

import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.zhuazhu.frame.mvp.image.ImageAdapter.ImageHolder
import conm.zhuazhu.common.utils.ScreenUtils
import mejust.frame.refactor.FrameManager
import mejust.frame.refactor.image.ImageLoadConfig
import java.io.File

/**
 * @author wangpeifeng
 * @date 2018/05/31 14:31
 */
class ImageAdapter : RecyclerView.Adapter<ImageHolder>() {

    private val imageUrlOne =
        "https://drscdn.500px.org/photo/260111043/q%3D80_m%3D1500/v2?webp=true&sig=9538ff6f6dc7f6ea89b90aaefdf4f7a46cbd2a2418e21ae1ba754e7387ec03a3"

    private val imageUrlTwo = "https://uploadfile.bizhizu.cn/2014/0305/20140305042102185.jpg"
    private val imageFilePath =
        "${Environment.getExternalStorageDirectory()}/DCIM/Camera/IMG_20170709_124823_HDR.jpg"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView = ImageView(parent.context)
        val size = ScreenUtils.dpToPxInt(150f)
        imageView.layoutParams =
                LinearLayout.LayoutParams(size, size).also { it.gravity = Gravity.CENTER }
        return ImageHolder(imageView)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        when (position) {
            0 -> FrameManager.imageLoadManager().loadNet(holder.imageView, imageUrlOne)
            1 -> FrameManager.imageLoadManager().loadNet(
                holder.imageView, imageUrlOne,
                ImageLoadConfig(true)
            )
            2 -> FrameManager.imageLoadManager().loadNet(
                holder.imageView, imageUrlOne,
                ImageLoadConfig(15)
            )
            3 -> FrameManager.imageLoadManager().loadAsset(holder.imageView, "one.jpg")
            4 -> FrameManager.imageLoadManager().loadAsset(
                holder.imageView, "one.jpg",
                ImageLoadConfig(true)
            )
            5 -> FrameManager.imageLoadManager().loadAsset(
                holder.imageView, "one.jpg",
                ImageLoadConfig(15)
            )
            6 -> FrameManager.imageLoadManager().loadLocal(
                holder.imageView, File(imageFilePath),
                ImageLoadConfig(true)
            )
            7 -> FrameManager.imageLoadManager().loadLocal(holder.imageView, File(imageFilePath))
            8 -> FrameManager.imageLoadManager().loadLocal(
                holder.imageView, File(imageFilePath),
                ImageLoadConfig(15)
            )
            else -> FrameManager.imageLoadManager().loadNet(
                holder.imageView, imageUrlTwo,
                ImageLoadConfig(30)
            )
        }
    }

    override fun getItemCount() = 10

    class ImageHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}