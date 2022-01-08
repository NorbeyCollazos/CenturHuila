package com.ycjn.centurhuila;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.model.PostSitiosNaturales;

import java.util.HashMap;

public class DescSitioNatural extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    public AVLoadingIndicatorView avi;
    LinearLayout lininfo,linreco,linruta;
    LinearLayout linedatos,linereco,lineubi;
    RelativeLayout linevia;
    AppBarLayout barLayout;
    BottomNavigationView bottomNavigationView2;
    TextView datosbasicos,recomendaciones,cordenadas,viaterrestre,direccion;

    CollapsingToolbarLayout collapsingToolbarLayout;

    private SliderLayout mDemoSlider;

    FloatingActionButton fab;
    ImageButton atras;

    String mun = "";
    String id = "";

    DatabaseReference ref;
    private DatabaseReference sitioRef;
    PostSitiosNaturales pssitios;


   public void MostrarSitio(String paramString){
        sitioRef.child(paramString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pssitios = (PostSitiosNaturales) dataSnapshot.getValue(PostSitiosNaturales.class);


                collapsingToolbarLayout.setTitle(pssitios.getNombre());
               datosbasicos.setText(pssitios.getDatosbasicos());
               recomendaciones.setText(pssitios.getRecomendaciones());
               viaterrestre.setText(pssitios.getViaterrestre());
               direccion.setText(pssitios.getDireccion());

               stopAnim();

               //codigos para el slider
                HashMap<String,String> url_maps = new HashMap<String, String>();

                url_maps.put(pssitios.getDescimagen1(), pssitios.getImagen1());
                url_maps.put(pssitios.getDescimagen2(), pssitios.getImagen2());
                url_maps.put(pssitios.getDescimagen3(), pssitios.getImagen3());
                url_maps.put(pssitios.getDescimagen4(), pssitios.getImagen4());

                for(String name : url_maps.keySet()){
                    TextSliderView textSliderView = new TextSliderView(DescSitioNatural.this);
                    // initialize a SliderLayout
                    textSliderView
                            .description(name)
                            .image(url_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(DescSitioNatural.this);

                    //add your extra information
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra",name);

                    mDemoSlider.addSlider(textSliderView);
                }
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                mDemoSlider.setDuration(4000);
                mDemoSlider.addOnPageChangeListener(DescSitioNatural.this);
                //hasta aqui codigos para el slider
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_sitio_natural);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

         linedatos = findViewById(R.id.lineardatossn);
         linereco = findViewById(R.id.linearrecosn);
         lineubi = findViewById(R.id.linearubisn);
         linevia = findViewById(R.id.relaviasn);
        bottomNavigationView2 = (BottomNavigationView)findViewById(R.id.bottomNavigationSN);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);


        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();


        atras = (ImageButton)findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            mun = (String) bundle.get("mun");
        }



        //ref = FirebaseDatabase.getInstance().getReference();
        sitioRef = FirebaseDatabase.getInstance().getReference().child("CenturHuila/"+mun+"/sitiosNaturales");

        if (getIntent() != null)
            id = getIntent().getStringExtra("id");

        if (!id.isEmpty())
        {MostrarSitio(id);}




        if (mun.equals("Agrado")){

            ColoresAgrado();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAgrado)); //Define color blanco.
            }


        }else  if (mun.equals("Altamira")){

            ColoresAltamira();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAltamira)); //Define color blanco.
            }

        }else  if (mun.equals("Garzon")){
            ColoresGarzon();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGarzon)); //Define color blanco.
            }

        }else  if (mun.equals("Gigante")){
            ColoresGigante();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGigante)); //Define color blanco.
            }

        }else  if (mun.equals("Guadalupe")){
            ColoresGuadalupe();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGuadalupe)); //Define color blanco.
            }
        }else  if (mun.equals("Pital")){
            ColoresPital();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkPital)); //Define color blanco.
            }

        }else  if (mun.equals("Suaza")){
            ColoresSuaza();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSuaza)); //Define color blanco.
            }

        }else  if (mun.equals("Tarqui")){
            ColoresTarqui();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkTarqui)); //Define color blanco.
            }

        }

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        lininfo = findViewById(R.id.lininfo);
        linreco = findViewById(R.id.linrecomen);
        linruta = findViewById(R.id.linruta);

        datosbasicos = (TextView)findViewById(R.id.tvdatosbasicos);
        recomendaciones = (TextView)findViewById(R.id.tvrecomendaciones);
        viaterrestre = (TextView)findViewById(R.id.tvviaterrestre);
        direccion = (TextView)findViewById(R.id.tvdireccion);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationSN);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();


                switch (item.getItemId()) {
                    case R.id.informacionItemSN:
                        getSupportActionBar().setTitle(getString(R.string.info));
                        lininfo.setVisibility(View.VISIBLE);
                        linreco.setVisibility(View.GONE);
                        linruta.setVisibility(View.GONE);
                        //fab.setVisibility(View.GONE);
                        break;

                    case R.id.precaucionItemSN:
                        getSupportActionBar().setTitle(getString(R.string.recomendaciones));
                        lininfo.setVisibility(View.GONE);
                        linreco.setVisibility(View.VISIBLE);
                        linruta.setVisibility(View.GONE);
                        //fab.setVisibility(View.GONE);
                        break;

                    case R.id.rutaItemSN:
                        getSupportActionBar().setTitle(getString(R.string.ruta));
                        lininfo.setVisibility(View.GONE);
                        linreco.setVisibility(View.GONE);
                        linruta.setVisibility(View.VISIBLE);
                        //fab.setVisibility(View.VISIBLE);
                        break;
                }

                return true;
            }
        });

    }

    //metodos para el slider
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    //hasta aqui metodos para el slider-------:)


    void startAnim()
    {
        this.avi.show();
    }

    void stopAnim()
    {
        this.avi.hide();
    }


    public void ColoresAgrado(){
        linedatos.setBackgroundColor(getResources().getColor(R.color.colorTargetAgrado));
        linereco.setBackgroundResource(R.color.colorTargetAgrado);
        lineubi.setBackgroundResource(R.color.colorTargetAgrado);
        linevia.setBackgroundResource(R.color.colorTargetAgrado);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryAgrado);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAgrado));
    }

    public void ColoresAltamira(){
        linedatos.setBackgroundResource(R.color.colorTargetAltamira);
        linereco.setBackgroundResource(R.color.colorTargetAltamira);
        lineubi.setBackgroundResource(R.color.colorTargetAltamira);
        linevia.setBackgroundResource(R.color.colorTargetAltamira);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryAltamira);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAltamira));
    }

    public void ColoresGarzon(){
        linedatos.setBackgroundResource(R.color.colorTargetGarzon);
        linereco.setBackgroundResource(R.color.colorTargetGarzon);
        lineubi.setBackgroundResource(R.color.colorTargetGarzon);
        linevia.setBackgroundResource(R.color.colorTargetGarzon);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryGarzon);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGarzon));
    }

    public void ColoresGigante(){
        linedatos.setBackgroundResource(R.color.colorTargetGigante);
        linereco.setBackgroundResource(R.color.colorTargetGigante);
        lineubi.setBackgroundResource(R.color.colorTargetGigante);
        linevia.setBackgroundResource(R.color.colorTargetGigante);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryGigante);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGigante));
    }

    public void ColoresGuadalupe(){
        linedatos.setBackgroundResource(R.color.colorTargetGuadalupe);
        linereco.setBackgroundResource(R.color.colorTargetGuadalupe);
        lineubi.setBackgroundResource(R.color.colorTargetGuadalupe);
        linevia.setBackgroundResource(R.color.colorTargetGuadalupe);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGuadalupe));
    }

    public void ColoresPital(){
        linedatos.setBackgroundResource(R.color.colorTargetPital);
        linereco.setBackgroundResource(R.color.colorTargetPital);
        lineubi.setBackgroundResource(R.color.colorTargetPital);
        linevia.setBackgroundResource(R.color.colorTargetPital);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryPital);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryPital));
    }

    public void ColoresSuaza(){
        linedatos.setBackgroundResource(R.color.colorTargetSuaza);
        linereco.setBackgroundResource(R.color.colorTargetSuaza);
        lineubi.setBackgroundResource(R.color.colorTargetSuaza);
        linevia.setBackgroundResource(R.color.colorTargetSuaza);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimarySuaza);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimarySuaza));
    }

    public void ColoresTarqui(){
        linedatos.setBackgroundResource(R.color.colorTargetTarqui);
        linereco.setBackgroundResource(R.color.colorTargetTarqui);
        lineubi.setBackgroundResource(R.color.colorTargetTarqui);
        linevia.setBackgroundResource(R.color.colorTargetTarqui);
        bottomNavigationView2.setBackgroundResource(R.color.colorPrimaryTarqui);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryTarqui));
    }

}
