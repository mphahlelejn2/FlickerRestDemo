package com.jeffreymphahlele.jeffdemo;

import com.jeffreymphahlele.jeffdemo.models.Contents;
import com.jeffreymphahlele.jeffdemo.models.Photo;
import com.jeffreymphahlele.jeffdemo.models.ServerRespond;
import java.util.ArrayList;


public class Data {

    public static ServerRespond getServerRespond(){
        ServerRespond serverRespond =new ServerRespond();
        serverRespond.setContents(getContents());
        return serverRespond;

    }
    private static Contents getContents(){
        Contents c=new Contents();
        c.setPhotoList(getPhotoList());
        return c;
    }
    private static ArrayList<Photo> getPhotoList() {

        ArrayList<Photo> photosList=new  ArrayList<>();
        Photo photo1=new Photo();
        photo1.setFarm(1);
        photo1.setId("43380648002");
        photo1.setOwner("139187437@N04");
        photo1.setSecret("a6395c8c0a");
        photo1.setTitle("DSC08764");
        //return
        // { "id": "29557588168", "owner": "139187437@N04", "secret": "8af2e29272", "server": "915", "farm": 1, "title": "DSC08763", "ispublic": 1, "isfriend": 0, "isfamily": 0 },
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

}
