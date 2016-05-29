package rs.forexample.goodfellas.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.util.ArrayList;
import java.util.List;

import rs.forexample.goodfellas.R;

public class SlideMenuFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private View containerView;
    private static ArrayList<String> titles = null;
    private static List<Drawable> icons;
    private SlideMenuListener drawerListener;

    private ListView lv;

    public SlideMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slide_menu, container, false);

        generateIcons();
        generateTitles();

        lv = (ListView) v.findViewById(R.id.menuList);

        MenuAdapter adapt = new MenuAdapter(getActivity(), getMenuData());

        lv.setAdapter(adapt);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3 || position == 4)
                    drawerListener.onMenuItemSelected(view, position);

                mDrawerLayout.closeDrawer(containerView);
            }
        });

        return v;
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    private   List<MenuItem> getMenuData() {
        List<MenuItem> data = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            data.add(new MenuItem(titles.get(i), icons.get(i)));
        }
        return data;
    }

    private void generateIcons(){
        icons = new ArrayList<>();

        icons.add(new IconDrawable(getActivity(), MaterialIcons.md_account_balance).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), IoniconsIcons.ion_cash).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), MaterialIcons.md_import_export).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), MaterialIcons.md_credit_card).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), IoniconsIcons.ion_card).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), IoniconsIcons.ion_social_euro).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), IoniconsIcons.ion_ios_calculator).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), MaterialIcons.md_pin_drop).colorRes(R.color.menuItemsColor).actionBarSize());
        icons.add(new IconDrawable(getActivity(), MaterialIcons.md_phone).colorRes(R.color.menuItemsColor).actionBarSize());
    }

    private void generateTitles(){
        titles = new ArrayList<>();
        titles.add("Accounts");
        titles.add("Payments");
        titles.add("Transfers");
        titles.add("Cozy card");
        titles.add("Digital cards");
        titles.add("Exchange office");
        titles.add("Calculator");
        titles.add("Locations");
        titles.add("Contact");
    }

    public static interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public void setDrawerListener(SlideMenuListener listener) {
        this.drawerListener = listener;
    }

    public void toggle(){
        if(mDrawerLayout.isDrawerOpen(containerView)){
            mDrawerLayout.closeDrawer(containerView);
        }else{
            mDrawerLayout.openDrawer(containerView);
        }
    }

    public interface SlideMenuListener {
        void onMenuItemSelected(View view, int position);
    }

    private class MenuItem {
        private String title;
        private Drawable icon;

        public MenuItem() {
        }

        public MenuItem(String title, Drawable icon) {
            this.title = title;
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }
    }

    private class MenuAdapter extends BaseAdapter {

        private TextView tvTitle;
        private ImageView ivIcon;
        private Context ctx;
        private List<MenuItem> items;

        private LayoutInflater inflater;

        MenuAdapter(Context context, List<MenuItem> items) {
            this.ctx = context;
            this.items = items;

            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null)
                v = inflater.inflate(R.layout.item_menu, parent, false);

            tvTitle = (TextView) v.findViewById(R.id.title);
            ivIcon = (ImageView) v.findViewById(R.id.icon);

            tvTitle.setText(items.get(position).getTitle());
            ivIcon.setImageDrawable(items.get(position).getIcon());

            return v;
        }
    }

}
