package com.rjzim.mygrocerystore.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rjzim.mygrocerystore.R;
import com.rjzim.mygrocerystore.adapters.HomeAdapters;
import com.rjzim.mygrocerystore.adapters.PopularAdapters;
import com.rjzim.mygrocerystore.adapters.RecommendedAdapters;
import com.rjzim.mygrocerystore.adapters.ViewAllAdapter;
import com.rjzim.mygrocerystore.databinding.FragmentHomeBinding;
import com.rjzim.mygrocerystore.models.HomeCategory;
import com.rjzim.mygrocerystore.models.PopularModel;
import com.rjzim.mygrocerystore.models.RecommendedModel;
import com.rjzim.mygrocerystore.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRec,homeCatRec,recommendedRec;
    FirebaseFirestore db;

    List<PopularModel>popularModelList;
    PopularAdapters popularAdapters;

    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;

    List<HomeCategory>categoryList;
    HomeAdapters homeAdapters;

    List<RecommendedModel>recommendedModelList;
    RecommendedAdapters recommendedAdapters;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scrollView);
        progressBar = root.findViewById(R.id.p_bar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //popular item
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Home Category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapters = new HomeAdapters(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapters);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapters.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //recommended item
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapters = new RecommendedAdapters(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapters);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapters.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        recyclerViewSearch = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else {
                    searchProduct(s.toString());
                }
            }
        });

        return root;
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()){
            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

}