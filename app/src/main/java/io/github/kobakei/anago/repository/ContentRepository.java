package io.github.kobakei.anago.repository;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.Content;
import io.github.kobakei.anago.net.GitHubApiClient;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */
@Singleton
public class ContentRepository {

    private final GitHubApiClient gitHubApiClient;

    @Inject
    public ContentRepository(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    /**
     * 指定のディレクトリ内のファイルリストを取得する
     * @param user
     * @param repo
     * @param path
     * @return
     */
    public Single<List<Content>> getFilesByDir(@NonNull final String user,
                                               @NonNull final String repo,
                                               @NonNull final String path) {
        return gitHubApiClient.getDirContent(user, repo, path);
    }

    /**
     * 指定パスのファイルを取得する
     * @param user
     * @param repo
     * @param path
     * @return
     */
    public Single<Content> getFileByPath(@NonNull final String user,
                                         @NonNull final String repo,
                                         @NonNull final String path) {
        return gitHubApiClient.getFileContent(user, repo, path);
    }
}
