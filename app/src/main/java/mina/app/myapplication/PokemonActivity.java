package mina.app.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class PokemonActivity extends AppCompatActivity {
    EditText name_input;
    EditText national_number_input;
    EditText species_input;
    RadioButton male_button;
    RadioButton female_button;
    RadioButton unk_button;
    EditText height_input;
    EditText weight_input;
    Spinner level_spinner;
    EditText HP_input;
    EditText attack_input;
    EditText defense_input;
    Cursor mCursor;

    TextView name_text;
    TextView national_number_text;
    TextView species_text;
    TextView gender_text;
    TextView height_text;
    TextView weight_text;
    TextView level_text;
    TextView HP_text;
    TextView attack_text;
    TextView defense_text;
    Button DatabaseButton;

        public void onClick(View view) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
        }

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
            }
            else if (count == 0) {
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

                //Code for Database
                boolean bool = true;
                ContentValues contentValues = new ContentValues();
                contentValues.put(pokemonDBProvider.COLUMN_ONE, national_number_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_TWO, name_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_THREE, species_input.getText().toString().trim());
                if (unk_button.isChecked() == true){
                    contentValues.put(pokemonDBProvider.COLUMN_FOUR, "Unknown".trim());
                }
                else if (male_button.isChecked() == true){
                    contentValues.put(pokemonDBProvider.COLUMN_FOUR, "Male".trim());
                }
                else{
                    contentValues.put(pokemonDBProvider.COLUMN_FOUR, "Female".trim());
                }
                contentValues.put(pokemonDBProvider.COLUMN_FIVE, height_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_SIX, weight_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_SEVEN, level_spinner.getSelectedItem().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_EIGHT, HP_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_NINE, attack_input.getText().toString().trim());
                contentValues.put(pokemonDBProvider.COLUMN_TEN, defense_input.getText().toString().trim());


                String gen = "";
                if (unk_button.isChecked() == true){
                    gen = "Unknown";
                }
                else if (male_button.isChecked() == true){
                    gen = "Male";
                }
                else{
                    gen = "Female";
                }

                String[] projection = {
                        pokemonDBProvider.COLUMN_ONE,
                        pokemonDBProvider.COLUMN_TWO,
                        pokemonDBProvider.COLUMN_THREE,
                        pokemonDBProvider.COLUMN_FOUR,
                        pokemonDBProvider.COLUMN_FIVE,
                        pokemonDBProvider.COLUMN_SIX,
                        pokemonDBProvider.COLUMN_SEVEN,
                        pokemonDBProvider.COLUMN_EIGHT,
                        pokemonDBProvider.COLUMN_NINE,
                        pokemonDBProvider.COLUMN_TEN
                };

                String selection = pokemonDBProvider.COLUMN_ONE + "= ? AND " +
                        pokemonDBProvider.COLUMN_TWO + "= ? AND " +
                        pokemonDBProvider.COLUMN_THREE + "= ? AND " +
                        pokemonDBProvider.COLUMN_FOUR + "= ? AND " +
                        pokemonDBProvider.COLUMN_FIVE + "= ? AND " +
                        pokemonDBProvider.COLUMN_SIX + "= ? AND " +
                        pokemonDBProvider.COLUMN_SEVEN + "= ? AND " +
                        pokemonDBProvider.COLUMN_EIGHT + "= ? AND " +
                        pokemonDBProvider.COLUMN_NINE + "= ? AND " +
                        pokemonDBProvider.COLUMN_TEN + "= ?";

                String[] selectionArgs = {
                        national_number_input.getText().toString().trim(),
                        name_input.getText().toString().trim(),
                        species_input.getText().toString().trim(),
                        gen,
                        height_input.getText().toString().trim(),
                        weight_input.getText().toString().trim(),
                        level_spinner.getSelectedItem().toString().trim(),
                        HP_input.getText().toString().trim(),
                        attack_input.getText().toString().trim(),
                        defense_input.getText().toString().trim()
                };

                Cursor cursor = getContentResolver().query(pokemonDBProvider.CONTENT_URI, projection, selection, selectionArgs, null);

                if (cursor.getCount() < 1){
                    getContentResolver().insert(pokemonDBProvider.CONTENT_URI, contentValues);
                    String message = "We Have added your Pokemon to the Collection!";
                    Toast.makeText(PokemonActivity.this, message, Toast.LENGTH_SHORT).show();
                } else{
                    String message = "This is a duplicate Pokemon. Please Try Different Values!";
                    Toast.makeText(PokemonActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void reset(View view) {
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
        female_button.setChecked(true);
        male_button.setChecked(false);
        unk_button.setChecked(false);
    }

    public void maleRadios(View view) {
        female_button.setChecked(false);
        male_button.setChecked(true);
        unk_button.setChecked(false);
    }

    public void unkRadios(View view) {
        female_button.setChecked(false);
        male_button.setChecked(false);
        unk_button.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint);


        name_input = findViewById(R.id.name_input);
        national_number_input = findViewById(R.id.national_number_input);
        species_input = findViewById(R.id.species_input);
        male_button = findViewById(R.id.male_button);
        female_button = findViewById(R.id.female_button);
        unk_button = findViewById(R.id.unk_button);
        height_input = findViewById(R.id.height_input);
        weight_input = findViewById(R.id.weight_input);
        level_spinner = findViewById(R.id.level_spinner);
        HP_input = findViewById(R.id.HP_input);
        attack_input = findViewById(R.id.attack_input);
        defense_input = findViewById(R.id.defense_input);

        name_text = findViewById(R.id.name_text);
        national_number_text = findViewById(R.id.national_number_text);
        species_text = findViewById(R.id.species_text);
        gender_text = findViewById(R.id.gender_text);
        height_text = findViewById(R.id.height_text);
        weight_text = findViewById(R.id.weight_text);
        level_text = findViewById(R.id.level_text);
        HP_text = findViewById(R.id.HP_text);
        attack_text = findViewById(R.id.attack_text);
        defense_text = findViewById(R.id.defense_text);

        DatabaseButton = findViewById(R.id.database_button);
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