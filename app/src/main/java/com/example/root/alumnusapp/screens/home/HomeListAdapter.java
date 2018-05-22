package com.example.root.alumnusapp.screens.home;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.example.root.alumnusapp.screens.Callback.RecyclerOnClick;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListHolder> {

    List<Kelas> kelasList;
    View view;
    RecyclerOnClick<HomeSharedView> kelasRecyclerOnClick;
    public HomeListAdapter(List<Kelas> kelas, RecyclerOnClick<HomeSharedView> kelasRecyclerOnClick){
        this.kelasList = kelas;
        this.kelasRecyclerOnClick = kelasRecyclerOnClick;
    }

    @NonNull
    @Override
    public HomeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_rv_item,parent,false);
        final HomeListHolder homeListHolder = new HomeListHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("response", "onClick: "+homeListHolder.sampul_kelas);
                HomeSharedView homeSharedView = new HomeSharedView(homeListHolder.sampul_kelas,kelasList.get(homeListHolder.getAdapterPosition()));
                kelasRecyclerOnClick.response(homeSharedView);
            }
        });
        return homeListHolder;
    }

    @Override
    public int getItemCount() {
        return kelasList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListHolder holder, int position) {
        holder.bind(view, kelasList.get(position));
    }

    class HomeListHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.home_kelas_sampul)
        ImageView sampul_kelas;
        @BindView(R.id.home_kelas_nama)
        TextView nama_kelas;
        @BindView(R.id.home_kelas_motto)
        TextView motto_kelas;
        View view;
        Context context;
        public HomeListHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
            sampul_kelas = itemView.findViewById(R.id.home_kelas_sampul);
            this.view = itemView;

        }
        void bind(final View view, Kelas kelas){
            ButterKnife.bind(this,view);
            StorageReference storageReference = StorageRemoteSource.get().getSampulImage(kelas.getSampul());
            Picasso.get().load(kelas.getSampul()).fit().centerCrop().into(sampul_kelas);
            ViewCompat.setTransitionName(sampul_kelas,kelas.getNama());
            nama_kelas.setText(kelas.getNama());
            motto_kelas.setText(kelas.getMotto());
        }
    }
}
