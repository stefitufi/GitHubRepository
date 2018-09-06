package com.github.moreno.stephania.githubuser.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.moreno.stephania.githubuser.R;
import com.github.moreno.stephania.githubuser.models.Items_;
import com.github.moreno.stephania.githubuser.utils.StringUtil;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Adapter que infla la vista con cada repositorio
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class RepositoryAdapter extends BaseAdapter {

    /**
     * List de tipo {@link Items_}
     */
    private List<Items_> mItems_ ;

    /**
     * Variable de tipo {@link Context}
     */
    private Context context ;

    /** Tag para logs **/
    private static final String TAG_LOG = RepositoryAdapter.class.getName();

    /**
     * Contructor del adapter
     *  @param ctx
     *          Contexto
     * @param items
     */
    public RepositoryAdapter(Context ctx, List<Items_> items) {
        super();
        this.context = ctx ;
        this.mItems_ = items ;
    }

    /**
     * Metodo que determima la cantidad de registros
     * @return numero de registros
     */
    @Override
    public int getCount() {
        return this.mItems_.size();
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Método que llena los items con la información correspondiente
     * @param position
     *          posición de los items
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater mLayoutInflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View mView = mLayoutInflater.inflate(R.layout.item_repository, viewGroup, false);

        TextView mNameRepoTv = mView.findViewById(R.id.name_repo_tv);
        TextView mDesciptionRepoTv = mView.findViewById(R.id.descriptions_repo_tv);
        ImageView mAvatarIv = mView.findViewById(R.id.avatar_repo_iv);
        TextView mLogin = mView.findViewById(R.id.login_repo_tv);
        TextView mFullName = mView.findViewById(R.id.full_name_repo_tv);

        Items_ item = mItems_.get(position);

        Picasso.with(context).load(item.getUser().getAvatarUrl()).
                resize(100,100).into(mAvatarIv);
        mNameRepoTv.setText(StringUtil.capitalize(item.getTitle()));
        mDesciptionRepoTv.setText(item.getBase().getRepo().getDescription());
        mLogin.setText(item.getUser().getLogin());
        mFullName.setText(item.getBase().getRepo().getFullName());
        Log.d(TAG_LOG, item.getUser().getLogin());

        return mView;
    }
}
