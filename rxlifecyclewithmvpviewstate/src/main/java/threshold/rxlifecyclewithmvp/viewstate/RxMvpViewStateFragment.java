package threshold.rxlifecyclewithmvp.viewstate;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.BaseMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import threshold.rxlifecyclewithmvp.RxMvpFragment;

/**
 * This is a enhancement of {@link RxMvpFragment} that introduces the
 * support of {@link com.hannesdorfmann.mosby.mvp.viewstate.ViewState}.
 * <p>
 * You can change the behaviour of what to do if the viewstate is empty (usually if the fragment
 * creates the viewState for the very first time and therefore has no state / data to restore) by
 * overriding {@link #onNewViewStateInstance()}
 * </p>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class RxMvpViewStateFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends RxMvpFragment<V, P> implements BaseMvpViewStateDelegateCallback<V, P> {

    /**
     * The viewstate will be instantiated by calling {@link #createViewState()} in {@link
     * #onViewCreated(View, Bundle)}. Don't instantiate it by hand.
     */
    protected ViewState<V> viewState;

    /**
     * A simple flag that indicates if the restoring ViewState  is in progress right now.
     */
    private boolean restoringViewState = false;

    /**
     * Create the view state object of this class
     */
    public abstract ViewState createViewState();

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
        this.viewState = viewState;
    }

    @Override public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // not needed. You could override this is subclasses if needed
    }
}