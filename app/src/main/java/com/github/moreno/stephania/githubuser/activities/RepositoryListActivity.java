package com.github.moreno.stephania.githubuser.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.github.moreno.stephania.githubuser.models.Items_;
import com.github.moreno.stephania.githubuser.rest.RestClientRepository;
import com.github.moreno.stephania.githubuser.utils.ConnectionUtil;
import com.github.moreno.stephania.githubuser.utils.ConstantUtil;
import com.github.moreno.stephania.githubuser.view.adapter.RepositoryAdapter;
import com.github.moreno.stephania.githubuser.view.desing.ControlCustom;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Activity que permite listar los repositorio del item seleccionado
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class RepositoryListActivity extends AppCompatActivity {

    /**
     * Swipe Refresh
     */
    private SwipeRefreshLayout mContainerSwipeRefreshLayout;

    /**
     * ListView que muestra la lista de repositorios
     */
    private ListView mReposioryLv;

    /**
     * List de tipo {@link Component}
     */
    private List<Items_> mItems_;

    /**
     * Variable de tipo {@link RepositoryAdapter}
     */
    private RepositoryAdapter adapter ;

    /**
     * Variable de tipo {@link Items}
     */
    public static Items mItems;

    /**
     * Variable de tipo {@link Items_}
     */
    private Items_ mItem;

    /** Llave que carga la informacion del item seleccioando **/
    public static final String ITEMS = "ITEM";

    /** Tag para logs **/
    private static final String TAG_LOG = RepositoryAdapter.class.getName();

    /** String que guarda resultado de validación de conexión a internet **/
    String mIsConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        //Obtiene el item del repositorio seleccionado
        mItems = (Items) getIntent().getSerializableExtra(ITEMS);

        //Asocia el control SwipeRefreshLayout
        this.mContainerSwipeRefreshLayout = findViewById(R.id.container_repo_srl);

        //Asocia el control ListView
        this.mReposioryLv = findViewById(R.id.repository_lv);

        //Crea objeto de tipo List Items
        mItems_ = new ArrayList<Items_>();

        //Crea objeto de tipo Items
        mItem = new Items_();

        //Se obtiene rel resultado de validación de conexión a internet
        mIsConnect = ConnectionUtil.validateConnection(RepositoryListActivity.this);

        if(mIsConnect == ConstantUtil.ON_CONNECTION)
        {
            //Invocacion del metodo
            cargarDatos();
        }
        else
        {
            View mToastVw = RepositoryListActivity.this.getLayoutInflater().
                    inflate(R.layout.toast_custom_error, null);
            ControlCustom.showFullyCustomToast(RepositoryListActivity.this, mToastVw,
                    ConstantUtil.OFF_CONNECTION);
        }

        //Onclick listener de la lista
        mReposioryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItem = mItems_.get(i);
                String url = mItem.getHtmlUrl();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

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
                  View mToastVw = RepositoryListActivity.this.getLayoutInflater().
                          inflate(R.layout.toast_custom_error, null);
                  ControlCustom.showFullyCustomToast(RepositoryListActivity.this, mToastVw,
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
    }

    /**
     * Metodo que carga la informacion del web service
     */
    private void cargarDatos()
    {
        final View mToastVw = RepositoryListActivity.this.getLayoutInflater().
                inflate(R.layout.toast_custom_error, null);

        try
        {
            final ProgressDialog dialog = ProgressDialog.show(this, "",
                    getString(R.string.loading));

            RestClientRepository.ApiInterface mCliente = RestClientRepository.getClientRepo();

            Call<List<Items_>> call = mCliente.getRepository(mItems.getOwner().getLogin(),
                    mItems.getName());

            call.enqueue(new Callback<List<Items_>>() {
                @Override
                public void onResponse(Response<List<Items_>> response) {
                    dialog.dismiss();
                    Log.d(TAG_LOG, "Status Code = " + response.code());
                    if (response.isSuccess())
                    {

                        List<Items_> result = response.body();
                        Log.d(TAG_LOG, "response = " + new Gson().toJson(result));

                        mItems_ = result;

                        Log.d(TAG_LOG, "Items = " + mItems_.size());
                        adapter = new RepositoryAdapter(RepositoryListActivity.this, mItems_);
                        mReposioryLv.setAdapter(adapter);
                        mContainerSwipeRefreshLayout.setRefreshing(false);
                    }
                    else
                    {
                        ControlCustom.showFullyCustomToast(RepositoryListActivity.this,
                                mToastVw, ConstantUtil.NOT_DATA);
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
            ControlCustom.showFullyCustomToast(RepositoryListActivity.this, mToastVw,
                    ConstantUtil.NOT_DATA);
        }
    }

    /**
     * Metodo que devuelve a la actividad {@link GitHubListActivity}
     */
    @Override
    public void onBackPressed()
    {
        Intent mIntent = new Intent(this, GitHubListActivity.class);
        startActivity(mIntent);
    }
}
