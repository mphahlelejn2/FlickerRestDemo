package com.jeffreymphahlele.jeffdemo.util;

import com.jeffreymphahlele.jeffdemo.models.Photo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.ArrayList;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class UtilsTest {

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Utils.class);
    }

@Test
    public void getUrlPhotoListFromListOfPhotoObjects() {

        ArrayList<Photo> photosList=getPhotoList();
        ArrayList<String> list= Utils.getUrlPhotoListFromListOfPhotoObjects(photosList);
        Assert.assertNotNull(list);
    }

    private ArrayList<Photo> getPhotoList() {

        ArrayList<Photo> photosList=new  ArrayList<>();
        Photo photo1=new Photo();
        photo1.setFarm(1);
        photo1.setId("43380648002");
        photo1.setOwner("139187437@N04");
        photo1.setSecret("a6395c8c0a");
        photo1.setTitle("DSC08764");

        Photo photo2=new Photo();
        photo2.setFarm(1);
        photo2.setId("41620442070");
        photo2.setOwner("25343763@N05");
        photo2.setSecret("a033776001");
        photo2.setTitle("MIN_6022");

        Photo photo3=new Photo();
        photo3.setFarm(1);
        photo3.setId("29557588168");
        photo3.setOwner("139187437@N04");
        photo3.setSecret("8af2e29272");
        photo3.setTitle("DSC08763");
        photosList.add(photo1);
        photosList.add(photo2);
        photosList.add(photo3);

        return photosList;
    }

    @Test
    public void getUrlFromPhotoClass() {
        Photo photo=getPhotoList().get(2);
        Assert.assertNotNull(photo);
         String url="https://farm1.staticflickr.com/915/29557588168_8af2e29272.jpg";
         Assert.assertTrue(Utils.getUrlFromPhotoClass(photo).equals(url));
    }
}