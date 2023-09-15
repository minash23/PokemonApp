package mina.app.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PokemonActivity extends AppCompatActivity {

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText name_input = findViewById(R.id.name_input);
            EditText national_number_input = findViewById(R.id.national_number_input);
            EditText species_input = findViewById(R.id.species_input);
            RadioButton male_button = findViewById(R.id.male_button);
            RadioButton female_button = findViewById(R.id.female_button);
            RadioButton unk_button = findViewById(R.id.unk_button);
            EditText height_input = findViewById(R.id.height_input);
            EditText weight_input = findViewById(R.id.weight_input);
            Spinner level_spinner = findViewById(R.id.level_spinner);
            EditText HP_input = findViewById(R.id.HP_input);
            EditText attack_input = findViewById(R.id.attack_input);
            EditText defense_input = findViewById(R.id.defense_input);

            TextView name_text = findViewById(R.id.name_text);
            TextView national_number_text = findViewById(R.id.national_number_text);
            TextView species_text = findViewById(R.id.species_text);
            TextView gender_text = findViewById(R.id.gender_text);
            TextView height_text = findViewById(R.id.height_text);
            TextView weight_text = findViewById(R.id.weight_text);
            TextView level_text = findViewById(R.id.level_text);
            TextView HP_text = findViewById(R.id.HP_text);
            TextView attack_text = findViewById(R.id.attack_text);
            TextView defense_text = findViewById(R.id.defense_text);
            int count = 0;

            if (Integer.parseInt(national_number_input.getText().toString()) < 0 || Integer.parseInt(national_number_input.getText().toString()) > 1010 || national_number_input.getText().toString().isEmpty()) {
                count++;
                national_number_text.setTextColor(Color.RED);
            }
            else{
                national_number_text.setTextColor(Color.BLACK);
            }
            if (name_input.getText().toString().length() < 3 || name_input.getText().toString().length() > 12) {
                count++;
                name_text.setTextColor(Color.RED);
            }else{
                name_text.setTextColor(Color.BLACK);
            }
            if (species_input.getText().toString().isEmpty()) {
                count++;
                species_text.setTextColor(Color.RED);
            }
            else{
                species_text.setTextColor(Color.BLACK);
            }
            if (unk_button.isChecked() == false && male_button.isChecked() == false && female_button.isChecked() == false) {
                count++;
                gender_text.setTextColor(Color.RED);
            }else{
                gender_text.setTextColor(Color.BLACK);
            }
            if (Integer.parseInt(HP_input.getText().toString()) < 1 || Integer.parseInt(HP_input.getText().toString()) > 362 || HP_input.getText().toString().isEmpty()) {
                count++;
                HP_text.setTextColor(Color.RED);
            }else{
                HP_text.setTextColor(Color.BLACK);
            }
            if (Integer.parseInt(attack_input.getText().toString()) < 5 || Integer.parseInt(attack_input.getText().toString()) > 562 || attack_input.getText().toString().isEmpty()) {
                count++;
                attack_text.setTextColor(Color.RED);
            }else{
                attack_text.setTextColor(Color.BLACK);
            }
            if (Integer.parseInt(defense_input.getText().toString()) < 5 || Integer.parseInt(defense_input.getText().toString()) > 614 || defense_input.getText().toString().isEmpty()) {
                count++;
                defense_text.setTextColor(Color.RED);
            }else{
                defense_text.setTextColor(Color.BLACK);
            }
            if (Double.parseDouble(height_input.getText().toString()) < 0.3 || Double.parseDouble(height_input.getText().toString()) > 19.99 || height_input.getText().toString().isEmpty()) {
                count++;
                height_text.setTextColor(Color.RED);
            }else{
                height_text.setTextColor(Color.BLACK);
            }
            if (Double.parseDouble(weight_input.getText().toString()) < 0.1 || Double.parseDouble(weight_input.getText().toString()) > 820.0 || weight_input.getText().toString().isEmpty()) {
                count++;
                weight_text.setTextColor(Color.RED);
            }else{
                weight_text.setTextColor(Color.BLACK);
            }
            /*
            if(level_spinner.getSelectedItem().toString().isEmpty()){
                count++;
                level_text.setTextColor(Color.RED);
            }
             */
            if (count > 0) {
                String badMessage = "Please Fix all Required Fields. ";
                Toast.makeText(PokemonActivity.this, badMessage, Toast.LENGTH_SHORT).show();
            } else if (count == 0) {
                String message = "We Have added your Pokemon to the Collection!";
                Toast.makeText(PokemonActivity.this, message, Toast.LENGTH_SHORT).show();
                name_text.setTextColor(Color.BLACK);
                species_text.setTextColor(Color.BLACK);
                national_number_text.setTextColor(Color.BLACK);
                gender_text.setTextColor(Color.BLACK);
                height_text.setTextColor(Color.BLACK);
                weight_text.setTextColor(Color.BLACK);
                level_text.setTextColor(Color.BLACK);
                HP_text.setTextColor(Color.BLACK);
                attack_text.setTextColor(Color.BLACK);
                defense_text.setTextColor(Color.BLACK);
            }
        }
    };

    public void reset(View view) {
        //access all input ids
        EditText name_input = findViewById(R.id.name_input);
        EditText national_number_input = findViewById(R.id.national_number_input);
        EditText species_input = findViewById(R.id.species_input);
        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button = findViewById(R.id.female_button);
        RadioButton unk_button = findViewById(R.id.unk_button);
        EditText height_input = findViewById(R.id.height_input);
        EditText weight_input = findViewById(R.id.weight_input);
        Spinner level_spinner = findViewById(R.id.level_spinner);
        EditText HP_input = findViewById(R.id.HP_input);
        EditText attack_input = findViewById(R.id.attack_input);
        EditText defense_input = findViewById(R.id.defense_input);
        //access all text ids
        TextView name_text = findViewById(R.id.name_text);
        TextView national_number_text = findViewById(R.id.national_number_text);
        TextView species_text = findViewById(R.id.species_text);
        TextView gender_text = findViewById(R.id.gender_text);
        TextView height_text = findViewById(R.id.height_text);
        TextView weight_text = findViewById(R.id.weight_text);
        TextView level_text = findViewById(R.id.level_text);
        TextView HP_text = findViewById(R.id.HP_text);
        TextView attack_text = findViewById(R.id.attack_text);
        TextView defense_text = findViewById(R.id.defense_text);

        String defaultType = "";
        String defaultInt = "0";
        name_input.setText(defaultType);
        species_input.setText(defaultType);
        national_number_input.setText( defaultInt);
        male_button.setChecked(false);
        female_button.setChecked(false);
        unk_button.setChecked(false);
        height_input.setText(defaultInt);
        weight_input.setText(defaultInt);
        HP_input.setText(defaultInt);
        attack_input.setText(defaultInt);
        defense_input.setText(defaultInt);

        name_text.setTextColor(Color.BLACK);
        species_text.setTextColor(Color.BLACK);
        national_number_text.setTextColor(Color.BLACK);
        gender_text.setTextColor(Color.BLACK);
        height_text.setTextColor(Color.BLACK);
        weight_text.setTextColor(Color.BLACK);
        level_text.setTextColor(Color.BLACK);
        HP_text.setTextColor(Color.BLACK);
        attack_text.setTextColor(Color.BLACK);
        defense_text.setTextColor(Color.BLACK);
    }

    public void femaleRadios(View view) {
        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button = findViewById(R.id.female_button);
        RadioButton unk_button = findViewById(R.id.unk_button);

        female_button.setChecked(true);
        male_button.setChecked(false);
        unk_button.setChecked(false);
    }

    public void maleRadios(View view) {
        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button = findViewById(R.id.female_button);
        RadioButton unk_button = findViewById(R.id.unk_button);

        female_button.setChecked(false);
        male_button.setChecked(true);
        unk_button.setChecked(false);
    }

    public void unkRadios(View view) {
        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button = findViewById(R.id.female_button);
        RadioButton unk_button = findViewById(R.id.unk_button);

        female_button.setChecked(false);
        male_button.setChecked(false);
        unk_button.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(saveListener);

        Spinner spinner = (Spinner) findViewById(R.id.level_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stringArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }
};