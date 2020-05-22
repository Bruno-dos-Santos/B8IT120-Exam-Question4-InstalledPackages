package com.example.installedpackages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listViewPck;
    TextView textPckName;
    List<String> listItem =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPck = (ListView)findViewById(R.id.ListView_packages);
        textPckName = (TextView)findViewById(R.id.textPacakageName);

        final PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                listItem.add((((String)packageManager.getApplicationLabel(packageInfo))));
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        listViewPck.setAdapter(adapter);

        listViewPck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String namePck = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "Package name: " + namePck, Toast.LENGTH_LONG).show();
            }
        });
    }
}
