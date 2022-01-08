package com.ycjn.centurhuila;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.model.PostBanerPrincipal;

import java.util.HashMap;

public class InicioActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private static final int MI_PERMISO_CALL = 12;
    private static final int MI_PERMISO_UBICACION = 1 ;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout barLayout;
    private SliderLayout mDemoSlider;

    CardView Agrado,Altamira,Garzon,Gigante,Guadalupe,Pital,Suaza,Tarqui;
    TextView tvAgrado,tvAltamira,tvGarzon,tvGigante,tvGuadalupe,tvPital,tvSuaza,tvTarqui;
    TextView menConexion;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference banerRef = ref.child("CenturHuila/banerprincipal");

    PostBanerPrincipal banerPrincipal;

    public AVLoadingIndicatorView avi;

    public static final String TAG = "NOTICIAS";

    private TextView infoTextView;

    //Handle para retardar acciones
    private Handler mHandler= new Handler();


    //flotante de altura translationZ o elevation
    float alturaOff=0;
    float alturaOn=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        menConexion = (TextView)findViewById(R.id.tvconexion);

        //verificar si hay conexion a internet
        if (!verificaConexion(this)) {
            menConexion.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    menConexion.setVisibility(View.GONE);
                }
            },3000);

        }


        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.azul_claro));


        tvAgrado =(TextView)findViewById(R.id.tituloagrado);
        tvAltamira =(TextView)findViewById(R.id.tituloaltamira);
        tvGarzon =(TextView)findViewById(R.id.titulogarzon);
        tvGigante =(TextView)findViewById(R.id.titulogigante);
        tvGuadalupe =(TextView)findViewById(R.id.tituloguadalupe);
        tvPital =(TextView)findViewById(R.id.titulopital);
        tvSuaza =(TextView)findViewById(R.id.titulosuaza);
        tvTarqui =(TextView)findViewById(R.id.titulotarqui);


        Tarqui = (CardView)findViewById(R.id.cardTarqui);
        Suaza = (CardView)findViewById(R.id.cardSuaza);
        Pital = (CardView)findViewById(R.id.cardPital);
        Guadalupe = (CardView)findViewById(R.id.cardGuadalupe);
        Gigante = (CardView)findViewById(R.id.cardGigante);
        Garzon = (CardView)findViewById(R.id.cardGarzon);
        Altamira = (CardView)findViewById(R.id.cardAltamira);
        Agrado = (CardView)findViewById(R.id.cardAgrado);

        altura();


        Agrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Agrado.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Agrado.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Agrado.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Agrado.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Agrado";
                inicio.putExtra("municipio",env);
                startActivity(inicio);
            }
        });


        Altamira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Altamira.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Altamira.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Altamira.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Altamira.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Altamira";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });

        Garzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Garzon.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Garzon.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Garzon.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Garzon.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Garzon";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });

        Gigante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Gigante.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Gigante.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Gigante.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Gigante.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Gigante";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });

        Guadalupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Guadalupe.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Guadalupe.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Guadalupe.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        Guadalupe.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Guadalupe";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });

        Pital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Pital.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Pital.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Pital.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Pital.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Pital";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });

        Suaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Suaza.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Suaza.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Suaza.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Suaza.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Suaza";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });



        Tarqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT == 19) {
                    Tarqui.setCardElevation(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Tarqui.setCardElevation(alturaOn);
                        }
                    },1000);
                }
                else {
                    Tarqui.setTranslationZ(alturaOff);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Tarqui.setTranslationZ(alturaOn);
                        }
                    },1000);
                }
                Intent inicio = new Intent(InicioActivity.this,Categorias.class);
                String env="Tarqui";
                inicio.putExtra("municipio",env);
                startActivity(inicio);

            }
        });


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);


            banerRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    banerPrincipal = (PostBanerPrincipal) dataSnapshot.getValue(PostBanerPrincipal.class);
                    //codigos para el slider

                    HashMap<String, String> url_maps = new HashMap<String, String>();

                    url_maps.put(/*banerPrincipal.getDescAgrado()*/"AGRADO", banerPrincipal.getImageAgrado());
                    url_maps.put(/*banerPrincipal.getDescAltamira()*/"ALTAMIRA", banerPrincipal.getImageAltamira());
                    url_maps.put(/*banerPrincipal.getDescGarzon()*/"GARZÓN", banerPrincipal.getImageGarzon());
                    url_maps.put(/*banerPrincipal.getDescGigante()*/"GIGANTE", banerPrincipal.getImageGigante());
                    url_maps.put(/*banerPrincipal.getDescGuadalupe()*/"GUADALUPE", banerPrincipal.getImageGuadalupe());
                    url_maps.put(/*banerPrincipal.getDescPital()*/"PITAL", banerPrincipal.getImagePital());
                    url_maps.put(/*banerPrincipal.getDescSuaza()*/"SUAZA", banerPrincipal.getImageSuaza());
                    url_maps.put(/*banerPrincipal.getDescTarqui()*/"TARQUI", banerPrincipal.getImageTarqui());

                    for (String name : url_maps.keySet()) {
                        TextSliderView textSliderView = new TextSliderView(InicioActivity.this);
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(InicioActivity.this);

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra", name);

                        mDemoSlider.addSlider(textSliderView);
                    }
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                    mDemoSlider.setDuration(4000);
                    mDemoSlider.addOnPageChangeListener(InicioActivity.this);
                    //hasta aqui codigos para el slider

                    stopAnim();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        // funcion de los BMB
        BoomMenuButton rightBmb = (BoomMenuButton) findViewById(R.id.action_bar_right_bmb_garzon);
        rightBmb.setButtonEnum(ButtonEnum.Ham);
        rightBmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);//numero de botones
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < rightBmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {


                            switch (index){
                                case  0:
                                    Intent manual = new Intent(InicioActivity.this,ReciclerManual.class);
                                    startActivity(manual);
                                    break;

                                case 1:
                                    Intent idioma = new Intent(InicioActivity.this,Idioma.class);
                                    startActivity(idioma);
                                    break;

                                case 2:
                                    Intent info = new Intent(InicioActivity.this,Informacion.class);
                                    startActivity(info);
                                    break;


                            }
                        }
                    })
                    .pieceColor(Color.WHITE)
                    .normalImageRes(getImageResource())
                    .normalTextRes(getTituloResource())
                    .subNormalTextRes(getSubTituloResource())
                    .textSize(20)
                    .subTextSize(15)
                    .normalColorRes(getColorResource());
            rightBmb.addBuilder(builder);
        }//fin de funcion BMB




    }



    //metodos para el slider
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
         //mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        String namemun = (String) slider.getBundle().get("extra");
        if (namemun.equals("AGRADO")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Agrado";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("ALTAMIRA")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Altamira";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("GARZÓN")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Garzon";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("GIGANTE")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Gigante";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("GUADALUPE")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Guadalupe";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("PITAL")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Pital";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("SUAZA")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Suaza";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }else if (namemun.equals("TARQUI")){

            Intent inicio = new Intent(InicioActivity.this,Categorias.class);
            String env="Tarqui";
            inicio.putExtra("municipio",env);
            startActivity(inicio);

        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {

            /*Intent intent = new Intent(InicioActivity.this,MapsMunicipios.class);
            //intent.putExtra("categoria","municipios");
            startActivity(intent);
            */

            Uri uri = Uri.parse("https://drive.google.com/open?id=1xuKJia9FyNvolj_MaLfTyxwsSd_w2grr&usp=sharing");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);


            /*Uri gmmIntentUri = Uri.parse("geo:2.1953679,-75.62730959999999");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
            */

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
            R.drawable.ic_ayuda,
            R.drawable.ic_info,
            R.drawable.ic_desarrolladores,

    };



    //arreglo para los colores
    private static int[] colores = new int[]{
            R.color.azul_oscuro,
            R.color.azul_medio,
            R.color.azul_claro,
    };

    //arreglo para los titulos
    private static int[] titulos = new int[]{
            R.string.ayuda,
            R.string.acerca,
            R.string.desa,
    };

    //arreglo para los subtitulos
    private static int[] subtitulos = new int[]{
            R.string.manual,
            R.string.app_name,
            R.string.equidesa,
    };

    // fin de arreglos para los BMB--------------------------------------------------------------------


public void altura(){
    //si la api es 19 se debe utilizar CardElevation, para evitar que se rompa la app
    if (Build.VERSION.SDK_INT == 19) {
        Pital.setCardElevation(alturaOn);
        Gigante.setCardElevation(alturaOn);
        Agrado.setCardElevation(alturaOn);
        Tarqui.setCardElevation(alturaOn);
        Garzon.setCardElevation(alturaOn);
        Guadalupe.setCardElevation(alturaOn);
        Suaza.setCardElevation(alturaOn);
        Altamira.setCardElevation(alturaOn);
    } else {
        Pital.setTranslationZ(alturaOn);
        Gigante.setTranslationZ(alturaOn);
        Agrado.setTranslationZ(alturaOn);
        Tarqui.setTranslationZ(alturaOn);
        Garzon.setTranslationZ(alturaOn);
        Guadalupe.setTranslationZ(alturaOn);
        Suaza.setTranslationZ(alturaOn);
        Altamira.setTranslationZ(alturaOn);
    }
}


}
