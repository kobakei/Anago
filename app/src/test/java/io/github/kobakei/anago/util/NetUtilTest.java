package io.github.kobakei.anago.util;

import android.os.Build;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.github.kobakei.anago.BuildConfig;
import io.github.kobakei.anago.TestAnagoApplication;

/**
 * Created by keisuke on 2016/10/13.
 */
@RunWith(value = RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN,
        application = TestAnagoApplication.class)
public class NetUtilTest {

    @Test
    public void getBasicHeader_isSuccess() {
        Assert.assertEquals("Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==", NetUtil.getBasicHeader("Aladdin", "open sesame"));
    }

}
