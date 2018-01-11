package com.zhuazhu.frame.mvp.presenter;

import io.reactivex.Flowable;
import mejust.frame.net.AjaxResult;
import mejust.frame.net.Callback;
import mejust.frame.net.FlowableUtils;
import mejust.frame.net.Optional;

/**
 * 创建时间:2017/12/21 23:41<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 23:41<br/>
 * 描述:
 */

public class ImagePresenterImp {

    private void test() {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        ajaxResult.setCode("200");
        ajaxResult.setData(null);
        Flowable.just(ajaxResult)
                .compose(FlowableUtils.transformerResult())
                .subscribeWith(new Callback<Optional<String>>(null) {
                    @Override
                    public void success(Optional<String> stringOptional) {
                        String data;
                        if (stringOptional.isEmpty()) {
                            data = "";
                        } else {
                            data = stringOptional.get();
                        }
                    }
                });
    }
}
