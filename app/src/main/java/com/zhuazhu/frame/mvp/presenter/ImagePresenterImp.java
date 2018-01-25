package com.zhuazhu.frame.mvp.presenter;

import mejust.frame.net.AjaxResult;

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
//        Flowable.just(ajaxResult)
//                .compose(FlowableUtils.transformerResult())
//                .subscribeWith(new Callback<String>(null) {
//                    @Override
//                    public void success(String stringOptional) {
////                        String data;
////                        if (stringOptional.isEmpty()) {
////                            data = "";
////                        } else {
////                            data = stringOptional.get();
////                        }
//                    }
//                });
    }
}
