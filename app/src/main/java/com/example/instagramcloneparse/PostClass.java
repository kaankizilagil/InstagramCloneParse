package com.example.instagramcloneparse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> userName;
    private final ArrayList<String> userComment;
    private final ArrayList<Bitmap> userImage;
    private final Activity context;

    public PostClass(ArrayList<String> userName, ArrayList<String> userComment, ArrayList<Bitmap> userImage, Activity context) {

        super(context, R.layout.custom_view, userName);
        this.userName = userName;
        this.userComment = userComment;
        this.userImage = userImage;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View customView = layoutInflater.inflate(R.layout.custom_view, null,true);

        TextView userNameText = customView.findViewById(R.id.customViewUsernameTextView);
        TextView commentText = customView.findViewById(R.id.customViewCommentTextView);
        ImageView imageView = customView.findViewById(R.id.customViewImageView);

        userNameText.setText(userName.get(position));
        commentText.setText(userComment.get(position));
        imageView.setImageBitmap(userImage.get(position));

        return customView;
    }
}
