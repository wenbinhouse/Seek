package wb.app.seek.modules.zhihu;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseRefreshListFragment;
import wb.app.seek.common.rxbus.RxBus;
import wb.app.seek.common.rxbus.RxEvent;
import wb.app.seek.common.rxbus.RxEventType;
import wb.app.seek.common.utils.DateTimeUtils;
import wb.app.seek.modules.model.ZhihuDailyNews;
import wb.app.seek.modules.model.ZhihuDailyStory;
import wb.app.seek.modules.zhihu.adapter.ZhihuDailyAdapterNew;
import wb.app.seek.modules.zhihu.presenter.ZhihuDailyContract;
import wb.app.seek.modules.zhihu.presenter.ZhihuDailyPresenter;
import wb.app.seek.widgets.recyclerview.BaseRecyclerAdapter;

import static wb.app.seek.modules.zhihu.ZhihuDailyDetailActivity.INTENT_KEY_STORY_ID;

/**
 * Created by W.b on 04/05/2017.
 */
public class ZhihuDailyFragmentNew extends BaseRefreshListFragment<ZhihuDailyStory, ZhihuDailyPresenter> implements ZhihuDailyContract.View {

    private Subscription mSubscription;

    public static ZhihuDailyFragmentNew newInstance() {
        return new ZhihuDailyFragmentNew();
    }

    @Override
    protected ZhihuDailyPresenter createPresenter() {
        return new ZhihuDailyPresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void initComponents(View view) {
        super.initComponents(view);

        mSubscription = RxBus.getInstance().toObservable(RxEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        if (rxEvent.getType() == RxEventType.SCROLL_TO_TOP) {
                            smoothScrollTop();
                            getPresenter().refreshNews(rxEvent.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    protected BaseRecyclerAdapter initAdapter() {
        ZhihuDailyAdapterNew adapter = new ZhihuDailyAdapterNew(getContext());
        adapter.setOnItemClickListener(new ZhihuDailyAdapterNew.OnItemClickListener() {
            @Override
            public void onClick(int id, View view) {
                // Lollipop 以上实现 Transition
                Intent intent = new Intent(getActivity(), ZhihuDailyDetailActivity.class);
                intent.putExtra(INTENT_KEY_STORY_ID, id);
                ActivityOptionsCompat options
                        = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.transition_name_cover_image));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                getActivity().overridePendingTransition(0, 0);
            }

            @Override
            public void onBannerClick(int id) {
                Intent intent = new Intent(getActivity(), ZhihuDailyDetailActivity.class);
                intent.putExtra(INTENT_KEY_STORY_ID, id);
                getActivity().startActivity(intent);
            }
        });
        return adapter;
    }

    @Override
    protected void query() {
        getPresenter().refreshNews(DateTimeUtils.getCurrentDay());
    }

    @Override
    protected void queryMore() {
        getPresenter().loadMoreNews();
    }

    @Override
    public void showNews(ZhihuDailyNews dailyNews) {
        fillData(false, dailyNews.getStories());
    }

    @Override
    public void showMoreNews(ZhihuDailyNews dailyNews) {
        fillData(true, dailyNews.getStories());
    }

    @Override
    public void showTopStory(List<ZhihuDailyStory> dailyStoryList) {

    }
}
