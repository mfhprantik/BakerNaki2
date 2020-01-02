package com.cse370.bakernaki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_circular_add);
        if (User.type == 1) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
                    rl.removeAllViews();
                    LayoutInflater layoutInflater = (LayoutInflater)
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    rl.addView(layoutInflater.inflate(R.layout.layout_register_employer_2, null, false));

                    rl.setGravity(Gravity.CENTER);
                    setTitle(getString(R.string.title_register_circular));
                }
            });
        }

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
            navigationView.setCheckedItem(R.id.nav_dashboard);
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
        getMenuInflater().inflate(R.menu.dashboard, menu);

        if (inProfile) menu.findItem(R.id.action_edit).setVisible(true);
        else menu.findItem(R.id.action_edit).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rl.addView(layoutInflater.inflate(R.layout.layout_edit_profile, null, false));

            inProfile = false;
            invalidateOptionsMenu();

            setTitle(getString(R.string.title_edit_profile));

            if (User.type == 1) setEmployeeEditProfile();
            else setEmployerEditProfile();
        } else if (id == R.id.action_logout) {
            new File(Settings.loginCredentials).delete();
            User.online = false;

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

        if (id == R.id.nav_dashboard) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_dashboard, null, false));

            setTitle(getString(R.string.title_dashboard));

            if (User.type == 1)
                employeeDashboard((LinearLayout) findViewById(R.id.linearLayout_dashboard_circulars));
            else
                employerDashboard((LinearLayout) findViewById(R.id.linearLayout_dashboard_circulars));

            inProfile = false;
            invalidateOptionsMenu();
        } else if (id == R.id.nav_profile) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (User.type == 1) {
                rl.addView(layoutInflater.inflate(R.layout.layout_employee_profile, null, false));
                setTitle(getString(R.string.title_profile));
                setEmployeeProfile();
            } else {
                rl.addView(layoutInflater.inflate(R.layout.layout_employer_profile, null, false));
                setTitle(getString(R.string.title_profile));
                setEmployerProfile();
            }

            inProfile = true;
            invalidateOptionsMenu();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    boolean inProfile = false;

    public void employerDashboard(LinearLayout circulars) {
        Api.findCirculars();

        for (int i = 0; i < Api.CIRCULARDATA.length; i++) {
            LinearLayout circular = new LinearLayout(this);
            circular.setId(i);
            circular.setOrientation(LinearLayout.VERTICAL);
            circular.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            circular.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 0) lp.setMargins(10, 10, 10, 10);
            else lp.setMargins(10, 0, 10, 10);
            circular.setLayoutParams(lp);

            String employerName = Settings.split(Api.CIRCULARDATA[i][9], '?')[0];

            TextView companyName = new TextView(this);
            companyName.setText(employerName);
            companyName.setTextSize(20);
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 30);
            companyName.setLayoutParams(lp);
            circular.addView(companyName);

            TextView jobNature = new TextView(this);
            jobNature.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            jobNature.setText(getString(R.string.circular_nature) + " " + Api.CIRCULARDATA[i][6]);
            circular.addView(jobNature);

            TextView minDegree = new TextView(this);
            minDegree.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            minDegree.setText(getString(R.string.circular_degree) + " " + Api.CIRCULARDATA[i][2]);
            circular.addView(minDegree);

            circular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CircularActivity.class);
                    intent.putExtra("circularIndex", view.getId());
                    startActivity(intent);
                }
            });

            circulars.addView(circular);
        }
    }

    public void employeeDashboard(LinearLayout circulars) {
        Api.findMatches();

        for (int i = 0; i < Api.CIRCULARDATA.length; i++) {
            LinearLayout circular = new LinearLayout(this);
            circular.setId(i);
            circular.setOrientation(LinearLayout.VERTICAL);
            circular.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            circular.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 0) lp.setMargins(10, 10, 10, 10);
            else lp.setMargins(10, 0, 10, 10);
            circular.setLayoutParams(lp);

            String employerName = Settings.split(Api.CIRCULARDATA[i][9], '?')[0];

            TextView companyName = new TextView(this);
            companyName.setTextSize(20);
            companyName.setText(employerName);
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 30);
            companyName.setLayoutParams(lp);
            circular.addView(companyName);

            TextView jobNature = new TextView(this);
            jobNature.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            jobNature.setText(getString(R.string.circular_nature) + " " + Api.CIRCULARDATA[i][6]);
            circular.addView(jobNature);

            TextView minDegree = new TextView(this);
            minDegree.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            minDegree.setText(getString(R.string.circular_degree) + " " + Api.CIRCULARDATA[i][2]);
            circular.addView(minDegree);

            circular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CircularActivity.class);
                    intent.putExtra("circularIndex", view.getId());
                    startActivity(intent);
                }
            });

            circulars.addView(circular);
        }
    }

    public void setEmployeeProfile() {
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

        Api.getEmployeeProfile(User.id + "");
        String[] data = Settings.split(Api.PROFILEDATA, '?');

        name.setText(name.getText() + " " + data[0]);
        sex.setText(sex.getText() + " " + data[4]);
        birthday.setText(birthday.getText() + " " + data[1]);
        email.setText(email.getText() + " " + User.credentials.get("email"));
        address.setText(address.getText() + " " + data[2]);
        phone.setText(phone.getText() + " " + data[3]);

        Api.getJobPreference(User.id + "");
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

    public void setEmployerProfile() {
        TextView name = (TextView) findViewById(R.id.textView_profile_employer_name);
        TextView details = (TextView) findViewById(R.id.textView_profile_employer_details);
        TextView email = (TextView) findViewById(R.id.textView_profile_employer_email);
        TextView address = (TextView) findViewById(R.id.textView_profile_employer_address);
        TextView phone = (TextView) findViewById(R.id.textView_profile_employer_phone);

        String[] data = Settings.split(Api.getEmployerData(User.id + ""), '?');

        name.setText(name.getText() + " " + data[0]);
        details.setText(details.getText() + " " + data[3]);
        email.setText(email.getText() + " " + User.credentials.get("email"));
        address.setText(address.getText() + " " + data[1]);
        phone.setText(phone.getText() + " " + data[2]);
    }

    public void Employer2(View v) {
        TextView error = (TextView) findViewById(R.id.textView_register_employer_step2_error);
        Spinner nature = (Spinner) findViewById(R.id.spinner_register_employer_nature);
        EditText location = (EditText) findViewById(R.id.editText_register_employer_location);
        EditText salary = (EditText) findViewById(R.id.editText_register_employer_salary);
        EditText description = (EditText) findViewById(R.id.editText_register_employer_description);
        DatePicker deadline = (DatePicker) findViewById(R.id.datePicker_register_employer_deadline);

        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String n = nature.getSelectedItem().toString().trim(), l = location.getText().toString().trim(), s = salary.getText().toString().trim(), des = description.getText().toString(), dea = deadline.getYear() + "-" + (deadline.getMonth() + 1) + "-" + deadline.getDayOfMonth();

        if (nature.getSelectedItemId() == 0 || l.length() == 0 || des.length() == 0)
            error.setVisibility(View.VISIBLE);
        else {
            Employer.NATURE = n;
            Employer.LOCATION = l;
            Employer.SALARY = s;
            Employer.DESCRIPTION = des;
            Employer.DEADLINE = dea;

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register_category, null, false));

            setTitle(getString(R.string.title_register_category));

            fetchCategories();
        }
    }

    public void fetchCategories() {
        error = (TextView) findViewById(R.id.textView_register_category_error);
        category1 = (Spinner) findViewById(R.id.spinner_register_category_category1);
        category2 = (Spinner) findViewById(R.id.spinner_register_category_category2);
        category3 = (Spinner) findViewById(R.id.spinner_register_category_category3);
        role1 = (Spinner) findViewById(R.id.spinner_register_category_role1);
        role2 = (Spinner) findViewById(R.id.spinner_register_category_role2);
        role3 = (Spinner) findViewById(R.id.spinner_register_category_role3);

        final ArrayList<ArrayList<String>> categories = Api.getCategories(getString(R.string.spinner_register_categories));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories.get(1));

        category1.setAdapter(adapter);
        category2.setAdapter(adapter);
        category3.setAdapter(adapter);

        category1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> roles = Api.getRoles(getString(R.string.spinner_register_roles), categories.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, roles.get(1));
                    role1.setAdapter(adapter);

                    role1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) r1 = roles.get(0).get(i) + "_";
                            else r1 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    r1 = "";
                    role1.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> roles = Api.getRoles(getString(R.string.spinner_register_roles), categories.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, roles.get(1));
                    role2.setAdapter(adapter);

                    role2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) r2 = roles.get(0).get(i) + "_";
                            else r2 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    r2 = "";
                    role2.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> roles = Api.getRoles(getString(R.string.spinner_register_roles), categories.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, roles.get(1));
                    role3.setAdapter(adapter);

                    role3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) r3 = roles.get(0).get(i) + "_";
                            else r3 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    r3 = "";
                    role3.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    TextView error;
    String r1 = "", r2 = "", r3 = "";
    Spinner category1, category2, category3, role1, role2, role3;

    public void registerCategory(View v) {
        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        if ((r1 + r2 + r3).length() == 0)
            error.setVisibility(View.VISIBLE);
        else {
            Employer.CATEGORIES = "_" + r1 + r2 + r3;

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register_employer_4, null, false));

            setTitle(getString(R.string.title_register_qualification));

            ((Button) findViewById(R.id.button_register_employer_step4_finish)).setText(R.string.circular_add);

            fetchDegrees2();
        }
    }

    public void fetchDegrees2() {
        error = (TextView) findViewById(R.id.textView_register_employer_step4_error);
        level1 = (Spinner) findViewById(R.id.spinner_register_levels_level1);
        level2 = (Spinner) findViewById(R.id.spinner_register_levels_level2);
        level3 = (Spinner) findViewById(R.id.spinner_register_levels_level3);
        degree1 = (Spinner) findViewById(R.id.spinner_register_levels_degree1);
        degree2 = (Spinner) findViewById(R.id.spinner_register_levels_degree2);
        degree3 = (Spinner) findViewById(R.id.spinner_register_levels_degree3);
        experience = (EditText) findViewById(R.id.editText_register_employer_experience);

        final ArrayList<ArrayList<String>> levels = Api.getLevels(getString(R.string.spinner_register_employer_education));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels.get(1));

        level1.setAdapter(adapter);
        level2.setAdapter(adapter);
        level3.setAdapter(adapter);

        level1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> degrees = Api.getDegrees(getString(R.string.spinner_register_degree), levels.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, degrees.get(1));
                    degree1.setAdapter(adapter);

                    degree1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) d1 = degrees.get(0).get(i) + "_";
                            else d1 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    d1 = "";
                    degree1.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        level2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> degrees = Api.getDegrees(getString(R.string.spinner_register_degree), levels.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, degrees.get(1));
                    degree2.setAdapter(adapter);

                    degree2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) d2 = degrees.get(0).get(i) + "_";
                            else d2 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    d2 = "";
                    degree2.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        level3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> degrees = Api.getDegrees(getString(R.string.spinner_register_degree), levels.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, degrees.get(1));
                    degree3.setAdapter(adapter);

                    degree3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) d3 = degrees.get(0).get(i) + "_";
                            else d3 = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    d3 = "";
                    degree3.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    boolean registered = false;
    String d1 = "", d2 = "", d3 = "";
    Spinner level1, level2, level3, degree1, degree2, degree3;
    EditText experience;

    public void Employer4(View v) {
        if (registered) return;
        else registered = true;
        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String e = experience.getText().toString();

        if ((d1 + d2 + d3).length() == 0) {
            error.setText(R.string.empty_levels);
            error.setVisibility(View.VISIBLE);
        } else {
            if (e.equals("")) e = "0";

            Employer.DEGREE = "_" + d1 + d2 + d3;
            Employer.EXPERIENCE = e;

            Api.addCircular();
            Api.match(Api.RESULT);

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_dashboard, null, false));

            setTitle(getString(R.string.title_dashboard));

            if (User.type == 1)
                employeeDashboard((LinearLayout) findViewById(R.id.linearLayout_dashboard_circulars));
            else
                employerDashboard((LinearLayout) findViewById(R.id.linearLayout_dashboard_circulars));
        }
    }

    public void setEmployeeEditProfile() {
        LinearLayout work = (LinearLayout) findViewById(R.id.linearLayout_editProfile_work);
        LinearLayout skills = (LinearLayout) findViewById(R.id.linearLayout_editProfile_skills);

        work.setVisibility(View.VISIBLE);
        skills.setVisibility(View.VISIBLE);

        EditText address = (EditText) findViewById(R.id.editText_editProfile_address);
        EditText phone = (EditText) findViewById(R.id.editText_editProfile_phone);
        EditText experience = (EditText) findViewById(R.id.editText_editProfile_experience);
        EditText companies = (EditText) findViewById(R.id.editText_editProfile_companies);
        EditText workDetails = (EditText) findViewById(R.id.editText_editProfile_work);
        EditText interpersonal = (EditText) findViewById(R.id.editText_editProfile_interpersonal);
        EditText computer = (EditText) findViewById(R.id.editText_editProfile_computer);
        EditText language = (EditText) findViewById(R.id.editText_editProfile_language);
        EditText achievements = (EditText) findViewById(R.id.editText_editProfile_achievements);

        String[] data = Settings.split(Api.PROFILEDATA, '?');

        address.setText(data[2]);
        phone.setText(data[3]);

        data = Settings.split(Api.RESULT, '?');

        experience.setText(data[3]);
        companies.setText(data[2]);
        workDetails.setText(data[1]);
        interpersonal.setText(data[7]);
        computer.setText(data[9]);
        language.setText(data[8]);
        achievements.setText(data[10]);
    }

    public void setEmployerEditProfile() {
        LinearLayout company = (LinearLayout) findViewById(R.id.linearLayout_editProfile_company);

        company.setVisibility(View.VISIBLE);

        EditText address = (EditText) findViewById(R.id.editText_editProfile_address);
        EditText phone = (EditText) findViewById(R.id.editText_editProfile_phone);
        EditText details = (EditText) findViewById(R.id.editText_editProfile_details);

        String[] data = Settings.split(Api.RESULT, '?');

        details.setText(data[3]);
        address.setText(data[1]);
        phone.setText(data[2]);
    }

    public void save(View view) {
        EditText address = (EditText) findViewById(R.id.editText_editProfile_address);
        EditText phone = (EditText) findViewById(R.id.editText_editProfile_phone);

        String a = address.getText().toString().trim(), p = phone.getText().toString().trim();

        String[] data;

        if (User.type == 1) {
            data = Settings.split(Api.PROFILEDATA, '?');

            if (a.length() == 0) a = data[2];
            if (p.length() == 0) p = data[3];
        } else {
            data = Settings.split(Api.RESULT, '?');

            if (a.length() == 0) a = data[1];
            if (p.length() == 0) p = data[2];
        }

        HashMap<String, String> postdata = new HashMap<>();
        postdata.put("id", User.id + "");
        postdata.put("address", a);
        postdata.put("phone", p);


        if (User.type == 1) {
            EditText experience = (EditText) findViewById(R.id.editText_editProfile_experience);
            EditText companies = (EditText) findViewById(R.id.editText_editProfile_companies);
            EditText workDetails = (EditText) findViewById(R.id.editText_editProfile_work);
            EditText interpersonal = (EditText) findViewById(R.id.editText_editProfile_interpersonal);
            EditText computer = (EditText) findViewById(R.id.editText_editProfile_computer);
            EditText language = (EditText) findViewById(R.id.editText_editProfile_language);
            EditText achievements = (EditText) findViewById(R.id.editText_editProfile_achievements);

            String exp = experience.getText().toString().trim(), c = companies.getText().toString().trim(), wd = workDetails.getText().toString().trim(), i = interpersonal.getText().toString().trim(), cmptr = computer.getText().toString().trim(), l = language.getText().toString().trim(), acvmnts = achievements.getText().toString().trim();

            if (exp.length() == 0) exp = "0";

            postdata.put("experience", exp);
            postdata.put("companies", c);
            postdata.put("details", wd);
            postdata.put("interpersonal", i);
            postdata.put("computer", cmptr);
            postdata.put("language", l);
            postdata.put("achievements", acvmnts);

            Api.saveEmployeeProfile(postdata);
        } else {
            EditText details = (EditText) findViewById(R.id.editText_editProfile_details);

            String d = details.getText().toString().trim();

            postdata.put("details", d);

            Api.saveEmployerProfile(postdata);
        }

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.dashboard);
        rl.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (User.type == 1) {
            rl.addView(layoutInflater.inflate(R.layout.layout_employee_profile, null, false));
            setTitle(getString(R.string.title_profile));
            setEmployeeProfile();
        } else {
            rl.addView(layoutInflater.inflate(R.layout.layout_employer_profile, null, false));
            setTitle(getString(R.string.title_profile));
            setEmployerProfile();
        }

        inProfile = true;
        invalidateOptionsMenu();
    }

    public Context getContext() {
        return this;
    }
}
