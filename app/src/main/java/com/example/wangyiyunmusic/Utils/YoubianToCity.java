package com.example.wangyiyunmusic.Utils;

public class YoubianToCity {
    public static String getCity(int i){
        String city;
        int j = i/10000;
        switch (j){
            case 10:
                city = "北京";
                break;
            case 20:
                city = "上海";
                break;
            case 30:
                city = "重庆";
                break;
            case 1:
                city = "内蒙";
                break;
            case 2:
                city = "内蒙";
                break;
            case 3:
                city = "山西";
                break;
            case 4:
                city = "山西";
                break;
            case 5:
                city = "河北";
                break;
            case 6:
                city = "河北";
                break;
            case 7:
                city = "河北";
                break;
            case 23:
                city = "安徽";
                break;
            case 35:
                city = "福建";
                break;
            case 51:
                city = "广东";
                break;
            case 73:
                city = "甘肃";
                break;
            case 53:
                city = "广西";
                break;
            case 45:
                city = "河南";
                break;
            case 15:
                city = "黑龙江";
                break;
            case 16:
                city = "黑龙江";
                break;
            case 41:
                city = "湖南";
                break;
                case 13:
                city = "吉林";
                break;
            case 21:
                city = "江苏";
                break;
            case 11:
                city = "辽宁";
                break;
            case 12:
                city = "辽宁";
                break;
            case 75:
                city = "宁夏";
                break;
            case 81:
                city = "青海";
                break;
            case 25:
                city = "山东";
                break;
            case 71:
                city = "陕西";
                break;
            case 83:
                city = "新疆";
                break;
            case 65:
                city = "云南";
                break;
            case 31:
                city = "浙江";
                break;
            case 85:
                city = "西藏";
                break;
            case 33:
                city = "江西";
                break;
            case 61:
                city = "四川";
                break;
            case 43:
                city = "湖北";
                break;
            case 55:
                city = "贵州";
                break;
            case 22:
                city = "江苏";
                break;
            case 32:
                city = "浙江";
                break;
            case 24:
                city = "安徽";
                break;
            case 36:
                city = "福建";
                break;
            case 34:
                city = "江西";
                break;
            case 27:
                city = "山东";
                break;
            case 26:
                city = "山东";
                break;
            case 46:
                city = "河南";
                break;
            case 47:
                city = "河南";
                break;
            case 44:
                city = "湖北";
                break;
                case 42:
                city = "湖南";
                break;
            case 52:
                city = "广东";
                break;
            case 57:
                city = "海南";
                break;
            case 62:
                city = "四川";
                break;
            case 63:
                city = "四川";
                break;
            case 64:
                city = "四川";
                break;
                case 56:
                city = "贵州";
                break;
            case 66:
                city = "云南";
                break;
            case 67:
                city = "云南";
                break;
            case 72:
                city = "陕西";
                break;
            case 74:
                city = "甘肃";
                break;
            case 84:
                city = "新疆";
                break;
            case 54:
                city = "广西";
                break;
                default:
                   city = "unknown";
                   break;
        }return city;
    }
}
