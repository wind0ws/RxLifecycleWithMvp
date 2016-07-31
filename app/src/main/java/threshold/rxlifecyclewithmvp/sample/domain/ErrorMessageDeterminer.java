package threshold.rxlifecyclewithmvp.sample.domain;

import android.content.Context;

import java.io.IOError;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import threshold.rxlifecyclewithmvp.sample.R;

/**
 * Created by threshold on 16/7/31.
 */
public class ErrorMessageDeterminer {

    protected Context mContext;
    public ErrorMessageDeterminer(Context context) {
        this.mContext = context;
    }

    public String getErrorMessage(Throwable e) {
        if (e instanceof IOError) {
            return mContext.getString(R.string.error_on_request);
        } else if (e instanceof ConnectException) {
            return mContext.getString(R.string.connect_failed);
        } else if (e instanceof SocketTimeoutException) {
            return mContext.getString(R.string.request_timeout);
        }
        return "Unknown Error";
    }

}
