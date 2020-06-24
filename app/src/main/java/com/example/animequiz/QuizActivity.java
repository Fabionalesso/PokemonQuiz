package com.example.animequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4, rbResposta5, rb;
    RadioGroup rgRespostas;
    Button btnResponder;
    int pontos, cont=0;
    int respostaCerta;
    
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Quantas evoluções possui o eevee?", R.id.rbResposta3,"3","5","8","7","6"));
            add(new Questao("Qual o pokemon com a maior defesa?", R.id.rbResposta1,"Shuckle","Groudon","Torterra","Blastoise","Bastiodon"));
            add(new Questao("Qual primeiro pokemon capturado por Ash?", R.id.rbResposta2,"Pikachu","Caterpie","Pidgey","Dragonite","Charmander"));
            add(new Questao("Quantos pokémon existem na Pokedex atualmente?", R.id.rbResposta5,"151","674","1000","750","890"));
            add(new Questao("Quantas insigneas de ginásio sao necessarias para participar da liga Pokémon?", R.id.rbResposta2,"6","8","5","7","4"));
            add(new Questao("Qual desses lendários pode Mega evoluir?", R.id.rbResposta4,"Arceus","Giratina","Zygarde","Rayquaza","Celebi"));
            add(new Questao("O que Arceus é considerado na mitologia Pokémon?", R.id.rbResposta5,"Parte de Zygarde","Rival de Ho-Oh","Cão lendario","Irmao de Regigigas","Deus dos Pokémon"));
            add(new Questao("Qual desses Pokémon é uma Ultra beast?", R.id.rbResposta3,"Marshadow","Magearna","Buzzwole","Solgaleo","Lunala"));
            add(new Questao("Qual desses Pókemon nao é lendario?", R.id.rbResposta4,"kubfu","Urshifu","Zarude","Archeops","Zeraora"));
            add(new Questao("Quais são os iniciais da região de Alola?", R.id.rbResposta2,"Charmander, Squirtle, Bulbassaur","Rowlet, Poplio, Litten","Chimchar, Piplup, Turtwig","Sobble, Grookey, Scorbunny","Froakie, Chespin, Fennekin"));
            add(new Questao("Shelmet evolui para qual Pokémon?", R.id.rbResposta1,"Accelgor","Carracosta","Sylveon","Dustox","Karrablast"));
            add(new Questao("Qual o unico pokemon shiny de Ash?", R.id.rbResposta3,"Torkoal","Greninja","Noctowl","Gengar","Dragonite"));        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        Collections.shuffle(questoes);

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        rbResposta5 = (RadioButton)findViewById(R.id.rbResposta5);
        carregarQuestao();
    }

    private void carregarQuestao(){

        if(cont < 10) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            rbResposta5.setText(resposta.get(4));
            respostaCerta = q.getRespostaCerta();
            cont++;
        }
        else{
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }

    public void btnResponderOnClick(View v){
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }
    public void btnJogarNovamenteOnClick(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}
