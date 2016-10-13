package io.github.kobakei.anago.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * アプリケーションのインジェクタ
 * Created by keisuke on 2016/09/18.
 */
@Singleton
@Component(modules = {AppModule.class, EventBusModule.class})
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

    /**
     * このインジェクタをベースに、Service用のインジェクタを生成する
     * @param serviceModule
     * @return
     */
    ServiceComponent serviceComponent(ServiceModule serviceModule);

    /**
     * このインジェクタをベースに、View用のインジェクタを生成する
     * @param viewModule
     * @return
     */
    ViewComponent viewComponent(ViewModule viewModule);
}
