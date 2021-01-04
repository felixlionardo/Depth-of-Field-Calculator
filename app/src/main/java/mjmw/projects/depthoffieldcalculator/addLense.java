package mjmw.projects.depthoffieldcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class addLense extends AppCompatActivity {
    private Toolbar toolbar;

    public static Intent makeLaunchIntent(Context c){
        Intent i = new Intent(c,addLense.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lense);

        toolbar = findViewById(R.id.ToolbarID);
        setSupportActionBar(toolbar);


    }

    //Inspired from https://www.youtube.com/watch?v=62y6Ad2SJEQ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);
        return true;
    }
    //

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_back:
                Toast.makeText(this, "Back to menu.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addLense.this, MainActivity.class);
                startActivityForResult(intent,42);
                return true;

            case R.id.action_settings:
                boolean isdigit1;
                boolean isdigit2;
                EditText userTextEntry1 = (EditText) findViewById(R.id.editText);
                String userData1 = userTextEntry1.getText().toString();
                EditText userTextEntry2 = (EditText) findViewById(R.id.editText2);
                String userData2 = userTextEntry2.getText().toString();
                try {
                    BigDecimal n = new BigDecimal(userData2);
                    isdigit1 = true;
                } catch (Exception e) {
                    isdigit1 = false;
                }
                if(isdigit1 == false) {
                    Toast.makeText(this, "ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
                }
                else if (isdigit1 == true) {
                    EditText userTextEntry3 = (EditText) findViewById(R.id.editText3);
                    String userData3 = userTextEntry3.getText().toString();
                    try {
                        BigDecimal n = new BigDecimal(userData3);
                        isdigit2 = true;
                    } catch (Exception e) {
                        isdigit2 = false;
                    }
                    if (isdigit2 == false) {
                        Toast.makeText(this, "ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int userNumber2 = Integer.parseInt(userData2);
                        double userNumber3 = Double.parseDouble(userData3);
                        if (userData1.length() <= 0 || userNumber2 <= 0 || userNumber3 < 1.4) {
                            Toast.makeText(this, "ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            int test = 1;
                            Intent i = new Intent(addLense.this, MainActivity.class);
                            i.putExtra("parameter name 1", userData1);
                            i.putExtra("parameter name 2", userNumber2);
                            i.putExtra("parameter name 3", userNumber3);
                            i.putExtra("test", test);
                            Toast.makeText(this, "Added new lens!", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
