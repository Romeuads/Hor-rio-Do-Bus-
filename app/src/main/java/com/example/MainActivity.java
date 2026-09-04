package com.example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Next screen navigation
        View proximaTelaButton = findViewById(R.id.proxima_tela_button);
        if (proximaTelaButton != null) {
            proximaTelaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Jotur external link
        View joturButton = findViewById(R.id.jotur_button);
        if (joturButton != null) {
            joturButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWebUrl("https://www.jotur.com.br/horarios/");
                }
            });
        }

        // Consórcio Fênix external link
        View consorcioFenixButton = findViewById(R.id.consorcio_fenix_button);
        if (consorcioFenixButton != null) {
            consorcioFenixButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWebUrl("https://www.consorciofenix.com.br/horarios");
                }
            });
        }
    }

    private void openWebUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível abrir o link: " + url, Toast.LENGTH_SHORT).show();
        }
    }
}
