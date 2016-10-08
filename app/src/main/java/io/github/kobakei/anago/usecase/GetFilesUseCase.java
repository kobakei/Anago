package io.github.kobakei.anago.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.Content;
import io.github.kobakei.anago.repository.ContentRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */

public class GetFilesUseCase {

    private final ContentRepository contentRepository;

    @Inject
    public GetFilesUseCase(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Single<List<Content>> run(String user, String repo, String path) {
        return contentRepository.getFilesByDir(user, repo, path);
    }

}
