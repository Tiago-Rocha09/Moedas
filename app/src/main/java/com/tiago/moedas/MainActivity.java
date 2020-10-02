package com.tiago.moedas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tiago.moedas.model.Currency;
import com.tiago.moedas.service.HTTPService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.editValue = findViewById(R.id.edit_value);
        this.mViewHolder.textDolar = findViewById(R.id.text_dolar);
        this.mViewHolder.textEuro = findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calculate);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);

        this.clearValues();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_calculate){
            //Lógica
            String value = this.mViewHolder.editValue.getText().toString();
            if ("".equals(value)){
                //Mostra mensagem para o usuário
                Toast.makeText(this, "Informe um valor", Toast.LENGTH_LONG).show();
            }else{
                Double real = Double.valueOf(value);

                HTTPService service = new HTTPService();
                try {
                    Currency response = service.execute().get();

                    this.mViewHolder.textDolar.setText(String.format("%.2f", (real/response.getResponse().getDollar())));
                    this.mViewHolder.textEuro.setText(String.format("%.2f", (real/response.getResponse().getEuro())));

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void clearValues(){
        this.mViewHolder.textDolar.setText("");
        this.mViewHolder.textEuro.setText("");
    }

    private static class ViewHolder{
        EditText editValue;
        TextView textDolar;
        TextView textEuro;
        Button buttonCalculate;
    }
}
