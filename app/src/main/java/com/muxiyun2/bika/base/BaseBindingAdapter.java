package com.muxiyun2.bika.base;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.muxiyun2.bika.BR;

import java.util.List;

import me.jingbin.library.adapter.BaseByRecyclerViewAdapter;


public abstract class BaseBindingAdapter<T, B extends ViewDataBinding> extends BaseByRecyclerViewAdapter<T, BaseBindingHolder<T, B>> {

    private int mLayoutId;

    public BaseBindingAdapter(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
    }

    public BaseBindingAdapter(@LayoutRes int layoutId, List<T> data) {
        super(data);
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseBindingHolder<T, B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, mLayoutId);
    }


    private class ViewHolder extends BaseBindingHolder<T, B> {
        ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        protected void onBindingView(BaseBindingHolder holder, T bean, int position) {
            if (holder != null && bean != null && binding != null) {
                holder.binding.setVariable(BR.m, bean);
                bindView(holder, bean, binding, position);
                holder.binding.executePendingBindings();
            }
        }

        @Override
        protected void onBindingViewPayloads(BaseBindingHolder holder, T bean, int position, @NonNull List<Object> payloads) {
            if (holder != null && bean != null && binding != null) {
                bindViewPayloads(holder, bean, binding, position, payloads);
            }
        }
    }

    protected abstract void bindView(@NonNull BaseBindingHolder holder, @NonNull T bean, @NonNull B binding, int position);

    /**
     * 局部刷新，非必须
     */
    protected void bindViewPayloads(@NonNull BaseBindingHolder holder, @NonNull T bean, @NonNull B binding, int position, @NonNull List<Object> payloads) {
        /*
         * fallback to onBaseBindView(holder, bean,position) if app does not override this method.
         * 如果不覆盖 bindViewPayloads() 方法，就走 bindView()
         */
        bindView(holder, bean, binding, position);
    }
}