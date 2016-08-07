package com.example.vishal.newsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class Recent extends Fragment {


    String api_key ="822ec150d7b145a5b5f8c146618e9d6d";
    View rootView;

    ExpandableListView lv;
    public String[] groups =new String[]{};
    public String[][] children =new String[][]{{}};




    public Recent() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startThread();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lineup, container, false);

        return rootView;

    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children));
        lv.setGroupIndicator(null);

    }


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
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
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
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
                    String strings[] = new String[jsonArray.length()];
                    String child[][] =new String[jsonArray.length()][jsonArray.length()+1];
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        strings[i] =jsonObject.getString("title");
                        System.out.println("HG"+strings[i]);
                    }

                    for(int j =0 ;j<jsonArray.length();j++){
                          JSONObject json =jsonArray.getJSONObject(j);
                          child[j][j+1] =json.getString("abstract");
                            System.out.println("ONN"+child[j][j+1]);


                    }
                        children =child;
                        groups =strings;






                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();







    }


}



