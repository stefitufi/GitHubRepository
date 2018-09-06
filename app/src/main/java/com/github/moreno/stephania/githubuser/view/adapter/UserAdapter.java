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
import com.github.moreno.stephania.githubuser.models.Items;
import com.github.moreno.stephania.githubuser.models.Owner;
import com.github.moreno.stephania.githubuser.utils.StringUtil;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Adapter que infla la vista con cada usuario
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class UserAdapter extends BaseAdapter {

    /**
     * List de tipo {@link Items}
     */
    private List<Items> users ;

    /**
     * List de tipo {@link Owner}
     */
    private List<Owner> owners;

    /**
     * Variable de tipo {@link Context}
     */
    private Context context ;

    /** Tag para logs **/
    private static final String TAG_LOG = UserAdapter.class.getName();

    /**
     * Contructor del adapter
     *  @param ctx
     *          Contexto
     * @param items
     */
    public UserAdapter(Context ctx, List<Items> items) {
        super();
        this.context = ctx ;
        this.users = items ;
    }

    /**
     * Metodo que determima la cantidad de registros
     * @return
     */
    @Override
    public int getCount() {
        return this.users.size();
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

    /***
     * Método que llena o infla los items con la información correspondiente
     * @param position
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater mLayoutInflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View mView = mLayoutInflater.inflate(R.layout.item_user, viewGroup, false);
        ImageView mAvatarIv = mView.findViewById(R.id.avatar_iv);
        TextView mLoginTv = mView.findViewById(R.id.login_tv);
        TextView mFullNameTv = mView.findViewById(R.id.full_name_tv);
        TextView mNameTv = mView.findViewById(R.id.name_tv);
        TextView mDescriptionTv = mView.findViewById(R.id.descriptions_tv);
        TextView mForksTv = mView.findViewById(R.id.forks_tv);
        TextView mStartsTv = mView.findViewById(R.id.start_tv);

        Items user = users.get(position);

        Picasso.with(context).load(user.getOwner().getAvatarUrl()).
                resize(100,100).into(mAvatarIv);
        mLoginTv.setText(StringUtil.capitalize(user.getOwner().getLogin()));
        mFullNameTv.setText(StringUtil.firstCapitalize(user.getFullName()));
        mNameTv.setText(StringUtil.firstCapitalize(user.getName()));
        mDescriptionTv.setText(user.getDescription());
        mForksTv.setText(user.getForksCount().toString());
        mStartsTv.setText(user.getStargazersCount().toString());
        Log.d(TAG_LOG, user.getName());

        return mView;
    }


}
