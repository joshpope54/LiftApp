package dev.joshpope.lift;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.joshpope.lift.ui.exercises.ExercisesFragment;
import dev.joshpope.lift.ui.activity.ActivityFragment;
import dev.joshpope.lift.ui.workout.WorkoutFragment;
import dev.joshpope.lift.ui.workout.active.ActiveFragment;

public class MainActivity extends AppCompatActivity {

    public boolean workoutActive = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(myToolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchToFragmentHistory();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        if(item.getItemId() == R.id.navigation_history){
            switchToFragmentHistory();
            return true;
        }else if(item.getItemId() == R.id.navigation_workout){
            if(workoutActive){
                switchToFragmentActive();
            }else{
                switchToFragmentWorkout();
            }
            return true;
        }else if(item.getItemId() == R.id.navigation_exercises){
            switchToFragmentExercises();
            return true;
        }else{
            return false;
        }
    };

    public void switchToFragmentHistory() {
        getSupportActionBar().setTitle("Activity");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new ActivityFragment()).commit();
    }
    public void switchToFragmentWorkout() {
        getSupportActionBar().setTitle("Workout - Inactive");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new WorkoutFragment()).commit();
    }

    public void switchToFragmentActive() {
        workoutActive=true;
        getSupportActionBar().setTitle("Workout - Active");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new ActiveFragment()).commit();
    }

    public void cancelWorkout(){
        workoutActive=false;
        switchToFragmentWorkout();
        //TODO: Cancel Timer.
    }

    public void switchToFragmentExercises() {
        getSupportActionBar().setTitle("Exercises");

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new ExercisesFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings_icon) {
            // your code
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        }else if (id == R.id.profile_icon){
            Intent intent = new Intent(MainActivity.this, Profile.class);
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}