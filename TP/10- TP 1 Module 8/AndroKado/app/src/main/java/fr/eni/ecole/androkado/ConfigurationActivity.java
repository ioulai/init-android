package fr.eni.ecole.androkado;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import fr.eni.ecole.androkado.application.AndroKadoApplication;

public class ConfigurationActivity extends AppCompatActivity {

    private boolean tri;
    private boolean active;
    private float defaultPrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ActionBar bar = getActionBar();

        if(bar != null){
            bar.setTitle(R.string.menu_configuration);
        }

        SharedPreferences pref= getSharedPreferences(AndroKadoApplication.CONFIGURATION_PREF,MODE_PRIVATE);

       tri = pref.getBoolean("tri", false);
       defaultPrix  = pref.getFloat("defaultPrix", 50f);
       active = pref.getBoolean("active", false);

        gererConfiguration();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref= getSharedPreferences(AndroKadoApplication.CONFIGURATION_PREF,MODE_PRIVATE);

        EditText prix = findViewById(R.id.editDefaultPrix);
        defaultPrix = Float.parseFloat(prix.getText().toString());

        pref.edit()
                .putBoolean("tri", tri)
                .putBoolean("active", active)
                .putFloat("defaultPrix", defaultPrix)
                .apply();
    }

    /**
     *
     */
    private void gererConfiguration(){
        final Switch sTri = findViewById(R.id.switchTri);
        sTri.setChecked(tri);
        sTri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tri = sTri.isChecked();
            }
        });

        final Switch sActive = findViewById(R.id.switchTriActive);
        sActive.setChecked(active);
        sActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                active = sActive.isChecked();
            }
        });

        EditText prix = findViewById(R.id.editDefaultPrix);
        prix.setText(String.valueOf(defaultPrix));

    }
}
