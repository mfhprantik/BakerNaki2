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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Settings s = new Settings(this);

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
            MenuItem item =  navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
            navigationView.setCheckedItem(R.id.nav_login);
        }

        checkCredentials();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register, null, false));

            setTitle(getString(R.string.title_signup));
        } else if (id == R.id.nav_login) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_login, null, false));

            setTitle(getString(R.string.title_login));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void login(View v) {
        TextView error = (TextView) findViewById(R.id.textView_login_error);
        EditText email = (EditText) findViewById(R.id.editText_login_email);
        EditText password = (EditText) findViewById(R.id.editText_login_password);

        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String e = email.getText().toString().trim(), p = password.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            error.setText(R.string.empty_credentials);
            error.setVisibility(View.VISIBLE);
            return;
        }

        User.credentials.put("email", e);
        User.credentials.put("password", p);

        Api.login();

        if (Api.RESULT.length() == 0) {
            error.setText(R.string.wrong_credentials);
            error.setVisibility(View.VISIBLE);
        } else if (Api.RESULT.length() < 13) {
            String[] user = Settings.split(Api.RESULT, '?');

            User.id = Integer.parseInt(user[0]);
            User.type = Integer.parseInt(user[1]);
            User.online = true;

            try {
                PrintWriter writer = new PrintWriter(new File(Settings.loginCredentials));
                writer.println(e);
                writer.print(p);
                writer.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        } else {
            error.setText(R.string.connection_error);
            error.setVisibility(View.VISIBLE);
        }
    }

    public void checkCredentials() {
        try {
            FileReader reader = new FileReader(new File(Settings.loginCredentials));
            BufferedReader br = new BufferedReader(reader);

            User.credentials.put("email", br.readLine());
            User.credentials.put("password", br.readLine());

            Api.login();

            if (Api.RESULT.length() > 0 && Api.RESULT.length() <= 13) {
                String[] user = Settings.split(Api.RESULT, '?');

                User.id = Integer.parseInt(user[0]);
                User.type = Integer.parseInt(user[1]);
                User.online = true;

                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(View v) {
            TextView error = (TextView) findViewById(R.id.textView_register_step0_error);
            Spinner type = (Spinner) findViewById(R.id.spinner_register_type);
            EditText email = (EditText) findViewById(R.id.editText_register_email);
            EditText password1 = (EditText) findViewById(R.id.editText_register_password);
            EditText password2 = (EditText) findViewById(R.id.editText_register_confirmpw);

            if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

            int t = (int) type.getSelectedItemId();
            String e = email.getText().toString().trim(), p1 = password1.getText().toString(), p2 = password2.getText().toString();

            if (t == 0 || e.length() == 0 || p1.length() == 0 || p2.length() == 0) {
                error.setText(R.string.empty_fields);
                error.setVisibility(View.VISIBLE);
            } else if (!p1.equals(p2)) {
                error.setText(R.string.password_match_error);
                error.setVisibility(View.VISIBLE);
            } else if (Api.userExist(e)) {
                if (Api.RESULT.length() > 1) error.setText(R.string.connection_error);
                else error.setText(R.string.user_exist);

                error.setVisibility(View.VISIBLE);
            } else {
                if (t == 1) {
                    User.type = 1;
                    Employee.EMAIL = e;
                    Employee.PASSWORD = p1;

                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                    rl.removeAllViews();
                    LayoutInflater layoutInflater = (LayoutInflater)
                            this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rl.addView(layoutInflater.inflate(R.layout.layout_register_employee_1, null, false));

                    setTitle(getString(R.string.title_register_personal));
                } else {
                    User.type = 2;
                    Employer.EMAIL = e;
                    Employer.PASSWORD = p1;

                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                    rl.removeAllViews();
                    LayoutInflater layoutInflater = (LayoutInflater)
                            this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rl.addView(layoutInflater.inflate(R.layout.layout_register_employer_1, null, false));

                    setTitle(getString(R.string.title_register_company));
                }
            }
    }

    public void Employer1(View v) {
        TextView error = (TextView) findViewById(R.id.textView_register_employer_step1_error);
        EditText name = (EditText) findViewById(R.id.editText_register_employer_name);
        EditText address = (EditText) findViewById(R.id.editText_register_employer_address);
        EditText phone = (EditText) findViewById(R.id.editText_register_employer_phone);
        EditText details = (EditText) findViewById(R.id.editText_register_employer_details);

        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String n = name.getText().toString().trim(), a = address.getText().toString().trim(), p = phone.getText().toString().trim(), d = details.getText().toString().trim();

        if (n.length() == 0 || a.length() == 0 || p.length() == 0)
            error.setVisibility(View.VISIBLE);
        else {
            Employer.FULLNAME = n;
            Employer.ADDRESS = a;
            Employer.PHONE = p;
            Employer.DETAILS = d;

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register_employer_2, null, false));

            setTitle(getString(R.string.title_register_circular));
        }
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

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register_category, null, false));

            setTitle(getString(R.string.title_register_category));

            fetchCategories();
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


            Api.registerEmployer();
            if (Api.RESULT.length() > 10) {
                registered = false;
                error.setText(R.string.connection_error);
                error.setVisibility(View.VISIBLE);
            } else {
                Api.match(Api.RESULT);

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                rl.removeAllViews();
                LayoutInflater layoutInflater = (LayoutInflater)
                        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rl.addView(layoutInflater.inflate(R.layout.layout_login, null, false));

                setTitle(getString(R.string.title_login));
            }
        }
    }

    public void Employee1(View v) {
        TextView error = (TextView) findViewById(R.id.textView_register_employee_step1_error);
        EditText name = (EditText) findViewById(R.id.editText_register_employee_name);
        RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup_register_employee_sex);
        EditText address = (EditText) findViewById(R.id.editText_register_employee_address);
        EditText phone = (EditText) findViewById(R.id.editText_register_employee_phone);
        DatePicker birthday = (DatePicker) findViewById(R.id.datePicker_register_employee_birthday);

        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        try {
            RadioButton r = (RadioButton) findViewById(sex.getCheckedRadioButtonId());
            String n = name.getText().toString().trim(), a = address.getText().toString().trim(), p = phone.getText().toString().trim(), b = birthday.getYear() + "-" + (birthday.getMonth() + 1) + "-" + birthday.getDayOfMonth(), s = r.getText().toString();

            if (n.length() == 0 || a.length() == 0 || p.length() == 0 || sex.equals(""))
                error.setVisibility(View.VISIBLE);
            else {
                Employee.FULLNAME = n;
                Employee.SEX = s;
                Employee.ADDRESS = a;
                Employee.PHONE = p;
                Employee.BIRTHDAY = b;

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                rl.removeAllViews();
                LayoutInflater layoutInflater = (LayoutInflater)
                        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rl.addView(layoutInflater.inflate(R.layout.layout_register_category, null, false));

                setTitle(getString(R.string.title_register_category));

                fetchCategories();
            }
        } catch (Exception e) {
            error.setVisibility(View.VISIBLE);
            e.printStackTrace();
            return;
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
    String r1 = "", r2 = "", r3 = "";
    Spinner category1, category2, category3, role1, role2, role3;

    public Context getContext() {
        return this;
    }

    public void registerCategory(View v) {
        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        if ((r1 + r2 + r3).length() == 0)
            error.setVisibility(View.VISIBLE);
        else {
            if (User.type == 1) {
                Employee.CATEGORIES = "_" + r1 + r2 + r3;

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                rl.removeAllViews();
                LayoutInflater layoutInflater = (LayoutInflater)
                        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rl.addView(layoutInflater.inflate(R.layout.layout_register_employee_3, null, false));

                setTitle(getString(R.string.title_register_education));

                fetchDegrees();
            } else {
                Employer.CATEGORIES = "_" + r1 + r2 + r3;

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
                rl.removeAllViews();
                LayoutInflater layoutInflater = (LayoutInflater)
                        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rl.addView(layoutInflater.inflate(R.layout.layout_register_employer_4, null, false));

                setTitle(getString(R.string.title_register_qualification));

                fetchDegrees2();
            }
        }
    }

    public void fetchDegrees() {
        error = (TextView) findViewById(R.id.textView_register_employee_step3_error);
        education = (Spinner) findViewById(R.id.spinner_register_employee_education);
        degree = (Spinner) findViewById(R.id.spinner_register_employee_degree);
        year = (EditText) findViewById(R.id.editText_register_employee_year);
        institution = (EditText) findViewById(R.id.editText_register_employee_institution);

        final ArrayList<ArrayList<String>> levels = Api.getLevels(getString(R.string.spinner_register_employee_education));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels.get(1));
        education.setAdapter(adapter);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos != 0) {
                    final ArrayList<ArrayList<String>> degrees = Api.getDegrees(getString(R.string.spinner_register_degree), levels.get(0).get(pos));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, degrees.get(1));
                    degree.setAdapter(adapter);

                    degree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i != 0) d = degrees.get(0).get(i);
                            else d = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    d = "";
                    degree.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    String d = "";
    TextView error;
    Spinner education, degree;
    EditText year, institution;

    public void Employee3(View v) {
        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String y = year.getText().toString().trim(), i = institution.getText().toString().trim();

        if (d.length() == 0 || y.length() == 0 || i.length() == 0)
            error.setVisibility(View.VISIBLE);
        else {
            Employee.DEGREE = d;
            Employee.YEAR = y;
            Employee.INSTITUTION = i;

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_register_employee_4, null, false));

            setTitle(getString(R.string.title_register_work));
        }
    }

    public void Employee4(View v) {
        EditText experience = (EditText) findViewById(R.id.editText_register_employee_experience);
        EditText companies = (EditText) findViewById(R.id.editText_register_employee_companies);
        EditText work = (EditText) findViewById(R.id.editText_register_employee_work);

        String e = experience.getText().toString().trim(), c = companies.getText().toString().trim(), w = work.getText().toString().trim();

        if (e.equals("")) e = "0";

        Employee.EXPERIENCE = e;
        Employee.COMPANIES = c;
        Employee.WORK = w;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
        rl.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rl.addView(layoutInflater.inflate(R.layout.layout_register_employee_5, null, false));

        setTitle(getString(R.string.title_register_skills));
    }

    public void Employee5(View v) {
        TextView error = (TextView) findViewById(R.id.textView_register_employee_step5_error);
        EditText interpersonal = (EditText) findViewById(R.id.editText_register_employee_skills_interpersonal);
        EditText computer = (EditText) findViewById(R.id.editText_register_employee_skills_computer);
        EditText language = (EditText) findViewById(R.id.editText_register_employee_skills_language);
        EditText achievements = (EditText) findViewById(R.id.editText_register_employee_achievements);

        if (registered) return;
        else registered = true;

        if (error.getVisibility() == View.VISIBLE) error.setVisibility(View.GONE);

        String i = interpersonal.getText().toString().trim(), c = computer.getText().toString().trim(), l = language.getText().toString().trim(), a = achievements.getText().toString().trim();

        Employee.INTERPERSONAL = i;
        Employee.COMPUTER = c;
        Employee.LANGUAGE = l;
        Employee.ACHIEVEMENTS = a;

        Api.registerEmployee();
        if (Api.RESULT.length() > 0) {
            registered = false;
            error.setVisibility(View.VISIBLE);
        } else {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.main);
            rl.removeAllViews();
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rl.addView(layoutInflater.inflate(R.layout.layout_login, null, false));

            setTitle(getString(R.string.title_login));
        }
    }
}
