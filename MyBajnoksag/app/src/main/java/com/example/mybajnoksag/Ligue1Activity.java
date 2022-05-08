package com.example.mybajnoksag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class Ligue1Activity extends AppCompatActivity {

    private static final String LOG_TAG = Ligue1Activity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth auth;

    EditText newTeamNameEditText;
    EditText newTeamWinEditText;
    EditText newTeamDrawEditText;
    EditText newTeamLoseEditText;
    EditText newTeamPointsEditText;

    private FirebaseFirestore firestore;
    private CollectionReference items;
    private ArrayList<Team> itemsData;
    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private int gridNumber = 1;
    private Integer itemLimit = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligue1);

        newTeamNameEditText = findViewById(R.id.newTeamName);
        newTeamWinEditText = findViewById(R.id.newTeamWin);
        newTeamDrawEditText = findViewById(R.id.newTeamDraw);
        newTeamLoseEditText = findViewById(R.id.newTeamLose);
        newTeamPointsEditText = findViewById(R.id.newTeamPoints);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        itemsData = new ArrayList<>();

        adapter = new TeamAdapter(this, itemsData);

        recyclerView.setAdapter(adapter);


        firestore = FirebaseFirestore.getInstance();
        items = firestore.collection("Ligue1Teams");
        getTeams();
//        initializeData();
    }

//    private void initializeData() {
//        // Get the resources from the XML file.
//        String[] teamNames = getResources()
//                .getStringArray(R.array.laliga_team_names);
//        String[] teamWin = getResources()
//                .getStringArray(R.array.laliga_team_win);
//        String[] teamLose = getResources()
//                .getStringArray(R.array.laliga_team_lose);
//        String[] teamDraw = getResources()
//                .getStringArray(R.array.laliga_team_draw);
//        String[] teamPoints = getResources()
//                .getStringArray(R.array.laliga_team_points);
//
//        // Create the ArrayList of Sports objects with the titles and
//        // information about each sport.
//        for (int i = 0; i < teamNames.length; i++) {
//            items.add(new Team(
//                    teamNames[i],
//                    teamWin[i],
//                    teamLose[i],
//                    teamDraw[i],
//                    teamPoints[i]));
//        }
//
//        adapter.notifyDataSetChanged();
//    }

    private void getTeams(){
        itemsData.clear();
        items.orderBy("points", Query.Direction.DESCENDING).limit(itemLimit).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                Team item = document.toObject(Team.class);
                itemsData.add(item);
            }

            if(itemsData.size() == 0){
//                initializeData();
                getTeams();
            }

            adapter.notifyDataSetChanged();
        });
    }

    public void onBack(View view) {
        finish();
    }

    public void addTeam(View view) {
        String newTeamName = newTeamNameEditText.getText().toString();
        String newTeamWin = newTeamWinEditText.getText().toString();
        String newTeamDraw = newTeamDrawEditText.getText().toString();
        String newTeamLose = newTeamLoseEditText.getText().toString();
        String newTeamPoints = newTeamPointsEditText.getText().toString();

        if(Integer.parseInt(newTeamPoints) < 10){
            Log.d(LOG_TAG, "Sikertelen csapat hozzáadás");
            Toast.makeText(Ligue1Activity.this, "Sikertelen csapat hozzáadás: Ebben a szakaszban már legalább 10 pontja kell hogy legyen a csapatnak", Toast.LENGTH_LONG).show();
        }else{
            Log.d(LOG_TAG, "Sikeres csapat hozzáadás");
            Toast.makeText(Ligue1Activity.this, "Sikeres csapat hozzáadás, lépj vissza majd nyisd meg megint a ligát hogy lásd az új csapatod", Toast.LENGTH_LONG).show();
            items.add(new Team(
                    newTeamName,
                    newTeamWin,
                    newTeamDraw,
                    newTeamLose,
                    newTeamPoints));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}