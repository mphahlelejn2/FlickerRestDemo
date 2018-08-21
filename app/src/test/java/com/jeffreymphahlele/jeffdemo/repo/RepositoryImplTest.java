package com.jeffreymphahlele.jeffdemo.repo;

import com.jeffreymphahlele.jeffdemo.Data;
import com.jeffreymphahlele.jeffdemo.models.ServerRespond;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import static org.mockito.Mockito.when;
public class RepositoryImplTest {

    @Mock
    private APIClient client;
    private IRepository  repository;
    private String lon="";
    private String lat="";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository=new RepositoryImpl(client);
    }

    @Test
    public void getPlaceList_Results_Ok() {

        ServerRespond serverRespond= Data.getServerRespond();
        when(client.getPlaceList(UrlManager.api_key,lon,lat)).thenReturn(Maybe.just(serverRespond));

        //When
        TestObserver<ServerRespond> observer = new TestObserver<>();
        repository.getPlaceList(UrlManager.api_key,lon,lat).subscribe(observer);
        //then

        observer.assertNoErrors();
        observer.assertSubscribed();
        observer.assertComplete();
        observer.awaitTerminalEvent();

        ServerRespond s=  observer.values().get(0);
        Assert.assertEquals(s.getContents(), serverRespond.getContents());
        Assert.assertEquals(s.getContents().getPhotoList().get(1).getId(), serverRespond.getContents().getPhotoList().get(1).getId());

    }

    @Test
    public void getPlaceList_Results_Empty() {

        when(client.getPlaceList(UrlManager.api_key,lon,lat)).thenReturn(Maybe.<ServerRespond>empty());
        //When
        TestObserver<ServerRespond> observer = new TestObserver<>();
        repository.getPlaceList(UrlManager.api_key,lon,lat).subscribe(observer);
        //then

        observer.assertNoErrors();
        observer.assertSubscribed();
        observer.assertComplete();
        observer.awaitTerminalEvent();

        Assert.assertTrue(observer.values().isEmpty());

    }
    @Test
    public void getPlaceList_Results_Error() {

        Exception exception = new HttpException(
                Response.error(403, ResponseBody.create(MediaType.parse("application/json"), "Cannot execute request")));

        when(client.getPlaceList(UrlManager.api_key,lon,lat))
                .thenReturn(Maybe.<ServerRespond>error(exception));

        //When
        TestObserver<ServerRespond> observer = new TestObserver<>();
        repository.getPlaceList(UrlManager.api_key,lon,lat).subscribe(observer);
        //then

        //Then
        observer.awaitTerminalEvent();
        observer.assertError(HttpException.class);

    }

}