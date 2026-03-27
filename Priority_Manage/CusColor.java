package Todolist.Priority_Manage;


import java.awt.Color;

/**
 *
 * @Kanin
 */
public class CusColor implements java.io.Serializable {
    public String labelColor,borderColer,inTextColor,textColor;
    public static final CusColor DEFAULT = new CusColor("#ffffff","#ffffff","#000000","#000000");
    public static final CusColor GRAY = new CusColor("#F0EFED","#D9D7D3","#2F2E2D","#7D7A75");
    public static final CusColor BROWN = new CusColor("#F5EDE9","#E3D0C4","#44291D","#9F765A");
    public static final CusColor ORANGE = new CusColor("#FBEBDE","#F0CDB1","#49260D","#D27B2D");
    public static final CusColor YELLOW = new CusColor("#F9F3DC","#F0CDB1","#4B3320","#CB9434");
    public static final CusColor GREEN = new CusColor("#E8F1EC","#C4DACD","#1E392A","#50946E");
    public static final CusColor BLUE = new CusColor("#E5F2FC","#B6D9F5","#1C364F","#387DC9");
    public static final CusColor PURPLE = new CusColor("#F3EBF9","#DECAED","#3F2253","#9A6BB4");
    public static final CusColor PINK = new CusColor("#FAE9F1","#F0C5D8","#4B2537","#C14C8A");
    public static final CusColor RED = new CusColor("#FCE9E7","#F4C7C1","#4B2537","#CF5148");
    public static final CusColor COLORLIST[] = {GRAY,BROWN,ORANGE,YELLOW,GREEN,BLUE,PURPLE,PINK,RED};
    public static final String COLORNAME[] = {"Gray", "Brown", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Red"};


    public CusColor(String labelColor, String borderColer, String inTextColor, String textColor) {
        this.labelColor = labelColor;
        this.borderColer = borderColer;
        this.inTextColor = inTextColor;
        this.textColor = textColor;
    }

    public static CusColor colorFromString(String color) {
        for (int i=0;i<COLORNAME.length;i++) {
            if (color.toLowerCase().equals(COLORNAME[i].toLowerCase())) {
                return COLORLIST[i];
            }
        }
        return null;
    }
    
    public static String getColor(CusColor color) {
        for (int i=0;i<COLORLIST.length;i++) {
            if (color.equals(COLORLIST[i])) {
                return COLORNAME[i];
            }
        }
        return null;
    }


    public static Color hexToColorObject(String hex) {
        Color color = Color.decode(hex);

        // Get the RGB components
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return new Color(red,green,blue);
    }
    
    
    
    
}
