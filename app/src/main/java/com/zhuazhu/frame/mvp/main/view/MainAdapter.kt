package com.zhuazhu.frame.mvp.main.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhuazhu.frame.R
import com.zhuazhu.frame.mvp.main.model.PageItem
import com.zhuazhu.frame.mvp.main.view.MainAdapter.MainHolder

/**
 * @author wangpeifeng
 * @date 2018/05/31 13:55
 */
class MainAdapter(private val pageList: List<PageItem>) : RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pages_main, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textView.text = pageList[position].name
        holder.textView.setOnClickListener {
            val context = holder.view.context
            context.startActivity(Intent(context, pageList[position].aClass))
        }
    }

    override fun getItemCount() = pageList.size

    class MainHolder(val view: View) : ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_pages_item)
    }
}