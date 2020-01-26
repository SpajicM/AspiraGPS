package hr.marin.aspiragps;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutesFragment extends ListFragment {

    private ArrayAdapter adapter;
    private ArrayList<Route> route_list = new ArrayList<>();

    public RoutesFragment() {
        // Required empty public constructor
    }

    void loadData() {
        route_list.clear();
        route_list.addAll(RouteStore.getRoutes(getActivity()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1, route_list);
        setListAdapter(adapter);
        loadData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Route route = route_list.get(pos);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage(
                "Distance: " + Formatter.getDistanceString(route.getDistance()) + "\n" +
                "Duration: " + Formatter.getTimeString(route.getTimeSeconds()) + "\n" +
                "Average speed: " + Formatter.getAvgSpeedString(route.getDistance(), route.getTimeSeconds()) + "\n" +
                "Date & time: " + Formatter.getDateTimeString(route.getTimestamp()) + "\n"
        );
        alert.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

        alert.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> av, View v, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("Are you sure you want to delete this route record?");
                alert.setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RouteStore.removeRoute(position, getActivity());
                                loadData();
                                dialog.dismiss();

                            }
                        });

                alert.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                return true;
            }
        });
    }
}
