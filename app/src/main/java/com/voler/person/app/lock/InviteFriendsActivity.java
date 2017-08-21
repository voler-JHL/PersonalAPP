//package com.voler.person.app.lock;
//
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.saiko.android.BaseActivity;
//import com.saiko.android.R;
//import com.saiko.android.adapter.RecyclingPagerAdapter;
//import com.saiko.android.me.SaikoPointsDialogAty;
//import com.saiko.android.bean.FeedModel;
//import com.saiko.android.bean.FeedShareInfoModel;
//import com.saiko.android.bean.InviteFriendsItemModel;
//import com.saiko.android.bean.InviteFriendsModel;
//import com.saiko.android.network.APIKey;
//import com.saiko.android.network.NetworkUtil;
//import com.saiko.android.parsing.InviteFriendsParsing;
//import com.saiko.android.share.ShareDialogActivity;
//import com.saiko.android.util.CodeUtils;
//import com.saiko.android.util.SettingUtils;
//import com.saiko.android.util.Utils;
//import com.saiko.android.customview.ClipViewPager;
//import com.saiko.android.customview.ScalePageTransformer;
//
//import java.util.List;
//
//public class InviteFriendsActivity extends BaseActivity implements View.OnClickListener {
//
//    private TextView text_quamx, text_bottom_progressbar;
//    private ProgressBar bottom_progressbar;
//    private NetworkUtil networkUtil;
//    private String token;
//    private InviteFriendsModel inviteFriendsList;
//    private ClipViewPager mViewPager;
//    private TubatuAdapter mPagerAdapter;
//    private FeedShareInfoModel shareInfoModel;
//    private List<InviteFriendsItemModel> badges;
//    private static int LightImageViews[] = {R.mipmap.light_lv1, R.mipmap.light_lv2, R.mipmap.light_lv3, R.mipmap.light_lv4, R.mipmap.light_lv5, R.mipmap.light_lv6};
//    private static int DarkImageViews[] = {R.mipmap.gray_lv1, R.mipmap.gray_lv2, R.mipmap.gray_lv3, R.mipmap.gray_lv4, R.mipmap.gray_lv5, R.mipmap.gray_lv6};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_invite_friends);
//        initTop();
//        initView();
//        getData();
//    }
//
//    private void initTop() {
//        ImageButton left_button = (ImageButton) findViewById(R.id.left_btn);
//        left_button.setVisibility(View.VISIBLE);
//        TextView center_title = (TextView) findViewById(R.id.center_title);
//        center_title.setText(getResources().getString(R.string.invite_friends));
//
//        left_button.setOnClickListener(this);
//    }
//
//    private void initView() {
//        networkUtil = new NetworkUtil(this, this);
//        token = SettingUtils.getInstance(InviteFriendsActivity.this).getValue(SettingUtils.TOKEN, null);
//        text_quamx = (TextView) findViewById(R.id.text_quamx);
//        TextView text_copy = (TextView) findViewById(R.id.text_copy);
//        TextView text_share = (TextView) findViewById(R.id.text_share);
//        text_bottom_progressbar = (TextView) findViewById(R.id.text_bottom_progressbar);
//        mViewPager = (ClipViewPager) findViewById(R.id.viewpager);
//        bottom_progressbar = (ProgressBar) findViewById(R.id.bottom_progressbar);
//        ImageView iv_bottom_left = (ImageView) findViewById(R.id.iv_bottom_left);
//        ImageView iv_bottom_right = (ImageView) findViewById(R.id.iv_bottom_right);
//        ImageView iv_sp = (ImageView) findViewById(R.id.iv_sp);
//        text_copy.setOnClickListener(this);
//        text_share.setOnClickListener(this);
//        iv_bottom_left.setOnClickListener(this);
//        iv_bottom_right.setOnClickListener(this);
//        iv_sp.setOnClickListener(this);
//        /*********下面的仿土巴兔效果***************/
//        mViewPager = (ClipViewPager) findViewById(R.id.viewpager);
//        mViewPager.setPageTransformer(true, new ScalePageTransformer());
//
//        findViewById(R.id.rel_bottom_middle).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mViewPager.dispatchTouchEvent(event);
//            }
//        });
//
//    }
//
//    private static class TubatuAdapter extends RecyclingPagerAdapter {
//
//        private final List<InviteFriendsItemModel> mList;
//        private final Context mContext;
//        private final int inviteNum;
//        private final int level;
//
//        private TubatuAdapter(Context context, List<InviteFriendsItemModel> mList, int inviteNum,int level) {
//            mContext = context;
//            this.mList = mList;
//            this.inviteNum = inviteNum;
//            this.level = level;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup container) {
//            ImageView imageView;
//            if (convertView == null) {
//                imageView = new ImageView(mContext);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//            imageView.setTag(position);
//            if (inviteNum == 0) {
//                Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), DarkImageViews[position]);
//                imageView.setImageBitmap(bm);
//            } else {
//                if(position < level + 1){
//                    Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), LightImageViews[position]);
//                    imageView.setImageBitmap(bm);
//                }else{
//                    Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), DarkImageViews[position]);
//                    imageView.setImageBitmap(bm);
//                }
//            }
//            return imageView;
//        }
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//    }
//
//    private void getData() {
//        lockScreen("");
//        networkUtil.getInviteCode(APIKey.KEY_GET_INVITE_CODE, token);
//    }
//
//    @Override
//    public void onClick(View v) {
//        int currentItem = mViewPager.getCurrentItem();
//        switch (v.getId()) {
//            case R.id.left_btn:
//                finish();
//                break;
//            case R.id.text_copy:
//                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                clipboardManager.setText(inviteFriendsList.getInviteCode());
//                showShortToast("copy ok");
//                break;
//            case R.id.text_share:
//                Intent shareIntent = new Intent(this, ShareDialogActivity.class);
//                FeedModel model = new FeedModel();
//                model.setFeedInfo(shareInfoModel);
//                shareIntent.putExtra("model", model);
//                shareIntent.putExtra("isInvite", true);
//                startActivityForResult(shareIntent, CodeUtils.CODE_REQUEST_FEED);
//                break;
//            case R.id.iv_bottom_left:
//                if (currentItem > 0)
//                    mViewPager.setCurrentItem(currentItem - 1);
//                break;
//            case R.id.iv_bottom_right:
//                if (currentItem < mPagerAdapter.getCount() - 1)
//                    mViewPager.setCurrentItem(currentItem + 1);
//                break;
//            case R.id.iv_sp:
//                Intent intent = new Intent(this, SaikoPointsDialogAty.class);
//                intent.putExtra("center_text", "2");
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    @Override
//    public void apiOnFailure(int key, String strMsg) {
//        super.apiOnFailure(key, strMsg);
//        releaseScreen();
//    }
//
//    @Override
//    public void apiOnSuccess(int key, String strMsg) {
//        super.apiOnSuccess(key, strMsg);
//        Gson gson = new Gson();
//        switch (key) {
//            case APIKey.KEY_GET_INVITE_CODE:
//                Utils.print("KEY_GET_INVITE_CODE", strMsg);
//                releaseScreen();
//                try {
//                    InviteFriendsParsing inviteFedParsing = gson.fromJson(strMsg, InviteFriendsParsing.class);
//                    if (inviteFedParsing.getCode() == 0) {
//                        inviteFriendsList = inviteFedParsing.getData();
//                        badges = inviteFriendsList.getBadges();
//                        text_quamx.setText(inviteFriendsList.getInviteCode());
//                        shareInfoModel = inviteFedParsing.getData().getInviteInfo();
//                        mViewPager.setOffscreenPageLimit(badges.size());
//                        int inviteNum = inviteFriendsList.getInviteNum();
//                        int level = inviteFriendsList.getLevel();
//                        mPagerAdapter = new TubatuAdapter(this, badges, inviteNum,level);
//                        mViewPager.setAdapter(mPagerAdapter);
//                        bottom_progressbar.setProgress(inviteFriendsList.getInviteNum());
//                        if (inviteFriendsList.getInviteNum() == 0) {
//                            text_bottom_progressbar.setText(inviteFriendsList.getInviteNum() + "/" + badges.get(0).getLevelNum());
//                            bottom_progressbar.setMax(badges.get(inviteFriendsList.getLevel()).getLevelNum());
//                        } else {
//                            text_bottom_progressbar.setText(inviteFriendsList.getInviteNum() + "/" + badges.get(inviteFriendsList.getLevel() + 1).getLevelNum());
//                            bottom_progressbar.setMax(badges.get(inviteFriendsList.getLevel() + 1).getLevelNum());
//                        }
//                        mViewPager.setCurrentItem(inviteFriendsList.getLevel());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            default:
//                break;
//        }
//    }
//}
//
