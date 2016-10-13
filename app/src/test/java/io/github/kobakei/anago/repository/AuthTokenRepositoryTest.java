package io.github.kobakei.anago.repository;

import android.content.Context;
import android.os.Build;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.github.kobakei.anago.BuildConfig;
import io.github.kobakei.anago.TestAnagoApplication;
import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.net.GitHubApiClient;
import io.github.kobakei.anago.net.body.AuthorizationBody;
import rx.Single;

/**
 * リポジトリのテスト
 * 依存しているAPIクライアントとDAOをモック化しています
 * Created by keisuke on 2016/10/13.
 */
@RunWith(value = RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN,
        application = TestAnagoApplication.class)
public class AuthTokenRepositoryTest {

    AuthTokenRepository authTokenRepository;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;

        // APIクライアントのモック
        GitHubApiClient client = mock(GitHubApiClient.class);
        AuthToken authToken = new AuthToken(1L, "token", null, null, null);
        when(client.putAuthorization(anyString(), anyString(), anyString(), any(AuthorizationBody.class)))
                .thenReturn(Single.just(authToken));

        // DAOのモック
        AuthTokenDao authTokenDao = mock(AuthTokenDao.class);
        when(authTokenDao.upsert(any(AuthToken.class))).thenReturn(Single.just(1L));

        authTokenRepository = new AuthTokenRepository(context, client, authTokenDao);
    }

    @Test
    public void getOrCreate_isSuccess() {
        String name = "user";
        String password = "password";
        AuthToken authToken = authTokenRepository.getOrCreate(name, password)
                .toBlocking()
                .value();
        Assert.assertEquals("token", authToken.token);
    }

}
