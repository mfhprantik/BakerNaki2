package com.cse370.bakernaki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CircularActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            MenuItem item = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
            navigationView.setCheckedItem(R.id.nav_circular);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.circular, menu);

        if (User.type == 1) menu.findItem(R.id.action_delete).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Api.deleteCircular(Api.CIRCULARDATA[getIntent().getExtras().getInt("circularIndex")][0]);

            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_circular) {
            // Handle the camera action
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.circular);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_circular, null, false));

            setTitle(getString(R.string.title_circular));
            setUI();

            showCircular();
        } else if (id == R.id.nav_candidates) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.circular);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_candidates, null, false));

            setTitle(getString(R.string.title_candidates));

            showCandidates();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setUI() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (User.type == 1) {
            setInterestState();

            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_candidates).setVisible(false);
        } else {
            ((Button) findViewById(R.id.button_circular_interest)).setVisibility(View.GONE);
        }
    }

    public void setInterestState() {
        Button interest = (Button) findViewById(R.id.button_circular_interest);
        Api.getCandidates(Api.CIRCULARDATA[getIntent().getExtras().getInt("circularIndex")][0]);

        if (Api.RESULT.contains(User.id + "")) {
            interest.setText(getText(R.string.circular_interested));
        }
    }

    public void interest(View v) {
        Button interest = (Button) findViewById(R.id.button_circular_interest);
        if (interest.getText().toString().equals(getString(R.string.circular_interest))) {
            Api.addInterest(Api.CIRCULARDATA[getIntent().getExtras().getInt("circularIndex")][0]);
            interest.setText(getString(R.string.circular_interested));
        } else {
            Api.removeInterest(Api.CIRCULARDATA[getIntent().getExtras().getInt("circularIndex")][0]);
            interest.setText(getString(R.string.circular_interest));
        }
    }

    public void showCircular() {
        TextView info = (TextView) findViewById(R.id.textView_circular_info);
        TextView name = (TextView) findViewById(R.id.textView_circular_name);
        TextView address = (TextView) findViewById(R.id.textView_circular_address);
        TextView details = (TextView) findViewById(R.id.textView_circular_details);
        TextView nature = (TextView) findViewById(R.id.textView_circular_nature);
        TextView category = (TextView) findViewById(R.id.textView_circular_category);
        TextView location = (TextView) findViewById(R.id.textView_circular_location);
        TextView description = (TextView) findViewById(R.id.textView_circular_description);
        TextView salary = (TextView) findViewById(R.id.textView_circular_salary);
        TextView deadline = (TextView) findViewById(R.id.textView_circular_deadline);
        TextView degree = (TextView) findViewById(R.id.textView_circular_degree);
        TextView experience = (TextView) findViewById(R.id.textView_circular_experience);

        int circularIndex = getIntent().getExtras().getInt("circularIndex");

        nature.setText(nature.getText() + " " + Api.CIRCULARDATA[circularIndex][6]);
        category.setText(category.getText() + " " + Api.CIRCULARDATA[circularIndex][1]);
        location.setText(location.getText() + " " + Api.CIRCULARDATA[circularIndex][7]);
        description.setText(description.getText() + " " + Api.CIRCULARDATA[circularIndex][5]);
        salary.setText(salary.getText() + " " + Api.CIRCULARDATA[circularIndex][8]);
        deadline.setText(deadline.getText() + " " + Api.CIRCULARDATA[circularIndex][4]);
        degree.setText(degree.getText() + " " + Api.CIRCULARDATA[circularIndex][2]);
        experience.setText(experience.getText() + " " + Api.CIRCULARDATA[circularIndex][3]);

        if (User.type == 1) {
            String[] employeeData = Settings.split(Api.CIRCULARDATA[circularIndex][9], '?');

            name.setText(name.getText() + " " + employeeData[0]);
            address.setText(address.getText() + " " + employeeData[1]);
            details.setText(details.getText() + " " + employeeData[3]);

            LinearLayout company = (LinearLayout) findViewById(R.id.linearLayout_company);
            company.setVisibility(View.VISIBLE);
        }
    }

    public void showCandidates() {
        LinearLayout candidates = (LinearLayout) findViewById(R.id.linearLayout_candidates);

        Api.findCandidatesData(Api.CIRCULARDATA[getIntent().getExtras().getInt("circularIndex")][0]);

        for (int i = 0; i < Api.CANDIDATEDATA.length; i++) {
            LinearLayout candidate = new LinearLayout(this);
            candidate.setId(i);
            candidate.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            candidate.setOrientation(LinearLayout.VERTICAL);
            candidate.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 0) lp.setMargins(10, 10, 10, 10);
            else lp.setMargins(10, 0, 10, 10);
            candidate.setLayoutParams(lp);

            TextView name = new TextView(this);
            name.setTextSize(20);
            name.setText(Api.CANDIDATEDATA[i][1]);
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 30);
            name.setLayoutParams(lp);
            candidate.addView(name);

            TextView experience = new TextView(this);
            experience.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            experience.setText(getString(R.string.profile_experience) + Api.CANDIDATEDATA[i][9]);
            candidate.addView(experience);

            TextView degree = new TextView(this);
            degree.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            degree.setText(getString(R.string.profile_degree) + Api.CANDIDATEDATA[i][10]);
            candidate.addView(degree);

            candidate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.circular);
                    rl.removeAllViews();
                    LayoutInflater layoutInflater = (LayoutInflater)
                            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rl.addView(layoutInflater.inflate(R.layout.layout_employee_profile, null, false));

                    setTitle(getString(R.string.title_candidate_profile));

                    showProfile(Api.CANDIDATEDATA[view.getId()][0]);
                }
            });

            candidates.addView(candidate);
        }
    }

    public void showProfile(String employeeID) {
        TextView name = (TextView) findViewById(R.id.textView_profile_employee_name);
        TextView sex = (TextView) findViewById(R.id.textView_profile_employee_sex);
        TextView birthday = (TextView) findViewById(R.id.textView_profile_employee_birthday);
        TextView email = (TextView) findViewById(R.id.textView_profile_employee_email);
        TextView address = (TextView) findViewById(R.id.textView_profile_employee_address);
        TextView phone = (TextView) findViewById(R.id.textView_profile_employee_phone);
        TextView categories = (TextView) findViewById(R.id.textView_profile_categories);
        TextView degree = (TextView) findViewById(R.id.textView_profile_degree);
        TextView year = (TextView) findViewById(R.id.textView_profile_year);
        TextView institution = (TextView) findViewById(R.id.textView_profile_institution);
        TextView experience = (TextView) findViewById(R.id.textView_profile_experience);
        TextView companies = (TextView) findViewById(R.id.textView_profile_companies);
        TextView details = (TextView) findViewById(R.id.textView_profile_details);
        TextView interpersonal = (TextView) findViewById(R.id.textView_profile_interpersonal);
        TextView computer = (TextView) findViewById(R.id.textView_profile_computer);
        TextView language = (TextView) findViewById(R.id.textView_profile_language);
        TextView achievements = (TextView) findViewById(R.id.textView_profile_achievements);

        int id = Integer.parseInt(employeeID);

        Api.getEmployeeProfile(id + "");
        String[] data = Settings.split(Api.PROFILEDATA, '?');

        name.setText(name.getText() + " " + data[0]);
        sex.setText(sex.getText() + " " + data[4]);
        birthday.setText(birthday.getText() + " " + data[1]);
        email.setText(email.getText() + " " + User.credentials.get("email"));
        address.setText(address.getText() + " " + data[2]);
        phone.setText(phone.getText() + " " + data[3]);

        Api.getJobPreference(id + "");
        data = Settings.split(Api.RESULT, '?');

        categories.setText(categories.getText() + " " + data[0]);
        degree.setText(degree.getText() + " " + data[4]);
        year.setText(year.getText() + " " + data[5]);
        institution.setText(institution.getText() + " " + data[6]);
        experience.setText(experience.getText() + " " + data[3]);
        companies.setText(companies.getText() + " " + data[2]);
        details.setText(details.getText() + " " + data[1]);
        interpersonal.setText(interpersonal.getText() + " " + data[7]);
        computer.setText(computer.getText() + " " + data[9]);
        language.setText(language.getText() + " " + data[8]);
        achievements.setText(achievements.getText() + " " + data[10]);
    }

    public Context getContext() {
        return this;
    }
}
