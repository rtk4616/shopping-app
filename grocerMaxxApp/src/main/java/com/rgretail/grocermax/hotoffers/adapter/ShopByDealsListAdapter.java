package com.rgretail.grocermax.hotoffers.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rgretail.grocermax.BaseActivity;
import com.rgretail.grocermax.MyApplication;
import com.rgretail.grocermax.R;
import com.rgretail.grocermax.bean.ShopByDealModel;
import com.rgretail.grocermax.hotoffers.HomeScreen;
import com.rgretail.grocermax.preference.MySharedPrefs;
import com.rgretail.grocermax.utils.AppConstants;
import com.rgretail.grocermax.utils.UtilityMethods;

import java.util.ArrayList;

public class ShopByDealsListAdapter extends RecyclerView.Adapter<ShopByDealsListAdapter.ViewHolder> {

    private Activity context;
    private Fragment fragment;
    private ArrayList<ShopByDealModel> data;
    private String SCREENNAME = "ShopByDealsListAdapter-";
    public ShopByDealsListAdapter(Activity activity, Fragment fragment) {
//        this.context = context;
        this.context = activity;
        this.fragment = fragment;
    }

    public void setListData(ArrayList<ShopByDealModel> data) {

        this.data = data;
//        if(data!=null)
//        adminReservationList.clear();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView footer;
        CardView parentLayout;
        LinearLayout ll_;
        View view1Space,viewVerticalLine,view2Space;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            footer = (TextView) itemView.findViewById(R.id.footer);
            parentLayout  = (CardView) itemView.findViewById(R.id.layoutParent);
            ll_ = (LinearLayout) itemView.findViewById(R.id.ll_);

            view1Space = (View) itemView.findViewById(R.id.view_1_space);
            viewVerticalLine = (View) itemView.findViewById(R.id.view_line);
            view2Space = (View) itemView.findViewById(R.id.view_2_space);

            parentLayout.setShadowPadding(0,0,0,0);
            parentLayout.setCardElevation(0);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_list_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ImageLoader.getInstance().displayImage(data.get(position).getImg(),
                holder.imageView, ((BaseActivity) context).baseImageoptions);

        if(position == data.size()-1){
            holder.view1Space.setVisibility(View.GONE);
            holder.viewVerticalLine.setVisibility(View.GONE);
            holder.view2Space.setVisibility(View.GONE);
        }else{
            holder.view1Space.setVisibility(View.VISIBLE);
            holder.viewVerticalLine.setVisibility(View.VISIBLE);
            holder.view2Space.setVisibility(View.VISIBLE);
        }

     /*   try{
            UtilityMethods.clickCapture(context, "", "", data.get(position).getId(),data.get(position).getDealType(), SCREENNAME + AppConstants.SHOP_BY_DEAL_SCROLLING);
        }catch(Exception e){}*/

        holder.footer.setText(data.get(position).getDealType());
        holder.footer.setVisibility(View.GONE);
        holder.ll_.setVisibility(View.GONE);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShopByDealDetailListAdapter.strDealListDeatilHeading = "";
//                ShopByDealDetailListAdapter.strDealListDeatilHeading = data.get(position).getDealType();
                AppConstants.strTitleHotDeal = "";
                AppConstants.strTitleHotDeal = data.get(position).getDealType();
                ((HomeScreen) context).hitForShopByDeals(data.get(position).getId());

                /*  tracking GA event for click on Shop By Deals from Home screen */
                try{
                    MyApplication.isFromDrawer=false;
                    UtilityMethods.clickCapture(context, "Deal Category L1", "", data.get(position).getDealType(),"", MySharedPrefs.INSTANCE.getSelectedCity());
                }catch(Exception e){}
                /*-----------------------------------------------------*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

}