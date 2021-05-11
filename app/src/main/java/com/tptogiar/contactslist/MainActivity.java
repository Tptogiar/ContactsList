package com.tptogiar.contactslist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<ContactInfo> contactsList=new ArrayList();
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * @Author: Tptogiar
     * @Description: 初始化函数
     * @Date: 2021/4/25-16:37
     */
    public void init(){
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        refresh=(Button)findViewById(R.id.btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(this,contactsList);
        recyclerView.setAdapter(contactAdapter);
        //设置监听
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loadContacts();
                }
        });
    }

    /**
     * @Author: Tptogiar
     * @Description: 加载联系人列表
     * @Date: 2021/4/25-16:40
     */
    private void loadContacts(){

        //先向用户获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( getApplicationContext().checkSelfPermission( Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED )
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        contactsList = ContactsUtils.getContactList(MainActivity.this, contactsList);
        contactAdapter.setContactList(contactsList);
        recyclerView.setAdapter(contactAdapter);
    }
}