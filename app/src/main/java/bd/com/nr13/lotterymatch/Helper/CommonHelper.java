package bd.com.nr13.lotterymatch.Helper;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bd.com.nr13.lotterymatch.LotteryMatch;
import bd.com.nr13.lotterymatch.R;

/**
 * Created by nomanurrashid on 5/16/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class CommonHelper {

    private static Handler uiHandler = new Handler(Looper.getMainLooper());

    public static List<String> splitText(String text) {
        //Log.e(AppConstant.LOGTAG, "text: " + text);
        List<String> list = new ArrayList<>();
        if (!text.equals("")) {
            text = text.replace(" ", "");
            //Log.e(AppConstant.LOGTAG, "text.replace: " + text);

            if (text.contains(AppConstant.SEPARATOR_COMMA)) {
                list = Arrays.asList(text.split(AppConstant.SEPARATOR_COMMA));
            } else if (text.contains(AppConstant.SEPARATOR_HYPHEN)) {
                list.addAll(splitTextByHyphen(text));
            } else {
                list.add(text);
            }
        }
        Log.e(AppConstant.LOGTAG, "text: " + text + " list: " + list);
        return list;
    }

    private static List<String> splitTextByHyphen(String text) {
        List<String> list = new ArrayList<>();
        List<String> multiList = Arrays.asList(text.split(AppConstant.SEPARATOR_HYPHEN));
        if (multiList.size() == 2) {
            String fromStr = multiList.get(0);
            String toStr = multiList.get(1);
            String firstStr = fromStr.substring(fromStr.length() - toStr.length());
            String mainStr = fromStr.substring(0, (fromStr.length() - firstStr.length()));
            try {
                int start = Integer.parseInt(firstStr), end = Integer.parseInt(toStr);
                for (int i = start; i <= end; i++) {
                    String joinStr = mainStr + i;
                    list.add(joinStr);
                }
            } catch (NumberFormatException e) {
                Log.e(AppConstant.LOGTAG, "NumberFormatException: " + text);
                showToast(LotteryMatch.getAppContext().getString(R.string.toast_message_for_invalid_number));
            }

            //Log.e(AppConstant.LOGTAG, " list: " + list);

        } else {
            list.add(text);
        }
        return list;
    }

    private static void showToast(final String message) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast ifx = Toast.makeText(LotteryMatch.getAppContext(), message, Toast.LENGTH_SHORT);
                ifx.show();
            }
        };
        uiHandler.post(runnable);
    }
}
