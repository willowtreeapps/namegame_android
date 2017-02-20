package com.willowtreeapps.namegame.network.api;

import android.os.Build;
import android.os.Parcel;

import com.google.gson.Gson;
import com.willowtreeapps.namegame.BuildConfig;
import com.willowtreeapps.namegame.network.api.model.Person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class PersonTest {

    private Gson gson;
    private String testString;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        testString = "{\n" +
                "      \"id\": \"4NCJTL13UkK0qEIAAcg4IQ\",\n" +
                "      \"type\": \"people\",\n" +
                "      \"slug\": \"joel-garrett\",\n" +
                "      \"jobTitle\": \"Senior Software Engineer\",\n" +
                "      \"firstName\": \"Joel\",\n" +
                "      \"lastName\": \"Garrett\",\n" +
                "      \"headshot\": {\n" +
                "        \"type\": \"image\",\n" +
                "        \"mimeType\": \"image/jpeg\",\n" +
                "        \"id\": \"4Mv2CONANym46UwuuCIgK\",\n" +
                "        \"url\": \"//images.contentful.com/3cttzl4i3k1h/4Mv2CONANym46UwuuCIgK/cbeb43c93a843a43c07b1de9954795e2/headshot_joel_garrett.jpg\",\n" +
                "        \"alt\": \"headshot joel garrett\",\n" +
                "        \"height\": 340,\n" +
                "        \"width\": 340\n" +
                "      },\n" +
                "      \"socialLinks\": [\n" +
                "        \n" +
                "      ]\n" +
                "    }";
    }

    @Test
    public void testFromGson() throws Exception {
        Person person = gson.fromJson(testString, Person.class);

        // Test name
        Assert.assertEquals("First name should match", "Joel", person.getFirstName());
        Assert.assertEquals("Last name should match", "Garrett", person.getLastName());

        // Test headshot url
        Assert.assertEquals("Headshot url should match",
                "//images.contentful.com/3cttzl4i3k1h/4Mv2CONANym46UwuuCIgK/cbeb43c93a843a43c07b1de9954795e2/headshot_joel_garrett.jpg",
                person.getHeadshot().getUrl());

    }

    @Test
    public void testWriteToParcel() throws Exception {
        Parcel parcel = Parcel.obtain();

        //Write ourselves to the parcel
        Person person = gson.fromJson(testString, Person.class);
        person.writeToParcel(parcel, 0);

        // After you're done with writing, you need to reset the parcel for reading:
        parcel.setDataPosition(0);

        // Reconstruct object from parcel and asserts:
        person = Person.CREATOR.createFromParcel(parcel);

        // Test name
        Assert.assertEquals("First name should match", "Joel", person.getFirstName());
        Assert.assertEquals("Last name should match", "Garrett", person.getLastName());

        // Test headshot url
        Assert.assertEquals("Headshot url should match",
                "//images.contentful.com/3cttzl4i3k1h/4Mv2CONANym46UwuuCIgK/cbeb43c93a843a43c07b1de9954795e2/headshot_joel_garrett.jpg",
                person.getHeadshot().getUrl());
    }
}