package com.example.vishal.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public  class Recent extends Fragment {

    private String title;
    private int page;
    private static final String TAG = "Recent";
    String api_key ="6350aaae004949039a2cc5a804fe6bd1";
    View rootView;
    ImageView image;
    Context mContext;
    Activity activity;
    ExpandableListView lv;
    public String[] groups =new String[]{};
    public String[][] children =new String[][]{};




    public Recent() {

    }

    public static Recent newInstance(int page, String title) {
        Recent fragmentFirst = new Recent();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startThread();
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lineup, container, false);
         image = (ImageView) rootView.findViewById(R.id.imageView);
        return rootView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ExpandableListView) view.findViewById(R.id.expListView);


    }



    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;
        private String[] imageUrls;

        public ExpandableListAdapter(String[] groups, String[][] children, String[] imageUrls) {
            this.groups = groups;
            this.children = children;
            this.imageUrls = imageUrls;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.text_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.textView);
                holder.imageNews = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());
            Picasso.with(mContext).load(imageUrls[groupPosition]).into(holder.imageNews);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
            ImageView imageNews;
        }
    }
    //https://api.nytimes.com/svc/topstories/v1/home.json?
// api-key=822ec150d7b145a5b5f8c146618e9d6d
    protected void startThread() {

        final String stringUrl ="https://api.nytimes.com/svc/topstories/v1/home.json?" +
                "api-key="+api_key;
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpsURLConnection httpsURLConnection =null;
                InputStream inputStream =null;
                try {
                    URL url =new URL(stringUrl);
                    httpsURLConnection =(HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setConnectTimeout(10000);
                    inputStream =httpsURLConnection.getInputStream();

                    JSONObject jsonRootObject = new JSONObject(convertStreamToString(inputStream));
                    JSONArray jsonArray =jsonRootObject.getJSONArray("results");
                    JSONArray imageArray;

                    String group[] = new String[jsonArray.length()];
                    final String imageUrls[] = new String[jsonArray.length()];
                    final String clickUrls[] =new String [jsonArray.length()];
                    String child[][] =new String[jsonArray.length()][1];
                    Log.e(TAG, "run: " + jsonArray.getJSONObject(0));
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);
                        group[i] =jsonObject.getString("title");
                        clickUrls[i] =jsonObject.getString("url");
                        String multimedia =jsonObject.getString("multimedia");
                        if(multimedia.length()>0){
                            imageArray = new JSONArray(multimedia.substring(multimedia.indexOf("["), multimedia.indexOf("]")+1));
                            imageUrls[i] = imageArray.getJSONObject(0).getString("url");
                        }
                        else {
                            imageUrls[i] = "https://static01.nyt.com/images/2016/08/06/upshot/07UP-Growth/07UP-Growth-thumbStandard.jpg";
                        }

                    }

                    for(int j =0 ;j<jsonArray.length();j++)
                    {

                        for(int k =0;k<1;k++){

                            JSONObject json = jsonArray.getJSONObject(j);
                            child[j][k] = json.getString("abstract");


                        }

                    }

                    children =child;
                    groups =group;


                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv.setAdapter(new ExpandableListAdapter(groups, children, imageUrls));
                            lv.setGroupIndicator(null);
                            lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                    Intent intent =new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(clickUrls[childPosition]));

                                    startActivity(intent);

                                    return false;
                                }
                            });
                        }
                    });

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }


        }).start();







    }


}



