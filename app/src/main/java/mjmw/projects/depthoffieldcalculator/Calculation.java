package mjmw.projects.depthoffieldcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import mjmw.projects.depthoffieldcalculator.model.DepthOfFieldCalculator;
import mjmw.projects.depthoffieldcalculator.model.Lens;
import mjmw.projects.depthoffieldcalculator.model.LensManager;

public class Calculation extends AppCompatActivity {
    private EditText distance1;
    private EditText aperture1;
    private EditText coc1;
    private TextView nearfocal1;
    private TextView farfocal1;
    private TextView dof1;
    private TextView hyperfocal1;
    private TextView detail;
    private double maxAperture;
    private int focalLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depthcalculator);

        Intent i = getIntent();
        String make = i.getStringExtra("make");
        maxAperture = i.getDoubleExtra("maxAperture",0);
        focalLength = i.getIntExtra("focalLength",0);
        coc1 = findViewById(R.id.coc);
        distance1 = findViewById(R.id.distance);
        aperture1 = findViewById(R.id.aperture);
        nearfocal1 = findViewById(R.id.nearfocal);
        farfocal1 = findViewById(R.id.farfocal);
        dof1 = findViewById(R.id.dof);
        hyperfocal1 = findViewById(R.id.hyperfocal);
        detail = findViewById(R.id.details);
        detail.setText(make + " " + focalLength + "mm " + maxAperture+"F");

        coc1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double dis = Double.parseDouble(distance1.getText().toString());
                double aper = Double.parseDouble(aperture1.getText().toString());
                double circle = Double.parseDouble(coc1.getText().toString());
                DepthOfFieldCalculator DOFC = new DepthOfFieldCalculator(focalLength, dis, aper, circle);
                double near = DOFC.getNearFocalPoint();
                double far = DOFC.getFarFocalPoint();
                double depth = DOFC.getDepthOfField();
                double hyper = DOFC.getHyperFocalDistance();
                nearfocal1.setText(formatM(near) + "m");
                farfocal1.setText(formatM(far) + "m");
                dof1.setText(formatM(depth) + "m");
                hyperfocal1.setText(formatM(hyper) + "m");
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarID);
        setSupportActionBar(toolbar);

    }



    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    //Inspired from https://www.youtube.com/watch?v=62y6Ad2SJEQ
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.calculator_app_bar_menu,menu);
        return true;
    }
    //

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_back:
                Toast.makeText(this, "Back to menu.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Calculation.this, MainActivity.class);
                startActivityForResult(intent,42);
                return true;

            case R.id.action_settings:
                if(Double.parseDouble(distance1.getText().toString()) <= 0 || Double.parseDouble(aperture1.getText().toString()) < 1.4 || Double.parseDouble(coc1.getText().toString()) < 0){
                    distance1.setText("Distance to Subject");
                    aperture1.setText("Aperture");
                    coc1.setText("0.029");
                    nearfocal1.setText("Invalid Value");
                    farfocal1.setText("Invalid Value");
                    dof1.setText("Invalid Value");
                    hyperfocal1.setText("Invalid Value");
                    Toast.makeText(this,"ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
                }
                else if(maxAperture > Double.parseDouble(aperture1.getText().toString())){
                    nearfocal1.setText("Invalid Value");
                    farfocal1.setText("Invalid Value");
                    dof1.setText("Invalid Value");
                    hyperfocal1.setText("Invalid Value");
                    Toast.makeText(this,"ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Data Calculated!", Toast.LENGTH_SHORT).show();
                    double dis = Double.parseDouble(distance1.getText().toString());
                    double aper = Double.parseDouble(aperture1.getText().toString());
                    double circle = Double.parseDouble(coc1.getText().toString());
                    DepthOfFieldCalculator DOFC = new DepthOfFieldCalculator(focalLength, dis, aper, circle);
                    double near = DOFC.getNearFocalPoint();
                    double far = DOFC.getFarFocalPoint();
                    double depth = DOFC.getDepthOfField();
                    double hyper = DOFC.getHyperFocalDistance();
                    nearfocal1.setText(formatM(near) + "m");
                    farfocal1.setText(formatM(far) + "m");
                    dof1.setText(formatM(depth) + "m");
                    hyperfocal1.setText(formatM(hyper) + "m");
                }

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
