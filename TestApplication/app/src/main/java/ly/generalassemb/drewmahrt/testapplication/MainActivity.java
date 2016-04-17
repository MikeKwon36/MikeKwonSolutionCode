package ly.generalassemb.drewmahrt.testapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String [] mStatesArray;
    BaseAdapter mAdapter;

    //Added final String constant as key for intent passing index position
    public static final String POSITION_KEY = "ly.generalassemb.drewmahrt.testapplication.KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatesArray = getResources().getStringArray(R.array.states);

        final ListView listView = (ListView)findViewById(R.id.list_view);

        TextView emptyText = (TextView)findViewById(R.id.emptyText);
        listView.setEmptyView(emptyText);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatesArray = new String[]{};

                //Added instruction to update adapter with new data set
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mStatesArray.length;
            }

            @Override
            public Object getItem(int position) {
                return mStatesArray[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;

                if (convertView == null){
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_view_item, parent, false);
                }

                view = convertView;

                /*Corrected duplicate reference to R.id.state in list_view_item.xml (changed xml
                layout's height to wrap_content instead of hardcoded "20dp")*/
                TextView text1 = (TextView) view.findViewById(R.id.state);
                TextView text2 = (TextView) view.findViewById(R.id.state1);

                text1.setText(mStatesArray[position]);
                text2.setText(mStatesArray[position]);
                return view;
            }
        };

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,PositionActivity.class);

                //added data containing list item's position and set key as a final constant
                intent.putExtra(POSITION_KEY,position);

                startActivity(intent);
            }
        });
    }

}
