package dang.ugi.com.view.MonAn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnAdapter;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

public class ChonMonCoSanActivity extends AppCompatActivity {
    RecyclerView recyclerViewMonAn;
    FloatingActionButton fb_timkiemmon,fabCancle;
    List<MonAn> listMonAn;
    ImpPresenterMonAn impPresenterMonAn;
    private Dialog dialog;
    private  SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mon_co_san);
        impPresenterMonAn = new ImpPresenterMonAn(this);
        listMonAn = impPresenterMonAn.listAllMonAn();
        addControls();
        loadData(listMonAn);


    }
    private void loadData(List<MonAn> list) {
        MonAnAdapter monAnAdapter = new MonAnAdapter(this,list,R.layout.custom_cardview_monan,2);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
        recyclerViewMonAn.setLayoutManager(layoutManager);
        recyclerViewMonAn.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMonAn.setAdapter(monAnAdapter);
        monAnAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        recyclerViewMonAn = (RecyclerView) findViewById(R.id.recyclerView_listThemMon);
        fb_timkiemmon = (FloatingActionButton) findViewById(R.id.fabTimKiemMon);
        fb_timkiemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSearch();
            }
        });

    }
  /*  public void showPopupSearch(View view){
        PopupMenu popupmenu = new PopupMenu(this,view);
        MenuInflater inflater = popupmenu.getMenuInflater();
        Menu menu = popupmenu.getMenu();
        inflater.inflate(R.menu.menu_search,menu);
        popupmenu.setGravity(Gravity.START);
        MenuItem menuItem = menu.findItem(R.id.item_search_monan);
        SearchView searchview = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listMonAn = impPresenterMonAn.listTimKiemMonAn(s);
                loadData(listMonAn);
                return false;
            }
        });
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        popupmenu.show();
    }*/
    private void showDialogSearch(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      /*  dialog.setContentView(R.layout.dialog_searchview);
        dialog.getWindow().setBackgroundDrawable(new AnimationDrawable());
        searchView = (SearchView) dialog.findViewById(R.id.searchview_monan);*/
        fabCancle = (FloatingActionButton) dialog.findViewById(R.id.fb_cancle);
        searchView.onActionViewExpanded();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.LEFT | Gravity.BOTTOM;
        dialog.setCanceledOnTouchOutside(true);
        searchView.isShown();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listMonAn = impPresenterMonAn.listTimKiemMonAn(s);
                loadData(listMonAn);
                return false;
            }
        });
        fabCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        dialog.show();
    }
}
