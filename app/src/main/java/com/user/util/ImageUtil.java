package com.user.util;

import com.example.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageUtil {

    public static int getImage() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.t1);
        list.add(R.drawable.t2);
        list.add(R.drawable.t3);
        list.add(R.drawable.t4);
        list.add(R.drawable.t5);
        Random random = new Random();
        int a = random.nextInt(5);
        return list.get(a);
    }
}
