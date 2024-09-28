package com.example.trabalhopaulinho;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Nome, Email, Idade, Disciplina, Nota1, Nota2;
    private TextView Erro, Resumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nome = findViewById(R.id.Nome);
        Email = findViewById(R.id.Email);
        Idade = findViewById(R.id.Idade);
        Disciplina = findViewById(R.id.Disciplina);
        Nota1 = findViewById(R.id.Nota1);
        Nota2 = findViewById(R.id.Nota2);
        Button btEnviar = findViewById(R.id.btEnviar);
        Button btLimpar = findViewById(R.id.btLimpar);
        Erro = findViewById(R.id.Erro);
        Resumo = findViewById(R.id.Resumo);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDados();
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void validarDados() {
        String nome = Nome.getText().toString();
        String email = Email.getText().toString();
        String idade = Idade.getText().toString();
        String disciplina = Disciplina.getText().toString();
        String nota1 = Nota1.getText().toString();
        String nota2 = Nota2.getText().toString();

        StringBuilder erros = new StringBuilder();

        if (TextUtils.isEmpty(nome)) {
            erros.append("O campo de nome está vazio\n");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            erros.append("O email é inválido\n");
        }
        if (TextUtils.isEmpty(idade) || !TextUtils.isDigitsOnly(idade) || Integer.parseInt(idade) <= 0) {
            erros.append("A idade deve ser um número positivo\n");
        }
        if (TextUtils.isEmpty(nota1) || !isNotaValida(nota1)) {
            erros.append("A nota 1 deve ser um número entre 0 e 10\n");
        }
        if (TextUtils.isEmpty(nota2) || !isNotaValida(nota2)) {
            erros.append("A nota 2 deve ser um número entre 0 e 10\n");
        }

        if (erros.length() > 0) {
            Erro.setText(erros.toString());
        } else {
            Erro.setText("");
            float media = (Float.parseFloat(nota1) + Float.parseFloat(nota2)) / 2;
            String status = media >= 6 ? "Aprovado" : "Reprovado";
            Resumo.setText(String.format(
                    "Nome: %s\nEmail: %s\nIdade: %s\nDisciplina: %s\nNotas 1º e 2º Bimestres: %s, %s\nMédia: %.2f\nStatus: %s",
                    nome, email, idade, disciplina, nota1, nota2, media, status
            ));
        }
    }

    private boolean isNotaValida(String nota) {
        try {
            float valor = Float.parseFloat(nota);
            return valor >= 0 && valor <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void limparCampos() {
        Nome.setText("");
        Email.setText("");
        Idade.setText("");
        Disciplina.setText("");
        Nota1.setText("");
        Nota2.setText("");
        Erro.setText("");
        Resumo.setText("");
    }
}
