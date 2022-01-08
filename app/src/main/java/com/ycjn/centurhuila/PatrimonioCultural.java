package com.ycjn.centurhuila;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.ycjn.centurhuila.Interface.ItemClickListener;
import com.ycjn.centurhuila.model.PostPatriMaterial;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatrimonioCultural extends AppCompatActivity {
    public AVLoadingIndicatorView avi;
    ImageButton atras;
    TextView TituloBaner,SubtituloBaner;
    String mun = "";
    RelativeLayout rToolbar;
    private DatabaseReference listaRefM;

    private RecyclerView mPostRVM;

    FirebaseRecyclerAdapter<PostPatriMaterial, ListaViewHolder> firebaseRecyclerAdapterM;

    PostPatriMaterial pspatri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonio_cultural);

        avi = (AVLoadingIndicatorView)findViewById(R.id.avi) ;
        startAnim();

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null) {
            mun = (String) bundle.get("mun");
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

        if (mun.equals("Garzon")) {
            SubtituloBaner.setText("Garzón");
        }else{SubtituloBaner.setText(mun);}


        if (mun.equals("Agrado")){
            //coloresAgrado();
            rToolbar.setBackgroundResource(R.color.colorPrimaryAgrado);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAgrado)); //Define color blanco.
            }

        }else if (mun.equals("Altamira")){
            //coloresAltamira();
            rToolbar.setBackgroundResource(R.color.colorPrimaryAltamira);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkAltamira)); //Define color blanco.
            }
        }else if (mun.equals("Garzon")){
            //coloresGarzon();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGarzon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGarzon)); //Define color blanco.
            }
        }else if (mun.equals("Gigante")){
            //coloresGigante();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGigante);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGigante)); //Define color blanco.
            }
        }else if (mun.equals("Guadalupe")){
            //coloresGuadalupe();
            rToolbar.setBackgroundResource(R.color.colorPrimaryGuadalupe);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkGuadalupe)); //Define color blanco.
            }
        }else if (mun.equals("Pital")){
            SubtituloBaner.setText("El Pital");
            //coloresPital();
            rToolbar.setBackgroundResource(R.color.colorPrimaryPital);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkPital)); //Define color blanco.
            }
        }else if (mun.equals("Suaza")){
            //coloresSuaza();
            rToolbar.setBackgroundResource(R.color.colorPrimarySuaza);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSuaza)); //Define color blanco.
            }
        }else if (mun.equals("Tarqui")){
            //coloresTarqui();
            rToolbar.setBackgroundResource(R.color.colorPrimaryTarqui);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkTarqui)); //Define color blanco.
            }
        }

        listaRefM = FirebaseDatabase.getInstance().getReference().child("CenturHuila/"+mun+"/PatrimonioCultural");
        listaRefM.keepSynced(true);
        mPostRVM = (RecyclerView) findViewById(R.id.post_rv_patrimonio);
        mPostRVM.setHasFixedSize(true);
        mPostRVM.setLayoutManager(new LinearLayoutManager(PatrimonioCultural.this));


    }




    @Override
    protected void onStart() {
        super.onStart();

        //de aqui para abajo lo del recicler de lista de monumentos
        firebaseRecyclerAdapterM= new FirebaseRecyclerAdapter<PostPatriMaterial,ListaViewHolder>
                (
                        PostPatriMaterial.class,
                        R.layout.item_layout_post_sn,
                        ListaViewHolder.class,
                        listaRefM
                ) {
            @Override
            protected void populateViewHolder(ListaViewHolder viewHolder, PostPatriMaterial model, int position) {
                getRef(position).getKey();
                viewHolder.setNombre(model.getNombre());
                viewHolder.setDireccion(model.getDireccion());
                viewHolder.setImage(getApplicationContext(),model.getImagen());

                stopAnim();

                if (mun.equals("Agrado")){
                    viewHolder.setColorFondoAgrado();
                }else if (mun.equals("Altamira")){
                    viewHolder.setColorFondoAltamira();
                }if (mun.equals("Garzon")){
                    viewHolder.setColorFondoGarzon();
                }if (mun.equals("Gigante")){
                    viewHolder.setColorFondoGigante();
                }if (mun.equals("Guadalupe")){
                    viewHolder.setColorFondoGuadalupe();
                }if (mun.equals("Pital")){
                    viewHolder.setColorFondoPital();
                }if (mun.equals("Suaza")){
                    viewHolder.setColorFondoSuaza();
                }if (mun.equals("Tarqui")){
                    viewHolder.setColorFondoTarqui();
                }


                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, final int position, boolean isLongClick) {


                        final Dialog dialogA = new Dialog(PatrimonioCultural.this);
                        dialogA.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogA.setContentView(R.layout.mostrar_patrimonio);
                        //dialogA.setCancelable(false);
                        Window window = dialogA.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                        final ImageView imagen = (ImageView)dialogA.findViewById(R.id.imageverpatri);
                        final TextView titulo = (TextView)dialogA.findViewById(R.id.tvtitulopatri);
                        final TextView descri = (TextView)dialogA.findViewById(R.id.tvdescripatri);
                        final TextView dir = (TextView)dialogA.findViewById(R.id.tvdirpatri);
                        //final TextView ver = (TextView)dialogA.findViewById(R.id.tvvereda);
                        final TextView pro = (TextView)dialogA.findViewById(R.id.tvpropietario);
                        final TextView tel = (TextView)dialogA.findViewById(R.id.tvtele);
                        final TextView indi = (TextView)dialogA.findViewById(R.id.tvinidicaciones);

                        final LinearLayout sn = dialogA.findViewById(R.id.lintitulopatri);
                        final LinearLayout sn2 = dialogA.findViewById(R.id.cardpatri);

                        if (mun.equals("Agrado")){
                            sn.setBackgroundResource(R.color.colorTargetAgrado);
                            sn2.setBackgroundResource(R.color.colorTargetAgrado);

                        }else if (mun.equals("Altamira")){
                            sn.setBackgroundResource(R.color.colorTargetAltamira);
                            sn2.setBackgroundResource(R.color.colorTargetAltamira);

                        }else if (mun.equals("Garzon")){
                            sn.setBackgroundResource(R.color.colorTargetGarzon);
                            sn2.setBackgroundResource(R.color.colorTargetGarzon);

                        }else if (mun.equals("Gigante")){
                            sn.setBackgroundResource(R.color.colorTargetGigante);
                            sn2.setBackgroundResource(R.color.colorTargetGigante);

                        }else if (mun.equals("Guadalupe")){
                            sn.setBackgroundResource(R.color.colorTargetGuadalupe);
                            sn2.setBackgroundResource(R.color.colorTargetGuadalupe);

                        }else if (mun.equals("Pital")){
                            sn.setBackgroundResource(R.color.colorTargetPital);
                            sn2.setBackgroundResource(R.color.colorTargetPital);

                        }else if (mun.equals("Suaza")){
                            sn.setBackgroundResource(R.color.colorTargetSuaza);
                            sn2.setBackgroundResource(R.color.colorTargetSuaza);

                        }else if (mun.equals("Tarqui")){
                            sn.setBackgroundResource(R.color.colorTargetTarqui);
                            sn2.setBackgroundResource(R.color.colorTargetTarqui);

                        }



                        listaRefM.child(firebaseRecyclerAdapterM.getRef(position).getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                pspatri = (PostPatriMaterial) dataSnapshot.getValue(PostPatriMaterial.class);

                                Picasso.with(getBaseContext()).load(pspatri.getImagen()).into(imagen);
                                titulo.setText(pspatri.getNombre());
                                descri.setText(pspatri.getDescripcion());
                                dir.setText(pspatri.getDireccion());
                                //ver.setText(pspatri.getVereda());
                                pro.setText(pspatri.getPropietario());
                                tel.setText(pspatri.getTelefono());
                                indi.setText(pspatri.getIndicaciones());

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        ImageButton cerrar = (ImageButton)dialogA.findViewById(R.id.btncerrar);
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
        mPostRVM.setAdapter(firebaseRecyclerAdapterM);


    }


    //metodos para el recicler de listas de Patrimonis Materiales
    public static class ListaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RelativeLayout targe;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        View mView;
        public ListaViewHolder(View itemView){
            super(itemView);
            mView=itemView;

            itemView.setOnClickListener(this);

            targe = (RelativeLayout)itemView.findViewById(R.id.relasn);
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
