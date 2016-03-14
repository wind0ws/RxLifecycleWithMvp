package threshold.rxlifecyclewithmvp.viewstate.lce;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;

import threshold.rxlifecyclewithmvp.lce.RxMvpLceAppCompatActivity;

/**
 * A {mylink RxMvpLceAppCompatActivity} with {mylink ViewState} support.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class RxMvpLceViewStateAppCompatActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends RxMvpLceAppCompatActivity<CV, M, V, P>
        implements MvpLceView<M>, ActivityMvpViewStateDelegateCallback<V, P> {

    protected LceViewState<M, V> viewState;
    protected boolean restoringViewState = false;

    @Override protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this);
        }

        return mvpDelegate;
    }

    @Override public ViewState<V> getViewState() {
        return viewState;
    }

    @Override public void setViewState(ViewState<V> viewState) {
        if (!(viewState instanceof LceViewState)) {
            throw new IllegalArgumentException(
                    "Only " + LceViewState.class.getSimpleName() + " are allowed as view state");
        }

        this.viewState = (LceViewState<M, V>) viewState;
    }

    @Override public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // not needed. You could override this is subclasses if needed
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

    @Override protected void showLightError(String msg) {
        if (isRestoringViewState()) {
            return; // Do not display toast again while restoring viewstate
        }

        super.showLightError(msg);
    }

    /**
     * Creates the viewstate
     *
     * @return a new ViewState
     */
    public abstract LceViewState<M, V> createViewState();

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