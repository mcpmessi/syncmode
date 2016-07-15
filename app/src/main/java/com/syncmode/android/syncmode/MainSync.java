package com.syncmode.android.syncmode;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.syncmode.android.custom.TimeSyncValues;
import com.syncmode.android.persistence.DatabaseMenager;

import java.util.ArrayList;

public class MainSync extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private DatabaseMenager database;
    public static int timeSync = 3;
    private SpinnerAdapter spinerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sync);

        //verificar se o gogle ploay services está instalado
        Integer resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (resultCode == ConnectionResult.SUCCESS) {
            //Do what you want
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
                //This dialog will help the user update to the latest GooglePlayServices
                dialog.show();
            }
        }

        initComponent();
        createService();

    }

    /**
     * Apenas inicializar os componentes da activity
     * @return void
     **/
    private void initComponent() {
        database = new DatabaseMenager(this);
        //inicializar o time sync
        timeSync = Integer.parseInt(database.getTimeSync());


        final Spinner sp_times = (Spinner) findViewById(R.id.sp_sync_times);
        ArrayList<TimeSyncValues> values = initAdapter();

        spinerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, values);

        sp_times.setAdapter(spinerAdapter);


        sp_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button btn_salvar = (Button) findViewById(R.id.bt_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = sp_times.getSelectedItemPosition();

                TimeSyncValues item = (TimeSyncValues)spinerAdapter.getItem(position);

                if(item==null){
                    Log.i("ERRO item NULL::", "O item não foi selecionado");
                    Toast.makeText(getBaseContext(),"O item não foi selecionado" , Toast.LENGTH_SHORT).show();
                    return;
                }


                if(database.setConfigTimeSync(item.getId()+"")>0){
                    Log.i("btn_salvar:::", "Configuração de atualização atualizada com sucesso! >>"+item);
                    timeSync = Integer.parseInt(database.getTimeSync());
                    Toast.makeText(getBaseContext(),"Configuração de atualização atualizada com sucesso para "+item , Toast.LENGTH_SHORT).show();
                }else{
                    Log.e("btn_salvar:::", "Erro ao salvar as configurações. >>"+item.getId());

                }
                Log.i("OBJECT SELECTED::", item.getId()+"");
            }
        });
    }


    synchronized void createService() {
        startService(new Intent(this, SyncPosition.class));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private ArrayList<TimeSyncValues> initAdapter(){
        ArrayList<TimeSyncValues> t = new ArrayList<>();
        for(int i=1; i<8; i++){
            TimeSyncValues v = new TimeSyncValues();
            v.setName((i*5)+" minutos");
            v.setId(i*5);
            t.add(v);
        }

        return t;
    }
}
