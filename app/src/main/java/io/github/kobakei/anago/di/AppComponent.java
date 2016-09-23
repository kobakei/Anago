package io.github.kobakei.anago.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * アプリケーションのインジェクタ
 * Created by keisuke on 2016/09/18.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    /**
     * このインジェクタをベースに、Activity用のインジェクタを生成する
     * @param activityModule
     * @return
     */
    ActivityComponent activityComponent(ActivityModule activityModule);

    /**
     * このインジェクタをベースに、Fragment用のインジェクタを生成する
     * @param fragmentModule
     * @return
     */
    FragmentComponent fragmentComponent(FragmentModule fragmentModule);
}
