package android.marshon.likequanmintv.librarys.mvpbase;

import android.app.Activity;
import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.di.component.DaggerFragmentComponent;
import android.marshon.likequanmintv.di.component.FragmentComponent;
import android.marshon.likequanmintv.di.module.FragmentModule;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * 作者：Marshon.Chen on 2016/9/8 09:43
 * 邮箱：itmarshon@163.com
 * 功能描述：slidingtab模板
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected View rootView;
    protected BaseActivity mActivity;

    protected T mPresenter;
    protected FragmentComponent mFragmentComponent;

    protected abstract T  initInjector();
    protected abstract int getLayoutId();
    protected abstract void initView(View rootView);
    public abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((APP) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        mPresenter=initInjector();
        mPresenter.attachView(this);
        mPresenter.onCreate();

        //leak
//        RefWatcher refWatcher = APP.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        rootView = inflater.inflate(layoutId, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected View findViewById(int resId){
        return rootView.findViewById(resId);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void stopNetWork() {
        super.stopNetWork();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }


    public <E extends View> E find(int viewId) {
        View view = rootView.findViewById(viewId);
        return (E) view;
    }


    public void showToast(String msg) {
        if (mActivity != null) {
            Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
        }

    }


//baseView 部分的实现
    @Override
    public void showProgress(@Nullable String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void showLongToast(String msg) {

    }



}
