package mjmw.projects.depthoffieldcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mjmw.projects.depthoffieldcalculator.model.Lens;
import mjmw.projects.depthoffieldcalculator.model.LensManager;

public class MainActivity extends AppCompatActivity {
    LensManager manager = LensManager.getInstance();
    public static int test;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.ToolbarID);
        setSupportActionBar(toolbar);

        populateListView();
        registerClickCallback();
        FAButton();
    }

@Override
public void onResume(){
    super.onResume();
    Intent i = getIntent();
    test = i.getIntExtra("test",0);
    if(test == 1) {
        String value1 = i.getStringExtra("parameter name 1");
        int value2 = i.getIntExtra("parameter name 2", 0);
        double value3 = i.getDoubleExtra("parameter name 3", 0);
        manager.add(new Lens(value1, value3, value2));
        populateListView();
        test = 0;
    }
}

    private void FAButton() {
        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = addLense.makeLaunchIntent(MainActivity.this);
                startActivity(i);
            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setEmptyView(View view){

    }

    private void populateListView() {
        List<String> lensList = new ArrayList<>();
        for (Lens lenses : manager) {
            lensList.add(lenses.getMake() + " " + lenses.getFocalLength() + "mm " + lenses.getMaximumAperture() + "F");
        }
        String[] stringLens = new String[lensList.size()];
        stringLens = lensList.toArray(stringLens);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.lens_option,
                stringLens);

        ListView list = findViewById(R.id.lensList);
        list.setEmptyView(findViewById(R.id.empty_list_item_subtext));
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.lensList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Intent i = new Intent(MainActivity.this,Calculation.class);
                i.putExtra("make", manager.getLens(position).getMake());
                i.putExtra("maxAperture", manager.getLens(position).getMaximumAperture());
                i.putExtra("focalLength", manager.getLens(position).getFocalLength());
                startActivity(i);
            }
        });
    }
}
