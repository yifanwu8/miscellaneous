package com.yifanwu.algo.tanium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYF on 7/14/2017.
 */
public class Tanium {

    public static int circle(int students) {
        // deal with corner case and validate the input
        if (students <= 1) {
            return 0;
        }

        // initialize the student list
        List<Integer> studentList = new ArrayList<Integer>(students);
        for (int i = 0; i < students; i++) {
            studentList.add(i);
        }

        boolean isLastin = false;

        while (studentList.size() > 1) {
            List<Integer> nextStuds = new ArrayList<Integer>();
            for (int i = 0; i < studentList.size(); i++) {
                //
                isLastin = !isLastin;
                if (isLastin) {
                    nextStuds.add(studentList.get(i));
                }
            }
            studentList = nextStuds;
        }

        return studentList.get(0);
    }
}
