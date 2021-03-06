package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cheetatech.com.colorhub.R;
import cheetatech.com.colorhub.adapters.MaterialGridViewAdapter;
import cheetatech.com.colorhub.controller.ColorArrayController;
import cheetatech.com.colorhub.defines.ColorInfo;
import cheetatech.com.colorhub.listeners.ListenerModel;


public class MaterialColorFragment extends Fragment implements AdapterView.OnItemLongClickListener , ListenerModel.OnModelStateListener {

    private GridView gridView = null;
    private List<ColorInfo> colorInfoArrayList = null;
    private ColorPicker1.OnColorListener mListener = null;
    private MaterialGridViewAdapter mAdapter = null;
    public MaterialColorFragment() {
    }

    public static MaterialColorFragment newInstance(ColorPicker1.OnColorListener listener){
        MaterialColorFragment fragment = new MaterialColorFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(ColorPicker1.OnColorListener listener) {
        this.mListener = listener;
    }

    public ColorPicker1.OnColorListener getListener() {
        return this.mListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material_color, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if(colorInfoArrayList == null)
            colorInfoArrayList = new ArrayList<ColorInfo>();

        ListenerModel.getInstance().setListener(this);

        ColorArrayController controller  = ColorArrayController.getInstance();
        colorInfoArrayList = controller.getMaterialColorInfoList().get(0).getColorInfoList();
        gridView = (GridView) getView().findViewById(R.id.gridviewmaterial);
        mAdapter = new MaterialGridViewAdapter(getContext(),R.layout.grid_list,colorInfoArrayList,this.mListener);
        gridView.setAdapter(mAdapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    @Override
    public void onSelectedColorIndex(int index) {
        int fi = MaterialGridViewAdapter.HeaderIndex;
        MaterialGridViewAdapter.HeaderIndex = index;
        ColorArrayController controller  = ColorArrayController.getInstance();
        colorInfoArrayList = controller.getMaterialColorInfoList().get(index).getColorInfoList();
        mAdapter = new MaterialGridViewAdapter(getContext(),R.layout.grid_list,colorInfoArrayList,this.mListener);
        gridView.setAdapter(mAdapter);
    }
}
