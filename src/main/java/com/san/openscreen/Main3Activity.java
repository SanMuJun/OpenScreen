package com.san.openscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main3Activity extends Activity {

    private Button press_button;
    private TextView context;
    private Dao dao;
    private AddDao addDao;
    private List<Dao> list;
    private ListView listView;
    private ContextAdapter contextAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        context = (TextView) findViewById(R.id.Context);
        press_button = (Button) findViewById(R.id.press_button);
        listView = (ListView) findViewById(R.id.listView);

        addDao = new AddDao(this);
        list= addDao.query();
        
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,1,0,"删除");
                contextMenu.add(0,2,0,"取消");
                AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
                position = info.position;
            }
        });

        Log.e("Tag",list.toString());
        contextAdapter = new ContextAdapter();
        listView.setAdapter(contextAdapter);

        press_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View inflate = View.inflate(Main3Activity.this, R.layout.addtext, null);
                final EditText textload= (EditText) inflate.findViewById(R.id.textload);

                new AlertDialog.Builder(Main3Activity.this)
                        .setTitle("添加备忘录")
                        .setView(inflate)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String textContext = textload.getText().toString();
                                dao=new Dao(-1,textContext);
                                addDao.insert(dao);
                                list.add(0,dao);
                                contextAdapter.notifyDataSetChanged();
                                Log.e("Tag",list.toString());

                            }
                        })
                        .show();
                Toast.makeText(Main3Activity.this, "添加备忘录", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Dao dao = list.get(position);
        switch (item.getItemId()){

            case 1:
                addDao.delete(dao.getId());
                list.remove(position);
                contextAdapter.notifyDataSetChanged();
                break;
            case 2:
                addDao.update(dao);
                break;

        }

        return super.onContextItemSelected(item);
    }

    public void out (View v){
        finish();
        Toast.makeText(Main3Activity.this, "程序已退出，只要不完全清理备忘功能还在O(∩_∩)O哦！！！", Toast.LENGTH_SHORT).show();
    }
    class ContextAdapter extends BaseAdapter{
       private ViewHolder viewHolder;
       @Override
       public int getCount() {
           return list.size();
       }
       @Override
       public Object getItem(int i) {
           return null;
       }
       @Override
       public long getItemId(int i) {
           return 0;
       }
       @Override
       public View getView(int i, View view, ViewGroup viewGroup) {

           if (view==null){
               viewHolder = new ViewHolder();
               view=View.inflate(Main3Activity.this,R.layout.addtext1,null);
               viewHolder.textView= (TextView) view.findViewById(R.id.textload);

               view.setTag(viewHolder);
           }else{

               viewHolder= (ViewHolder) view.getTag();
           }
           Dao data = list.get(i);
           viewHolder.textView.setText(i+"." +data.getText());

           return view;
       }

       class ViewHolder{

           TextView textView;
       }
   }
}
