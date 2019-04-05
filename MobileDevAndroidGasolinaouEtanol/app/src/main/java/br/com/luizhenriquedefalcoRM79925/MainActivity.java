package br.com.luizhenriquedefalcoRM79925;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView precoGasolinaTextView;
    private SeekBar precoGasolinaSeekBar;
    private TextView precoEtanolTextView;
    private SeekBar precoEtanolSeekBar;
    private TextInputLayout melhorCombustivel;
    private ImageView melhorCombustivelImageView;

    private double precoGasolina;
    private double precoEtanol;
    private double razaoEtanolGasolina;

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoGasolinaTextView =
                findViewById(R.id.preco_gasolina_textview);
        precoGasolinaSeekBar =
                findViewById(R.id.preco_gasolina_seekbar);
        precoEtanolTextView =
                findViewById(R.id.preco_etanol_textview);
        precoEtanolSeekBar =
                findViewById(R.id.preco_etanol_seekbar);
        melhorCombustivel =
                findViewById(R.id.melhor_combustivel_wrapper);
        melhorCombustivelImageView =
                findViewById(R.id.melhor_combustivel_imageview);

        precoGasolina = precoEtanol = 3;
        calcularCusto();
        precoGasolinaSeekBar.setOnSeekBarChangeListener(observer);
        precoEtanolSeekBar.setOnSeekBarChangeListener(observer);

    }

    @SuppressLint("StringFormatInvalid")
    private void calcularCusto (){
        razaoEtanolGasolina = precoEtanol / precoGasolina;
        precoGasolinaTextView.setText(currencyFormat.format(precoGasolina));
        precoEtanolTextView.setText(currencyFormat.format(precoEtanol));
        if (razaoEtanolGasolina >= 0.7){
            melhorCombustivelImageView.setImageResource(R.drawable.gasolina1);
            melhorCombustivel.setHint(getString(R.string.combustivel_gasolina, getString(R.string.preco_gasolina), percentFormat.format(razaoEtanolGasolina)));
        }
        else{
            melhorCombustivelImageView.setImageResource(R.drawable.etanol1);
            melhorCombustivel.setHint(getString(R.string.combustivel_etanol, getString(R.string.preco_etanol), percentFormat.format(razaoEtanolGasolina)));
        }
    }

    private SeekBar.OnSeekBarChangeListener observer =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (seekBar.getId() == R.id.preco_gasolina_seekbar){
                        precoGasolina = progress / 100.;
                    }
                    else{
                        precoEtanol = progress / 100.;
                    }
                    calcularCusto();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

}
