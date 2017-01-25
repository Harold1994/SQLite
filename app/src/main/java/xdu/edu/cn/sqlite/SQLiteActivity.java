package xdu.edu.cn.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SQLiteActivity extends AppCompatActivity {
    private Button createdb,updatedb;
    private Button insert,update,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createdb = (Button) findViewById(R.id.createdb);
        updatedb = (Button) findViewById(R.id.updatedb);
        insert = (Button) findViewById(R.id.insert);
        update = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);
        //delete = (Button) findViewById(R.id.delete);

        createdb.setOnClickListener(new CreateListener());
        updatedb.setOnClickListener(new UpdatedbListener());
        insert.setOnClickListener(new InsertListener());
        update.setOnClickListener(new UpdateListener());
        query.setOnClickListener(new QueryListener());
        //delete.setOnClickListener(new DeleteListener());
    }

     class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    /*private class DeleteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("user","id = ?", new String[]{"2"});
        }
    }*/

     class QueryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"},null, null, null, null);
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("query------>" + name);
            }
        }
    }

     class UpdatedbListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

     class UpdateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "litongtong");
            db.update("user", values, "id=?", new String[]{"1"});
        }
    }

     class InsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ContentValues values = new ContentValues();
            values.put("id",1);
            values.put("name","lihe");
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert("user", null, values);
        }
    }
}
