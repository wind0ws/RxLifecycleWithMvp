package threshold.rxlifecyclewithmvp.sample.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import threshold.rxlifecyclewithmvp.sample.App;
import threshold.rxlifecyclewithmvp.sample.R;
import threshold.rxlifecyclewithmvp.sample.dagger.components.DaggerCityFragmentComponent;
import threshold.rxlifecyclewithmvp.sample.dagger.modules.CityModule;
import threshold.rxlifecyclewithmvp.sample.domain.ErrorMessageDeterminer;
import threshold.rxlifecyclewithmvp.sample.presentation.CityPresenter;
import threshold.rxlifecyclewithmvp.sample.ui.adapter.MyCityRecyclerViewAdapter;
import threshold.rxlifecyclewithmvp.sample.ui.fragment.dummy.DummyContent.DummyItem;
import threshold.rxlifecyclewithmvp.sample.ui.viewinterface.CityView;
import threshold.rxlifecyclewithmvp.viewstate.lce.RxMvpLceViewStateFragment;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CityFragment extends RxMvpLceViewStateFragment<SwipeRefreshLayout,List<DummyItem>,CityView,CityPresenter> implements SwipeRefreshLayout.OnRefreshListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private Unbinder mUnbinder;
    private MyCityRecyclerViewAdapter mAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    CityPresenter mCityPresenter;

    @Inject
    ErrorMessageDeterminer mErrorMessageDeterminer;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityFragment() {
    }

    @Override
    public CityPresenter createPresenter() {
        return mCityPresenter;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CityFragment newInstance(int columnCount) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        injectDependency();
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void injectDependency() {
        DaggerCityFragmentComponent.builder()
                .appComponent(App.getApp(getContext()).getAppComponent())
                .cityModule(new CityModule(getContext()))
                .build()
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set the RecyclerView
        if (mRecyclerView!=null) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter= new MyCityRecyclerViewAdapter(new ArrayList<DummyItem>(), mListener);
            mRecyclerView.setAdapter(mAdapter);
        }
        contentView.setOnRefreshListener(this);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return mErrorMessageDeterminer.getErrorMessage(e);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void setData(List<DummyItem> data) {
        mAdapter.setData(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }



    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public LceViewState<List<DummyItem>, CityView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<DummyItem> getData() {
        return mAdapter.getData();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        Logger.e(e,"Error");
    }


    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
