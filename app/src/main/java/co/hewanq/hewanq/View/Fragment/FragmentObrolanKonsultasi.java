package co.hewanq.hewanq.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.hewanq.hewanq.R;

public class FragmentObrolanKonsultasi extends Fragment
{
    View view;

    public FragmentObrolanKonsultasi()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_obrolan_konsultasi, container, false);

        return view;
    }


}
