package com.jeffreymphahlele.jeffdemo.photoGridView;
import android.support.test.rule.ActivityTestRule;
import com.jeffreymphahlele.jeffdemo.photosFolder.FolderActivity;
import org.junit.Rule;


public class PhotosGridViewFragmentTest {

    @Rule
    public ActivityTestRule<FolderActivity> mActivityRule=new ActivityTestRule<>(FolderActivity.class);
}