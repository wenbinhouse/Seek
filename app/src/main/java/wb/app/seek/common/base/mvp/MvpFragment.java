//package wb.app.seek.common.base.mvp;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import wb.app.seek.common.base.BaseFragment;
//import wb.app.seek.common.base.BasePresenter;
//
///**
// * Created by W.b on 2016/12/29.
// */
//public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
//
//  protected P mPresenter;
//
//  protected abstract P createPresenter();
//
//  @Override
//  public void onAttach(Context context) {
//    super.onAttach(context);
//  }
//
//  @Override
//  public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//  }
//
//  @Nullable
//  @Override
//  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//    return super.onCreateView(inflater, container, savedInstanceState);
//  }
//
//  @Override
//  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//    mPresenter = createPresenter();
//    if (mPresenter != null) {
//      mPresenter.attachView();
//    }
//    super.onViewCreated(view, savedInstanceState);
//  }
//
//  @Override
//  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//    super.onActivityCreated(savedInstanceState);
//  }
//
//  @Override
//  public void onStart() {
//    super.onStart();
//  }
//
//  @Override
//  public void onResume() {
//    super.onResume();
//  }
//
//  @Override
//  public void onPause() {
//    super.onPause();
//  }
//
//  @Override
//  public void onStop() {
//    super.onStop();
//  }
//
//  @Override
//  public void onDestroyView() {
//    if (mPresenter != null) {
//      mPresenter.detachView();
//    }
//    super.onDestroyView();
//  }
//
//  @Override
//  public void onDestroy() {
//    super.onDestroy();
//  }
//
//  @Override
//  public void onDetach() {
//    super.onDetach();
//  }
//
//  public void showLoading() {
//  }
//
//  public void hideLoading() {
//  }
//
//  public void onFailure(String msg, String exception) {
//
//  }
//}
