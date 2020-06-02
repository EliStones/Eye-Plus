package com.example.theocrreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayText extends AppCompatActivity {
    private TextView detectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);

        detectedText = (TextView) findViewById(R.id.detected_text);
        registerForContextMenu(detectedText);

        Intent intent = getIntent();
        String text = intent.getStringExtra("my message");
        detectedText.setText(text);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(), 0, "Copy");
        menu.setHeaderTitle("Copy text");
        TextView textView = (TextView) v;
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", textView.getText());
        manager.setPrimaryClip(clipData);
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
