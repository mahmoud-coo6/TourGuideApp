package com.example.android.tourguideapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrailsFragment extends Fragment {

    RecyclerView recyclerView;
    private ItemAdapter adapter;
    private View myTrailsFragment;
    private static final String TAG = "CuisineFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myTrailsFragment = inflater.inflate(R.layout.recycler_list, container, false);

        if (isConnected()) {
            runTask();
        }
        return myTrailsFragment;
    }

    public void runTask() {
        new TourContent(getActivity()).execute();
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {

            Toast.makeText(getContext(), "please, check there is internet connection.", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    class TourContent extends AsyncTask<Void, Void, List<Item>> {

        private static final String TAG = "TourContent";
        Context mContext;
        public ProgressDialog pDialog;
        List<Item> myItems = null;

        public TourContent(Context context) {
            mContext = context;
        }


        @Override
        protected List<Item> doInBackground(Void... voids) {
            ArrayList<String> images = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            myItems = new ArrayList<>();
            int count = 0;
            Document document;
            Elements element;
            try {
                document = Jsoup.connect("http://www.travelpalestine.ps/en/category/8/1/Sports").get();

                element = document.select(".h_cat_box img");

                for (Element image : element) {
                    if (image.hasAttr("src") && !image.attr("src").equals(""))
                        images.add(image.attr("src"));
                }

                element = document.select("a.stitle");

                for (Element name : element) {
                    if (name.hasAttr("href") && !name.attr("href").equals(""))
                        names.add(name.text());
                }


                if (images.size() > names.size()) {
                    count = names.size();
                } else if (images.size() < names.size()) {
                    count = images.size();
                } else {
                    count = images.size();
                }

                for (int i = 0; i < count; i++) {
                    myItems.add(new Item(Uri.parse(names.get(i)).getLastPathSegment(), "desription", images.get(i), "information"));
                }
                Log.i(TAG, "doInBackground44 test : " + myItems.toString());

                return myItems;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(mContext,
                    ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
            pDialog.setTitle("Please wait");
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage("Loading data...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.setInverseBackgroundForced(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);

            recyclerView = myTrailsFragment.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new ItemAdapter(getActivity(), (ArrayList<Item>) items, R.color.category_cuisine);
            recyclerView.setAdapter(adapter);
            pDialog.dismiss();

        }
    }
}

