package com.oneworld.claimit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.graphics.TypefaceCompatUtil.getTempFile;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDoList> toDoList = new ArrayList<ToDoList>();
    private ArrayList<ClaimItem> claimItemList = new ArrayList<ClaimItem>();

    private RecyclerView mCheckedListRecyclerView;
    private CheckListItemAdapter mCheckedListAdapter;

    private static int[] mClaimItemList = {
            R.drawable.kimbap,
            R.drawable.lays,
            R.drawable.playdoh,
            R.drawable.car_ride
    };
    private RecyclerView mClaimListRecyclerView;
    private ClaimListItemAdapter mClaimListAdapter;

    private ItemTouchHelper checkedListTouchHelper;
    private ItemTouchHelper claimListTouchHelper;

    private CurrentBalance currentBalance;

    private Button resetButton;
    private ImageButton addToDoButton;
    private ImageButton addClaimButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList.add(new ToDoList("Wake up at 9am"));
        toDoList.add(new ToDoList("Brush Teeth in the morning"));
        toDoList.add(new ToDoList("Read a book"));
        toDoList.add(new ToDoList("Boom cards"));
        toDoList.add(new ToDoList("Take a walk"));
        toDoList.add(new ToDoList("Sleep at 10pm"));

        claimItemList.add(new ClaimItem(createBitmap(R.drawable.kimbap)));
        claimItemList.add(new ClaimItem(createBitmap(R.drawable.lays)));
        claimItemList.add(new ClaimItem(createBitmap(R.drawable.playdoh)));
        claimItemList.add(new ClaimItem(createBitmap(R.drawable.car_ride)));

        currentBalance = new CurrentBalance((TextView) findViewById(R.id.currentBalance));

        mCheckedListRecyclerView = findViewById(R.id.checkedRecycledView);
        mCheckedListAdapter = new CheckListItemAdapter(currentBalance, toDoList);
        mCheckedListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCheckedListRecyclerView.setAdapter(mCheckedListAdapter);
        checkedListTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(mCheckedListAdapter));
        checkedListTouchHelper.attachToRecyclerView(mCheckedListRecyclerView);;

        mClaimListRecyclerView = findViewById(R.id.claimRecyclerView);
        mClaimListAdapter = new ClaimListItemAdapter(currentBalance, claimItemList);
        mClaimListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mClaimListRecyclerView.setAdapter(mClaimListAdapter);
        claimListTouchHelper = new ItemTouchHelper(new HorizontalSwipeToDeleteCallback(mClaimListAdapter));
        claimListTouchHelper.attachToRecyclerView(mClaimListRecyclerView);;

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Reset button clicked" , Toast.LENGTH_SHORT).show();
                mCheckedListAdapter.notifyDataSetChanged();
            }
        });
        addToDoButton = findViewById(R.id.addToDo);
        addToDoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Add TODO");

                final EditText input = new EditText(v.getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDoList.add(new ToDoList(input.getText().toString()));
                        mCheckedListAdapter.notifyItemInserted(toDoList.size());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        addClaimButton = findViewById(R.id.addClaim);
        addClaimButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
    }

    private Bitmap createBitmap(int drawableResource) {
        return BitmapFactory.decodeResource(getResources(), drawableResource);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            claimItemList.add(new ClaimItem(imageBitmap));
            mClaimListAdapter.notifyItemInserted(claimItemList.size());
        }
    }
}