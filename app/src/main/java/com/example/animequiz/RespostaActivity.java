package com.example.animequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);
        getSupportActionBar().hide();

        ImageView imgResposta = (ImageView)findViewById(R.id.imgResposta);
        TextView resposta = (TextView)findViewById(R.id.resposta);
        Button btnJogarNovamente = (Button)findViewById(R.id.btnJogarNovamente);

        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        if(intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            if (acertou) {
                imgResposta.setImageResource(R.drawable.deubom);
                resposta.setText("Acertou!");
            }
            else {
                imgResposta.setImageResource(R.drawable.deuruim);
                resposta.setText("Errou!");
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        }
        else{
            btnJogarNovamente.setVisibility(View.VISIBLE);
            resposta.setText("Fez " + pontos + " pontos!");

            if(pontos >= 9){
                imgResposta.setImageResource(R.drawable.deubom);
                resposta.setText("Voce é um Mestre Pokémon");
            }
            else{
                imgResposta.setImageResource(R.drawable.deuruim);
                resposta.setText("Voce não esta apto para ser um Metre Pokémon");
            }
        }
    }

    public void btnJogarNovamenteOnClick(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}

