package com.ycjn.centurhuila;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.model.PostHotel;


public class DescRestaurante extends AppCompatActivity {

    private static final int MI_PERMISO_CALL = 1;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public AVLoadingIndicatorView avi;

    LinearLayout lininfo,lincontactos,linservicios,linruta,content;
    TextView informacion,paginaweb,telefono,correo,disponible,cordenadas,direccion;
    ImageView imagen;


    LinearLayout linedescri,lineubica;
    RelativeLayout linetel;


    FloatingActionButton fab;
    ImageButton atras;

    String mun = "";
    String id = "";

    DatabaseReference ref;
    DatabaseReference sitioRef;
    PostHotel pshotel;

    CardView cardTelefono,cardPagina,cardCorreo;


    public void MostrarHotel(String paramString){
        sitioRef.child(paramString).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                pshotel = (PostHotel) dataSnapshot.getValue(PostHotel.class);

                Picasso.with(getBaseContext()).load(pshotel.getImagen()).into(imagen);


                collapsingToolbarLayout.setTitle(pshotel.getNombre());
                informacion.setText(pshotel.getDescripcion());
                telefono.setText(pshotel.getTelefono());
                //cordenadas.setText(pshotel.getLactitud()+"\n"+pshotel.getLongitud());
                direccion.setText(pshotel.getDireccion());

                stopAnim();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_restaurante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();

        atras = (ImageButton)findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        linedescri = findViewById(R.id.lindescrirest);
        linetel = findViewById(R.id.lintelrest);
        lineubica = findViewById(R.id.linubirest);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        //fab = (FloatingActionButton) findViewById(R.id.fab);



        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            mun = (String) bundle.get("mun");
        }

        ref = FirebaseDatabase.getInstance().getReference();
        sitioRef = ref.child("CenturHuila/"+mun+"/Restaurantes");

        if (getIntent() != null)
            id = getIntent().getStringExtra("id");

        if (!id.isEmpty())
            MostrarHotel(id);


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



        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DescRestaurante.this,MapsActivity.class);
                intent.putExtra("mun",mun);
                intent.putExtra("categoria","Restaurantes");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        */


        //codigos para que al darle clic llamar al restaurante
        cardTelefono = (CardView)findViewById(R.id.cardtelefono);
        cardTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder prueba = new AlertDialog.Builder(DescRestaurante.this);
                prueba.setMessage(getString(R.string.menscall)+" "+pshotel.getNombre());
                prueba.setTitle("CenturHuila");
                prueba.setIcon(R.mipmap.ic_launcher);



                prueba.setPositiveButton(getString(R.string.call), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+pshotel.getTelefono()));
                        if (ActivityCompat.checkSelfPermission(DescRestaurante.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                if (ActivityCompat.shouldShowRequestPermissionRationale(DescRestaurante.this, android.Manifest.permission.CALL_PHONE)) {


                                    final AlertDialog.Builder prueba = new AlertDialog.Builder(DescRestaurante.this);
                                    prueba.setCancelable(false);
                                    prueba.setMessage(getString(R.string.mensactivarrrll));
                                    prueba.setTitle(R.string.impo);
                                    prueba.setIcon(R.mipmap.ic_launcher);


                                    prueba.setPositiveButton(getString(R.string.ACTIVAR), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                            dialog.cancel();
                                            ActivityCompat.requestPermissions(DescRestaurante.this, new String[]{android.Manifest.permission.CALL_PHONE},
                                                    MI_PERMISO_CALL);

                                        }
                                    });

                                    prueba.setNegativeButton(getString(R.string.NOPER), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog2, int which) {

                                            //mMap.getUiSettings().setMyLocationButtonEnabled(false);
                                            dialog2.cancel();


                                        }
                                    });

                                    AlertDialog dialog2 = prueba.create();
                                    dialog2.show();


                                } else {
                                    ActivityCompat.requestPermissions(DescRestaurante.this, new String[]{android.Manifest.permission.CALL_PHONE},
                                            MI_PERMISO_CALL);
                                }

                            }

                        }else{
                            startActivity(intent);
                            Toast.makeText(DescRestaurante.this, getString(R.string.llamando), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                prueba.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog dialog = prueba.create();
                dialog.show();

            }
        });

        content = findViewById(R.id.lincontent);
        lininfo = findViewById(R.id.lindescripcion);
        lincontactos = findViewById(R.id.lincontacto);
        //linservicios = findViewById(R.id.lindisponible);
        linruta = findViewById(R.id.linruta);


        imagen = (ImageView)findViewById(R.id.imagehotel);

        informacion = (TextView)findViewById(R.id.tvdescripcionh);
        //paginaweb = (TextView)findViewById(R.id.tvpagina);
        telefono = (TextView)findViewById(R.id.tvtelefono);
        //correo = (TextView)findViewById(R.id.tvcorreo);
        //disponible = (TextView)findViewById(R.id.tvdisponible);
        //cordenadas = (TextView)findViewById(R.id.tvcordenadash);
        direccion = (TextView)findViewById(R.id.tvdireccionh);


    }

    void startAnim()
    {
        this.avi.show();
    }

    void stopAnim()
    {
        this.avi.hide();
    }

    public void ColoresAgrado(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetAgrado));
        linetel.setBackgroundResource(R.color.colorTargetAgrado);
        lineubica.setBackgroundResource(R.color.colorTargetAgrado);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAgrado));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentAgrado));
    }

    public void ColoresAltamira(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetAltamira));
        linetel.setBackgroundResource(R.color.colorTargetAltamira);
        lineubica.setBackgroundResource(R.color.colorTargetAltamira);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryAltamira));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentAltamira));
    }

    public void ColoresGarzon(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetGarzon));
        linetel.setBackgroundResource(R.color.colorTargetGarzon);
        lineubica.setBackgroundResource(R.color.colorTargetGarzon);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGarzon));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGarzon));
    }

    public void ColoresGigante(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetGigante));
        linetel.setBackgroundResource(R.color.colorTargetGigante);
        lineubica.setBackgroundResource(R.color.colorTargetGigante);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGigante));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGigante));
    }

    public void ColoresGuadalupe(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetGuadalupe));
        linetel.setBackgroundResource(R.color.colorTargetGuadalupe);
        lineubica.setBackgroundResource(R.color.colorTargetGuadalupe);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryGuadalupe));
       // fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGuadalupe));
    }

    public void ColoresPital(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetPital));
        linetel.setBackgroundResource(R.color.colorTargetPital);
        lineubica.setBackgroundResource(R.color.colorTargetPital);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryPital));
       // fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentPital));
    }

    public void ColoresSuaza(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetSuaza));
        linetel.setBackgroundResource(R.color.colorTargetSuaza);
        lineubica.setBackgroundResource(R.color.colorTargetSuaza);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimarySuaza));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentSuaza));
    }

    public void ColoresTarqui(){
        linedescri.setBackgroundColor(getResources().getColor(R.color.colorTargetTarqui));
        linetel.setBackgroundResource(R.color.colorTargetTarqui);
        lineubica.setBackgroundResource(R.color.colorTargetTarqui);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryTarqui));
        //fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentTarqui));
    }

}
