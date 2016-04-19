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
        download_data.download_data_from_link("http://beta.json-generator.com/api/json/get/VyfnWqA1b");

    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                Group group = new Group(obj.getString("fullname"));

                Orders order = new Orders();
                order.address = obj.getString("address");
                order.email = obj.getString("email");
                order.phone = obj.getString("phone");
                order.company = obj.getString("company");
                order.fullname = obj.getString("fullname");
                order.latitude = obj.getString("latitude");
                order.longitude = obj.getString("longitude");
                order.registered = obj.getString("registered");
                order.isActive = obj.getBoolean("isActive");

                group.children.add(order);
                groups.append(i, group);

                Log.v("group", groups.toString());

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
