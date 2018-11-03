package app.android.gaf004.listview.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import app.android.gaf004.listview.R;
import app.android.gaf004.listview.adapters.FruitAdapter;
import app.android.gaf004.listview.models.Fruta;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Fruta> frutas;
    private FruitAdapter listAdapter;
    private FruitAdapter gridAdapter;
    private ListView listView;
    private GridView gridView;
    private MenuItem gridViewBtn;
    private MenuItem listViewBtn;
    private MenuItem deleteBtn;
    private ViewFlipper viewFlipper;
    private int precargadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_fruit);

        listView = (ListView) findViewById(R.id.listView);
        gridView = (GridView) findViewById(R.id.gridView);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        registerForContextMenu(listView);
        registerForContextMenu(gridView);

        Drawable d1 = getResources().getDrawable(R.drawable.platano);
        Drawable d2 = getResources().getDrawable(R.drawable.manzana);
        Drawable d3 = getResources().getDrawable(R.drawable.pera);
        Drawable d4 = getResources().getDrawable(R.drawable.pinha);

        Fruta F1,F2,F3,F4;
        F1 = new Fruta("Platano","Canarias",d1);
        F2 = new Fruta("Manzana","Alemania",d2);
        F3 = new Fruta("Pera","Francia",d3);
        F4 = new Fruta("Piña","Cuba",d4);

        frutas = new ArrayList<Fruta>();
        frutas.add(F1);
        frutas.add(F2);
        frutas.add(F3);
        frutas.add(F4);


        listAdapter = new FruitAdapter(MainActivity.this,R.layout.list_item,frutas);
        gridAdapter = new FruitAdapter(MainActivity.this,R.layout.grid_item,frutas);
        listView.setAdapter(listAdapter);
        precargadas = listAdapter.getCount()-1;

        listView.setOnItemClickListener(this);
        gridView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        listViewBtn = menu.findItem(R.id.listViewBtn);
        gridViewBtn = menu.findItem(R.id.gridViewBtn);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addItem:

                Fruta F = new Fruta("Cerezas","Extremadura",getResources().getDrawable(R.drawable.cerezas));
                this.frutas.add(F);
                listAdapter.notifyDataSetChanged();
                gridAdapter.notifyDataSetChanged();
                return true;

            case R.id.gridViewBtn:

                switchView(R.id.gridViewBtn);
                return true;

            case R.id.listViewBtn:

                switchView(R.id.listViewBtn);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void switchView(int view){

        if(view == R.id.gridViewBtn){
            viewFlipper.showNext();
            gridView.setAdapter(gridAdapter);
            listViewBtn.setVisible(true);
            gridViewBtn.setVisible(false);
        }
        if(view == R.id.listViewBtn){
            viewFlipper.showNext();
            listView.setAdapter(listAdapter);
            listViewBtn.setVisible(false);
            gridViewBtn.setVisible(true);
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position <= precargadas){
            Toast.makeText(MainActivity.this,"L@s "+frutas.get(position).getNombre()+ " son una de nuestras frutas habituales", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"No solemos tener "+frutas.get(position).getNombre()+ ".Hoy las tenemos y son excepcionales", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.frutas.get(info.position).getNombre());
        menuInflater.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.deleteBtn:
                this.frutas.remove(this.frutas.get(info.position));
                listAdapter.notifyDataSetChanged();
                gridAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
