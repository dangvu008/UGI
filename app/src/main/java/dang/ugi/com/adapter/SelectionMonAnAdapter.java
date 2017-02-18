package dang.ugi.com.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.Entities.MonAn;

/**
 * Created by DANG on 11/10/2016.
 */

abstract class MonAnSelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @SuppressWarnings("unused")

    private static final String TAG = MonAnSelectableAdapter.class.getSimpleName();



    private List<MonAn> listMonAnSelected;



    public MonAnSelectableAdapter() {

        listMonAnSelected = new ArrayList<>();

    }



    /**

     * Indicates if the item at position position is selected

     * @param position Position of the item to check

     * @return true if the item is selected, false otherwise

     */

    public boolean isSelected(int position) {

        return getSelectedItems().contains(position);

    }



    /**

     * Toggle the selection status of the item at a given position

     * @param position Position of the item to toggle the selection status for

     */

    public void toggleSelection(int position) {

        if (listMonAnSelected.get(position)==null) {

            //listMonAnSelected.delete(position);

        } else {

            //selectedItems.put(position, true);

        }

        notifyItemChanged(position);

    }



    /**

     * Clear the selection status for all items

     */

    public void clearSelection() {

        List<MonAn> selection = getSelectedItems();

        listMonAnSelected.clear();

        for (MonAn i : selection) {

            //notifyItemChanged(listMonAnSelected);

        }

    }



    /**

     * Count the selected items

     * @return Selected items count

     */

    public int getSelectedItemCount() {

        return listMonAnSelected.size();

    }



    /**

     * Indicates the list of selected items

     * @return List of selected items ids

     */

    public List<MonAn> getSelectedItems() {

        List<MonAn> items = new ArrayList<>(listMonAnSelected.size());

        for (int i = 0; i < listMonAnSelected.size(); ++i) {

            items.add(listMonAnSelected.get(i));

        }

        return items;

    }

}


