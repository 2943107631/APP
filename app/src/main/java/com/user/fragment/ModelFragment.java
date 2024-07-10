package com.user.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.R;
import com.user.activity.ModelDetail;
import com.user.bean.ModelBean;
import com.user.util.AdapterUtil;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.List;

public class ModelFragment extends Fragment {

    private RecyclerView recy;
    private List<ModelBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_model, container, false);
        initView(view);
        initData();

        setRecy();
        return view;
    }

    // 设置子列表内容
    private void setRecy() {
        AdapterUtil<ModelBean> adapter = new AdapterUtil<>(R.layout.model_item, ((data, position, holder) -> {
            TextView ChineseName = (TextView) holder.getView(R.id.ChineseName);
            TextView EnglishName = (TextView) holder.getView(R.id.EnglishName);
            TextView gender = (TextView) holder.getView(R.id.gender);
            TextView nation = (TextView) holder.getView(R.id.nation);
            TextView nationality = (TextView) holder.getView(R.id.nationality);
            ChineseName.setText(data.getChineseName());
            EnglishName.setText(data.getEnglishName());
            String sex = data.getGender() ? "男" : "女";
            gender.setText("性别:" + sex);
            nation.setText("民族:" + data.getNation());
            nationality.setText("国籍:" + data.getNationality());
            ImageView image = holder.getView(R.id.image);
            image.setImageResource(data.getList().get(0));
            LinearLayout layout = holder.getView(R.id.layout);
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ModelDetail.class);
                intent.putExtra("bean", data);
                getActivity().startActivity(intent);
            });
        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    private void initData() {
        List<Integer> listImage = new ArrayList<>();
        listImage.add(R.drawable.m1);
        listImage.add(R.drawable.m2);
        listImage.add(R.drawable.m3);
        listImage.add(R.drawable.m4);
        listImage.add(R.drawable.m5);
        list.add(new ModelBean("刘雯", "Liu Wen", false, "汉族", "中国"
                , "湖南永州", "1988年1月27日", "永州职业技术学院", "水瓶座"
                , "178 cm", "52 kg", "模特", "入选2011年度全球最美女人\n" +
                "首位进入“New Supers”榜单的亚裔模特\n" +
                "ELLE风尚大典“ELLE国际偶像超模”", listImage));

        List<Integer> listImage2 = new ArrayList<>();
        listImage2.add(R.drawable.m6);
        listImage2.add(R.drawable.m7);
        listImage2.add(R.drawable.m8);
        listImage2.add(R.drawable.m9);
        listImage2.add(R.drawable.m10);
        list.add(new ModelBean("奚梦瑶", "Ming Xi", false, "汉族", "中国"
                , "上海市", "1989年3月8日", "东华大学", "双鱼座"
                , "178 cm", "52 kg", "模特", "上海时装周春夏2012代言人\n" +
                "Givenchy纪梵希广告首位亚洲面孔\n" +
                "2013年维多利亚的秘密中国超模\n" +
                "代表中国时尚出席2013 Met Ball\n" +
                "受邀参加2013戛纳国际影展红毯", listImage2));

        List<Integer> listImage3 = new ArrayList<>();
        listImage3.add(R.drawable.m11);
        listImage3.add(R.drawable.m12);
        listImage3.add(R.drawable.m13);
        listImage3.add(R.drawable.m14);
        listImage3.add(R.drawable.m15);
        list.add(new ModelBean("何穗", "Sui He", false, "汉族", "中国"
                , "浙江省温州市瑞安市 ", "1989年9月23日", "天津工业大学", "天秤座"
                , "178 cm", "53 kg", "模特、演员", "第12届中国模特之星大赛冠军", listImage3));

        List<Integer> listImage4 = new ArrayList<>();
        listImage4.add(R.drawable.m16);
        listImage4.add(R.drawable.m17);
        listImage4.add(R.drawable.m18);
        listImage4.add(R.drawable.m19);
        listImage4.add(R.drawable.m20);
        list.add(new ModelBean("杜鹃", "Jennifer", false, "汉族", "中国"
                , "上海市", "1982年09月15日", "上海戏剧学院戏曲舞蹈分院", "处女座"
                , "179 cm", "52 kg", "模特、演员", "美国《W》杂志评选2007年度世界上最美的20人之一\n" +
                "2009MTV超级盛典最具风格时尚模特\n" +
                "国际时尚网站Style.com评选2006年世界十大模特之一\n" +
                "所获奖项：第33届香港电影金像奖最佳女配角提名  \n" +
                "第33届香港电影金像奖最佳新演员提名 \n" +
                "第29届中国电影金鸡奖最佳女配角提名  \n" +
                "亚洲影响力盛典最受欢迎女演员奖 ", listImage4));

        List<Integer> listImage5 = new ArrayList<>();
        listImage5.add(R.drawable.m21);
        listImage5.add(R.drawable.m22);
        listImage5.add(R.drawable.m23);
        listImage5.add(R.drawable.m24);
        listImage5.add(R.drawable.m25);
        list.add(new ModelBean("贺聪", "Liu Wen", false, "汉族", "中国"
                , "湖南永州", "1995年", "永州职业技术学院", "水瓶座"
                , "178 cm", "52 kg", "模特", "ELLE风尚大典“ELLE年度模特新面孔”奖项", listImage5));

        List<Integer> listImage6 = new ArrayList<>();
        listImage6.add(R.drawable.m26);
        listImage6.add(R.drawable.m27);
        listImage6.add(R.drawable.m28);
        listImage6.add(R.drawable.m29);
        listImage6.add(R.drawable.m30);
        list.add(new ModelBean("吉赛尔·邦辰", "Gisele Bündchen", false, "汉族", "巴西"
                , "巴西南里奥格兰德州霍里宗蒂那城", "1980年7月14日", "永州职业技术学院", "巨蟹座"
                , "180 cm", "52 kg", "模特", "2000年获得VH1/Vogue年度最佳模特\n" +
                "别名：Gisele Caroline Nonnenmacher Bündchen、吉娘娘 、Gise、The Body、The Midas Queen、The Hurricane Gisele、La Bundchen", listImage6));

        List<Integer> listImage7 = new ArrayList<>();
        listImage7.add(R.drawable.m31);
        listImage7.add(R.drawable.m32);
        listImage7.add(R.drawable.m33);
        listImage7.add(R.drawable.m34);
        listImage7.add(R.drawable.m35);
        list.add(new ModelBean("安吉拉·林德沃", "安吉拉·林德沃", false, "汉族", "美国"
                , "美国密苏里州堪萨斯城", "1979年1月14日", "永州职业技术学院", "水瓶座"
                , "180 cm", "57 kg", "模特", "无", listImage7));

        List<Integer> listImage8 = new ArrayList<>();
        listImage8.add(R.drawable.m36);
        listImage8.add(R.drawable.m37);
        listImage8.add(R.drawable.m38);
        listImage8.add(R.drawable.m39);
        listImage8.add(R.drawable.m40);
        list.add(new ModelBean("伊莉娜·莎伊克", "Irina Shayk", false, "鞑靼人与俄罗斯人混血", "俄罗斯"
                , "俄罗斯叶曼热林斯克", "1986年1月6日", "永州职业技术学院", "摩羯座"
                , "178 cm", "52 kg", "模特、演员", "世界十大最性感的美女之一 ", listImage8));

        List<Integer> listImage9 = new ArrayList<>();
        listImage9.add(R.drawable.m41);
        listImage9.add(R.drawable.m42);
        listImage9.add(R.drawable.m43);
        listImage9.add(R.drawable.m44);
        listImage9.add(R.drawable.m45);
        list.add(new ModelBean("赵磊", "Zhao Lei", true, "汉族", "中国"
                , "山东省菏泽市成武县", "1986年10月2日", "永州职业技术学院", "水瓶座"
                , "187  cm", "75  kg", "模特", "2010年“中国国际时装周颁奖典礼”年度最佳职业时装模特大奖", listImage9));

        List<Integer> listImage10 = new ArrayList<>();
        listImage10.add(R.drawable.m46);
        listImage10.add(R.drawable.m47);
        listImage10.add(R.drawable.m48);
        listImage10.add(R.drawable.m49);
        listImage10.add(R.drawable.m40);
        list.add(new ModelBean("胡兵", "Hu Bing", true, "汉族", "中国"
                , "浙江省杭州市", "1971年2月14日", "永州职业技术学院", "水瓶座"
                , "189  cm", "82 kg", "演员、歌手、模特、导演", "归去来、真情告别、粉红女郎、双响炮、亲爱的，回家、姐姐立正向前走", listImage10));

        List<Integer> listImage11 = new ArrayList<>();
        listImage11.add(R.drawable.m51);
        listImage11.add(R.drawable.m52);
        listImage11.add(R.drawable.m53);
        listImage11.add(R.drawable.m54);
        listImage11.add(R.drawable.m55);
        list.add(new ModelBean("陆开港", "Lu KaiGang", true, "汉族", "中国"
                , "广西南洋", "1999年", "永州职业技术学院", "水瓶座"
                , "178 cm", "52 kg", "模特", "123", listImage11));

        List<Integer> listImage12 = new ArrayList<>();
        listImage12.add(R.drawable.m56);
        listImage12.add(R.drawable.m57);
        listImage12.add(R.drawable.m58);
        listImage12.add(R.drawable.m59);
        listImage12.add(R.drawable.m60);
        list.add(new ModelBean("张亮", "Sean", true, "汉族", "中国"
                , "北京市", "1982年3月26日", "永州职业技术学院", "白羊座"
                , "187 cm", "74 kg", "模特、演员", "2013年荣获“音乐风云榜新人盛典”最受关注电视节目艺人奖", listImage12));

        List<Integer> listImage13 = new ArrayList<>();
        listImage13.add(R.drawable.m61);
        listImage13.add(R.drawable.m62);
        listImage13.add(R.drawable.m63);
        listImage13.add(R.drawable.m64);
        listImage13.add(R.drawable.m65);
        list.add(new ModelBean("杰里米·杜弗", "Jeremy Dufour", true, "法国", "法国"
                , "法国", "1986年7月5日", "永州职业技术学院", "巨蟹座"
                , "187 cm", "52 kg", "模特", "名列全球男模排行榜TOP50第15名", listImage13));

        List<Integer> listImage14 = new ArrayList<>();
        listImage14.add(R.drawable.m66);
        listImage14.add(R.drawable.m67);
        listImage14.add(R.drawable.m68);
        listImage14.add(R.drawable.m69);
        listImage14.add(R.drawable.m70);
        list.add(new ModelBean("oscarkindelan", "oscarkindelan", true, "西班牙", "西班牙"
                , "广西南洋", "1997年", "永州职业技术学院", "水瓶座"
                , "189 cm", "52 kg", "模特", "123", listImage14));

        List<Integer> listImage15 = new ArrayList<>();
        listImage15.add(R.drawable.m71);
        listImage15.add(R.drawable.m72);
        listImage15.add(R.drawable.m73);
        listImage15.add(R.drawable.m74);
        listImage15.add(R.drawable.m75);
        list.add(new ModelBean("弗朗西斯科·拉霍夫斯基", "Francisco Lachowsk", true, "巴西", "巴西"
                , "广西南洋", "1991年5月13日", "永州职业技术学院", "水瓶座"
                , "191 cm", "52 kg", "模特", "2008年福特超模世界大赛冠军", listImage15));
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText("模特秀展示区");
        recy = view.findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}