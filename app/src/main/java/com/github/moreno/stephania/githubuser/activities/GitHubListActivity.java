package com.github.moreno.stephania.githubuser.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.github.moreno.stephania.githubuser.R;
import com.github.moreno.stephania.githubuser.models.Component;
import com.github.moreno.stephania.githubuser.models.Items;
import com.github.moreno.stephania.githubuser.rest.RestClient;
import com.github.moreno.stephania.githubuser.utils.ConnectionUtil;
import com.github.moreno.stephania.githubuser.utils.ConstantUtil;
import com.github.moreno.stephania.githubuser.view.adapter.UserAdapter;
import com.github.moreno.stephania.githubuser.view.desing.ControlCustom;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Activity que permite listar los repositorios
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class GitHubListActivity extends AppCompatActivity {

    /**
     * Swipe Refresh
     */
    private SwipeRefreshLayout mContainerSwipeRefreshLayout;

    /**
     * ListView que muestra la lista de usuarios
     */
    private ListView mRepositoryLv;

    /**
     * List de tipo {@link Component}
     */
    private List<Items> mItems;

    /**
     * Variable de tipo {@link UserAdapter}
     */
    private UserAdapter adapter ;

    /**
     * Variable de tipo {@link Items}
     */
    private Items mItem;

    /** Llave para enviar informacion del item seleccioando **/
    public static final String ITEMS = "ITEM";

    /** Tag para logs **/
    private static final String TAG_LOG = GitHubListActivity.class.getName();

    /** String que guarda resultado de validación de conexión a internet **/
    String mIsConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //Asocia el control SwipeRefreshLayout
        this.mContainerSwipeRefreshLayout = findViewById(R.id.container_srl);

        //Asocia el contol SwipeRefreshLayout
        this.mRepositoryLv = findViewById(R.id.user_lv);

        //Crea objeto de tipo List Item
        mItems = new ArrayList<Items>();

        //Crea objeto de tipo Items
        mItem = new Items();

        //Se obtiene rel resultado de validación de conexión a internet
        mIsConnect = ConnectionUtil.validateConnection(GitHubListActivity.this);

        if(mIsConnect == ConstantUtil.ON_CONNECTION)
        {
            //Invocacion del metodo
            cargarDatos();
        }
        else
        {
            View mToastVw = GitHubListActivity.this.getLayoutInflater().
                    inflate(R.layout.toast_custom_error, null);
            ControlCustom.showFullyCustomToast(GitHubListActivity.this, mToastVw,
                    ConstantUtil.OFF_CONNECTION);
        }

        // Evento para actualizar la lista
        mContainerSwipeRefreshLayout.setOnRefreshListener(new
                  SwipeRefreshLayout.OnRefreshListener() {
                      @Override
                      public void onRefresh() {
              if(mIsConnect == ConstantUtil.ON_CONNECTION)
              {
                  cargarDatos();
              }
              else if(mIsConnect != ConstantUtil.ON_CONNECTION)
              {
                  cargarDatos();
              }
              else
              {
                  View mToastVw = GitHubListActivity.this.getLayoutInflater().
                          inflate(R.layout.toast_custom_error, null);
                  ControlCustom.showFullyCustomToast(GitHubListActivity.this, mToastVw,
                          ConstantUtil.OFF_CONNECTION);
                  mContainerSwipeRefreshLayout.setRefreshing(false);
              }
          }
      });

        // Configure the refreshing colors
        mContainerSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Onclick listener de la lista
        mRepositoryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GitHubListActivity.this, RepositoryListActivity.class);
                mItem = mItems.get(i);
                intent.putExtra(ITEMS, mItem);
                startActivity(intent);
            }
        });
        mContainerSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Metodo que carga la informacion del web service
     */
    private void cargarDatos()
    {
        final View mToastVw = GitHubListActivity.this.getLayoutInflater().
                inflate(R.layout.toast_custom_error, null);
       try
       {
           final ProgressDialog dialog = ProgressDialog.show(this, "",
                   getString(R.string.loading));

           RestClient.ApiInterface mCliente = RestClient.getClient();

           Call<Component> call = mCliente.getUsersNamedTom(ConstantUtil.NAME_USER);
           call.enqueue(new Callback<Component>() {
               @Override
               public void onResponse(Response<Component> response) {
                   dialog.dismiss();
                   Log.d(TAG_LOG, "Status Code = " + response.code());
                   if (response.isSuccess()) {

                       // request successful (status code 200, 201)
                       Component result = response.body();
                       Log.d(TAG_LOG, "response = " + new Gson().toJson(result));

                       mItems = result.getItems();

                       Log.d(TAG_LOG, "Items = " + mItems.size());
                       adapter = new UserAdapter(GitHubListActivity.this, mItems);
                       mRepositoryLv.setAdapter(adapter);
                       mContainerSwipeRefreshLayout.setRefreshing(false);
                   }
                   else
                   {
                       ControlCustom.showFullyCustomToast(GitHubListActivity.this, mToastVw,
                               ConstantUtil.NOT_DATA);
                   }
               }
               @Override
               public void onFailure(Throwable t) {
                   dialog.dismiss();
               }
           });
       }
       catch (Exception es)
       {
           ControlCustom.showFullyCustomToast(GitHubListActivity.this, mToastVw,
                   ConstantUtil.NOT_DATA);
       }
    }

    /**
     * Metodo que devuelve a la actividad {@link GitHubListActivity}
     */
    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}
