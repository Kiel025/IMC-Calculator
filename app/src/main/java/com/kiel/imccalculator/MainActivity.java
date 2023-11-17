package com.kiel.imccalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kiel.imccalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btCalculate.setOnClickListener(v -> {
            String editTextWeight = binding.editWeight.getText().toString();
            String editTextHeight = binding.editHeight.getText().toString();

            if (editTextWeight.isEmpty()) {
                binding.editWeight.setError("Informe seu Peso");
            } else if (editTextHeight.isEmpty()) {
                binding.editHeight.setError("Informe sua Altura");
            } else {
                calculateImc();
            }
        });
    }

    private void calculateImc() {
        float weight = Float.parseFloat(binding.editWeight.getText().toString().replace(',', '.'));
        float height = Float.parseFloat(binding.editHeight.getText().toString().replace(',', '.'));

        float imc = weight / (height * height);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (imc < 18.5) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(imc) + "\nAbaixo do Peso");
        } else if (imc < 25) {
            binding.txtResult.setText("Seu IMC é de " + imc + "\nPeso Normal");
        } else if (imc < 30) {
            binding.txtResult.setText("Seu IMC é de " + imc + "\nSobre Peso");
        } else if (imc < 35) {
            binding.txtResult.setText("Seu IMC é de " + imc + "\nObesidade (Grau 1)");
        } else if (imc < 40) {
            binding.txtResult.setText("Seu IMC é de " + imc + "\nObesidade Severa (Grau 2)");
        } else {
            binding.txtResult.setText("Seu IMC é de " + imc + "\nObesidade Mórbida (Grau 3)");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.ic_refresh) {
            binding.txtResult.setText("");
            binding.editHeight.setText("");
            binding.editWeight.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}