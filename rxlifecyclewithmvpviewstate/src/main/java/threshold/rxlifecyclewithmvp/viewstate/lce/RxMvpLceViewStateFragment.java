package threshold.rxlifecyclewithmvp.viewstate.lce;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.delegate.BaseMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;

import threshold.rxlifecyclewithmvp.lce.RxMvpLceFragment;

/**
 * A {mylink RxMvpLceFragment} with {mylink ViewState} support.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class RxMvpLceViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends RxMvpLceFragment<CV, M, V, P> implements MvpLceView<M>,
        BaseMvpViewStateDelegateCallback<V, P> {

    /**
     * The viewstate will be instantiated by calling {mylink #createViewState()} in {mylink
     * #onViewCreated(View, Bundle)}. Don't instantiate it by hand.
     */
    protected LceViewState<M, V> viewState;

    /**
     * A flag that indicates if the viewstate tires to restore the view right now.
     */
    private boolean restoringViewState = false;

    /**
     * Create the view state object of this class
     */
    public abstract LceViewState<M, V> createViewState();

    @Override protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new FragmentMvpViewStateDelegateImpl<V, P>(this);
        }

        return mvpDelegate;
    }

    @Override public ViewState getViewState() {
        return viewState;
    }

    @Override public void setViewState(ViewState<V> viewState) {
        this.viewState = (LceViewState<M, V>) viewState;
    }

    @Override public void showContent() {
        super.showContent();
        viewState.setStateShowContent(getData());
    }

    @Override public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        viewState.setStateShowError(e, pullToRefresh);
    }

    @Override public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        viewState.setStateShowLoading(pullToRefresh);
    }

    @Override public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // Not needed in general. override it in subclass if you need this callback
    }

    @Override public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override protected void showLightError(String msg) {
        if (isRestoringViewState()) {
            return; // Do not display toast again while restoring viewstate
        }

        super.showLightError(msg);
    }

    /**
     * Get the data that has been set before in {mylink #setData(Object)}
     * <p>
     * <b>It's necessary to return the same data as set before to ensure that {mylink ViewState} works
     * correctly</b>
     * </p>
     *
     * @return The data
     */
    public abstract M getData();
}
