package com.jeffreymphahlele.jeffdemo.photosFolder;
import com.jeffreymphahlele.jeffdemo.Data;
import com.jeffreymphahlele.jeffdemo.Rx.SchedulerProviderTest;
import com.jeffreymphahlele.jeffdemo.models.ServerRespond;
import com.jeffreymphahlele.jeffdemo.repo.IRepository;
import com.jeffreymphahlele.jeffdemo.repo.UrlManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import io.reactivex.Maybe;
import static org.mockito.Mockito.when;

public class FolderPresenterImplTest {

    @Mock
    private IFolder.View view;
    @Mock
    private IRepository repository;
    private SchedulerProviderTest scheduler;
    private FolderPresenterImpl folderPresenterImpl;

    private String lon="";
    private String lat="";


    @Before
    public void setUp() throws Exception {
        scheduler=SchedulerProviderTest.getInstance();
        MockitoAnnotations.initMocks(this);
        folderPresenterImpl=new FolderPresenterImpl(view,repository,scheduler);
    }

    @Test
    public void getListOfPhotosCloseToUserLocation_Results_Ok() {

        ServerRespond serverRespond= Data.getServerRespond();
        when(repository.getPlaceList(UrlManager.api_key,lat, lon)).thenReturn(Maybe.just(serverRespond));
        //then
          folderPresenterImpl.getListOfPhotosCloseToUserLocation(lat, lon);
        //then
        Mockito.verify(view).initLoadProgressDialog();
        Mockito.verify(view, Mockito.never()).errorLoadingImages();
        Mockito.verify(view).sendListOfPlaces(serverRespond.getContents().getPhotoList());
        Mockito.verify(view, Mockito.never()).emptyList();
    }


    @Test
    public void getListOfPhotosCloseToUserLocation_Results_Error()
    {
        Exception exception = new Exception();

        Mockito.when(repository.getPlaceList(UrlManager.api_key,lon,lat))
                .thenReturn(Maybe.<ServerRespond>error(exception));

        ServerRespond serverRespond= Data.getServerRespond();
        folderPresenterImpl.getListOfPhotosCloseToUserLocation(lat, lon);

        Mockito.verify(view).initLoadProgressDialog();
        Mockito.verify(view).errorLoadingImages();
        Mockito.verify(view, Mockito.never()).sendListOfPlaces(serverRespond.getContents().getPhotoList());
        Mockito.verify(view, Mockito.never()).emptyList();
    }

    @Test
    public void getListOfPhotosCloseToUserLocation_Results_Empty()
    {
        //Given
        Mockito.when(repository.getPlaceList(UrlManager.api_key,lon,lat)).thenReturn(Maybe.<ServerRespond>empty());
        //when
        ServerRespond serverRespond= Data.getServerRespond();
        folderPresenterImpl.getListOfPhotosCloseToUserLocation(lat, lon);
        //then
        Mockito.verify(view, Mockito.never()).errorLoadingImages();
        Mockito.verify(view, Mockito.never()).sendListOfPlaces(serverRespond.getContents().getPhotoList());

        Mockito.verify(view, Mockito.times(1)).emptyList();
        Mockito.verify(view).emptyList();
    }

    @Test
    public void getSortedFoldersByOwner() {
    }
}