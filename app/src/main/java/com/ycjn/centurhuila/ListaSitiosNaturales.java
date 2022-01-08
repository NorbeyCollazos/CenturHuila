package com.ycjn.centurhuila;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.Interface.ItemClickListener;
import com.ycjn.centurhuila.model.PostSitiosNaturales;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaSitiosNaturales extends AppCompatActivity {

    public AVLoadingIndicatorView avi;

    ImageButton atras;
    TextView TituloBaner,SubtituloBaner;
    LinearLayout ly1,ly2;
    String dato = "";
    RelativeLayout rToolbar;


    private DatabaseReference listaRef;
    private RecyclerView mPostRV;

    private FirebaseRecyclerAdapter<PostSitiosNaturales, ListaViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sitios_naturales);

        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            dato = (String) bundle.get("sn");
        }

        TituloBaner = (TextView) findViewById(R.id.tvtitulobaner);
        SubtituloBaner = (TextView) findViewById(R.id.tvsubtitulobaner);
        atras = (ImageButton)findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rToolbar = findViewById(R.id.relativetoolbar);


        if (dato.equals("Garzon")) {
            SubtituloBaner.setText("Garzón");
        }else{SubtituloBaner.setText(dato);}


        if (dato.equals("Agrado")){
            //coloresAgrado();
            rToolbar.setBackgroundResource(R.color.colorPrimaryAgrado);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAgrado)); //Define color blanco.
            }

        }else if (dato.equals("Altamira")){
            //coloresAltamira();
            rToolbar.setBackgroundResource(R.color.colorPrimaryAltamira);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAltamira)); //Define color blanco.
            }
        }else if (dato.equals("Garzon")){
            SubtituloBaner.setText("Garzón");
            //coloresGarzon();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGarzon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGarzon)); //Define color blanco.
            }
        }else if (dato.equals("Gigante")){
            //coloresGigante();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGigante);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGigante)); //Define color blanco.
            }
        }else if (dato.equals("Guadalupe")){
            //coloresGuadalupe();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGuadalupe);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGuadalupe)); //Define color blanco.
            }
        }else if (dato.equals("Pital")){
            SubtituloBaner.setText("El Pital");
            //coloresPital();
            rToolbar.setBackgroundResource(R.color.colorPrimaryPital);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkPital)); //Define color blanco.
            }
        }else if (dato.equals("Suaza")){
            //coloresSuaza();
            rToolbar.setBackgroundResource(R.color.colorPrimarySuaza);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSuaza)); //Define color blanco.
            }
        }else if (dato.equals("Tarqui")){
            //coloresTarqui();
            rToolbar.setBackgroundResource(R.color.colorPrimaryTarqui);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkTarqui)); //Define color blanco.
            }
        }



        listaRef = FirebaseDatabase.getInstance().getReference().child("CenturHuila/"+dato+"/sitiosNaturales");
        listaRef.keepSynced(true);

        mPostRV = (RecyclerView) findViewById(R.id.post_rv);
        mPostRV.setHasFixedSize(true);
        mPostRV.setLayoutManager(new LinearLayoutManager(ListaSitiosNaturales.this));



    }

    @Override
    protected void onStart() {
        super.onStart();

        //de aqui para abajo lo del recicler de lista de sitios naturales
         firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<PostSitiosNaturales, ListaViewHolder>
                (
                        PostSitiosNaturales.class,
                        R.layout.item_layout_post_sn,
                        ListaViewHolder.class,
                        listaRef
                ) {
            @Override
            protected void populateViewHolder(ListaViewHolder viewHolder, PostSitiosNaturales model, int position) {

                viewHolder.setNombre(model.getNombre());
                viewHolder.setDireccion(model.getDireccion());
                viewHolder.setImage(getApplicationContext(),model.getImagen1());

                stopAnim();

                if (dato.equals("Agrado")){
                    viewHolder.setColorFondoAgrado();
                }else if (dato.equals("Altamira")){
                    viewHolder.setColorFondoAltamira();
                }if (dato.equals("Garzon")){
                    viewHolder.setColorFondoGarzon();
                }if (dato.equals("Gigante")){
                    viewHolder.setColorFondoGigante();
                }if (dato.equals("Guadalupe")){
                    viewHolder.setColorFondoGuadalupe();
                }if (dato.equals("Pital")){
                    viewHolder.setColorFondoPital();
                }if (dato.equals("Suaza")){
                    viewHolder.setColorFondoSuaza();
                }if (dato.equals("Tarqui")){
                    viewHolder.setColorFondoTarqui();
                }

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent intent = new Intent(ListaSitiosNaturales.this, DescSitioNatural.class);
                        intent.putExtra("id", firebaseRecyclerAdapter.getRef(position).getKey());
                        intent.putExtra("mun",dato);
                        startActivity(intent);

                    }
                });


            }
        };
        mPostRV.setAdapter(firebaseRecyclerAdapter);
    }

    //metodos para el recicler de lista de sitios naturales
    public static class ListaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variables vistas aqui
        public RelativeLayout targe;

        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        View mView;
        public ListaViewHolder(View itemView){
            super(itemView);
            mView=itemView;

            targe = (RelativeLayout)itemView.findViewById(R.id.relasn);

            itemView.setOnClickListener(this);
        }

        public void setColorFondoAgrado(){
            targe.setBackgroundResource(R.color.colorTargetAgrado);
        }
        public void setColorFondoAltamira(){
            targe.setBackgroundResource(R.color.colorTargetAltamira);
        }
        public void setColorFondoGarzon(){
            targe.setBackgroundResource(R.color.colorTargetGarzon);
        }
        public void setColorFondoGigante(){
            targe.setBackgroundResource(R.color.colorTargetGigante);
        }
        public void setColorFondoGuadalupe(){
            targe.setBackgroundResource(R.color.colorTargetGuadalupe);
        }
        public void setColorFondoPital(){
            targe.setBackgroundResource(R.color.colorTargetPital);
        }
        public void setColorFondoSuaza(){
            targe.setBackgroundResource(R.color.colorTargetSuaza);
        }
        public void setColorFondoTarqui(){
            targe.setBackgroundResource(R.color.colorTargetTarqui);
        }

        public void setNombre(String nombre){
            TextView tnombre = (TextView) itemView.findViewById(R.id.post_nombre);
            tnombre.setText(nombre);
        }
        public void setDireccion(String dir){
            TextView tdireccion = (TextView) itemView.findViewById(R.id.post_direccion);
            tdireccion.setText(dir);
        }

        public void setImage(Context ctx, String image){
            CircleImageView postIV = (CircleImageView) itemView.findViewById(R.id.post_iv);
            Picasso.with(ctx).load(image).into(postIV);
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

}
