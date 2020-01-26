package com.example.instagramcloneparse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> userNamesFromParse;
    ArrayList<String> userCommentsFromParse;
    ArrayList<Bitmap> userImagesFromParse;
    PostClass postClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listView);

        userNamesFromParse      = new ArrayList<>();
        userCommentsFromParse   = new ArrayList<>();
        userImagesFromParse     = new ArrayList<>();

        postClass = new PostClass(userNamesFromParse, userCommentsFromParse, userImagesFromParse, this);

        listView.setAdapter(postClass);

        download();
    }

    public void download() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e != null) {

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {

                    if(objects.size() > 0) {

                        for(final ParseObject object : objects) {

                            ParseFile parseFile = (ParseFile) object.get("Image");

                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {

                                    if(e == null && data != null) {

                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0, data.length);
                                        userImagesFromParse.add(bitmap);
                                        userNamesFromParse.add(object.getString("Username"));
                                        userCommentsFromParse.add(object.getString("Comment"));

                                        postClass.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.add_post) {

            // Intent
            Intent intentAddPostImage = new Intent(getApplicationContext(), UploadPostActivity.class);
            startActivity(intentAddPostImage);

        } else if(item.getItemId() == R.id.logout) {

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    if(e != null) {

                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {

                        // Intent
                        Intent intentLogout =  new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intentLogout);
                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
