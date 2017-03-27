package dang.ugi.com.view.TrangChu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.FacebookSdk;

import dang.ugi.com.R;
import dang.ugi.com.asyntask.DownloadImage;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.CircleTransform;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.CuaHang.ImpPresenterCuaHang;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import dang.ugi.com.view.BanAn.FragmentBanAn;
import dang.ugi.com.view.BanAn.ThemBanActivity;
import dang.ugi.com.view.BanAn.ThemBanNhanh;
import dang.ugi.com.view.CuaHang.FragmentCuaHang;
import dang.ugi.com.view.CuaHang.ThemCuaHangActivity;
import dang.ugi.com.view.GoiMon.FragmentGoiMon;
import dang.ugi.com.view.GoiMon.GoiMonActivity;
import dang.ugi.com.view.MonAn.ChonLoaiMonAnActivity;
import dang.ugi.com.view.MonAn.FragmentChonMonCoSan;
import dang.ugi.com.view.MonAn.FragmentMonAn;
import dang.ugi.com.view.MonAn.ThemMonActivity;
import dang.ugi.com.view.NguoiDung.DangNhapActivity;
import dang.ugi.com.view.NguoiDung.FragmentNguoiDung;
import dang.ugi.com.view.ThongKe.ThongKeThangFragment;

import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_GOIMON;
import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_THEMBAN;
import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_THEMMON;
import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_THEMNHANVIEN;


//import dang.ugi.com.model.Utils.CircleTransform;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ImageView imageViewCancle, imageViewLogoCuaHang;
    /* private FloatingActionButton floatingActionButton, fb_themMon, fb_ThemBan, fb_ThemNguoidung, fb_cancle, fb_goiMon;
     private RelativeLayout layout_fb_goiMon, layout_fb_themNguoiDung, layout_fb_themBan, layout_fb_themMon;*/
    private FloatingActionButton floatingActionButton;
    private TextView textViewTenCuaHang;
    private Dialog dialog;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SwipeRefreshLayout refreshLayout;
    private ImplNguoiDungPresenter implPresenterDangNhap;
    private android.support.v4.app.FragmentManager fragmentManager;

    public static final String TAG_HOME = "Trang Chủ";
    public static final String TAG_THUCDON = "Thực đơn";
    public static final String TAG_GOIMON = "Gọi Món";
    public static final String TAG_BAN = "Bàn ";
    public static final String TAG_CUAHANG = "Cửa Hàng ";
    public static final String TAG_NGUOIDUNG = "Người Dùng ";
    public static final String TAG_THONGKE = "Thống kê ";
    public static String TAG_CURRENT = TAG_BAN;
    private String[] activityTitles;
    private boolean loadDuLieuKhiAnPhimBack = true;
    Bundle bundle;
    Fragment fragment;
    private Toolbar toolbar;
    private ImplPresenterBanAn implPresenterBanAn;
    private ImpPresenterCuaHang impPresenterCuaHang;
    private FragmentTransaction transaction;
    public static int itemCheckedId;
    private String title_fragment[];
    private String tenCuaHang;
    NguoiDung nguoiDung;
    CuaHang cuaHang;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        implPresenterBanAn = new ImplPresenterBanAn(this);
        implPresenterDangNhap = new ImplNguoiDungPresenter(this);
        impPresenterCuaHang = new ImpPresenterCuaHang(this);
        fragmentManager = getSupportFragmentManager();
        addviews();
        loadCustomFloatingButton();
        loadHeaderNavigation();
        setupNavigationView();
        loadBarAction();
        nguoiDung = PrefDangNhap.layNguoiDungHienTai(this);
        cuaHang = PrefNhaHang.layCuaHangHienTai(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {
            itemCheckedId = 2;
            TAG_CURRENT = TAG_BAN;
            loadHomeFragment();
        }

        if (nguoiDung != null) {
            if (cuaHang != null) {
                tenCuaHang = PrefNhaHang.layCuaHangHienTai(this).getTenCuahang();
                new DownloadImage(imageViewLogoCuaHang).execute(nguoiDung.getImage());
                textViewTenCuaHang.setText(tenCuaHang);
                if (nguoiDung.getMaQuyen() != 1) {
                    anItemNavigation();
                } else {
                    Intent intentCH = new Intent(MainActivity.this, ThemCuaHangActivity.class);
                    startActivity(intentCH);
                }
            }
        } else {
            implPresenterDangNhap.chuyenManHinhDanhNhap(MainActivity.this);
        }
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());

    }

    private void addviews() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        title_fragment = this.getResources().getStringArray(R.array.nav_drawer_title);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void loadHomeFragment() {
        selectNavMenu();
        setToobarTitle();
        if (getFragmentManager().findFragmentByTag(TAG_CURRENT) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        fragment = getFragmentHome();
        loadFragment(fragment);
        drawerLayout.closeDrawers();
    }

    public void loadFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fl_content, fragment, TAG_CURRENT);
        transaction.commit();
        drawerLayout.closeDrawers();
    }

    private Fragment getFragmentHome() {
        Fragment fragment = null;
        switch (itemCheckedId) {
            case 0: {
                fragment = new FragmentGoiMon();
            }
            break;
            case 1: {
                fragment = new FragmentMonAn();
            }
            break;
            case 2: {
                fragment = new FragmentBanAn();
            }
            break;
            case 3: {
                fragment = new FragmentNguoiDung();
            }
            break;
            case 4: {
                fragment = new FragmentCuaHang();
                floatingActionButton.setVisibility(View.GONE);
            }
            break;
            case 5: {
                fragment = new ThongKeThangFragment();
                floatingActionButton.setVisibility(View.GONE);
            }
            break;
            default:
                fragment = new FragmentBanAn();
        }
        return fragment;

    }

    private void loadBarAction() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void setupNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_drawer_goiMon: {
                        TAG_CURRENT = TAG_GOIMON;
                        itemCheckedId = 0;
                    }
                    break;

                    case R.id.item_drawer_thucdon: {
                        TAG_CURRENT = TAG_THUCDON;
                        itemCheckedId = 1;
                    }
                    break;

                    case R.id.item_drawer_ban: {
                        TAG_CURRENT = TAG_BAN;
                        itemCheckedId = 2;
                    }
                    break;
                    case R.id.item_drawer_NguoiDung: {
                        TAG_CURRENT = TAG_NGUOIDUNG;
                        itemCheckedId = 3;
                    }
                    break;
                    case R.id.item_drawer_cuahang: {
                        TAG_CURRENT = TAG_CUAHANG;
                        itemCheckedId = 4;
                    }
                    break;
                    case R.id.item_drawer_thongke: {
                        TAG_CURRENT = TAG_THONGKE;
                        itemCheckedId = 5;
                    }
                    break;
                    default:
                        itemCheckedId = 2;
                }
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadHomeFragment();

                return true;
            }
        });

    }

    private void loadHeaderNavigation() {
        navHeader = navigationView.getHeaderView(0);
        textViewTenCuaHang = (TextView) navHeader.findViewById(R.id.textViewTenCuaHang);
        imageViewLogoCuaHang = (ImageView) navHeader.findViewById(R.id.imageViewLogo);
        textViewTenCuaHang.setText(tenCuaHang);
        Glide.with(this).load(R.drawable.ugi_logo).crossFade().thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewLogoCuaHang);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        if (loadDuLieuKhiAnPhimBack) {
            if (itemCheckedId != 2) {
                itemCheckedId = 2;
                TAG_CURRENT = TAG_BAN;
                loadHomeFragment();
                return;
            }
        }
    }


    private void setToobarTitle() {
        getSupportActionBar().setTitle(title_fragment[itemCheckedId]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(itemCheckedId).setChecked(true);
    }

    private void anItemNavigation() {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.item_drawer_cuahang).setVisible(false);
        menu.findItem(R.id.item_drawer_thongke).setVisible(false);
        menu.findItem(R.id.item_drawer_NguoiDung).setVisible(false);
    }

    private void loadCustomFloatingButton() {
       /* dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_floatingbutton);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        dialog.setCanceledOnTouchOutside(true);
        fb_goiMon = (FloatingActionButton) dialog.findViewById(R.id.fb_GoiMon);
        fb_themMon = (FloatingActionButton) dialog.findViewById(R.id.fb_themMon);
        fb_ThemBan = (FloatingActionButton) dialog.findViewById(R.id.fb_themBan);
        fb_ThemNguoidung = (FloatingActionButton) dialog.findViewById(R.id.fb_themNguoiDung);
        fb_cancle = (FloatingActionButton) dialog.findViewById(R.id.fb_cancle);
        layout_fb_goiMon = (RelativeLayout) dialog.findViewById(R.id.layout_goiMon);
        layout_fb_themBan = (RelativeLayout) dialog.findViewById(R.id.layout_themBan);
        layout_fb_themNguoiDung = (RelativeLayout) dialog.findViewById(R.id.layout_themnguoidung);
        layout_fb_themMon = (RelativeLayout) dialog.findViewById(R.id.layout_themMon);
        hideFloatButton();
        fb_ThemNguoidung.setOnClickListener(this);
        fb_ThemBan.setOnClickListener(this);
        fb_themMon.setOnClickListener(this);*/
      /*  fb_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemCheckedId == 0) {
                    chuyenManHinhGoiMon();
                }
                if (itemCheckedId == 1) {
                    chuyenManHinhThemThucDon(floatingActionButton);
                }
                if (itemCheckedId == 2) {
                    showPopupMenutThemBan(floatingActionButton);
                }
                if (itemCheckedId == 3) {
                    Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_THEMNHANVIEN);
                }
                if (itemCheckedId == 4) {
                    Intent intent = new Intent(MainActivity.this, ThemCuaHangActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    private void chuyenManHinhThemThucDon(View view) {
        final PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setGravity(Gravity.CENTER_HORIZONTAL);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu_themmon, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_popup_themMon_themLoaiCoSan: {
                        Intent intent = new Intent(MainActivity.this, ChonLoaiMonAnActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_THEMMON);
                    }
                    break;
                    case R.id.item_popup_themMon_ChonCoSan: {
                        chuyenManHInhThemMonCoSan();
                    }
                    break;
                    case R.id.item_popup_themMon_ThemMoi: {
                        Intent intent = new Intent(MainActivity.this, ThemMonActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_THEMMON);
                    }
                    break;
                }

                return false;
            }


        });
        popupMenu.show();
    }

    private void chuyenManHInhThemMonCoSan() {
        itemCheckedId = 7;
        FragmentChonMonCoSan fragmentChonMonCoSan = new FragmentChonMonCoSan();
        Bundle bundle = new Bundle();
        bundle.putInt("chucnang", 2);
        fragmentChonMonCoSan.setArguments(bundle);
        loadFragment(fragmentChonMonCoSan);
    }


    private void chuyenManHinhGoiMon() {
        Intent intent = new Intent(MainActivity.this, GoiMonActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GOIMON);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if (requestCode==REQUEST_CODE_THEMBAN || requestCode ==REQUEST_CODE_THEMBANNHANH && resultCode == Activity.RESULT_OK){
            loadHomeFragment();
        }
        if (requestCode==REQUEST_CODE_GOIMON && resultCode ==Activity.RESULT_OK){

        }
    }*/


    public void showPopupMenutThemBan(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.CENTER_VERTICAL);
        final MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu_themban, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_menu_popup_thembanlanluot: {
                        Intent intent = new Intent(MainActivity.this, ThemBanActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_THEMBAN);
                    }
                    break;
                    case R.id.item_menu_popup_thembannhanh: {
                        Intent intent = new Intent(MainActivity.this, ThemBanNhanh.class);
                        startActivityForResult(intent, REQUEST_CODE_THEMMON);
                    }
                    break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_main, menu);
        MenuItem item_search = menu.findItem(R.id.item_menu_toolbar_search);
        MenuItem item_ten = menu.findItem(R.id.item_menu_tenNguoiDung);
        MenuItem item_sync = menu.findItem(R.id.item_menu_toolbar_refesh);
        item_sync.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                synchronizedFromServer();
                return false;
            }


        });
        if (nguoiDung != null)
            item_ten.setTitle(nguoiDung.getTenNguoiDung());
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item_search);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        return true;
    }

    private void synchronizedFromServer() {
        if (itemCheckedId == 0) {
            Toast.makeText(this, "goimon", Toast.LENGTH_SHORT).show();
        }
        if (itemCheckedId == 1) {
            Toast.makeText(this, "thucdon", Toast.LENGTH_SHORT).show();
        }
        if (itemCheckedId == 2) {
            //showPopupMenutThemBan(floatingActionButton);
        }
        if (itemCheckedId == 3) {
            //SyncNguoiDungServer syncNguoiDungServer = new SyncNguoiDungServer(this);
            // syncNguoiDungServer.syncNguoiDungToServer();
           /* Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
            startActivityForResult(intent, REQUEST_CODE_THEMNHANVIEN);*/
        }
        if (itemCheckedId == 4) {
           /* Intent intent = new Intent(MainActivity.this, ThemCuaHangActivity.class);
            startActivity(intent);*/
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_dangxuat: {
                implPresenterDangNhap.dangxuat();
            }
            case R.id.item_menu_tenNguoiDung: {
                Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                intent.putExtra("capnhatnguoidung", "Cập Nhật Người Dùng");
                startActivity(intent);
            }
            break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        bundle = new Bundle();
        bundle.putString("keySearch", s);
        fragment.setArguments(bundle);
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String s) {
        Fragment fragment = getFragmentHome();
        Bundle bundlek = new Bundle();
        bundlek.putString("keySearch", s);
        fragment.setArguments(bundlek);
        loadFragment(fragment);
        return false;
    }
}



