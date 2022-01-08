package com.ycjn.centurhuila;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.model.PostBaner;
import com.ycjn.centurhuila.utilidades.Utilidades;

import java.util.HashMap;

public class Categorias extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    CardView info,natural,fiesta,hotel,restaurante,paterimonio;
    ImageView imgInfo,imgNatura,imgCultura,imgFiesta,imgHotel,imgRestaurante;
    TextView tvSpinner,tvIdentificador,tvMunicipio;
    TextView menConexion;
    String datas;

    RelativeLayout relatnatural,relainfo,relacultura,relafiesta,relahoteles,relarestaurantes;
    AppBarLayout barLayout;

    ConexionSQLiteHelper conn;

    CollapsingToolbarLayout collapsingToolbarLayout;
    private SliderLayout mDemoSlider;

    String dato = "";

    DatabaseReference ref;
    DatabaseReference banerRef;
    PostBaner baner;

    public AVLoadingIndicatorView avi;

    ImageButton atras;

    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;

    //Handle para retardar acciones
    private Handler mHandler= new Handler();


    //flotante de altura translationZ o elevation
    float alturaOff=0;
    float alturaOn=10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //verificar si hay conexion a internet
        menConexion = (TextView)findViewById(R.id.tvconexion);
        if (!verificaConexion(this)) {
            menConexion.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    menConexion.setVisibility(View.GONE);
                }
            },3000);
        }


        atras = (ImageButton)findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();

        imgInfo= (ImageView)findViewById(R.id.imgInfoGarzon);
        imgNatura=(ImageView)findViewById(R.id.imgNaturaGarzon);
        imgCultura=(ImageView)findViewById(R.id.imgCulturaGarzon);
        imgFiesta=(ImageView)findViewById(R.id.imgFiestaGarzon);
        imgHotel=(ImageView)findViewById(R.id.imgHotelGarzon);
        imgRestaurante=(ImageView)findViewById(R.id.imgRestauranteGarzon);

        tvIdentificador=(TextView)findViewById(R.id.tvIdentificador);
        tvMunicipio=(TextView)findViewById(R.id.tvMunicipio);


        relatnatural = findViewById(R.id.relativenatural);
        relainfo = findViewById(R.id.relativeinfo);
        relacultura = findViewById(R.id.relativecultura);
        relafiesta = findViewById(R.id.relativefiestas);
        relahoteles = findViewById(R.id.relativehoteles);
        relarestaurantes = findViewById(R.id.relativerestaurantes);
        toolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        barLayout = (AppBarLayout)findViewById(R.id.app_bar);


        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null){
             dato = (String) bundle.get("municipio");

            if (dato.equals("Agrado")){
                agrado();
                actualizar();
                ColoresAgrado();//llamamos los colores para el municipio
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//definimos el color del snakbar
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAgrado)); //Define color blanco.
                }

            }else  if (dato.equals("Altamira")){
                altamira();
                actualizar();
                ColoresAltamira();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAltamira)); //Define color blanco.
                }

            }else  if (dato.equals("Garzon")){
                garzon();
                actualizar();
                ColoresGarzon();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGarzon)); //Define color blanco.
                }

            }else  if (dato.equals("Gigante")){
                gigante();
                actualizar();
                ColoresGigante();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGigante)); //Define color blanco.
                }

            }else  if (dato.equals("Guadalupe")){
                guadalupe();
                actualizar();
                ColoresGuadalupe();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGuadalupe)); //Define color blanco.
                }
            }else  if (dato.equals("Pital")){
                pital();
                actualizar();
                ColoresPital();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkPital)); //Define color blanco.
                }

            }else  if (dato.equals("Suaza")){
                suaza();
                actualizar();
                ColoresSuaza();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSuaza)); //Define color blanco.
                }

            }else  if (dato.equals("Tarqui")){
                tarqui();
                actualizar();
                ColoresTarqui();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkTarqui)); //Define color blanco.
                }

            }
        }else {
            //Se realizara la consulta del ultimo municipio registrado
            consultar();
        }


         ref = FirebaseDatabase.getInstance().getReference();
         banerRef = ref.child("CenturHuila/"+dato+"/baner");



        paterimonio = (CardView)findViewById(R.id.cardCulturaGarzon);
        fiesta = (CardView)findViewById(R.id.cardFiestaGarzon);
        hotel = (CardView)findViewById(R.id.cardHotelGarzon);
        restaurante = (CardView)findViewById(R.id.cardRestauranteGarzon);
        info = (CardView)findViewById(R.id.cardInfoGarzon);
        natural = (CardView)findViewById(R.id.cardNaturalGarzon);

        altura();
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {if (Build.VERSION.SDK_INT == 19) {
                hotel.setCardElevation(alturaOff);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hotel.setCardElevation(alturaOn);
                    }
                },1000);
            }
            else {
                hotel.setTranslationZ(alturaOff);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hotel.setTranslationZ(alturaOn);
                    }
                },1000);
            }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                imgHotel.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,ListaHotel.class);
                inicio.putExtra("hotel",dato);
                startActivity(inicio);
            }
        });
        restaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    restaurante.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restaurante.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    restaurante.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restaurante.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                imgRestaurante.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,ListaRestaurante.class);
                inicio.putExtra("mun",dato);
                startActivity(inicio);
            }
        });


        natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    natural.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            natural.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    natural.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            natural.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                imgNatura.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,ListaSitiosNaturales.class);
                inicio.putExtra("sn",dato);
                startActivity(inicio);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    info.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            info.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    info.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            info.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                // registrar();
                consultar();
                imgInfo.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,InformacionCategoria.class);
                inicio.putExtra("info",dato);
                startActivity(inicio);
            }
        });

        fiesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    fiesta.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fiesta.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    fiesta.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fiesta.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                imgFiesta.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,Fiestas.class);
                inicio.putExtra("mun",dato);
                startActivity(inicio);
            }
        });

        paterimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    paterimonio.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            paterimonio.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    paterimonio.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            paterimonio.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Animation animation= AnimationUtils.loadAnimation(Categorias.this,R.anim.rotacion);
                animation.setFillAfter(true);
                imgCultura.startAnimation(animation);
                Intent inicio = new Intent(Categorias.this,PatrimonioCultural.class);
                inicio.putExtra("mun",dato);
                startActivity(inicio);
            }
        });


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        banerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                baner = (PostBaner)dataSnapshot.getValue(PostBaner.class);
                //codigos para el slider

                HashMap<String,String> url_maps = new HashMap<String, String>();

                url_maps.put(baner.getDescimage1(), baner.getImage1());
                url_maps.put(baner.getDescimage2(), baner.getImage2());
                url_maps.put(baner.getDescimage3(), baner.getImage3());
                url_maps.put(baner.getDescimage4(), baner.getImage4());

                for(String name : url_maps.keySet()){
                    TextSliderView textSliderView = new TextSliderView(Categorias.this);
                    // initialize a SliderLayout
                    textSliderView
                            .description(name)
                            .image(url_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(Categorias.this);

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
                mDemoSlider.addOnPageChangeListener(Categorias.this);
                //hasta aqui codigos para el slider
                stopAnim();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



    private void tarqui() {
        getSupportActionBar().setTitle("TARQUI");
        getSupportActionBar().setSubtitle("Ruiseñor del Huila ");

    }

    private void suaza()
    {getSupportActionBar().setTitle("SUAZA");
        getSupportActionBar().setSubtitle("....");

    }

    private void pital() {
        getSupportActionBar().setTitle("EL PITAL");
        getSupportActionBar().setSubtitle("Edém de paz");

    }

    private void guadalupe() {
        getSupportActionBar().setTitle("GUADALUPE");
        getSupportActionBar().setSubtitle("...");
;
    }

    private void gigante() {
        getSupportActionBar().setTitle("GIGANTE");
        getSupportActionBar().setSubtitle("Capital cacaotera");

    }

    private void garzon() {
        getSupportActionBar().setTitle("GARZÓN");
        getSupportActionBar().setSubtitle("Capital Dioscesana ");
    }

    private void altamira() {
        getSupportActionBar().setTitle("ALTAMIRA");
        getSupportActionBar().setSubtitle("Tierra del bizcocho ");

    }

    private void agrado() {
        getSupportActionBar().setTitle("AGRADO");
        getSupportActionBar().setSubtitle("Oasis de paz");

    }

    public void consultar(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd usuarios",null,1);
        SQLiteDatabase db= conn.getReadableDatabase();
        tvIdentificador.setText("1");
        String[] parametros={tvIdentificador.getText().toString()};
        String[]campos={Utilidades.CAMPO_NOMBRE};

        try {

            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            tvMunicipio.setText(cursor.getString(0));
            cursor.close();
            String mun= tvMunicipio.getText().toString();
            if (mun.equals("agrado")){
                agrado();
            }
            else if (mun.equals("altamira")){
                altamira();
            } else if (mun.equals("garzon")){
                garzon();
            } else if (mun.equals("gigante")){
                gigante();
            } else if (mun.equals("guadalupe")){
                guadalupe();
            }else if (mun.equals("pital")){
                pital();
            }else if (mun.equals("suaza")){
                suaza();
            }else if (mun.equals("tarqui")){
                tarqui();
            }else {
                Toast.makeText(getApplication(),"no se encuentran municipios",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            registrar();
        }
    }

    public void actualizar(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String[] parametros ={tvIdentificador.getText().toString()};
        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,tvMunicipio.getText().toString());
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        db.close();

    }

    public void  registrar(){       //NO BORRAR ESTE CODIGO, EN CASO DE QUE NO EXISTA REGISTRO, SE DEBE REGISTRAR POR PRIMERA VEZ Y RETORNAR AL MENU PRINCIPAL
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        tvIdentificador.setText("1");
        tvMunicipio.setText("garzon");
        ContentValues values= new ContentValues();

        values.put(Utilidades.CAMPO_ID,tvIdentificador.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,tvMunicipio.getText().toString());

        Long idResultante= db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
        db.close();
        Intent inicio = new Intent(Categorias.this,InicioActivity.class);
        startActivity(inicio);
    }

    //metodos para los menus BMB ---------------------------------------------------------------------------------
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }


    private static int coloresIndex = 0;

    static int getColorResource() {
        if (coloresIndex >= colores.length) coloresIndex = 0;
        return colores[coloresIndex++];
    }

    private static int tituloIndex = 0;

    static int getTituloResource() {
        if (tituloIndex >= titulos.length) tituloIndex = 0;
        return titulos[tituloIndex++];
    }

    private static int subtituloIndex = 0;

    static int getSubTituloResource() {
        if (subtituloIndex >= subtitulos.length) subtituloIndex = 0;
        return subtitulos[subtituloIndex++];
    }

    //arreglos para las imagenes de los menu
    private static int[] imageResources = new int[]{
            R.drawable.ic_image,
            R.drawable.ic_image,

    };



    //arreglo para los colores
    private static int[] colores = new int[]{
            R.color.verde_oscuro,
            R.color.violeta,
    };

    //arreglo para los titulos
    private static int[] titulos = new int[]{
            R.string.mate,
            R.string.inmate,
    };

    //arreglo para los subtitulos
    private static int[] subtitulos = new int[]{
            R.string.smate,
            R.string.sinmate,
    };

    // fin de arreglos para los BMB--------------------------------------------------------------------


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

    //metodo para verificar la conexion a internet
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    public void ColoresAgrado(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryAgrado);
        relainfo.setBackgroundResource(R.color.colorPrimaryAgrado);
        relacultura.setBackgroundResource(R.color.colorPrimaryAgrado);
        relafiesta.setBackgroundResource(R.color.colorPrimaryAgrado);
        relahoteles.setBackgroundResource(R.color.colorPrimaryAgrado);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryAgrado);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAgrado));
    }

    public void ColoresAltamira(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryAltamira);
        relainfo.setBackgroundResource(R.color.colorPrimaryAltamira);
        relacultura.setBackgroundResource(R.color.colorPrimaryAltamira);
        relafiesta.setBackgroundResource(R.color.colorPrimaryAltamira);
        relahoteles.setBackgroundResource(R.color.colorPrimaryAltamira);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryAltamira);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAltamira));
    }

    public void ColoresGarzon(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryGarzon);
        relainfo.setBackgroundResource(R.color.colorPrimaryGarzon);
        relacultura.setBackgroundResource(R.color.colorPrimaryGarzon);
        relafiesta.setBackgroundResource(R.color.colorPrimaryGarzon);
        relahoteles.setBackgroundResource(R.color.colorPrimaryGarzon);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryGarzon);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGarzon));
    }

    public void ColoresGigante(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryGigante);
        relainfo.setBackgroundResource(R.color.colorPrimaryGigante);
        relacultura.setBackgroundResource(R.color.colorPrimaryGigante);
        relafiesta.setBackgroundResource(R.color.colorPrimaryGigante);
        relahoteles.setBackgroundResource(R.color.colorPrimaryGigante);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryGigante);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGigante));
    }

    public void ColoresGuadalupe(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        relainfo.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        relacultura.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        relafiesta.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        relahoteles.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGuadalupe));
    }

    public void ColoresPital(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryPital);
        relainfo.setBackgroundResource(R.color.colorPrimaryPital);
        relacultura.setBackgroundResource(R.color.colorPrimaryPital);
        relafiesta.setBackgroundResource(R.color.colorPrimaryPital);
        relahoteles.setBackgroundResource(R.color.colorPrimaryPital);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryPital);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryPital));
    }

    public void ColoresSuaza(){
        relatnatural.setBackgroundResource(R.color.colorPrimarySuaza);
        relainfo.setBackgroundResource(R.color.colorPrimarySuaza);
        relacultura.setBackgroundResource(R.color.colorPrimarySuaza);
        relafiesta.setBackgroundResource(R.color.colorPrimarySuaza);
        relahoteles.setBackgroundResource(R.color.colorPrimarySuaza);
        relarestaurantes.setBackgroundResource(R.color.colorPrimarySuaza);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimarySuaza));
    }

    public void ColoresTarqui(){
        relatnatural.setBackgroundResource(R.color.colorPrimaryTarqui);
        relainfo.setBackgroundResource(R.color.colorPrimaryTarqui);
        relacultura.setBackgroundResource(R.color.colorPrimaryTarqui);
        relafiesta.setBackgroundResource(R.color.colorPrimaryTarqui);
        relahoteles.setBackgroundResource(R.color.colorPrimaryTarqui);
        relarestaurantes.setBackgroundResource(R.color.colorPrimaryTarqui);
        toolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryTarqui));
    }

    public void altura(){
        //si la api es 19 se debe utilizar CardElevation, para evitar que se rompa la app
        if (Build.VERSION.SDK_INT == 19) {
            info.setCardElevation(alturaOn);
            natural.setCardElevation(alturaOn);
            hotel.setCardElevation(alturaOn);
            restaurante.setCardElevation(alturaOn);
            fiesta.setCardElevation(alturaOn);
            paterimonio.setCardElevation(alturaOn);

        }
        else {
            info.setTranslationZ(alturaOn);
            natural.setTranslationZ(alturaOn);
            hotel.setTranslationZ(alturaOn);
            restaurante.setTranslationZ(alturaOn);
            fiesta.setTranslationZ(alturaOn);
            paterimonio.setTranslationZ(alturaOn);
        }
    }


}
