package com.jeffreymphahlele.jeffdemo.photosFolder;

import com.jeffreymphahlele.jeffdemo.repo.IRepository;
import com.jeffreymphahlele.jeffdemo.rxjava.BaseSchedulerProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class FolderModule {

    @Binds
    abstract IFolder.View getView(FolderFragment folderFragment );

    @Provides
    static IFolder.Presenter getPhotosPresente(IFolder.View view, IRepository repository, BaseSchedulerProvider provider){
        return new FolderPresenterImpl(view,repository,provider);
    }

}