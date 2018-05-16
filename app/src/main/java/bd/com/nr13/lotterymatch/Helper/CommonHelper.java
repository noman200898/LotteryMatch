package bd.com.nr13.lotterymatch.Helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nomanurrashid on 5/16/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class CommonHelper {

    public static List<String> splitTextByComma(String text) {
        text = "1113243-50";
        Log.e(AppConstant.LOGTAG, "text: " + text);
        List<String> list = new ArrayList<>();
        if (!text.equals("")) {
            text = text.replace(" ","");
            Log.e(AppConstant.LOGTAG, "text.replace: " + text);

            if (text.contains(AppConstant.SEPARATOR_COMMA)) {
                list = Arrays.asList(text.split(AppConstant.SEPARATOR_COMMA));
            }else if(text.contains(AppConstant.SEPARATOR_HYPHEN)){
               List<String> multiList = Arrays.asList(text.split(AppConstant.SEPARATOR_HYPHEN));
               if (multiList.size() >1){
                   String fromStr = multiList.get(0);
                   String toStr = multiList.get(1);
                   String firstStr = fromStr.substring(fromStr.length() - toStr.length());
                   String mainStr = fromStr.substring(0 , (fromStr.length()-firstStr.length()));
                   int start = Integer.parseInt(firstStr), end = Integer.parseInt(toStr);
                   for (int i = start; i <= end; i++){
                       String joinStr = mainStr+i;
                       list.add(joinStr);
                   }
                   Log.e(AppConstant.LOGTAG," list: " + list);

               }
            }else {
                list.add(text);
            }
        }
        Log.e(AppConstant.LOGTAG, "text: " + text + " list: " + list);
        return list;
    }
}
