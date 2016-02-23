package com.plubits.pokerspeedrange;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.plubits.pokerspeedrange.adapters.TableAdapter;
import com.plubits.pokerspeedrange.models.TableValue;
import com.plubits.pokerspeedrange.utils.Constants;
import com.plubits.pokerspeedrange.utils.PSRHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private int tabSelected;
    private String jsonData;

    public View tab1View;
    public View tab2View;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tabSelected = Constants.TAB_CALL;

        //Carreguem el json
        this.loadJsonData();
        //this.createRadioButtonsGroups();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Show info", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    //PRIVATE METHODS

    private void loadJsonData() {
        this.jsonData = PSRHelper.loadJSONFromAsset(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.plubits.pokerspeedrange/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.plubits.pokerspeedrange/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_JSON_DATA = "json_data";
        private JSONObject json;
        private int numMaxRadioBtns = 0;
        private int minRadioBtnWidth = Integer.MAX_VALUE;
        private ArrayList<RadioGroup> radioGroups = new ArrayList<RadioGroup>();
        private int sectionNumber;
        ArrayList<TableValue> tableValues;
        private GridView tableLayout;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, String jsonData) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_JSON_DATA, jsonData);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TODO: Afegir creacio dels radiogroups
            Bundle bundle = getArguments();
            try {
                this.json = new JSONObject(bundle.getString(ARG_JSON_DATA));
                this.sectionNumber = bundle.getInt(ARG_SECTION_NUMBER);
                createRadioButtonsGroups(rootView, getJsonTabKey(sectionNumber), sectionNumber == 2);
                createTable(rootView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //jsonObject = new JSONObject(json);
            //createRadioButtonsGroups(rootView);
            return rootView;
        }

        private String getJsonTabKey(int sectionNumber) {
            String tabID = "";
            switch (sectionNumber) {
                case 1:
                    tabID = Constants.PUSH_TAB_KEY;
                    break;
                case 2:
                    tabID = Constants.CALL_TO_PUSH_TAB_KEY;
                    break;
            }

            return tabID;
        }

        private void upadteTableSelectedValues(){
            String[] checkedValues = new String[this.radioGroups.size()];
            int i = 0;

            for (RadioGroup rg : this.radioGroups){
                int rbID = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)rg.findViewById(rbID);
                if (rb == null) return;
                checkedValues[i] = String.valueOf(rb.getTag());
                ++i;
            }

            String percentChecked = checkedValues.length > 2 ? checkedValues[2] : "";
            ArrayList<String[]> antesValues = PSRHelper.getValues(this.json, this.getJsonTabKey(this.sectionNumber), checkedValues[0], checkedValues[1], percentChecked, true);
            ArrayList<String[]> noAntesValues = PSRHelper.getValues(this.json, this.getJsonTabKey(this.sectionNumber), checkedValues[0], checkedValues[1], percentChecked, false);

            if (antesValues.size() > 0 && noAntesValues.size() > 0) {
                updateTableValues(antesValues, noAntesValues);
            }
            else{
                for (TableValue tv : this.tableValues)
                    tv.state = Constants.VALUES_STATES.UNSELECTED;
            }

            ((TableAdapter) tableLayout.getAdapter()).notifyDataSetChanged();
        }

        private void updateTableValues(ArrayList<String[]> antesValues, ArrayList<String[]> noAntesValues){
            int indexAntes, indexNoAntes;
            indexAntes = indexNoAntes= 0;
            String[] intervalAntes = antesValues.get(indexAntes);
            String[] intervalNoAntes = noAntesValues.get(indexNoAntes);
            boolean inAntesInterval, fiAntesValues, inNoAntesInterval, fiNoAntesValues;
            inAntesInterval = fiAntesValues = inNoAntesInterval = fiNoAntesValues = false;

            for (TableValue tv : this.tableValues){
                tv.state = Constants.VALUES_STATES.UNSELECTED;
                boolean startInAntesInterval = inAntesInterval;
                boolean startInNoAntesInterval = inNoAntesInterval;
                if (!inNoAntesInterval) inAntesInterval = checkInterval(tv,inAntesInterval,intervalAntes,indexAntes, Constants.VALUES_STATES.ANTES);
                if (!inAntesInterval) inNoAntesInterval = checkInterval(tv,inNoAntesInterval,intervalNoAntes,indexNoAntes, Constants.VALUES_STATES.NO_ANTES);

                if (this.nextInterval(fiAntesValues, startInAntesInterval, inAntesInterval, intervalAntes)){
                    ++indexAntes;
                    inAntesInterval = false;
                    if (indexAntes < antesValues.size()){
                        intervalAntes = antesValues.get(indexAntes);
                    }
                    else{
                        fiAntesValues = true;
                    }
                }

                if (this.nextInterval(fiNoAntesValues, startInNoAntesInterval, inNoAntesInterval, intervalNoAntes)){
                    ++indexNoAntes;
                    inNoAntesInterval = false;
                    if (indexNoAntes < noAntesValues.size()){
                        intervalNoAntes = noAntesValues.get(indexNoAntes);
                    }
                    else{
                        fiNoAntesValues = true;
                    }
                }

                // No podem retornar pq hem de deseleccionar els seleccionats
                //if (fiAntesValues && fiNoAntesValues) return;
            }
        }

        /**
         * Retorna si cal avançar al següent interval en funció de si ja s'ha tractat l'interval actual
         * @param fiValues
         * @param startInInterval
         * @param inInterval
         * @param interval
         * @return
         */
        private boolean nextInterval(boolean fiValues, boolean startInInterval, boolean inInterval, String[] interval){
            return (!fiValues && (startInInterval && !inInterval) || (interval.length < 2 && inInterval));
        }

        private Boolean checkInterval(TableValue tv, boolean inInterval, String[] interval, int indexInterval, Constants.VALUES_STATES state){
            boolean startInAntesInterval = inInterval;
            String intervalIni = interval[0];
            String intervalFi = interval.length > 1 ? interval[1] : intervalIni;
            if (inInterval){
                tv.state = state;
                inInterval = !intervalFi.equalsIgnoreCase(tv.valueID);
            }
            else if (tv.valueID.equalsIgnoreCase(intervalIni)){
                tv.state = state;
                inInterval = true; //Estem dins d'un interval
                if(!inInterval) Log.i("UPDATE_TABLE", "Not in interval: " + intervalIni + ", " + intervalFi);
                else Log.i("UPDATE_TABLE", "Interval: " + intervalIni + ", " + intervalFi);
            }
            else{
                Log.i("UPDATE_TABLE", "UNSELECTED Interval: " + intervalIni + ", " + intervalFi + " -> Value: " + tv.valueID);
            }

            return inInterval;
        }

        private void createTable(View view){
            tableValues = PSRHelper.getTable(json);
            tableLayout = (GridView)view.findViewById(R.id.gridview);
            tableLayout.setAdapter(new TableAdapter(this.getContext(), tableValues));
        }

        private void createRadioButtonsGroups(View view, String tabKey, boolean showPercent) {
            this.createRadioButtons(view, tabKey, Constants.POSITIONS_LIST_KEY, R.id.positionsRadioGroup, R.drawable.radio_btn_position_selector, "");
            this.createRadioButtons(view, tabKey, Constants.BLINDS_LIST_KEY, R.id.blindsRadioGroup, R.drawable.radio_btn_blinds_selector, "bbs");
            if (showPercent)
                this.createRadioButtons(view, tabKey, Constants.PERCENT_LIST_KEY, R.id.percentRadioGroup, R.drawable.radio_btn_percent_selector, "%");
        }

        private void createRadioButtons(View view, String tabKey, String listID, int radioGroupID, int backgroundDrawableID, String extraText) {
            RadioGroup rg = (RadioGroup) view.findViewById(radioGroupID);
            rg.setVisibility(View.VISIBLE);

            String[] positionsList = PSRHelper.getList(this.json, tabKey, listID);

            for (String idPosition : positionsList) {
                RadioButton rb = getRadioButton(idPosition, backgroundDrawableID, extraText);
                rg.addView(rb);
            }

            //rg.setPadding(20,0,20,0);
            if (this.numMaxRadioBtns > positionsList.length) {
                int numRadioBtnMargin = this.numMaxRadioBtns - positionsList.length;
                int margin = this.minRadioBtnWidth*numRadioBtnMargin/2;
                rg.setPadding(margin, 0, margin, 0);
            } else {
                this.numMaxRadioBtns = positionsList.length;
                this.minRadioBtnWidth = this.getScreenWidth()/this.numMaxRadioBtns;
            }

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // This will get the radiobutton that has changed in its check state
                    RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                    // This puts the value (true/false) into the variable
                    boolean isChecked = checkedRadioButton.isChecked();
                    // If the radiobutton that has changed in check state is now checked...
                    if (isChecked) {
                        upadteTableSelectedValues();
                    }
                }
            });

            this.radioGroups.add(rg);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        private RadioButton getRadioButton(String id, int backgroundDrawableID, String extraText) {
            RadioButton radioBtn = new RadioButton(this.getActivity());
            radioBtn.setText(id + extraText);
            radioBtn.setTag(id);
            radioBtn.setButtonDrawable(new StateListDrawable());
            radioBtn.setBackgroundResource(backgroundDrawableID);
            radioBtn.setTextAlignment(RadioGroup.TEXT_ALIGNMENT_CENTER);
            radioBtn.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT, 1f));
            //radioBtn.setPadding(15, 15, 15, 15);
            int[][] states = new int[][]{
                    new int[]{android.R.attr.state_pressed}, // pressed
                    new int[]{android.R.attr.state_focused}, // focused
                    new int[]{android.R.attr.state_checked},
                    new int[]{}
            };
            int[] colors = new int[]{
                    getResources().getColor(R.color.radioButton_unselected), // black
                    getResources().getColor(R.color.radioButton_unselected), // gray
                    getResources().getColor(R.color.radioButton_unselected), // gray
                    getResources().getColor(R.color.positionList_radioButton_selected)  // gray
            };
            ColorStateList list = new ColorStateList(states, colors);
            radioBtn.setTextColor(list);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioBtn.setBackground(getResources().getDrawable(backgroundDrawableID, this.getActivity().getApplicationContext().getTheme()));
            } else {
                radioBtn.setBackground(getResources().getDrawable(backgroundDrawableID));
            }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            radioBtn.setTextColor(getResources().getDrawable(R.drawable.radio_btn_textcolor_selector, getApplicationContext().getTheme()));
        } else {
            radioBtn.setTextColor(getResources().getDrawable(R.drawable.radio_btn_textcolor_selector));
        }*/
            return radioBtn;
        }

        private int getScreenWidth() {
            int measuredWidth = 0;
            int measuredHeight = 0;
            WindowManager w = this.getActivity().getWindowManager();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point size = new Point();
                w.getDefaultDisplay().getSize(size);
                measuredWidth = size.x;
                measuredHeight = size.y;
            } else {
                Display d = w.getDefaultDisplay();
                measuredWidth = d.getWidth();
                measuredHeight = d.getHeight();
            }

            return measuredWidth;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, jsonData);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return Constants.NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.call_tab_title);
                case 1:
                    return getString(R.string.call_to_push_tab_title);
            }
            return null;
        }
    }
}
