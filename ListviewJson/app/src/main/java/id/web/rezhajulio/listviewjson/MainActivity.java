package id.web.rezhajulio.listviewjson;

import java.util.ArrayList;
import id.web.rezhajulio.listviewjson.Download_data.download_complete;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements download_complete {

    // More efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();
    public ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        adapter = new ExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);

        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://beta.json-generator.com/api/json/get/N1k4wNAk-");

    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                Group group = new Group(obj.getString("name"));
                group.children.add("Address : " + obj.getString("address"));
                group.children.add("Company : " + obj.getString("company"));
                group.children.add("Age : " + obj.getString("age"));
                group.children.add("Eye Color : " + obj.getString("eyeColor"));
                group.children.add(obj.getBoolean("isActive") ? "Active : Yes": "Active : No");

                groups.append(i, group);

                Log.v("group", groups.toString());

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
