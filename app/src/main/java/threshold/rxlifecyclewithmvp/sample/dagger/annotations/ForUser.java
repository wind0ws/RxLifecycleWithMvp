package threshold.rxlifecyclewithmvp.sample.dagger.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * See {@link javax.inject.Named named}
 * This just like @Named("ForUser")
 * Created by threshold on 16/7/30.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ForUser {
}
