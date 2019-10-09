package com.example.weibo5.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weibo5.R;

public class MeFragment extends Fragment {
    private TextView mtv;

    public static MeFragment newInstance(String tag){
        MeFragment meFragment = new MeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag",tag);
        meFragment.setArguments(bundle);
        return meFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment,null);
        mtv = view.findViewById(R.id.me_word);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle argument = getArguments();
        mtv.setText(argument.getString("tag"));
    }
}
