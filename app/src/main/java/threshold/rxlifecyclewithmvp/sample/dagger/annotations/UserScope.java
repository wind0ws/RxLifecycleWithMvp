package threshold.rxlifecyclewithmvp.sample.dagger.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by threshold on 16/7/30.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
