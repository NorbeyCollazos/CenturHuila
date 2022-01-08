package com.ycjn.centurhuila;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.Interface.ItemClickListener;
import com.ycjn.centurhuila.model.PostImages;
import com.ycjn.centurhuila.model.PostInformacion;

public class InformacionCategoria extends AppCompatActivity {


    public AVLoadingIndicatorView avi;


    String dato = "";
    String img = "imagenes";
    TextView Tvdato;
    LinearLayout lininfo,linimages,linhitoria,linruta;
    LinearLayout lindatos, lindescri,linhistoria,lineruta;
    RelativeLayout rToolbar, relavia,rsn;
    ImageButton atras;
    BottomNavigationView bottomNavigationView;
    TextView TituloBaner,SubtituloBaner;

    TextView temperatura,altura,poblacion,gentilicio,descripcion,historia,cordenadas,terrestre,aerea;

    //LottieAnimationView animationView;

    DatabaseReference ref;
    DatabaseReference infoRef;
    DatabaseReference imagesRef;
    PostInformacion informacion;
    PostImages psimagen;

    FirebaseRecyclerAdapter<PostImages, ImagesViewHolder> firebaseRecyclerAdapter;

    private RecyclerView mPostRV;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_categoria);



        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;

        startAnim();


        TituloBaner = (TextView) findViewById(R.id.tvtitulobaner);
        SubtituloBaner = (TextView) findViewById(R.id.tvsubtitulobaner);
        atras = (ImageButton)findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        temperatura = (TextView)findViewById(R.id.tvtemperatura);
        altura = (TextView)findViewById(R.id.tvaltura);
        poblacion = (TextView)findViewById(R.id.tvpoblacion);
        gentilicio = (TextView)findViewById(R.id.tvgentilicio);
        descripcion = (TextView)findViewById(R.id.tvdescripcion);
        historia = (TextView)findViewById(R.id.tvhistoria);
        cordenadas = (TextView)findViewById(R.id.tvcordenadas);
        terrestre = (TextView)findViewById(R.id.tvviaterrestre);
        aerea = (TextView)findViewById(R.id.tvaire);


        lininfo = findViewById(R.id.linearinfo);
        linimages = findViewById(R.id.linearimages);
        linhitoria = findViewById(R.id.linearhistoria);
        linruta = findViewById(R.id.linearruta);

        rToolbar = findViewById(R.id.relativetoolbar);
        lindatos = findViewById(R.id.lineardatos);
        lindescri = findViewById(R.id.lineardescri);
        linhistoria = findViewById(R.id.linearhisto);
        lineruta = findViewById(R.id.linearuta);
        relavia = findViewById(R.id.relativevia);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigation);

        fab = (FloatingActionButton) findViewById(R.id.fab);


        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            dato = (String) bundle.get("info");
        }

        if (dato.equals("Garzon")) {
            SubtituloBaner.setText("Garzón");}else{SubtituloBaner.setText(dato);}

            if (dato.equals("Agrado")){
                coloresAgrado();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAgrado)); //Define color blanco.
                }

            }else if (dato.equals("Altamira")){
                coloresAltamira();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAltamira)); //Define color blanco.
                }
            }else if (dato.equals("Garzon")){
                coloresGarzon();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGarzon)); //Define color blanco.
                }
            }else if (dato.equals("Gigante")){
                coloresGigante();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGigante)); //Define color blanco.
                }
            }else if (dato.equals("Guadalupe")){
                coloresGuadalupe();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGuadalupe)); //Define color blanco.
                }
            }else if (dato.equals("Pital")){
                SubtituloBaner.setText("El Pital");
                coloresPital();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkPital)); //Define color blanco.
                }
            }else if (dato.equals("Suaza")){
                coloresSuaza();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSuaza)); //Define color blanco.
                }
            }else if (dato.equals("Tarqui")){
                coloresTarqui();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkTarqui)); //Define color blanco.
                }
            }


        ref = FirebaseDatabase.getInstance().getReference();
          infoRef = ref.child("CenturHuila/"+dato+"/informacion" +
                  "");
          imagesRef = ref.child("CenturHuila/"+dato+"/Galeria");
          imagesRef.keepSynced(true);


        mPostRV = (RecyclerView) findViewById(R.id.post_rv);
        GridLayoutManager lmGrid = new GridLayoutManager(this, 2);
        mPostRV.setLayoutManager(lmGrid);
        //mPostRV.setHasFixedSize(true);
        //mPostRV.setLayoutManager(new LinearLayoutManager(InformacionCategoria.this));




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(InformacionCategoria.this,MapsActivity.class);
                intent.putExtra("mun",dato);
                intent.putExtra("categoria","informacion");
                intent.putExtra("ubica","ubica");
                startActivity(intent);
                */
                if (dato.equals("Agrado")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1UcpYcL8aBvSFjxi0FBVNT-mVtV1FDQsx&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Altamira")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1nffISA44L6dh5DSOyT7K7FX-BDa8eLjY&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Garzon")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1ITN8BU0JZrR3I7dwrYU86m1NS79QC8de&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Gigante")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1yREdLdiN9a7EcZXDBgYAtS28f_Cin0Fu&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Guadalupe")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=106kDIDojqdC3ezYjAMIXa-FIBlhDMXyW&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Pital")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1sC1bcjj1n5k9J9y-zlZQlBh63xy3-LIs&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Suaza")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=11394SGz8YKJPoMYexjwi0b-UxyEP9_7a&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else if (dato.equals("Tarqui")){
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1xhfqx4eSIfNtoqGZfxNj1u8L8vPDK0m4&usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.informacionItemInf:
                        TituloBaner.setText(getString(R.string.info));
                        if (dato.equals("Garzon")) {SubtituloBaner.setText("Garzón");}else{SubtituloBaner.setText(dato);}
                        linimages.setVisibility(View.GONE);
                        lininfo.setVisibility(View.VISIBLE);
                        linhitoria.setVisibility(View.GONE);
                        linruta.setVisibility(View.GONE);
                        fab.setVisibility(View.GONE);
                        break;

                    case R.id.imagenesItemInf:
                        TituloBaner.setText(getString(R.string.imagenes));
                        if (dato.equals("Garzon")) {SubtituloBaner.setText("Garzón");}else{SubtituloBaner.setText(dato);}
                        linimages.setVisibility(View.VISIBLE);
                        lininfo.setVisibility(View.GONE);
                        linhitoria.setVisibility(View.GONE);
                        linruta.setVisibility(View.GONE);
                       fab.setVisibility(View.GONE);
                        break;

                    case R.id.historiaItemInf:
                        TituloBaner.setText(getString(R.string.historia));
                        if (dato.equals("Garzon")) {SubtituloBaner.setText("Garzón");}else{SubtituloBaner.setText(dato);}
                        linimages.setVisibility(View.GONE);
                        lininfo.setVisibility(View.GONE);
                        linhitoria.setVisibility(View.VISIBLE);
                        linruta.setVisibility(View.GONE);
                        fab.setVisibility(View.GONE);
                        break;


                    case R.id.rutaItemInf:
                        TituloBaner.setText(getString(R.string.cll));
                        if (dato.equals("Garzon")) {SubtituloBaner.setText("Garzón");}else{SubtituloBaner.setText(dato);}
                        linimages.setVisibility(View.GONE);
                        lininfo.setVisibility(View.GONE);
                        linhitoria.setVisibility(View.GONE);
                        linruta.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.VISIBLE);
                        break;

                }

                return true;
            }
        });

        //de aqui para abajo lo del recicler de imagenes
        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<PostImages, ImagesViewHolder>
                (
                        PostImages.class,
                        R.layout.item_layout_post_images,
                        ImagesViewHolder.class,
                        imagesRef
                ) {


            @Override
            protected void populateViewHolder(ImagesViewHolder viewHolder, PostImages model, int position) {
                //viewHolder.setDesc(model.getDescimages());
                viewHolder.setImage(getApplicationContext(),model.getImages());
                stopAnim();


                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        /*Intent intent = new Intent(InformacionCategoria.this, VerImagen.class);
                        intent.putExtra("id", firebaseRecyclerAdapter.getRef(position).getKey());
                        intent.putExtra("mun",dato);
                        startActivity(intent);
                        */
                        final Dialog dialogA = new Dialog(InformacionCategoria.this);
                        dialogA.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogA.setContentView(R.layout.activity_ver_imagen);
                        //dialogA.setCancelable(false);
                        Window window = dialogA.getWindow();
                        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

                        final ImageView imagen = (ImageView)dialogA.findViewById(R.id.imagever);
                        final TextView descripcion = (TextView)dialogA.findViewById(R.id.tvdescripcion);
                        final LinearLayout sn = dialogA.findViewById(R.id.lindescimg);

                        if (dato.equals("Agrado")){
                            sn.setBackgroundResource(R.color.colorTargetAgrado);

                        }else if (dato.equals("Altamira")){
                            sn.setBackgroundResource(R.color.colorTargetAltamira);

                        }else if (dato.equals("Garzon")){
                            sn.setBackgroundResource(R.color.colorTargetGarzon);

                        }else if (dato.equals("Gigante")){
                            sn.setBackgroundResource(R.color.colorTargetGigante);

                        }else if (dato.equals("Guadalupe")){
                            sn.setBackgroundResource(R.color.colorTargetGuadalupe);

                        }else if (dato.equals("Pital")){
                            sn.setBackgroundResource(R.color.colorTargetPital);

                        }else if (dato.equals("Suaza")){
                            sn.setBackgroundResource(R.color.colorTargetSuaza);

                        }else if (dato.equals("Tarqui")){
                            sn.setBackgroundResource(R.color.colorTargetTarqui);

                        }

                        imagesRef.child(firebaseRecyclerAdapter.getRef(position).getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                psimagen = (PostImages) dataSnapshot.getValue(PostImages.class);

                                Picasso.with(getBaseContext()).load(psimagen.getImages()).into(imagen);
                                descripcion.setText(psimagen.getDescimages());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        ImageButton cerrar = (ImageButton) dialogA.findViewById(R.id.btncerrar);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogA.cancel();
                            }
                        });

                        dialogA.show();

                    }
                });

            }
        };
        mPostRV.setAdapter(firebaseRecyclerAdapter);



    }


    @Override
    protected void onStart() {
        super.onStart();
        infoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                    informacion = (PostInformacion) dataSnapshot.getValue(PostInformacion.class);

                    temperatura.setText(informacion.getTemperatura());
                    altura.setText(informacion.getAltura());
                    poblacion.setText(informacion.getPoblacion());
                    gentilicio.setText(informacion.getGentilicio());
                    descripcion.setText(informacion.getDescripcion());
                    historia.setText(informacion.getHistoria());
                    cordenadas.setText("" + informacion.getLactitud() + "\n" + informacion.getLongitud());
                    terrestre.setText(informacion.getTerrestre());
                    aerea.setText(informacion.getAerea());
                    stopAnim();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("El fallo es: " + databaseError.getCode());
            }
        });

    }

    //metodos para el recicler de imagenes
    public static class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        View mView;




        public ImagesViewHolder(View itemView){
            super(itemView);
            mView=itemView;

            itemView.setOnClickListener(this);
        }

        public void setImage(Context ctx,String image){
            ImageView postIV = (ImageView) itemView.findViewById(R.id.post_iv);
            Picasso.with(ctx).load(image).into(postIV);
        }

        public void fondo(){
            final RelativeLayout sn = mView.findViewById(R.id.relasn);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);

        }
    }





    void startAnim()
    {
        this.avi.show();
    }

    void stopAnim()
    {
        this.avi.hide();
    }


    public void coloresAgrado(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryAgrado);
        lindatos.setBackgroundResource(R.color.colorTargetAgrado);
        lindescri.setBackgroundResource(R.color.colorTargetAgrado);
        linhistoria.setBackgroundResource(R.color.colorTargetAgrado);
        lineruta.setBackgroundResource(R.color.colorTargetAgrado);
        relavia.setBackgroundResource(R.color.colorTargetAgrado);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryAgrado);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentAgrado));
    }

    public void coloresAltamira(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryAltamira);
        lindatos.setBackgroundResource(R.color.colorTargetAltamira);
        lindescri.setBackgroundResource(R.color.colorTargetAltamira);
        linhistoria.setBackgroundResource(R.color.colorTargetAltamira);
        lineruta.setBackgroundResource(R.color.colorTargetAltamira);
        relavia.setBackgroundResource(R.color.colorTargetAltamira);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryAltamira);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentAltamira));
    }

    public void coloresGarzon(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryGarzon);
        lindatos.setBackgroundResource(R.color.colorTargetGarzon);
        lindescri.setBackgroundResource(R.color.colorTargetGarzon);
        linhistoria.setBackgroundResource(R.color.colorTargetGarzon);
        lineruta.setBackgroundResource(R.color.colorTargetGarzon);
        relavia.setBackgroundResource(R.color.colorTargetGarzon);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryGarzon);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGarzon));
    }

    public void coloresGigante(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryGigante);
        lindatos.setBackgroundResource(R.color.colorTargetGigante);
        lindescri.setBackgroundResource(R.color.colorTargetGigante);
        linhistoria.setBackgroundResource(R.color.colorTargetGigante);
        lineruta.setBackgroundResource(R.color.colorTargetGigante);
        relavia.setBackgroundResource(R.color.colorTargetGigante);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryGigante);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGigante));
    }

    public void coloresGuadalupe(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        lindatos.setBackgroundResource(R.color.colorTargetGuadalupe);
        lindescri.setBackgroundResource(R.color.colorTargetGuadalupe);
        linhistoria.setBackgroundResource(R.color.colorTargetGuadalupe);
        lineruta.setBackgroundResource(R.color.colorTargetGuadalupe);
        relavia.setBackgroundResource(R.color.colorTargetGuadalupe);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryGuadalupe);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentGuadalupe));
    }

    public void coloresPital(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryPital);
        lindatos.setBackgroundResource(R.color.colorTargetPital);
        lindescri.setBackgroundResource(R.color.colorTargetPital);
        linhistoria.setBackgroundResource(R.color.colorTargetPital);
        lineruta.setBackgroundResource(R.color.colorTargetPital);
        relavia.setBackgroundResource(R.color.colorTargetPital);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryPital);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentPital));
    }

    public void coloresSuaza(){
        rToolbar.setBackgroundResource(R.color.colorPrimarySuaza);
        lindatos.setBackgroundResource(R.color.colorTargetSuaza);
        lindescri.setBackgroundResource(R.color.colorTargetSuaza);
        linhistoria.setBackgroundResource(R.color.colorTargetSuaza);
        lineruta.setBackgroundResource(R.color.colorTargetSuaza);
        relavia.setBackgroundResource(R.color.colorTargetSuaza);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimarySuaza);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentSuaza));
    }

    public void coloresTarqui(){
        rToolbar.setBackgroundResource(R.color.colorPrimaryTarqui);
        lindatos.setBackgroundResource(R.color.colorTargetTarqui);
        lindescri.setBackgroundResource(R.color.colorTargetTarqui);
        linhistoria.setBackgroundResource(R.color.colorTargetTarqui);
        lineruta.setBackgroundResource(R.color.colorTargetTarqui);
        relavia.setBackgroundResource(R.color.colorTargetTarqui);
        bottomNavigationView.setBackgroundResource(R.color.colorPrimaryTarqui);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccentTarqui));
    }


}
