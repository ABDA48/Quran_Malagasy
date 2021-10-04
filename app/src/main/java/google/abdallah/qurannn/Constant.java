package google.abdallah.qurannn;

import java.util.ArrayList;
import java.util.List;

import google.abdallah.qurannn.Model.JuzModel;


public class Constant {
    public Constant() {
    }
    public static final String TOOLBAR_BG_COLOR = "toolbar_bg_color";
    public static final String TOOLBAR_TITLE_COLOR = "toolbar_title_color";
    public static final String TOOLBAR_TITLE = "toolbar_title";
    //public static final String TOOLBAR_NAV_ICON = "toolbar_nav_icon";
    public static final String COMPASS_BG_COLOR = "compass_bg_color";
    public static final String ANGLE_TEXT_COLOR = "angle_text_color";
    public static final String DRAWABLE_DIAL = "drawable_dial";
    public static final String DRAWABLE_QIBLA = "drawable_qibla";
    public static final String FOOTER_IMAGE_VISIBLE = "bottom_image_visible";
    public static final String LOCATION_TEXT_VISIBLE = "location_text_visible";
    public List<JuzModel> juzList(){
        List<JuzModel> list=new ArrayList<>();
        list.add(new JuzModel(0,147));
        list.add(new JuzModel(148,258));
        list.add(new JuzModel(259,383));
        list.add(new JuzModel(384,515));
        list.add(new JuzModel(516,639));
        list.add(new JuzModel(640,750));
        list.add(new JuzModel(751,898));
        list.add(new JuzModel(809,1040));
        list.add(new JuzModel(1041,1199));
        list.add(new JuzModel(1200,1327));

        list.add(new JuzModel(1328,1477));
        list.add(new JuzModel(1378,1647));
        list.add(new JuzModel(1648,1802));
        list.add(new JuzModel(1803,2028));
        list.add(new JuzModel(2029,2213));
        list.add(new JuzModel(2214,2482));
        list.add(new JuzModel(2483,2672));
        list.add(new JuzModel(2673,2874));
        list.add(new JuzModel(2875,3217));
        list.add(new JuzModel(3218,3383));

        list.add(new JuzModel(3384,3562));
        list.add(new JuzModel(3563,3725));
        list.add(new JuzModel(3726,4088));
        list.add(new JuzModel(4089,4263));
        list.add(new JuzModel(4264,4509));
        list.add(new JuzModel(4510,4704));
        list.add(new JuzModel(4705,5103));
        list.add(new JuzModel(5104,5240));
        list.add(new JuzModel(5241,5671));
        list.add(new JuzModel(5672,6235));
        return  list;
    }

}
