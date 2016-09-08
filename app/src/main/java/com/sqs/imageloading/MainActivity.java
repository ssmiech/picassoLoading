package com.sqs.imageloading;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private LoadTwoHighResImagesConcurentFragmentFactory loadTwoHighResImagesConcurentFragmentFactory;
    private LoadOneHighResImageFragmentFactory loadOneHighResImageFragmentFactory;
    private LoadLowResHighResImagesConcurentFragmentFactory loadLowResHighResImagesConcurentFragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTwoHighResImagesConcurentFragmentFactory = new LoadTwoHighResImagesConcurentFragmentFactory();
        loadOneHighResImageFragmentFactory = new LoadOneHighResImageFragmentFactory();
        loadLowResHighResImagesConcurentFragmentFactory = new LoadLowResHighResImagesConcurentFragmentFactory();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        transitionFragment(loadTwoHighResImagesConcurentFragmentFactory.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.high_res_high_res_concurrent:
                setTitle("Two concurrent high res images");
                transitionFragment(loadTwoHighResImagesConcurentFragmentFactory.newInstance());
                break;
            case R.id.one_high_res:
                setTitle("One high res image");
                transitionFragment(loadOneHighResImageFragmentFactory.newInstance());
                break;
            case R.id.low_res_high_res_concurrent:
                setTitle("Concurrent low res + high");
                transitionFragment(loadLowResHighResImagesConcurentFragmentFactory.newInstance());
        }

        return super.onOptionsItemSelected(item);
    }

    private void transitionFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, "aa");
        fragmentTransaction.commit();
    }
}
